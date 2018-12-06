package com.yooli.demo.pettern.template;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/7
 * Time: 上午1:25
 * 默认实现它的方法，不会重写service方法，这个就是模版方法模式
 */
public abstract class AbstractClass {
    private String name;

    public Map.Entry service() {
        print();
        doGet();
        return entrySet();
    }

    Map.Entry entrySet() {
        return new Map.Entry() {
            @Override
            public Object getKey() {
                return null;
            }

            @Override
            public Object getValue() {
                return null;
            }

            @Override
            public Object setValue(Object value) {
                return null;
            }
        };
    }

    void print() {

    }

    abstract void doGet();

    protected class InnerClass {
        private int age;
    }
}
