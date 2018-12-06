package com.yooli.demo.jvm;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/19
 * Time: 10:05 PM
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        try {
            Thread.sleep(20000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
//        while (true) {
//            list.add(new OOMObject());
//        }
//        try {
//            BufferedOutputStream bytes = new BufferedOutputStream(new FileOutputStream("/tmp/file.txt", true));
//            Http(outputReq > as.stream.Bytes(bytes => {
//
//                    bos.write(bytes)
//
//            }));

//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
        bufferLoop:
        new BufferedReader(new InputStreamReader(System.in));

    }
}
