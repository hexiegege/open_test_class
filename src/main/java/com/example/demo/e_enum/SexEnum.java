package com.example.demo.e_enum;

/**
 * @author shanfa
 */
public enum SexEnum {
    /**
     * 男
     */
    Male("男"),
    /**
     * 女
     */
    Female("女");

    private String chineseChar;
    private static final String ONE = "1";
    SexEnum(String chineseChar) {
        this.chineseChar = chineseChar;
    }

    public static SexEnum fromValue(String value) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (value.equals(sexEnum.toString())) {
                return sexEnum;
            }
        }
        throw new IllegalArgumentException();
    }

    public static SexEnum fromWeChatValue(String value) {
        if (ONE.equals(value)) {
            return SexEnum.Male;
        }else {
            return SexEnum.Female;
        }
    }

    @Override
    public String toString() {
        return chineseChar;
    }

}