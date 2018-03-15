package com.yinyin.hazuki.config;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 顺便配置了web初始化的时候的工作。
 *
 */
@Configuration
public class Config {

    @Getter
    @Value("${app.debug}")
    private boolean debug;

}
