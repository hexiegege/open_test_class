package com.example.demo.controller;

import com.example.demo.dto.ResultDTO;
import com.example.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  检查在线状态
 * @author shanfa
 */
@Slf4j
@Api(value = "SessionController",tags = "会话接口")
@RestController
@RequestMapping("/session")
@PreAuthorize("isAuthenticated()")
public class SessionController {

    @ApiOperation(value = "检查在线", notes = "检查当前用户是否在线")
    @GetMapping("")
    public ResponseEntity<Object> checkIsOnline() {
        return ResponseEntity.ok(ResultDTO.success("当前用户在线"));
    }
    @ApiOperation(value = "注销", notes = "注销当前用户")
    @DeleteMapping("")
    public ResponseEntity<Object> logout() {
        //如果集成了Redis存储注销的黑名单token信息，可以将当前用户token存入Redis
        return ResponseEntity.ok(ResultDTO.success("注销成功"));
    }
}
