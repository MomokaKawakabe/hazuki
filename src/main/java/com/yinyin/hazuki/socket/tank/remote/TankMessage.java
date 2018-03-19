package com.yinyin.hazuki.socket.tank.remote;

import lombok.Data;


@Data
public class TankMessage<T extends TankBaseEntity> {

    private int code;
    private String msg;
    private T data;
}
