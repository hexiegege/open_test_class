package com.example.demo.e_enum;

import javax.persistence.AttributeConverter;

/**
 * SexEnum存储到数据库转换
 *
 * @author shanfa
 */
public class SexEnumAttributeConverter implements AttributeConverter<SexEnum, String> {
    @Override
    public String convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.toString();
    }

    @Override
    public SexEnum convertToEntityAttribute(String s) {
        return SexEnum.fromValue(s);
    }
}
