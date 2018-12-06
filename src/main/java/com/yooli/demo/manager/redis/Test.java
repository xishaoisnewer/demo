package com.yooli.demo.manager.redis;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/1
 * Time: 下午5:38
 */
class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}

public class Test {
    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}
