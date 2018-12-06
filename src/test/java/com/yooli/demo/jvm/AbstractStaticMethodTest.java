package com.yooli.demo.jvm;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/8
 * Time: 下午1:19
 */
public class AbstractStaticMethodTest {
    private static int age;
    private int name;
    class InnerClass{
        public void r(){
            System.out.println(name);
        }
    }

    static class NestedClass {
        public void tes() {
            System.out.println(age);
        }
    }

    private static void tes() {
        final int b = 1;
         class Inner{
            private int a = age;
            private int bb = b;

        }
        Inner inner = new Inner();
        System.out.println(inner.a);
    }
}
