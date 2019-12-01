package com.example.demo.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/16
 */
public class LocalDateUtil {
    /**
     * 默认时间格式, 年 月 日 时分秒
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 通过输入字符串，转换为LocalDate
     *
     * @param standardInputString 输入字符串 yyyy-MM-dd
     * @return LocalDateTime
     */
    public static LocalDate string2LocalDate(String standardInputString) {
        return string2LocalDate(standardInputString,DEFAULT_DATE_FORMAT);
    }

    /**
     * 通过输入字符串，转换为LocalDateTime
     *
     * @param standardInputString 输入字符串 yyyy-MM-dd hh:mm:ss
     * @return LocalDateTime
     */
    public static LocalDate string2LocalDate(String standardInputString,String format) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
            LocalDate ldt = LocalDate.parse(standardInputString, df);
            return ldt;
        } catch (Exception ex) {
            throw new IllegalArgumentException(String.format("Fail to parse [%s] to localDate by format:[%s]",standardInputString,format));
        }
    }

    /**
     * 通过LocalDateTime输入，转换为字符串
     *
     * @param localDate 输入的localDateTime
     * @return 返回结果
     */
    public static String localDate2String(LocalDate localDate) {
        return localDate2String(localDate,DEFAULT_DATE_FORMAT);
    }

    /**
     * 通过LocalDate输入，转换为字符串
     *
     * @param localDate 输入的localDate
     * @return 返回结果
     */
    public static String localDate2String(LocalDate localDate,String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return df.format(localDate);
    }


    public static long localDateToMilli(LocalDate localDate) {
        return localDateToMilli(localDate,LocalTime.MIN);
    }

    /**
     * 转成毫秒
     * @param localDate
     * @return
     */
    public static long localDateToMilli(LocalDate localDate, LocalTime localTime) {
        LocalDateTime  localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将时间戳转换为localDateTime
     * @param timeStamp
     * @return
     */
    public static LocalDate milliToLocalDate(long timeStamp) {
        Instant instant = Instant.ofEpochMilli(timeStamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone).toLocalDate();
    }
}
