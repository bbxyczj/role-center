package com.caihua.roleCenter.common.exception;


/**
 * 自定义异常
 */
public class RcException extends RuntimeException {

    // 错误码
    private String code;

    //错误消息
    private String msg;

    // 参数
    private String params;

    /**
     * 用错误信息构造
     *
     * @param msg msg
     */
    public RcException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RcException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 取msg
     *
     * @return 获取msg
     */
    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 取参数
     *
     * @return 去参数
     */
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
