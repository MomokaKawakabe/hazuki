package com.momoka.hazuki.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WebResult {

    private int code = ResultCode.OK;
    private java.util.Map<String, Object> data;
    private String msg = "成功";

    public WebResult(int code, String msg, java.util.Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public WebResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public WebResult(java.util.Map<String, Object> data) {
        this.data = data;
    }

}
