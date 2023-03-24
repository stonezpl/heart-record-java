package com.stonezpl.hr.log;

import cn.hutool.json.JSONUtil;
import com.stonezpl.hr.util.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author stonezpl
 * @Description 日志切面记录
 * @date 2023/3/23 21:36
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(public * com.stonezpl.hr.controller.*.*(..))")
    public void log() {

    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = doBefore(proceedingJoinPoint);
        Object result = null;
        try {

            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            // 打印出参
            sb.append("Response Args  : ").append(JSONUtil.toJsonStr(result)).append("\n");
            // 执行耗时
            sb.append("Time-Consuming : ").append(System.currentTimeMillis() - startTime).append("ms").append("\n");
            sb.append("========= End ==========").append("\n");
            log.info(sb.toString());
        }
        return result;
    }

    public StringBuilder doBefore(ProceedingJoinPoint proceedingJoinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("========= Start ========").append("\n");
        // 打印请求 url
        sb.append("URL            : ").append(request.getRequestURL().toString()).append("\n");
        // 打印 Http method
        sb.append("HTTP Method    : ").append(request.getMethod()).append("\n");
        // 打印请求的 IP
        sb.append("IP             : ").append(RequestUtil.getRealIp(request)).append("\n");
        // 打印请求入参
        sb.append("Request Args   : ").append(JSONUtil.toJsonStr(proceedingJoinPoint.getArgs())).append("\n");

        return sb;
    }

}
