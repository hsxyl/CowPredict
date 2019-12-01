package com.example.demo.util;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * @author xushenbao
 * @desc LocalDateTime工具类
 * @create 2019/11/2
 */
public class OffsetDateTimeUtilTest {

    @Test
    public void testLocalDateTimeUtil() {
        LocalDateTime localDateTime = LocalDateTimeUtil.milliToLocalDateTime(1572677510);
        long time = LocalDateTimeUtil.localDateTimeToMilli(localDateTime);
        System.out.println(time);
    }

    @Test
    public void testOffsetDateTimeUtil() {
        OffsetDateTime offsetDateTime = OffsetDateTimeUtil.fromLong(1572677510);
        long time = OffsetDateTimeUtil.toLong(offsetDateTime);
        System.out.println(time);
    }
}
