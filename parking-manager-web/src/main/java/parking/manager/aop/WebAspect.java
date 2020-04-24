package parking.manager.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Author: huang
 * Date: created in 2020/3/24 21:01
 * Description:
 */
@Component
@Aspect
public class WebAspect {

    private Logger logger = LoggerFactory.getLogger(WebAspect.class);

    @Pointcut(value = "execution(public  * parking.manager.web.*.*(..))")
    public void webLog() {
    }

//    /**
//     * 权限注解
//     */
//    @Pointcut(value = "@annotation(parking.manager.config.RequirePermission)")
//    public void permission() {
//    }
//
//    /**
//     * @param joinPoint
//     * @param requirePermission
//     * @return
//     * @throws Throwable
//     */
//    @Around(value = "permission()&&@annotation(requirePermission)")
//    public Object aroundAdvice(ProceedingJoinPoint joinPoint, RequirePermission requirePermission) throws Throwable {
//        System.out.println("环绕通知执行之前");
//        String permissionName = requirePermission.permissionName();
//        if (UserUtil.hasPermission(permissionName)) {
//            Object r = joinPoint.proceed();
//            System.out.println("环绕通知执行之后");
//            return r;
//        } else {
//            return AjaxResultBuilder.build(ResultCode.NOPERMS, ResultCode.findMessageByCode(ResultCode.NOPERMS), null);
//        }
//    }

    /**
     * @param joinPoint 切入点
     * @throws Throwable
     */
    @Before(value = "webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL:" + request.getRequestURL());
        logger.info("HTTP_METHOD : " + request.getMethod());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            logger.info("name:{" + name + "},value:{" + request.getParameter(name) + "}");
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

}
