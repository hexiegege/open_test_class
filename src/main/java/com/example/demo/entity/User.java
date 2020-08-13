package com.example.demo.entity;

import com.example.demo.e_enum.AuthorityEnum;
import com.example.demo.e_enum.AuthorityEnumAttributeConverter;
import com.example.demo.e_enum.SexEnum;
import com.example.demo.e_enum.SexEnumAttributeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 * @author shanfa
 */
@Entity
@Data
public class User implements Serializable {
    /**
     * 用户Id
     * JPA 自动生成
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    /**
     * 角色
     */
    @Column(length = 20, nullable = false)
    @Convert(converter = AuthorityEnumAttributeConverter.class)
    private AuthorityEnum authority;

    /**
     * 手机号
     * 不为空时,唯一
     */
    @Column(length = 11)
    private String cellPhoneNo;

    /**
     * 生日
     */
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    /**
     * 昵称/用户名
     * 唯一
     */
    @Column(unique = true, length = 10)
    private String nickname;

    /**
     * 姓名
     */
    @Column(length = 20)
    private String name;
    /**
     * 性别
     */
    @Column(length = 2, nullable = false)
    @Convert(converter = SexEnumAttributeConverter.class)
    private SexEnum sex;
    /**
     * 密码
     */
    @Column(length = 100)
    private String password;
    /**
     * 备注,预留
     */
    private String memo;
    /**
     * 创建用户时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    /**
     * 最后登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;

    /**
     * 有效标志
     */
    @Column(nullable = false)
    private Boolean significanceTag = true;

}
