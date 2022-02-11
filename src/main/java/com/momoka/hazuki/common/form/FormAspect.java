package com.momoka.hazuki.common.form;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

// form切面
@Aspect
@Component
@Slf4j
public class FormAspect {

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void methodLevelAnnotationPointcut() {}

    // 当参数大于1且第一个参数是BaseEntityForm时，则在函数钱调用校验函数
    @Before(value = "methodLevelAnnotationPointcut()")
    public void before(JoinPoint point) {
        List<Object> args = Arrays.asList(point.getArgs());
        if(args.size() > 0 && args.get(0) != null ){
            if(BaseEntityForm.class.isAssignableFrom(args.get(0).getClass())) {
                //调用form的校验函数
                ((BaseEntityForm<?>)args.get(0)).verification();
            }
        }
    }

}
