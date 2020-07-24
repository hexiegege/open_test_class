package com.example.demo.e_enum;

import javax.persistence.AttributeConverter;

/**
 * AuthorityEnum存储到数据库转换
 *
 * @author shanfa
 */
public class AuthorityEnumAttributeConverter implements AttributeConverter<AuthorityEnum, String> {
    @Override

    public String convertToDatabaseColumn(AuthorityEnum authorityEnum) {
        return authorityEnum.toString();
    }

    @Override
    public AuthorityEnum convertToEntityAttribute(String s) {
        return AuthorityEnum.fromValue(s);
    }
}
