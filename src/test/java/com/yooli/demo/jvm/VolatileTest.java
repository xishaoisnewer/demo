package com.yooli.demo.jvm;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/8
 * Time: 上午10:48
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static int increase() {
        return race++;
    }

    public static void main(String[] args) {
        int NUM = 20;
        for (int i = 0; i < NUM; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            }).start();
        }

        while (Thread.activeCount() > 1) Thread.yield();
        System.out.println(race);

    }

}
