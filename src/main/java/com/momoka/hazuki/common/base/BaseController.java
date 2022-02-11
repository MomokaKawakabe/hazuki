package com.momoka.hazuki.common.base;

import com.momoka.hazuki.common.ResultCode;
import com.momoka.hazuki.common.WebResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class BaseController {

    protected WebResult success() {
        return new WebResult(ResultCode.OK, "成功");
    }

    protected WebResult success(String message) {
        return new WebResult(ResultCode.OK, message);
    }

    protected WebResult success(BaseEntity baseEntity) {
        WebResult webResult = new WebResult(ResultCode.OK, "成功");
        if (baseEntity != null) {
            webResult.setData(baseEntity.detailMap());
        }
        return webResult;
    }

    //直接返回entity的detailMap.
    protected WebResult success(Base base) {
        WebResult webResult = success();
        webResult.setData(base.map());
        return webResult;
    }

    protected WebResult success(Map<String, Object> map) {
        WebResult webResult = new WebResult(ResultCode.OK, "成功");
        webResult.setData(map);
        return webResult;
    }
}
