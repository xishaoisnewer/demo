package com.yooli.demo.pettern.factory;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/8
 * Time: 下午10:16
 */
public class Test {
    public static void main(String[] args)
    {
        User user = new User();

        user.setName("liang");
        user.setAge("1");

        System.out.println(UserCheck.check(user));
    }
}
