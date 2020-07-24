package com.example.demo.e_enum;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * SexEnum序列器
 *
 * @author shanfa
 */
public class SexEnumSerializer extends JsonSerializer<SexEnum> {
    @Override
    public void serialize( SexEnum sexEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(sexEnum.toString());
    }
}
