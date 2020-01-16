package com.example.demo.deser;


import com.example.demo.util.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/12/30
 */
public class LocalDateTimeStandardSerialize extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(LocalDateTimeUtil.localDateTime2String(value));
    }
}
