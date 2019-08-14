package com.example.cloud.gatewayclient.pojo;

/**
 * @Auther: hcsy
 * @Date: 2018/5/31 10:34
 * @Description:
 */
public class HystreamResultVo<T> {
    private int code = 0;
    private String message = "";
    private T data = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public HystreamResultVo(String message) {
        this.message = message;
    }

    public HystreamResultVo(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
