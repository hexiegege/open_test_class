package com.example.demo.e_enum;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * AuthorityEnum序列器
 *
 * @author shanfa
 */
public class AuthorityEnumSerializer extends JsonSerializer<AuthorityEnum> {
    @Override
    public void serialize(AuthorityEnum authorityEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(authorityEnum.toString());
    }
}
