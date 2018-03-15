package com.yinyin.hazuki.config.interceptor;

import com.yinyin.hazuki.util.NetworkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * 浏览器请求拦截器 只允许浏览器访问特定服务
 */
@Slf4j
public class BrowserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        String ip = NetworkUtil.getIpAddress(request);
        log.info("IP = " + ip + ";URL = " + url + " start access!");

        Set<String> paths = new HashSet<>();
        //paths.add("/");
        String header = request.getHeader("Accept");
        //如果是浏览器请求且不在列表中，则跳转到404
        if (header.contains("text/html") && !paths.contains(url)) {
            log.info("One may be intercepted by an illegal request from a browser!URL=" + url + ";IP=" + ip);
            response.sendRedirect("/404");
            return false;//404
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
