package com.example.demo.constant;

import com.example.demo.model.TimeSequence;
import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/11
 */
public class CowHeatTemplate {
    final static List<Double> timeValueList = Arrays.asList(
            33.73, // 00:00
            33.51, // 02:00
            33.36, // 04:00
            33.50, // 06:00
            33.81, // 08:00
            34.33, // 10:00
            35.00, // 12:00
            35.51, // 14:00
            34.97, // 16:00
            34.53, // 18:00
            34.21, // 20:00
            33.84); // 22:00
    private final static LocalDate LOCAL_DATE = LocalDate.MIN;
    private final static LocalTime BEGIN_TIME = LocalTime.of(00, 00, 00);
    private final static LocalTime END_TIME = LocalTime.of(22, 00);
    private final static Double upFloat = 0.1;
    private final static Double dowFloat = 0.1;

    private static TimeSequence cowHeatSequence = new TimeSequence(
            LocalDateTime.of(LOCAL_DATE,BEGIN_TIME),
            LocalDateTime.of(LOCAL_DATE,END_TIME),
            Duration.ofHours(2),
            timeValueList);

    private static Random random = new Random();

    /**
     * 生成一个时间点的温度
     * @param time
     * @return
     */
    public static Double generatePoint(LocalDateTime time) {
        Double value = cowHeatSequence.getValue(LocalDateTime.of(LOCAL_DATE,
                LocalTime.of(time.getHour(),time.getMinute())));
        // 上浮或下浮
        if(random.nextInt()%2==0) {
            return value + random.nextDouble() * upFloat;
        } else {
            return value - random.nextDouble() * dowFloat;
        }
    }

    public static List<Double> generatePointRange(LocalDateTime beginTime,LocalDateTime endTime,Duration duration) {
        List<Double> valueList = new ArrayList<>();
        for(;!endTime.isBefore(beginTime);beginTime = beginTime.plusSeconds(duration.getSeconds())) {
            valueList.add(generatePoint(beginTime));
        }
        return valueList;
    }

}
