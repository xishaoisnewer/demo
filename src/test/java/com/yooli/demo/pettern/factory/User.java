package com.yooli.demo.pettern.factory;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/8
 * Time: 下午10:16
 */
public class User {
    @Validate(min = 2, max = 5)
    private String name;

    @Validate(isNotNull = false)
    private String age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }
}
