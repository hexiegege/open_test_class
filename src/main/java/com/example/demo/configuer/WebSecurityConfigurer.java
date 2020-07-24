package com.example.demo.configuer;

import com.example.demo.security.CommonAccessDeniedHandler;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtLoginFilter;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Web安全配置
 * TODO 使用springboot security必须实现UserDetailsService并重写接口下的方法
 * 本项目UserServiceImpl实现了该接口
 * @author shanfa
 */
@Configuration
//开启spring方法级的角色权限认证
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    //用户详情服务，注入UserDetailsServiceImpl
    @Autowired
    UserDetailsService userDetailsService;
    //密码编码器，注入bCrypt算法的密码编码器
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    CommonAccessDeniedHandler commonAccessDeniedHandler;


    /**
     * 必须override此方法，并添加@Bean，为解决Bean注入不明
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        return super.userDetailsService();
    }

    /**
     * 必须override此方法，并添加@Bean,为解决password grant_type
     *
     * @return AuthenticationManager的一个实例
     * @throws Exception 异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置验证使用的用户详情服务和密码编码器
     *
     * @param auth AuthenticationManagerBuilder的实例
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * 配置HTTP安全
     *
     * @param http HTTP上下文中的HttpSecurity实例
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置跨域资源共享（ CORS ）
        http.cors()
        //禁用CSRF支持，使用WebSecurityConfigurerAdapter时，默认启用
        // 通过配置AccessDeniedHandler使用不同方式处理不合法的CsrfTokenException，我们可以替换spring security crsf protection默认的http 403 access denied处理方式。
                .and().csrf().disable()
         // 定制我们自己的 session 策略 基于token，所以不需要session
                .sessionManagement()
        //调整为让 Spring Security 不创建和使用 session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //允许基于使用HttpServletRequest限制访问
                .and().authorizeRequests()
                // 忽略 login
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                //忽略Swagger UI和Actuator
                .antMatchers(HttpMethod.GET,
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/druid/**",
                        "/h2-console/**",
                        "/actuator/**").permitAll()
                //允许配置错误处理 配置权限不足
                .and().exceptionHandling().accessDeniedHandler(commonAccessDeniedHandler)
                 // 添加过滤器 登录 拦截/login 访问路径
                .and().addFilter(new JwtLoginFilter(authenticationManager(),userService, getApplicationContext()))
                 // 添加过滤器 验证授权
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userService, getApplicationContext()));
        // 禁用缓存
        http.headers().cacheControl();
        // 修改 Spring Securiy 对 iframe/frame 的安全策略，
        http.headers().frameOptions().sameOrigin();
    }
}
