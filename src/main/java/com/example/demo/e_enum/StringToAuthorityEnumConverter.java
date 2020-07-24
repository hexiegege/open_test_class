package com.example.demo.e_enum;

import org.springframework.core.convert.converter.Converter;

/**
 * String到AuthorityEnum转换器
 *
 * @author shanfa
 */
public class StringToAuthorityEnumConverter implements Converter<String, AuthorityEnum> {
    @Override
    public AuthorityEnum convert( String s) {
        return AuthorityEnum.fromValue(s);
    }
}
