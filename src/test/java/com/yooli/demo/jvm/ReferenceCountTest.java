package com.yooli.demo.jvm;

/**
 * Created by shaoxi.zhu
 * Date: 2018/10/27
 * Time: 上午11:57
 */
public class ReferenceCountTest {
    Object instance = null;
    private static final int int_1MB = 1024*1024;
    private byte[]bigSize=new byte[2*int_1MB];
    public static void testGC () {
        ReferenceCountTest objA=new ReferenceCountTest();
        ReferenceCountTest objB=new ReferenceCountTest();
        objA.instance=objB;
        objB.instance=objA;
        objA=null;
        objB=null;
//       GC,objA objB
        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountTest.testGC();
    }
}
