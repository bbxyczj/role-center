package com.caihua.roleCenter.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caihua.roleCenter.model.constants.Constants;
import com.caihua.roleCenter.web.annotation.NotLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * created by zhengliu on 2018/7/17
 */
@Component
public class AuthInterceptor implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 前置
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NotLogin methodAnnotation = handlerMethod.getMethodAnnotation(NotLogin.class);

        if(methodAnnotation!=null){
            return true;
        }
        // 没有登录
        if (request.getSession().getAttribute(Constants.LOGIN_USER) == null) {
            String reqType = request.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(reqType)){

                response.getWriter().print("{\"statusCode\":\"300\", \"message\":\"请刷新页面,重新登陆！\"}");
                return false;
            }
            StringBuffer redirectUri = request.getRequestURL();
            redirectUri = redirectUri.delete(redirectUri.length() - request.getRequestURI().length(),
                    redirectUri.length()).append("/login");
//            response.sendRedirect(Constants.LOGIN_URL + "?from=" + redirectUri);
            response.sendRedirect("/");
            return false;
        } else {
            //判断用户权限
            String path=request.getServletPath();
            HttpSession session = request.getSession();
            Object attribute = session.getAttribute(Constants.LOGIN_USER);
            logger.debug("用户信息{}", JSON.toJSONString(attribute));
//            List<String> functionInfoUrl = (List<String>)session.getAttribute(Constants.FUNCTION_INFO_URL);
            List<String> functionInfoUrl = JSONObject.parseArray(redisTemplate.opsForValue().get(Constants.FUNCTION_INFO_URL+session.getId()),String.class);

            if(functionInfoUrl.contains(path)){
                return true;
            }
            if("/index".equals(path)){
                //首页权限都没有，直接让丫重新登陆
                StringBuffer redirectUri = request.getRequestURL();
                redirectUri = redirectUri.delete(redirectUri.length() - request.getRequestURI().length(),
                        redirectUri.length()).append("/login.do");
//                response.sendRedirect(Constants.LOGOUT_URL + "?from=" + redirectUri);
                response.sendRedirect("/");
                return false;
            }
            response.getWriter().print("{\"statusCode\":\"300\", \"message\":\"对不起，你没有该操作权限！\"}");
            return false;
        }
    }

    /**
     * 后置
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    /**
     * 环绕
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
}
