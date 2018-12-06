package com.yooli.demo.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.rpc.service.GenericService;

import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;


/**
 * Created by lx on 2018/10/6.
 */
public class DubboService {
    public static final Logger logger= LoggerFactory.getLogger(DubboService.class);

    private ApplicationConfig application;
    private RegistryConfig registry;

    private static class SingletonHolder{
        private static DubboService INSTANCE=new DubboService();
    }

    private DubboService(){
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("lx-consumer");
        RegistryConfig registryConfig=new RegistryConfig();
        registryConfig.setAddress("zookeeper://172.16.3.138:2181");

        this.application=applicationConfig;
        this.registry=registryConfig;
    }

    public static DubboService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public Object genericInvoke(String interfaceClass, String methodName, List<Map<String, Object>> parameters){
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(interfaceClass); // 接口名
        reference.setGeneric(true);
        ReferenceConfigCache cache=ReferenceConfigCache.getCache();
        GenericService genericService=cache.get(reference);

        int len=parameters.size();
        String[] invokeParamTypes=new String[len];
        Object[] invokeParams=new Object[len];
        for (int i=0;i<len;i++){
            invokeParamTypes[i]=parameters.get(i).get("ParamType")+"";
            invokeParams[i]=parameters.get(i).get("Object");
        }
        return genericService.$invoke(methodName,invokeParamTypes,invokeParams);
    }
}
