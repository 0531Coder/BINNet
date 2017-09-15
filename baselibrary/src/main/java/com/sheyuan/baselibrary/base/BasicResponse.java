package com.sheyuan.baselibrary.base;

/**
 * Created by moutain on 17-9-12 13:07.
 */

public class BasicResponse<T> {
    private String code;
    private String msg;
    private T resultBean;
    private String timestamp;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getResultBean() {
        return resultBean;
    }

    public void setResultBean(T resultBean) {
        this.resultBean = resultBean;
    }

    public boolean isError() {
        return "0".equals(code)||"200".equals(code);
    }

}
