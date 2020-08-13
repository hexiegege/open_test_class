package com.example.demo.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.dto.CodeMsg;
import com.example.demo.dto.ResultDTO;
import javax.servlet.http.HttpServletRequest;

/**
 * @author shanfa
 * @Desc 全局异常处理
 * @date 2020/7/14
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResultDTO accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException e){
        log.error(CodeMsg.FORBIDDEN.getMsg()+ " "+e.toString());
        return ResultDTO.error(CodeMsg.FORBIDDEN);
    }


    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public ResultDTO exceptionHandler( HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是: {}",e.toString());
        return ResultDTO.error(CodeMsg.NULL_POINT);
    }

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    public ResultDTO commonExceptionHandler( HttpServletRequest req, CommonException e){
        log.error("发生业务异常！错误信息：{},{}",e.getErrorCode(),e.getErrorMsg());
        return ResultDTO.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResultDTO exceptionHandler( HttpServletRequest req, Exception e){
        log.error("未知异常！原因是: {}",e.toString());
        return ResultDTO.error(CodeMsg.INTERNAL_SERVER_ERROR);
    }

}
