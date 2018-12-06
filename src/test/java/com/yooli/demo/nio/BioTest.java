package com.yooli.demo.nio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/16
 * Time: 4:23 PM
 */
public class BioTest {
    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(50);
        for (int i =0;i<50;i++){
            executor.execute(new BioThread());
        }

    }

    static class BioThread implements Runnable {
        int count = 10;

        @Override
        public void run() {
            while (read() < 0) {

            }
            //开发分发请求到相应的controller，去处理
            System.out.println("i am done!!!¬¬¬");
        }


        public int read() {
            try {
                count--;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return count;
        }
    }
}
