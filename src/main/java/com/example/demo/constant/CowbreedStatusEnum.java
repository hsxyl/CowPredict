package com.example.demo.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CowbreedStatusEnum {
    /**
     * 未受精
     */
    NOTFERTILIZED,
    /**
     * 泌乳
     */
    LACTATION,
    /**
     * 正常
     */
    NORMAL;

    /**
     * 名字map
     */
    private static final Map<String,CowbreedStatusEnum> namesMap= Arrays
               .stream(CowbreedStatusEnum.values())
               .collect(Collectors.toMap(Enum::name, Function.identity()));

    /**
     * 包含
     * @param name
     * @return
     */
    public static boolean contain(String name) {
        return namesMap.containsKey(name);
    }
}
