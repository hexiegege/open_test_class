package com.example.demo.controller;


import com.example.demo.dto.ResultDTO;
import com.example.demo.e_enum.AuthorityEnum;
import com.example.demo.e_enum.SexEnum;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 用户Controller
 * @author shanfa
 */
@Slf4j
@Api(value = "用户Controller",tags = "用户接口")
@RestController
@RequestMapping("/users")
//@PreAuthorize("isAuthenticated()")
public class UserController {


    @Autowired
    private UserService userService;




    /**
     * 根据手机号获取用户
     * @param cellphone
     * @return
     */
    @GetMapping(value = "", params = {"cellPhoneNo"})
    @ApiOperation(value = "根据手机号获取用户", notes = "根据手机号获取用户")
    @ApiImplicitParam(name = "cellPhoneNo", value = "手机号码")
    public ResponseEntity<?> getUserByCellPhoneNo( @RequestParam(value = "cellPhoneNo") String cellphone) {

            User user = userService.getUserByCellPhoneNo(cellphone);
            if (user!=null) {
                return ResponseEntity.ok(ResultDTO.success(user));
            } else {
                return ResponseEntity.badRequest().body("该患者不存在");
            }

    }


    /**
     * 根据手机号获取用户
     * @return
     */
    @GetMapping("/save")
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public ResponseEntity<?> createNewUser() {
        User user =  new User();
        user.setCellPhoneNo("18519122415");
        user.setPassword("$2a$10$kIbhGsqfLVQaFMIOOd4dsuHaiKBKYNEERMmwUxEobhfwAKUWR.f2u");
        user.setSex(SexEnum.Male);
        user.setAuthority(AuthorityEnum.STUDENT);
        user.setCreatedTime(new Date());
        user.setSignificanceTag(true);
        user.setNickname("dan");
        user.setName("大王");
        user = userService.createNewUser(user);
        return ResponseEntity.ok(ResultDTO.success(user));

    }



}

