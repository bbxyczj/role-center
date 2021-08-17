package com.caihua.roleCenter.common.utils;


import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * linggan
 * 2017-10-10
 *
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param mapParam 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, Object> mapParam) {
        Long start = System.currentTimeMillis();
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        String param = null;
        URLConnection conn = null;
        try {
            URL realUrl = new URL(url);
            param = mapParamsToUrl(mapParam);
            // 打开和URL之间的连接
            conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置链接超时时间
            conn.setConnectTimeout(30*1000);
            //设置读取超时时间
            conn.setReadTimeout(30*1000);
            // 获取URLConnection对象对应的输出流，并设置编码格式
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            if (!StringUtils.isEmpty(param)) {
                // 发送请求参数
                out.write(param);
                // flush输出流的缓冲
                out.flush();
            }

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("Method[sendPost]发送 POST:url:{},参数：{} 请求出现异常信息：{}！", url, param, e.getMessage(), e);
        }  finally { //使用finally块来关闭输出流、输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Long end = System.currentTimeMillis();
        logger.debug("Method[sendPost]发送POST方法的请求url:{},参数：{}，返回结果：{},共消耗时间：{}", url, param, result,(end-start));
        return result;
    }


    public static String sendPost(String url) {
        return sendPost(url,null);
    }

    /**
     * 将MAP转换成GET提交URL形式
     *
     * @param params
     * @return
     */
    public static String mapParamsToUrl(Map<String, Object> params) {

        if(MapUtils.isEmpty(params)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : params.keySet()) {
            Object val = params.get(s);
            if(val != null){
                sb.append(s).append("=").append(val).append("&");
            }
        }
        if (params.size() > 0){
            sb.delete(sb.length() - 1, sb.length()); // 去掉最后一个字符
        }
        return sb.toString();
    }


    /**
     * 获取url后面参数key
     * @param url
     * @return
     */
    public static Map<String,Object> getUrlParamKey(String url){
        if(StringUtils.isEmpty(url)){
            return  null;
        }
        String params = url.substring(url.indexOf("?")+1,url.length());
        String[] paramArr = params.split("&");
        Map<String,Object> paramMap = new HashMap<String,Object>();
        String[] key = null;
        for(String param : paramArr){
            if(StringUtils.isEmpty(param)){
                continue;
            }
            key = param.split("=");
            if(key !=null && key.length > 0){
                paramMap.put(key[0],key[1]);
            }
        }
        return paramMap;
    }










}