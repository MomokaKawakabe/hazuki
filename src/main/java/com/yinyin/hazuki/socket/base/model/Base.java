package com.yinyin.hazuki.socket.base.model;

import com.yinyin.hazuki.util.JsonUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Base implements Serializable {


    public Map<String, Object> simpleMap() {
        return new HashMap<>();
    }


    public Map<String, Object> map() { return JsonUtil.toMap(this); }


    public Map<String, Object> detailMap() { return this.map(); }

    //将map中的所有字段转换成json字符串
    public String toJsonString() {
        return JsonUtil.toJson(this);
    }

    //将simpleMap中的所有字段转换成json字符串
    public String toSimpleJsonString() {
        return JsonUtil.toJson(this.simpleMap());
    }

}
