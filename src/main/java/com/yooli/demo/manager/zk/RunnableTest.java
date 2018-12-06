package com.yooli.demo.manager.zk;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/2
 * Time: 下午8:06
 */
public class RunnableTest implements Runnable {
    private volatile AtomicInteger anInt = new AtomicInteger(0);

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "start");
        System.out.println(Thread.currentThread().getName() + "medium" + anInt.incrementAndGet());
        System.out.println(Thread.currentThread().getName() + "end");
    }

    public static void main(String[] args) {
        Runnable r = new RunnableTest();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(r);
            thread.start();
        }
    }
}
