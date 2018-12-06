package com.yooli.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

    //	@Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }

        System.out.println((1 << (Integer.SIZE - 3)) - 1);
        System.out.println(Runtime.getRuntime().availableProcessors());
        String flag = "true";
        System.out.println(Boolean.getBoolean(flag));
        boolean orEqual = true;
        if (1==1){
            orEqual = false;
        }
        boolean orResult = true;
        orResult |= orEqual;
        System.out.println(true|false);
    }


}
