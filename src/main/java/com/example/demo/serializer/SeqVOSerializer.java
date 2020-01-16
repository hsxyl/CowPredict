package com.example.demo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/12/20
 */
public class SeqVOSerializer extends JsonSerializer<List<Double>> {

    @Override
    public void serialize(List<Double> doubles, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        jsonGenerator.writeStartArray();
        for(Double aDouble : doubles) {
            jsonGenerator.writeString(nf.format(aDouble));
        }
        jsonGenerator.writeEndArray();
    }
}
