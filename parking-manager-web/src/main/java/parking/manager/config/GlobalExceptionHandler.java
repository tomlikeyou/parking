package parking.manager.config;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import parking.common.AjaxResultBuilder;
import parking.common.ResultCode;

/**
 * @Author: huang
 * @Date: created in 2020-04-23 20:56
 * @Description: 全局异常处理器，处理shiro注解抛出的权限不足异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuth() {
        return AjaxResultBuilder.build(ResultCode.UNAUTH, ResultCode.findMessageByCode(ResultCode.UNAUTH), null);
    }
}
