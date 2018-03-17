package com.yinyin.hazuki.socket.Index;

import com.yinyin.hazuki.socket.base.BaseController;
import com.yinyin.hazuki.socket.user.feature.Feature;
import com.yinyin.hazuki.socket.user.feature.FeatureType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class IndexController extends BaseController{


    @RequestMapping("/404")
    @Feature(FeatureType.PUBLIC)
    public String index(HttpServletRequest request, HttpServletResponse response) {

        return "If you see this page. Hazuki started!";
    }
}
