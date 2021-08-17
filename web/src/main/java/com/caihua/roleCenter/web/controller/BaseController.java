package com.caihua.roleCenter.web.controller;

import com.alibaba.fastjson.JSON;
import com.caihua.roleCenter.common.exception.RcException;
import com.caihua.roleCenter.model.result.DwzResult;
import com.caihua.roleCenter.web.interceptor.BaseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * linggan on 2018/6/27
 */
@Slf4j
@ControllerAdvice
public class BaseController {

    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler
    public void exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String url = request.getRequestURI();
        String param = BaseInterceptor.getValue(request);
        response.reset();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            if (ex instanceof RcException) {
                log.warn("Method[业务异常]请求url:{}，参数：{}，异常信息：{}", url, param, ((RcException) ex).getMsg(), ex);
                response.getWriter().print( JSON.toJSONString( DwzResult.fail(((RcException) ex).getMsg()) ));
                response.flushBuffer();
                return;
            }
            log.warn("Method[系统异常]请求url:{}，参数：{}，异常信息：{}", url, param, ex.getMessage(), ex);
            response.getWriter().print(JSON.toJSONString( DwzResult.fail("系统异常")));
            response.flushBuffer();
        } catch (IOException e) {
            log.error("Method[exception]", e);
        }
    }

    /**
     * 执行成功
     * @param navTabId
     * @return
     */
    public DwzResult success(String navTabId){

         return DwzResult.success(navTabId);
    }

    /**
     * 执行成功 不关闭页面
     * @param navTabId
     * @return
     */
    public DwzResult successDel(String navTabId){

        return DwzResult.successDel(navTabId);
    }

    /**
     * 执行失败
     * @return
     */
    public DwzResult fail(){

        return DwzResult.fail(null);
    }
}
