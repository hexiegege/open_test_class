package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shanfa
 * @Desc
 */
@RestController
@Api(value = "登录接口",tags = "获取登录授权")
public class LoginController {

    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickname",value ="用户名"),
            @ApiImplicitParam(name = "password",value ="密码"),
    })
    ResponseEntity<Object> login( @RequestParam("nickname")String nickname, @RequestParam("password")String password){
        return ResponseEntity.ok().build();
     }
}
