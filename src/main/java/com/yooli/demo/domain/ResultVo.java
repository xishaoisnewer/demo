package com.yooli.demo.domain;

/**
 * Created by lx on 2018/10/9.
 */
public class ResultVo<T> {
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

    public T getP() {
        return p;
    }

    public void setP(T p) {
        this.p = p;
    }

    private String code;
    private String msg;
    private T p;

    public ResultVo(){
    }

    public ResultVo(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public String toString(){
        return String.format("code:[%s], msg:[%s]", this.code,this.msg);
    }
}
