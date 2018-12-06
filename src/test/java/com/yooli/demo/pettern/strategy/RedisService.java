package com.yooli.demo.pettern.strategy;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/7
 * Time: 上午12:58
 */
public class RedisService implements OrderService {
    @Override
    public void save() {
        System.out.println("i am save data ,use redis");
    }
}
