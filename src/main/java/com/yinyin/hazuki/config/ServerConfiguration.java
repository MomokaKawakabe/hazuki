package com.yinyin.hazuki.config;

import com.yinyin.hazuki.config.interceptor.AllowCrossInterceptor;
import com.yinyin.hazuki.config.interceptor.AuthInterceptor;
import com.yinyin.hazuki.config.interceptor.BrowserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 超级配置Configuration
 */
@Configuration
@EnableWebMvc
public class ServerConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    Config config;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if(config.isDebug()) {
            registry.addInterceptor(new AllowCrossInterceptor());//跨域和忽略OPTIONS
        }
        registry.addInterceptor(new BrowserInterceptor());//浏览器请求预处理
        registry.addInterceptor(new AuthInterceptor());//登陆验证
    }

}