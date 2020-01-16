package com.example.demo.deser;

import com.example.demo.util.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/12/30
 */
public class LocalDateTimeStandardDeserialize extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String input = jp.getText();
        return LocalDateTimeUtil.string2LocalDateTime(input);
    }
}
