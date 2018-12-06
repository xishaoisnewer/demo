package com.yooli.demo.domain;

/**
 * Created by lx on 2018/10/9.
 */
public class DubboRequestParam {
    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Object getParamVale() {
        return paramVale;
    }

    public void setParamVale(Object paramVale) {
        this.paramVale = paramVale;
    }

    private String paramType;
    private Object paramVale;

    public String toString(){
        return String.format("paramType:[%s], paramValue:[%s]", paramType, paramVale.toString());
    }
}
