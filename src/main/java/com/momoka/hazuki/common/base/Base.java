package com.momoka.hazuki.common.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Map;

public abstract class Base implements java.io.Serializable {

    private static final ObjectMapper mapper = new ObjectMapper()
            //反序列化的时候如果多了其他属性,不抛出异常
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            //如果是空对象的时候,不抛异常
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            //取消时间转化
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            //设置时间对象转化格式
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public Map<String, Object> map() {
        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {};
        return mapper.convertValue(this, typeReference);
    }
}
