package com.yooli.demo.pettern.strategy;


/**
 * Created by shaoxi.zhu
 * Date: 2018/11/7
 * Time: 上午12:59
 */
public class OrderExecutor {
    OrderService orderService;

    public OrderExecutor(OrderService orderService) {
        this.orderService = orderService;
    }

    public void save2Service() {
        orderService.save();
    }


    public static void main(String[] args) {
        OrderService service = new RedisService();
        service.save();//这是多态

        OrderExecutor executor = new OrderExecutor(new RedisService());
        executor.save2Service();//这是策略模式，主要是因为它将多态的参数传给了构造器，而多态再调用之前已经明确知道调用的是那个子类了

    }
}
