package com.example.demo.util;

import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
public class LocalDateTimeUtil {
    /**
     * 默认时间格式, 年 月 日 时分秒
     */
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 通过输入字符串，转换为LocalDateTime
     *
     * @param standardInputString 输入字符串 yyyy-MM-dd hh:mm:ss
     * @return LocalDateTime
     */
    public static LocalDateTime string2LocalDateTime(String standardInputString) {
        return string2LocalDateTime(standardInputString,DEFAULT_TIME_FORMAT);
    }

    /**
     * 通过输入字符串，转换为LocalDateTime
     *
     * @param standardInputString 输入字符串 yyyy-MM-dd hh:mm:ss
     * @return LocalDateTime
     */
    public static LocalDateTime string2LocalDateTime(String standardInputString,String format) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
            LocalDateTime ldt = LocalDateTime.parse(standardInputString, df);
            return ldt;
        } catch (Exception ex) {
            throw new IllegalArgumentException(String.format("Fail to parse [%s] to localDateTime by format:[%s]",standardInputString,format));
        }
    }

    /**
     * 通过LocalDateTime输入，转换为字符串
     *
     * @param localDateTime 输入的localDateTime
     * @return 返回结果
     */
    public static String localDateTime2String(LocalDateTime localDateTime) {
        return localDateTime2String(localDateTime,DEFAULT_TIME_FORMAT);
    }

    /**
     * 通过LocalDateTime输入，转换为字符串
     *
     * @param localDateTime 输入的localDateTime
     * @return 返回结果
     */
    public static String localDateTime2String(LocalDateTime localDateTime,String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return df.format(localDateTime);
    }

    /**
     * 转成秒
     * @param localDateTime
     * @return
     */
    public static long localDateTimeToSeconds(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(OffsetDateTime.now().getOffset());
    }
    /**
     * 转成毫秒
     * @param localDateTime
     * @return
     */
    public static long localDateTimeToMilli(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将时间戳转换为localDateTime
     * @param timeStamp
     * @return
     */
    public static LocalDateTime milliToLocalDateTime(long timeStamp) {
        Instant instant = Instant.ofEpochMilli(timeStamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }
    public static LocalDateTime dropMinuteAndSecond(LocalDateTime time) {
       return LocalDateTime.of(
               time.getYear(),
               time.getMonth(),
               time.getDayOfMonth(),
               time.getHour(),
               0,0) ;
    }

}
