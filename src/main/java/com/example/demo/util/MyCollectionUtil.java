package com.example.demo.util;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
public class MyCollectionUtil {
    public static double[] listToArray(List<Double> tmp) {
        double[] array = new double[tmp.size()];
        for(int i=0;i<tmp.size();i++) {
            array[i] = tmp.get(i);
        }
        return array;
    }
}
