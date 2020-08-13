package com.example.demo.entity;

import com.example.demo.e_enum.AuthorityEnum;
import com.example.demo.e_enum.AuthorityEnumAttributeConverter;
import com.example.demo.e_enum.SexEnum;
import com.example.demo.e_enum.SexEnumAttributeConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程信息
 * @author shanfa
 */
@Data
@Entity
public class Course implements Serializable {

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
     * 课程名称
     */
    @Column(length = 20, nullable = false)
    private String courseName;

    /**
     * 备注,预留
     */
    private String memo;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

}
