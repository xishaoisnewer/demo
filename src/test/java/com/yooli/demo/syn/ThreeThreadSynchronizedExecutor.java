package com.yooli.demo.syn;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/9
 * Time: 上午9:59
 */
public class ThreeThreadSynchronizedExecutor {
    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("execute thread1");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("execute thread2");
            }
        });

        thread1.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread2.start();
        thread2.join();
        System.out.println("main is thread3 execute end");
    }
}
