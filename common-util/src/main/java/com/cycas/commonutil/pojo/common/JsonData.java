package com.cycas.commonutil.pojo.common;

import java.io.Serializable;

public class JsonData implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     * 0表示成功;1表示处理中;-1表示失败
     */
    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;
    public JsonData(Integer code, Object data, String msg) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    /**
     * 成功，传入数据
     * @return
     */
    public static JsonData buildSuccess() {
        return  new JsonData(0,null,null);
    }

    /**
     * 成功，传入数据
     * @return
     */
    public static JsonData buildSuccess(Object data) {
        return  new JsonData(0,data,null);
    }
    /**
     * 成功，传入数据和状态码
     * @param data
     * @param code
     * @return
     */
    public static JsonData buildSuccess(Object data,Integer code) {
        return  new JsonData(code,data,null);
    }
    /**
     * 成功，传入状态码和信息
     * @param msg
     * @param code
     * @return
     */
    public static JsonData buildSuccess(String msg,Integer code) {
        return  new JsonData(code,msg,null);
    }


    /**
     * 失败，传入描述信息
     * @return
     */
    public static JsonData buildError(String msg) {
        return  new JsonData(-1,null,msg);
    }

    /**
     * 失败，传入信息和状态码
     * @param msg
     * @param code
     * @return
     */
    public static JsonData buildError(String msg,Integer code) {
        return new JsonData(code,null,msg);
    }
    /**
     * 失败，出入数据和状态码
     * @param data
     * @param code
     * @return
     */
    public static JsonData buildError(Object data,Integer code) {
        return new JsonData(code,data,null);
    }

    public JsonData() {}


    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "JsonData [code=" + code + ", data=" + data + ", msg=" + msg + "]";
    }
}
