package com.yooli.demo.leetcode;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/6
 * Time: 下午5:59
 */
public class MaxPointOnLine {
    private static Double[][] arr = {{1.0, 2.0}, {3.0, 6.0}, {2.0, 4.0}, {1.3, 2.5}, {89.0, 9.2}, {3.4, 8.4}, {0.5, 9.6}};
    private static Map<Double, Integer> map = new HashMap<>(arr.length);
    private static List<Integer> list = new ArrayList<>(arr.length);


    public static void main(String[] args) {
        map.put(23.0,1);
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            Map<Double, Integer> map = new HashMap<>(arr.length);
            for (int j = 0; j < arr.length; j++) {
                if (i == j) continue;
                Double consult = (arr[j][1] - arr[i][1]) / (arr[j][0] - arr[i][0]);
                map.put(consult, map.get(consult) == null ? 1 : map.get(consult) + 1);
            }
            Set<Map.Entry<Double, Integer>> set = map.entrySet();
            Set<Double> keySet = map.keySet();

            Iterator<Double> keyIterator = keySet.iterator();
            Iterator<Map.Entry<Double, Integer>> setIterator = set.iterator();
//            while (keyIterator.hasNext()) {
//                Double key = keyIterator.next();
//                Integer value = map.get(key);
//            }
            while (setIterator.hasNext()) {
                Map.Entry<Double, Integer> entry = setIterator.next();
//                Double key = entry.getKey();
                Integer value = entry.getValue();
                if (max <= value)
                    max = value;
            }
        }
        System.out.println(max + 1);

    }


}
