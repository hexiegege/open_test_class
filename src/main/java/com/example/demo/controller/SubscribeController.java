package com.example.demo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SubscribeController
 * @Author shanfa
 * @Date 2020/7/31 17:42
 * @Description SubscribeController
 * @Version 1.0
 */
@Slf4j
@Api(value = "订阅Controller",tags = "订阅接口")
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {
}
