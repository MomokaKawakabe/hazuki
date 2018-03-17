package com.yinyin.hazuki.socket.base;

import com.yinyin.hazuki.config.bean.AppContextManager;
import com.yinyin.hazuki.socket.base.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * BaseForm 用于对不定型对象的通用方法进行检出
 */
public abstract class BaseForm {

    @Getter
    @Setter
    protected Long id;

    //检出一个指定类型的实例。找不到抛异常。
    protected <T extends BaseEntity> T check(Class<T> clazz, Long id) {
        return AppContextManager.check(clazz, id);
    }

    //找出一个指定类型的实例。找不到返回null
    protected <T extends BaseEntity> T find(Class<T> clazz, Long id) {
        return AppContextManager.find(clazz, id);
    }

}
