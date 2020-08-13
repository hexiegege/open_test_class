package com.example.demo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CourseController
 * @Author shanfa
 * @Date 2020/7/31 17:42
 * @Description CourseController
 * @Version 1.0
 */
@Slf4j
@Api(value = "课程Controller",tags = "课程接口")
@RestController
@RequestMapping("/course")
public class CourseController {
}
