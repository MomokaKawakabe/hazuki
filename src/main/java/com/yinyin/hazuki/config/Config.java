package com.yinyin.hazuki.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 一些配置项
 */
@Configuration
@Getter
public class Config {

    @Value("${app.debug}")
    private Boolean isDebug;

    @Value("${app.version}")
    private String version;



}
