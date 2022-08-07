package com.moyang.room.okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/8/7 13:25
 */
public class Result<T> {
    private T data;
    private int errorCode;
    private String errorMsg;
    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
