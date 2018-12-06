package com.yooli.demo.es;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;

import java.io.*;
import java.util.Iterator;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/23
 * Time: 3:15 PM
 * java  object  serialization
 * 此对象属于小对象，大小是91bytes，由此可知普通对象大小可以按照百byte算，一个index2^32byte*100=4G*100,服务器1T可以存储3个这样的小服务
 * Exception：4285，异常日志是它的25倍
 * 每日请求量150万，每个ip大概打24条日志，可以得出，公司app端的DAU是6万
 */
public class StringSizeTest {
    public static void main(String[] args) {
        System.out.println(("message: 2018-11-23 15:38:55 ERROR [catalina-exec-37] c.y.m.s.r.c.i.FinancePlanServiceRpcImpl - 0550005D9D01673F81DFCC0A1FF70306 - 80 :  getLastestFinancePlan error\n" +
                "com.fuscent.core.exception.FuscentException: 参数异常！\n" +
                "at com.fuscent.core.service.impl.FinancePlanServiceImpl.getLastestFinancePlan(FinancePlanServiceImpl.java:6140) ~[na:na]\n" +
                "at sun.reflect.GeneratedMethodAccessor1112.invoke(Unknown Source) ~[na:na]\n" +
                "at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_152]\n" +
                "at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_152]\n" +
                "at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:319) ~[spring-aop-4.3.4.RELEASE.jar:4.3.4.RELEASE]\n" +
                "at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:196) ~[spring-aop-4.3.4.RELEASE.jar:4.3.4.RELEASE]\n" +
                "at com.sun.proxy.$Proxy50.getLastestFinancePlan(Unknown Source) ~[na:na]\n" +
                "at com.alibaba.dubbo.common.bytecode.Wrapper107.invokeMethod(Wrapper107.java) ~[na:na]\n" +
                "at com.alibaba.dubbo.rpc.proxy.javassist.JavassistProxyFactory$1.doInvoke(JavassistProxyFactory.java:46) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.proxy.AbstractProxyInvoker.invoke(AbstractProxyInvoker.java:72) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.InvokerWrapper.invoke(InvokerWrapper.java:53) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.filter.ExceptionFilter.invoke(ExceptionFilter.java:64) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.monitor.support.MonitorFilter.invoke(MonitorFilter.java:75) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.filter.TimeoutFilter.invoke(TimeoutFilter.java:42) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.dubbo.filter.TraceFilter.invoke(TraceFilter.java:78) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.fuscent.core.filter.DubboInvokeTraceFilter.invoke(DubboInvokeTraceFilter.java:29) ~[na:na]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.filter.ContextFilter.invoke(ContextFilter.java:60) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.filter.GenericFilter.invoke(GenericFilter.java:112) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.filter.ClassLoaderFilter.invoke(ClassLoaderFilter.java:38) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.filter.EchoFilter.invoke(EchoFilter.java:38) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol$1.reply(DubboProtocol.java:108) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler.handleRequest(HeaderExchangeHandler.java:84) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler.received(HeaderExchangeHandler.java:170) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.remoting.transport.DecodeHandler.received(DecodeHandler.java:52) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at com.alibaba.dubbo.remoting.transport.dispatcher.ChannelEventRunnable.run(ChannelEventRunnable.java:82) ~[dubbo-2.6.0.jar:2.6.0]\n" +
                "at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) ~[na:1.8.0_152]\n" +
                "at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) ~[na:1.8.0_152]\n" +
                "at java.lang.Thread.run(Thread.java:748) ~[na:1.8.0_152]").length());
        System.out.println("2018-11-22 23:59:59 [055100502B01673C2643C2052D8B3928] INFO [catalina-exec-3] c.y.a.c.u.controller.FinancePlanUtil - 637 : 用户userId=(2065772)开始获取红包信息, couponId=(46733866)".length());
        User user = new User();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.txt"));
            out.writeObject(user);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("serialable fail");
        }

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.txt"));
            User user1 = (User) in.readObject();
            System.out.println(user1.toString());
            System.out.println(user1.toString().length());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file not found or ioException");
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("class user no found ");
        }

    }

    static class User implements Serializable {
        int num = 12423423;
        String name = "xiaoghong";
        int age = 19;
        String sex = "female";

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return super.toString() + "num:" + num + "\tname:" + name + "\tage:" + age + "\tsex:" + sex;
        }
    }
}
