package com.yooli.demo.jvm;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by shaoxi.zhu
 * Date: 2018/12/5
 * Time: 12:57 AM
 * 定时执行录入功能，将文件入库到对账库里面
 * 考虑点：
 * 1、文件存储，多大的内存可以将它都放到内存里面，太大了，是不是应该分页读取，多个文件有该如何分页读取
 * 2、读到内存的数据是批量插入，还是一条一条的插入，如果插入时，系统重启怎么办
 * 3、此需求会不会是分布式的，多个系统同时入库，该如何做
 * 4、表索引字段该怎么建，是否应该考虑性能问题
 * 5、数据库量太大，是否应该做分页
 */
public class LazyInitialzationTest {
    public static void main(String[] args) {

        FileReader fileReader = null;
        BufferedReader bf = null;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            fileReader = new FileReader("aFile");
            bf = new BufferedReader(fileReader);
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        String[][] strings = new String[arrayList.size()][];
        for (int i = 0; i < arrayList.size(); i++) {
            strings[i] = arrayList.get(i).split(",");
        }
        System.out.println(strings);


    }

    class Order {
        private int id;
        private int consumerId;
        private int accountId;
        private BigDecimal amount;
    }

    class Apple {
        int color;
        int size;
    }

}
