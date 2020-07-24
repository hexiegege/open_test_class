package com.example.demo.e_enum;

import org.springframework.core.convert.converter.Converter;

/**
 * String到SexEnum转换器
 *
 * @author shanfa
 */
public class StringToSexEnumConverter implements Converter<String, SexEnum> {
    @Override
    public SexEnum convert(String s) {
        return SexEnum.fromValue(s);
    }
}
