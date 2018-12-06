package com.yooli.demo.consts;

/**
 * Created by lx on 2018/9/29.
 */
public enum TestEnum {
    BETA((short)10,"beta");
    private short code;
    private String name;
    TestEnum(short code,String name){
        this.code=code;
        this.name=name;
    }

}
