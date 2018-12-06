package com.yooli.demo.manager.zk;

/**
 * Created by shaoxi.zhu
 * //url https://www.cnblogs.com/liuyang0/p/6800538.html
 * Date: 2018/11/2
 * Time: 上午11:01
 */
public class Test {
    static int n = 500;

    public static void secskill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            public void run() {
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock("172.16.3.138:2181,172.16.3.139:2181,172.16.3.140:2181", "test1");
                    lock.lock();
                    secskill();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
