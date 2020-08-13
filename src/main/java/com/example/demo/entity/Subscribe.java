package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Subscribe
 * @Author shanfa
 * @Date 2020/7/31 17:22
 * @Description 订阅信息
 * @Version 1.0
 */
@Data
@Entity
public class Subscribe implements Serializable {
    /**
     * Id
     * JPA 自动生成
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    /**
     * 用户Id
     */
    @Column(length = 32)
    private String userId;

    /**
     * 课程
     */
    @Column(length = 10)
    private String course;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdTime;

}
