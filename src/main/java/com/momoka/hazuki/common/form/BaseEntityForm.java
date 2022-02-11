package com.momoka.hazuki.common.form;

import com.momoka.hazuki.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Form专注于表单正确性验证，同时也提供赋值操作的便利函数，可以选择性使用。
 */
public abstract class BaseEntityForm<E extends BaseEntity> {

    @Getter
    @Setter
    protected String uuid;

    public abstract void verification();

}
