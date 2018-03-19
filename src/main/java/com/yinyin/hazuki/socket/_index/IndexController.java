package com.yinyin.hazuki.socket._index;

import com.yinyin.hazuki.socket._base.BaseController;
import com.yinyin.hazuki.socket._common.feature.Feature;
import com.yinyin.hazuki.socket._common.feature.FeatureType;
import com.yinyin.hazuki.util.NetworkUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class IndexController extends BaseController {

    /**
     * 默认首页
     */
    @Feature(FeatureType.PUBLIC)
    @RequestMapping("/404")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        String directIp = request.getRemoteAddr();
        String ipAddress = NetworkUtil.getIpAddress(request);
        String remoteHost = request.getRemoteHost();
        String headerHost = request.getHeader("Host");

        return "If you see this page. hazuki started! Direct IP:" + directIp + " NetworkUtil IP:" + ipAddress + " RemoteHost:" + remoteHost + " HeaderHost:" + headerHost;
    }
}
