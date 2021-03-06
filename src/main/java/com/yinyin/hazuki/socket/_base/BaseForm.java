package com.yinyin.hazuki.socket._base;

import com.yinyin.hazuki.config.bean.AppContextManager;
import com.yinyin.hazuki.socket._base.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

public abstract class BaseForm {

    @Getter
    @Setter
    protected String uuid;

    //检出一个指定类型的实例。找不到抛异常。
    protected <T extends BaseEntity> T check(Class<T> clazz, String uuid) {
        return AppContextManager.check(clazz, uuid);
    }

    //找出一个指定类型的实例。找不到返回null
    protected <T extends BaseEntity> T find(Class<T> clazz, String uuid) {
        return AppContextManager.find(clazz, uuid);
    }

}
