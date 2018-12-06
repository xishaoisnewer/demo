package com.yooli.demo.manager.rpc.impl;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.yooli.demo.domain.DubboRequestForm;
import com.yooli.demo.domain.DubboRequestParam;
import com.yooli.demo.domain.ResultVo;
import com.yooli.demo.manager.rpc.DubboInterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mchange.v1.util.ArrayUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lx on 2018/10/9.
 */
@Service
public class DubboInterfaceServiceImpl implements DubboInterfaceService {

    private static final Logger logger= LoggerFactory.getLogger(DubboInterfaceServiceImpl.class);

    public ResultVo genericInvokeMethod(DubboRequestForm form) throws Exception{
        ResultVo resultVo=new ResultVo("00","");
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName(form.getTblDubboRegDto().getName());
        ReferenceConfig<GenericService> referenceCfg = new ReferenceConfig<GenericService>();
        referenceCfg.setApplication(applicationConfig);
        String protocl="dubbo://";
        referenceCfg.setUrl(protocl + form.getTblDubboRegDto().getIp() + "/" + form.getInterfaceName());
        logger.info("dubbo请求地址:" + protocl + form.getTblDubboRegDto().getIp() + "/" + form.getInterfaceName());
        referenceCfg.setInterface(form.getInterfaceName());
        referenceCfg.setGroup(form.getTblDubboRegDto().getGroup());
        referenceCfg.setVersion(form.getTblDubboRegDto().getVersion());
        referenceCfg.setGeneric(true);
        referenceCfg.setTimeout(1200000);

        GenericService genericService=referenceCfg.get();
        List<DubboRequestParam> requestParamList=form.getParams();
        String[] typeArr=parseParamsTypeArray(requestParamList);
        Object[] valueArr=parseParamsValueArray(requestParamList);
        logger.info(ArrayUtils.toString(typeArr));
        logger.info(ArrayUtils.toString(valueArr));

        Object res=genericService.$invoke(form.getMethod(),typeArr,valueArr);
        System.out.println(res);

        if (res==null){
            resultVo.setCode("02");
            resultVo.setMsg(String.format("请求接口[%s],未获得应答!", form.getInterfaceName()));
            return resultVo;
        }
        resultVo.setMsg(res.toString());
        return resultVo;
    }

    private String[] parseParamsTypeArray(List<DubboRequestParam> requestParamList) {
        int paramCount = requestParamList.size();
        if (paramCount == 0) {
            return null;
        }
        String[] typeArr = new String[paramCount];
        for (int i=0;i<paramCount;i++){
            typeArr[i]=requestParamList.get(i).getParamType();
        }
        return typeArr;
    }

    private Object[] parseParamsValueArray(List<DubboRequestParam> requestParamList) {
        int paramCount = requestParamList.size();
        if (paramCount == 0) {
            return null;
        }
        Object[] valueArr = new Object[paramCount];
        for (int i = 0; i < paramCount; i++) {
            DubboRequestParam param = requestParamList.get(i);
            if (param.getParamType().startsWith("com")) {
                String paramStr = (String) param.getParamVale();
                String replaceStr = paramStr.replace("'", "\"");
                System.out.println(replaceStr);
                Map map = (Map) com.alibaba.fastjson.JSON.parse(replaceStr);
                System.out.println(map.toString());
                valueArr[i] = map;
            } else if (param.getParamType().endsWith("List")) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                String reqParam = (String) param.getParamVale();
                if (reqParam.contains("{")) {
                    list = (List<Map<String, Object>>) com.alibaba.fastjson.JSON.parse(reqParam);
                    for (Map<String, Object> map : list) {
                        Set<String> keys = map.keySet();
                        for (String key : keys) {
                            if (key.contains("#")) {
                                String[] typeAndKey = key.split("#");
                                String val = (String) map.get(key);
                                if (typeAndKey[0].contains("Date")) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    try {
                                        Date dVal = sdf.parse(val);
                                        map.remove(key);
                                        map.put(typeAndKey[1], dVal);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }

                    }
                    valueArr[i] = list;
                } else {
                    List rawList = new ArrayList();
                    String[] params = reqParam.split(",");
                    for (String value : params) {
                        if (value.contains("#")) {
                            String[] valueWithType = value.split("#");
                            try {
                                Class clz = Class.forName(valueWithType[0]);

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            rawList.add(value);
                        }
                    }
                    valueArr[i] = rawList;
                }

            } else if (param.getParamType().contains("[")) {
                String value = (String) param.getParamVale();
                ArrayList lst = new ArrayList();
                if (value.contains(",")) {
                    String[] valueArrs = value.split(",");
                    for(String v: valueArrs){
                        lst.add(v);
                    }
                }else{
                    lst.add(value);
                }
                valueArr[i] = lst;
            }else{
                valueArr[i] = param.getParamVale();
            }
        }
        return valueArr;
    }
}
