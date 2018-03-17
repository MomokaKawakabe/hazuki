package com.yinyin.hazuki.socket.support.session;

import com.yinyin.hazuki.socket.base.BaseEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportSessionDao extends BaseEntityDao<SupportSession> {


    SupportSession findByAuthenticationAndDeleted(String authentication, boolean deleted);
}
