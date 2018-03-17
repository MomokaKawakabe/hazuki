package com.yinyin.hazuki.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configuration for email.
 *
 */
@EnableConfigurationProperties(MailProperties.class)
@Configuration
public class MailConfiguration {

    /*****************************Email************************************/

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding(mailProperties.getDefaultEncoding().toString());
        mailSender.setProtocol(mailProperties.getProtocol());
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        Properties properties = new Properties();

        properties.putAll(mailProperties.getProperties());

        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }




}
