package com.yooli.demo.syn;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/9
 * Time: 上午12:39
 */
public class A {
    JManager j = new JManager();

    public static void main(String[] args) {
        new A().call();
    }

    void call()

    {
        for (int i = 0; i < 2; i++) {
            new Thread(
                    new Runnable() {
                        public void run() {
                            while (true) {
                                j.accumulate();
                            }
                        }
                    }
            ).start();
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        j.subtract();
                    }
                }
            }).start();
        }
    }
}

class JManager {
    private int j = 0;

    public synchronized void subtract() {
        j--;
        System.out.println("j--:" + j);
    }

    public synchronized void accumulate() {
        j++;
        System.out.println("j++:" + j);

    }
}
