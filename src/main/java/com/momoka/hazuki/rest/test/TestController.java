package com.momoka.hazuki.rest.test;

import com.momoka.hazuki.common.WebResult;
import com.momoka.hazuki.common.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController  extends BaseController {

    @RequestMapping("/index")
    public WebResult page(
    ) {
        return success();
    }

}
