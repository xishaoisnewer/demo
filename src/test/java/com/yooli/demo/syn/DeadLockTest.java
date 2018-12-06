package com.yooli.demo.syn;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/19
 * Time: 3:30 PM
 */
public class DeadLockTest {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(() -> {
            synchronized (o1) {
                try {
                    System.out.println(Thread.currentThread().getName() + "i lock o1,sleep");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "i lock o1,sleeped");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "i lock o1,try lock o2");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "i lock o1,locked o2");

                }
            }
        }).start();
        new Thread(() -> {
            synchronized (o2) {
                try {
                    System.out.println(Thread.currentThread().getName() + "i lock o2,sleep");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "i lock o2,sleeped");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "i lock o2,try lock o1");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "i lock o2,locked o1");

                }
            }
        }).start();
    }
}

