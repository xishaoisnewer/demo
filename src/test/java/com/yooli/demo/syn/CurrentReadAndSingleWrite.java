package com.yooli.demo.syn;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/9
 * Time: 上午11:45
 * todo 考虑两点：
 * 1、write加锁以后，read怎么停止读的
 * 2、read既然是加锁，怎么并发读的
 * 3、写一个多线程并发访问下试试
 */
public class CurrentReadAndSingleWrite {
    ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock w = lock.writeLock();
    Lock r = lock.readLock();

    public void write(Integer num, String msg) {
        w.lock();
        try {
            concurrentHashMap.put(num, msg);
        } finally {
            w.unlock();
        }

    }

    public Set<Map.Entry<Integer, String>> getEntrys() {
        r.lock();
        try {
            return concurrentHashMap.entrySet();

        } finally {
            r.unlock();

        }
    }

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();

    }
}
