package com.yooli.demo.consts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by lx on 2018/9/28.
 */
public class IdCardNoNum {
    public static final Logger logger= LoggerFactory.getLogger(IdCardNoNum.class);

    String id="";

    public String randomOne(String s[]){
        return s[new Random().nextInt(s.length-1)];
    }

    public String randomCityCode(int max){
        int i=new Random().nextInt(max)+1;
        return i>9?i+"":"0"+i;
    }

    public String randomBirth(int minAge,int maxAge){
        SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
        Calendar date=Calendar.getInstance();
        date.setTime(new Date());
        int randomDay=365*minAge+new Random().nextInt(365*(maxAge-minAge));
        date.set(Calendar.DATE,date.get(Calendar.DATE)-randomDay);
        return df.format(date.getTime());
    }

    public String getRandomId(){
        String id="";
        String provinces[]={"11", "12", "13", "14", "15", "21", "22", "23",
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82" };
        String province=randomOne(provinces);
        String city=randomCityCode(18);
        String country=randomCityCode(28);
        String birth=randomBirth(20, 50);
        String no=new Random().nextInt(899)+100+"";
        String checks[]={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "X"};
        String check=randomOne(checks);
        id=province+city+country+birth+no+check;
        return id;
    }
    public static void main(String[] args){
        IdCardNoNum idCardNoNum=new IdCardNoNum();
        String id=idCardNoNum.getRandomId();
        logger.info("id="+id);
    }

}
