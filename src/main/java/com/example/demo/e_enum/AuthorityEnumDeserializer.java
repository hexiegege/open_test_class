package com.example.demo.e_enum;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * AuthorityEnum反序列化器
 *
 * @author shanfa
 */
public class AuthorityEnumDeserializer extends JsonDeserializer<AuthorityEnum> {
    @Override
    public AuthorityEnum deserialize( JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return AuthorityEnum.fromValue(jsonParser.getText());
    }
}
