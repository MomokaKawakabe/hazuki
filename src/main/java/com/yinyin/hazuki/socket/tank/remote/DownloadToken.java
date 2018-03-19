package com.yinyin.hazuki.socket.tank.remote;

import com.yinyin.hazuki.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class DownloadToken extends TankBaseEntity {

    private String userUuid;
    private String matterUuid;
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT)
    private Date expireTime;
    private String ip;

}
