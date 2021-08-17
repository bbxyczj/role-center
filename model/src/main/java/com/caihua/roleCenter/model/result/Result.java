package com.caihua.roleCenter.model.result;

import com.caihua.roleCenter.common.exception.Message;

import java.io.Serializable;

/**
 * linggan on 2018/1/15
 * 统一返回结果
 */
public class Result<T>  implements Serializable{

    private static final long serialVersionUID = -6998389175825829723L;

    private T data;
    //返回code
    private String code;
    //返回提示信息
    private String msg;
    //执行结果
    private boolean success;

    public Result() {
    }

    /**
     * 执行成功
     * @param data
     */
    public Result(T data) {
        this.code = Message.M0001;
        this.msg = "操作成功";
        this.success = true;
        this.data = data;
    }


    /**
     * 执行，发生异常
     */
    public Result(String code , String msg) {
        this.code =code;
        this.msg = msg;
        if(msg==null||msg.equals("")){
            this.msg =  Message.E5000;
        }
        this.success = false;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
