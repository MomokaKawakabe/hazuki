package com.yinyin.hazuki.socket._common.feature;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Feature {

    FeatureType[] value() default {FeatureType.OTHER};

}
