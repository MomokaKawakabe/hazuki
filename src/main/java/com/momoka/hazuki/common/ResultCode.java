package com.momoka.hazuki.common;

public class ResultCode {

    //正常
    public static final int OK = 200;
    //未登录
    public static final int LOGIN = -400;
    //没有权限
    public static final int UNAUTHORIZED = -401;
    //服务器出错。
    public static final int SERVER_ERROR = -500;
    //提交的表单验证不通过
    public static final int FORM_INVALID = -410;
}
