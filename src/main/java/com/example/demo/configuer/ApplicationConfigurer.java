package com.example.demo.configuer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 系统配置，读取自配置文件application节
 * @author shanfa
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationConfigurer {

    /**
     * 配置文件application.authentication节
     * 构建JWT时的签名信息
     */
    private Authentication authentication;

    /**
     * 配置文件application.default-password节
     * 默认密码
     */
    private String defaultPassword;

    /**
     * 配置文件application.role-name节
     * 权限名称
     */
    private RoleName roleName;

    /**
     * 配置文件application.base-url节
     * 根路径localhost+tomcat端口
     */
    private String baseUrl;

    @Getter
    @Setter
    public static class Authentication {
        /**
         * 配置文件application.authentication.key的值
         */
        private String key;
    }

    @Getter
    @Setter
    public static class RoleName {

        /**
         * 配置文件application.role-name.admin节
         */
        private String admin;
        /**
         * 配置文件application.role-name.patient节
         */
        private String student;
    }
}
