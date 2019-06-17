package com.lee.vademovies.bean;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 14:40
 * Description  :  响应返回类
 */
public class Result<T> {
    String status;
    String message;
    T result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}