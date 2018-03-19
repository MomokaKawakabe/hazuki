package com.yinyin.hazuki.socket._base;

import com.yinyin.hazuki.config.bean.AppContextManager;
import com.yinyin.hazuki.config.exception.UtilException;
import com.yinyin.hazuki.socket._base.model.BaseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public abstract class BaseService {

    //检出一个指定类型的实例。找不到抛异常。考虑deleted.
    protected <T extends BaseEntity> T check(Class<T> clazz, String uuid) {
        return AppContextManager.check(clazz, uuid);
    }

    //检出一个指定类型的实例。找不到抛异常。不考虑deleted.
    protected <T extends BaseEntity> T checkDeeply(Class<T> clazz, String uuid) {
        return AppContextManager.checkDeeply(clazz, uuid);
    }

    //找出一个指定类型的实例。找不到返回null 考虑deleted.
    protected <T extends BaseEntity> T find(Class<T> clazz, String uuid) {
        return AppContextManager.find(clazz, uuid);
    }

    //找出一个指定类型的实例。找不到返回null 不考虑deleted.
    protected <T extends BaseEntity> T findDeeply(Class<T> clazz, String uuid) {
        return AppContextManager.findDeeply(clazz, uuid);
    }

    //对page和pageSize进行验证
    public PageRequest getPageRequest(int page, int pageSize, Sort sort) {
        if (page < 0 || pageSize < 1 || pageSize > 50) {
            throw new UtilException("Exceed the pager limitation.");
        }
        // TODO :Deprecated
        return new PageRequest(page, pageSize, sort);
    }
}
