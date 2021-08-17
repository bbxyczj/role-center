package com.caihua.roleCenter.common.exception;

/**
 * linggan on 2018/1/15\
 * 错误码
 */
public interface Message {

    // ----- 消息 -----
    // 处理成功
    String M0001 = "0001";

    // ----- 异常 -----
    //系统异常
    String E5000 = "5000";

    /**
     *业务参数异常
     */
    String E4000 = "4000";

    /**
     * dwz 执行成功
     */
    String DWZ_SUCCESS = "200";

    /**
     * 操作失败
     */
    String DWZ_SUCCESS_MSG = "操作成功";

    /**
     * dwz执行失败
     */
    String DWZ_FAIL = "300";


    /**
     * 操作失败
     */
    String DWZ_FAIL_MSG = "操作失败";

    /**
     * dwz超时
     */
    String DWZ_TIMEOUT = "301";

    /**
     * 超时信息
     */
    String DWZ_TIMEOUT_MSG = "会话超时";
}
