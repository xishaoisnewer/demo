package com.yooli.demo.consts;

import java.util.HashMap;

/**
 * Created by lx on 2018/9/28.
 */
public class EnvGroupDef {
    public static final Short Beta=Short.valueOf((short)10001);
    public static final Short BetaB=Short.valueOf((short)10002);
    public static final Short BetaC=Short.valueOf((short)10003);
    public static final Short BetaD=Short.valueOf((short)10004);
    public static final Short BetaE=Short.valueOf((short)10005);
    public static final Short BetaF=Short.valueOf((short)10006);
    public static HashMap<Short,String> groupMap=new HashMap<>();

    public EnvGroupDef(){
    }


    public static boolean existGroup(short value){
        return groupMap.containsKey(Short.valueOf(value));
    }
    static {
        groupMap.put(Beta,"Beta");
        groupMap.put(BetaB,"BetaB");
        groupMap.put(BetaC,"BetaC");
        groupMap.put(BetaD,"BetaD");
        groupMap.put(BetaE,"BetaE");
        groupMap.put(BetaF,"BetaF");
    }
}
