package com.example.demo.security;


import com.example.demo.configuer.ApplicationConfigurer;
import com.example.demo.dto.CodeMsg;
import com.example.demo.dto.ResultDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.springframework.security.oauth2.common.OAuth2AccessToken.BEARER_TYPE;

/**
 * 过滤 /login 获取认证
 * @author shanfa
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final String BAD_SMS_VERIFICATION_CODE = "输入的验证码错误！";

    private final String USER_NOT_REGISTERED = "用户未注册";

    private final String LOGIN_FAILED = "登录失败";

    /**
     * 本系统配置
     */
    private ApplicationConfigurer applicationConfigurer;

    private AuthenticationManager authenticationManager;

    private UserService userService;

    private ObjectMapper mapper;

    public JwtLoginFilter( AuthenticationManager authenticationManager, UserService userService, ApplicationContext applicationContext) {
        super.setFilterProcessesUrl("/login");
        this.authenticationManager = authenticationManager;
        this.applicationConfigurer = applicationContext.getBean(ApplicationConfigurer.class);
        this.userService = userService;
        this.mapper = applicationContext.getBean(ObjectMapper.class);
    }


    /**
     * 尝试登录
     *
     * @param request  HTTP请求
     * @param response HTTP响应
     * @return 认证
     * @throws AuthenticationException 认证异常
     */
    @Override
    public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(nickname)) {
            logger.error("用户名");
            throw new InternalAuthenticationServiceException("用户名不能为空！");
        }
        if (StringUtils.isEmpty(password)) {
            logger.info("登录密码有为空");
            throw new InternalAuthenticationServiceException("登录密码不能为空！");
        }
        User user = userService.getUserByNickname(nickname);

        if (user==null) {
            logger.info("用户未注册");
            throw new InternalAuthenticationServiceException(USER_NOT_REGISTERED);
        }
        if (StringUtils.isEmpty(user.getPassword())){
            logger.info("用户未设置密码");
            throw new InternalAuthenticationServiceException(LOGIN_FAILED);
        }

        if (userService.loginByCellphoneNoAndPassword(user.getCellPhoneNo(), password)) {
            return new UsernamePasswordAuthenticationToken(user.getId(), "", new ArrayList<>());
        }

        throw new InternalAuthenticationServiceException(LOGIN_FAILED);

    }

    /**
     * 失败处理
     *
     * @param request  HTTP请求
     * @param response HTTP响应
     * @param failed   AuthenticationException
     */
    @Override
    protected void unsuccessfulAuthentication( HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        response.setHeader("Content-type", "text/json;charset=UTF-8");
        String nickname = request.getParameter("nickname");
        logger.info("用户名为:"+nickname+"的用户登录失败");
        response.sendError(HttpStatus.UNAUTHORIZED.value(),"没有登录认证成功");
        ResultDTO resultDTO = ResultDTO.error(new CodeMsg(-1,"密码错误，登录失败"));
        String json = mapper.writeValueAsString(resultDTO);
        response.getWriter().write(json);
        return;

    }


    /**
     * 认证成功处理
     *
     * @param request    HTTP请求
     * @param response   HTTP响应
     * @param chain      过滤器链
     * @param authResult 授权结果
     */
    @Override
    protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                // 将token有效时间为一年
                .setExpiration(new Date(System.currentTimeMillis() + 60L * 60L * 24L * 1000L * 30L * 12L))
                //.signWith(SignatureAlgorithm.HS512, applicationConfigurer.getAuthentication().getKey())
                .signWith(SignatureAlgorithm.HS512, "eartech")
                .compact();
        String userId = authResult.getName();
        User user = userService.getUserById(userId);
        if (user!=null) {
            response.addHeader(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER_TYPE, token));
            response.addHeader("Content-type", "text/json;charset=UTF-8");
            ResultDTO resultDTO = ResultDTO.success(user);
            resultDTO.setMsg("登录成功");
            logger.info("登录成功");
            response.addHeader("Content-type", "text/json;charset=UTF-8");
            String json = mapper.writeValueAsString(resultDTO);
            response.getWriter().write(json);
            return;
        }
    }
}

