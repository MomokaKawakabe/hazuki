package com.yinyin.hazuki.socket.base;

import com.yinyin.hazuki.config.bean.AppContextManager;
import com.yinyin.hazuki.config.exception.UtilException;
import com.yinyin.hazuki.socket.base.model.BaseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public abstract class BaseService {


    //检出一个指定类型的实例。找不到抛异常。考虑deleted.
    protected <T extends BaseEntity> T check(Class<T> clazz, Long id) {
        return AppContextManager.check(clazz, id);
    }

    //检出一个指定类型的实例。找不到抛异常。不考虑deleted.
    protected <T extends BaseEntity> T checkDeeply(Class<T> clazz, Long id) {
        return AppContextManager.checkDeeply(clazz, id);
    }

    //找出一个指定类型的实例。找不到返回null 考虑deleted.
    protected <T extends BaseEntity> T find(Class<T> clazz, Long id) {
        return AppContextManager.find(clazz, id);
    }

    //找出一个指定类型的实例。找不到返回null 不考虑deleted.
    protected <T extends BaseEntity> T findDeeply(Class<T> clazz, Long id) {
        return AppContextManager.findDeeply(clazz, id);
    }


    //对page和pageSize进行验证
    public PageRequest getPageRequest(int page, int pageSize, Sort sort) {
        if (page < 0 || pageSize < 1 || pageSize > 50) {
            throw new UtilException("Exceed the pager limitation.");
        }
        return new PageRequest(page, pageSize, sort);
    }
}
