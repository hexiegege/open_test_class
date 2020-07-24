package com.example.demo.e_enum;

/**
 * Authority枚举
 *
 * @author shanfa
 */

public enum AuthorityEnum {

    /**
     * 管理员
     */
    ADMIN("Admin"),
    /**
     * 教师
     */
    TEACHER("Teacher"),
    /**
     * 学生
     */
    STUDENT("Student");


    private String value;


    AuthorityEnum(String value) {
        this.value = value;
    }

    public static AuthorityEnum fromValue(String value) {
        for (AuthorityEnum authorityEnum :
                AuthorityEnum.values()) {
            if (value.equals(authorityEnum.toString())) {
                return authorityEnum;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return value;
    }
}
