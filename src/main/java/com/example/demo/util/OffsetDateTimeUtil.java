package com.example.demo.util;

import com.sun.scenario.effect.Offset;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
public class OffsetDateTimeUtil {
    /**
     * OffsetDateTime 转 long
     */
    public static long toLong(OffsetDateTime offsetDateTime) {
        return LocalDateTimeUtil.localDateTimeToMilli(toLocalDateTime(offsetDateTime));
    }

    public static OffsetDateTime fromString(String string) {
        return fromLocalDateTime(LocalDateTimeUtil.string2LocalDateTime(string));
    }

    public static String toString(OffsetDateTime offsetDateTime) {
        return LocalDateTimeUtil.localDateTime2String(toLocalDateTime(offsetDateTime));
    }

    public static OffsetDateTime fromLong(long time) {
        return fromLocalDateTime(LocalDateTimeUtil.milliToLocalDateTime(time));

//        LocalDateTime localDateTime = LocalDateTimeUtil.milliToLocalDateTime(time);
//        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime,ZoneOffset.UTC);
//        return offsetDateTime;
    }

    public static LocalDateTime toLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDateTime();
    }

    public static OffsetDateTime fromLocalDateTime(LocalDateTime localDateTime) {
        return OffsetDateTime.of(localDateTime,ZoneOffset.UTC);
    }

}
