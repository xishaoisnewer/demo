package com.yooli.demo.syn;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/9
 * Time: 上午12:50
 */
public class MutilCycle {

    public static void main(String[] args) {
        new MutilCycle().init();
    }

    public void init() {
        Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    business.subThread(i);
                }
            }
        }).start();
        for (int i = 0; i < 50; i++) {
            business.mainThread(i);
        }

    }

    class Business {
        private boolean bShouldSub = true;//开关，控制main和sub之间交互


        public synchronized void mainThread(int i) {
            if (bShouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 5; j++) {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            bShouldSub = true;
            this.notify();
        }

        public synchronized void subThread(int i) {
            if (!bShouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 10; j++) {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            bShouldSub = false;
            this.notify();
        }
    }
}
