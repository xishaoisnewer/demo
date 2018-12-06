package com.yooli.demo.syn;

import java.util.concurrent.*;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/9
 * Time: 下午1:37
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new CallableImpl());
//        executor.shutdown();
        try {
            System.out.println("task运行结果" + future.get());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.submit(new FutureTask<Integer>(new CallableImpl()));
        executor.shutdown();

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/
    }
}

class CallableImpl implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return sum;
    }
}
