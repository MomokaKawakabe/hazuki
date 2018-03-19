package com.yinyin.hazuki.socket._base.model;

import com.yinyin.hazuki.util.JsonUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Base implements Serializable {

    /**
     * 极简模式，只返回关键数据。一般用于 小权限数据，或者是为了加快接口速度。
     */
    public Map<String, Object> simpleMap() {
        return new HashMap<>();
    }

    public Map<String, Object> map() {
        return JsonUtil.toMap(this);
    }

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
