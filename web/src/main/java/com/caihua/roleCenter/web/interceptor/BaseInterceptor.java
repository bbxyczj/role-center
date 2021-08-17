package com.caihua.roleCenter.web.interceptor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


/**
 * linggan
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    private long startTime;
    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * --------------1--------------- 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller 然后进入拦截器链, 从最后一个拦截器往回执行所有的postHandle()
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        startTime = System.currentTimeMillis();
        return true;
    }

    /**
     * -------------------2--------------------------
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long endTime = System.currentTimeMillis();
        //url
        String url = httpServletRequest.getRequestURI();
        //参数
        String params= getValue(httpServletRequest);
        //消耗时间
        long time = (endTime - startTime);

        logger.info("请求URL：{},入参：{},响应时间：{}毫秒", url, params, time);
    }

    /**
     * ----------------3--------------------- 在DispatcherServlet完全处理完请求后被调用
     * <p>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 取得参数值
     */
    public static String getValue(HttpServletRequest request) {
        StringBuffer value = new StringBuffer("{");
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            if (!"_dc".equals(paraName) && !"node".equals(paraName)) {//_dc的参数不要
                String[] arr = request.getParameterValues(paraName);
                if (arr != null && arr.length > 1) {
                    value.append(paraName).append("=").append(ConvertObjectArrToStr(arr)).append(",");
                } else {
                    value.append(paraName).append("=").append(request.getParameter(paraName)).append(",");
                }
            }
        }

        if (value.length() > 1 ) {
            value.deleteCharAt(value.length() - 1);
        }

        value.append("}");
        return value.toString();
    }


    /**
     * 把对象列表,转化成逗号分隔的字符串
     */
    public static String ConvertObjectArrToStr(Object[] arr) {
        StringBuffer result = new StringBuffer("{");
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                if (StringUtils.isNotEmpty(String.valueOf(arr[i]))) {
                    result.append(String.valueOf(arr[i])).append(",");
                }
            }
            result.deleteCharAt(result.length() - 1);
        }
        result.append("}");
        return result.toString();
    }


}
