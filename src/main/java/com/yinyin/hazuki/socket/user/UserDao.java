package com.yinyin.hazuki.socket.user;

import com.yinyin.hazuki.socket.base.BaseEntityDao;
import com.yinyin.hazuki.socket.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseEntityDao<User> {

    User findByEmailAndDeletedFalse(String email);

    int countByEmail(String email);

}
