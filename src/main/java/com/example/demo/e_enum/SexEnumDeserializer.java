package com.example.demo.e_enum;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * SexEnum反序列化器
 *
 * @author shanfa
 */
public class SexEnumDeserializer extends JsonDeserializer<SexEnum> {
    @Override
    public SexEnum deserialize( JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return SexEnum.fromValue(jsonParser.getText());
    }
}
