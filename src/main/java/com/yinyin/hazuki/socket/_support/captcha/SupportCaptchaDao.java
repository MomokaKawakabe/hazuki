package com.yinyin.hazuki.socket._support.captcha;

import com.yinyin.hazuki.socket._base.BaseEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportCaptchaDao extends BaseEntityDao<SupportCaptcha> {

    SupportCaptcha findBySessionId(String sessionId);
}
