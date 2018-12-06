package com.yooli.demo.consts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Random;
import org.apache.commons.lang3.*;
import sun.security.util.Length;

/**
 * Created by lx on 2018/9/29.
 */
public class BankGenerateNum {

    public static final Logger logger= LoggerFactory.getLogger(BankGenerateNum.class);

    public Random getRandomInstance(){
        Random random=new Random(new Date().getTime());
        return random;
    }

    public String getBankCard(String bankName){
        String binpath="C:\\Users\\lx\\Downloads\\demo\\src\\main\\resources\\cardbin.txt";
        String bin="";
        int cardLength=0;
        String bankCard="";
        String banklend="";
        try {
            File file = new File(binpath);
            FileInputStream in=new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            StringBuffer stringBuffer=new StringBuffer();
            String temp=bufferedReader.readLine();
            String[] radmon = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            while (temp!=null){
                if (temp.contains(bankName)){
                    bin=temp.split("\t")[0];
                    cardLength=Integer.parseInt(temp.split("\t")[4]);
                    break;
                }
                temp=bufferedReader.readLine();
            }
            for (int i=0;i<(cardLength-bin.length());i++)
            {
                banklend=radmon[new Random().nextInt(10)];
                stringBuffer.append(banklend);
            }
            bankCard=bin+stringBuffer;
        }catch (Exception e){
            e.printStackTrace();
        }
        return bankCard;
    }



    public static void main(String args[]) throws Exception{
        BankGenerateNum bankGenerateNum=new BankGenerateNum();
        logger.info(bankGenerateNum.getBankCard("招商银行"));
    }
}
