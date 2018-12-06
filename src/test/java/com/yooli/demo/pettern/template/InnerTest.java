package com.yooli.demo.pettern.template;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/7
 * Time: 上午10:10
 * <p>
 * 非静态内部类是可以被它的子类集成的
 */
public class InnerTest {
    public static void main(String[] args) {
        AbstractClass.InnerClass innerClass = new ClassImpl().new InnerClass();
    }
}
