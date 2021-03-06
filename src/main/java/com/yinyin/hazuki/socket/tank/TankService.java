package com.yinyin.hazuki.socket.tank;

import com.yinyin.hazuki.config.Config;
import com.yinyin.hazuki.config.exception.UtilException;
import com.yinyin.hazuki.socket._base.BaseEntityService;
import com.yinyin.hazuki.socket.tank.remote.*;
import com.yinyin.hazuki.socket.user.User;
import com.yinyin.hazuki.util.DateUtil;
import com.yinyin.hazuki.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
@Service
public class TankService extends BaseEntityService<Tank> {

    @Autowired
    private Config config;

    @Autowired
    TankDao tankDao;

    @Getter
    @Value("${app.tank.url}")
    private String tankUrl;

    @Getter
    @Value("${app.tank.email}")
    private String tankEmail;

    @Getter
    @Value("${app.tank.password}")
    private String tankPassword;

    private final static String URL_FETCH_UPLOAD_TOKEN = "/api/alien/fetch/upload/token";
    private final static String URL_FETCH_DOWNLOAD_TOKEN = "/api/alien/fetch/download/token";
    private final static String URL_CONFIRM = "/api/alien/confirm";
    private final static String URL_UPLOAD = "/api/alien/upload";
    private final static String URL_DOWNLOAD = "/api/alien/download";

    public TankService() {
        super(Tank.class);
    }

    private <T extends TankBaseEntity> TankMessage<T> doPost(String url, Map<String, String> params, TypeReference<TankMessage<T>> typeReference) {

        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new UtilException(e);
        }

        TankMessage<T> tankMessage = null;
        try (CloseableHttpResponse response1 = httpclient.execute(httpPost)) {
            HttpEntity entity1 = response1.getEntity();
            String json = EntityUtils.toString(entity1, "UTF-8");
            log.debug(json);

            EntityUtils.consume(entity1);


            tankMessage = JsonUtil.toGenericObject(typeReference, json);
        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();
            if (message == null || message.equals("")) {
                message = "???????????????????????????????????????????????????";
            }
            throw new UtilException(message);
        }

        if (tankMessage == null) {
            throw new UtilException("??????????????????????????????????????????????????????");
        }

        return tankMessage;

    }

    //???????????????tank?????????????????????????????????????????????????????? /app/blog/yyyy/MM/dd/timestamp
    private String getStoreDir() {
        Date date = new Date();
        String dateString = DateUtil.convertDateToString(date, "/yyyy/MM/dd");
        return "/app/blog" + dateString + "/" + date.getTime();
    }

    //??????????????????uploadToken.
    public Tank httpFetchUploadToken(String filename, boolean privacy, long size, User operator) {

        TankMessage<UploadToken> tankMessage = this.doPost(this.tankUrl + URL_FETCH_UPLOAD_TOKEN, new HashMap<String, String>() {{
            put("email", TankService.this.tankEmail);
            put("password", TankService.this.tankPassword);
            put("filename", filename);
            put("privacy", "" + privacy);
            put("size", "" + size);
            put("dir", TankService.this.getStoreDir());
        }}, new TypeReference<TankMessage<UploadToken>>() {
        });


        if (tankMessage.getCode() != 200) {
            throw new UtilException(tankMessage.getMsg());
        }


        //????????????uploadToken??????????????????????????????????????????tank????????????
        UploadToken uploadToken = tankMessage.getData();
        Tank tank = new Tank(operator.getUuid(), uploadToken.getFilename(), uploadToken.getSize(), uploadToken.isPrivacy());

        tank = tankDao.save(tank);

        //????????????????????????????????????
        tank.setUploadTokenUuid(uploadToken.getUuid());
        tank.setUploadUrl(tankUrl + URL_UPLOAD);
        return tank;
    }

    //????????????????????????
    public Tank httpConfirm(String uuid, @RequestParam String matterUuid) {

        Tank tank = this.check(uuid);
        if (tank.isConfirmed()) {
            throw new UtilException("????????????????????????????????????????????????");
        }

        TankMessage<Matter> tankMessage = this.doPost(this.tankUrl + URL_CONFIRM, new HashMap<String, String>() {{
            put("email", TankService.this.tankEmail);
            put("password", TankService.this.tankPassword);
            put("matterUuid", matterUuid);
        }}, new TypeReference<TankMessage<Matter>>() {
        });
        if (tankMessage.getCode() != 200) {
            throw new UtilException(tankMessage.getMsg());
        }


        Matter matter = tankMessage.getData();
        if (!tank.getName().equals(matter.getName())) {
            throw new UtilException("????????????????????????????????????");
        }
        if (tank.getSize() != matter.getSize()) {
            throw new UtilException("???????????????????????????????????????");
        }
        if (tank.getPrivacy() != matter.isPrivacy()) {
            throw new UtilException("??????????????????????????????????????????");
        }

        tank.setMatterUuid(matterUuid);
        tank.setConfirmed(true);
        tank.setUrl(this.tankUrl + URL_DOWNLOAD + "/" + matterUuid + "/" + tank.getName());

        tankDao.save(tank);

        return tank;
    }

    //??????????????????downloadToken
    public String httpFetchDownloadUrl(String uuid) {

        Tank tank = this.check(uuid);
        if (!tank.isConfirmed()) {
            throw new UtilException("??????????????????????????????????????????");
        }

        if (!tank.getPrivacy()) {
            return tank.getUrl();
        }


        TankMessage<DownloadToken> tankMessage = this.doPost(this.tankUrl + URL_FETCH_DOWNLOAD_TOKEN, new HashMap<String, String>() {{
            put("email", TankService.this.tankEmail);
            put("password", TankService.this.tankPassword);
            put("matterUuid", tank.getMatterUuid());
            put("expire", 86400 + "");
        }}, new TypeReference<TankMessage<DownloadToken>>() {
        });
        if (tankMessage.getCode() != 200) {
            throw new UtilException(tankMessage.getMsg());
        }


        return tank.getUrl() + "?downloadTokenUuid=" + tankMessage.getData().getUuid();
    }
}
