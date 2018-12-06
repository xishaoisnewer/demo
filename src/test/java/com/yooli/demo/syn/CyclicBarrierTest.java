package com.yooli.demo.syn;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/3
 * Time: 下午4:36
 * 栅栏锁
 * 等到其他线程都满足某种状态，再往下执行
 */

//todo 栅栏锁循环执行的原因是啥
public class CyclicBarrierTest {
    public static void msain(String[] args) {
        System.out.println(Thread.currentThread());
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "主线程等其他任务都执行完毕，答应一句，caonima");
            }
        });
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < N; ++i) {
            exec.execute(new WriteTask(barrier));
        }
        exec.shutdown();
        System.out.println(Thread.currentThread());

    }

    public static void main(String[] args) throws Exception {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new CyclicBarrierManager(barrier, exec, N));
        exec.shutdown();
    }
}

class CyclicBarrierManager implements Runnable {
    private CyclicBarrier barrier;
    private ExecutorService exec;

    public CyclicBarrierManager(CyclicBarrier barrier, ExecutorService exec, int N) {
        this.barrier = barrier;
        this.exec = exec;
        for (int i = 0; i < N - 1; ++i) {
            exec.execute(new WriteTask(barrier));
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println("dayede");
                barrier.await();
                System.out.println("shabia");
            } catch (InterruptedException e) {
                System.out.println(getClass().getSimpleName() + " 被中断了！");
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class WriteTask implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private CyclicBarrier barrier;
    private static Random random = new Random(47);

    public WriteTask(CyclicBarrier cyclicBarrier) {
        this.barrier = cyclicBarrier;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            System.out.println(this + " 开始写入数据...");
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));      //以睡眠来模拟写入数据操作
                System.out.println(this + " 写入数据完毕，等待其他线程写入完毕" + " " + System.currentTimeMillis());
                barrier.await();
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(this + "is interrupted!");
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this + "所有任务写入完毕，继续处理其他任务... " + System.currentTimeMillis());
        }

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "-" + id;
    }
}

