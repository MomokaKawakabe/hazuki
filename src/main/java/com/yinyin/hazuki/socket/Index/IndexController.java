package com.yinyin.hazuki.socket.Index;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class IndexController {


    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        return "If you see this page. Hazuki started!中文！";
    }
}
