package com.yinyin.hazuki.socket._support.validation;

import com.yinyin.hazuki.socket._base.BaseEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportValidationDao extends BaseEntityDao<SupportValidation> {


    SupportValidation findByCodeAndTypeAndDeleted(String code, SupportValidation.Type validation, boolean b);

}
