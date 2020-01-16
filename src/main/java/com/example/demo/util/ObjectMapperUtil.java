package com.example.demo.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc
 * @Author Mushen
 * @Create 2019-01-09
 */
@Slf4j(topic = "System.Util")
public final class ObjectMapperUtil {
    private static final ObjectMapper MAPPER = objectMapper();

    /**
     * ObjectMapper
     * @return
     */
    public static ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        // Date: yyyy-MM-dd HH:mm:ss
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(outputFormat);
        // Determines whether encountering of unknown properties or not.
        // unknown properties means: ones that do not map to a property,
        // and there is no "any setter" or handler that can handle it
        // Feature is enabled by default
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // LocalDateTime: yyyy-MM-dd HH:mm:ss
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // LocalDate: yyyy-MM-dd
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // LocalTime: HH:mm
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm")));

        // Method for registering a module that can extend functionality provided by this mapper;
        mapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());

        return mapper;
    }

    /**
     * JSON转换成对象
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return MAPPER.readValue(content, valueType);
        } catch (IOException e) {
            log.error(String.format("将JSON[%s]转换成指定的Java对象[%s], 出错了: ", content, valueType), e);
            return null;
        }
    }

    /**
     * JSON转换成对象
     * @param content
     * @param javaType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String content, JavaType javaType) {
        try {
            return MAPPER.readValue(content, javaType);
        } catch (Exception e) {
            log.error(String.format("将JSON[%s]转换成指定的Java对象[%s], 出错了: ", content, javaType), e);
            return null;
        }
    }

    /**
     * JSON转换成列表
     * @param content
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> List<T> readValueAsList(String content, Class<T> classType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, classType);
        try {
            return MAPPER.readValue(content, javaType);
        } catch (IOException e) {
            return Lists.newArrayList();
        }
    }

    /**
     * JSON转换成键值对
     * @param content
     * @param keyClass
     * @param valueClass
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> readValueAsMap(String content, Class<K> keyClass, Class<V> valueClass) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(Map.class, keyClass, valueClass);
        try {
            return MAPPER.readValue(content, javaType);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    /**
     * 将对象转换成JSON字符串
     * @param value
     * @return
     */
    public static String writeValueAsString(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 漂亮的打印对应的对象内容
     *
     * @param value 对象内容
     * @return 格式化的数据字符串
     */
    public static String writeValueAsPrettyString(Object value) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            return "";
        }
    }

    public static String simpleCompress(String json) {
        return json.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" {4}", "").replaceAll(" {3}", "").replaceAll(" {2}", "");
    }
}
