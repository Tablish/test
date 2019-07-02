package com.qyc.config;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author qianyongchao
 * @Description
 * @Date 2019/5/10 13:33
 * @Modified By
 */
@Aspect
@Component
@Log4j
public class AopConfig {

    public static long startTime;
    public static long endTime;

    //切点
    @Pointcut("execution(public * com.qyc.controller.*.*(..))")
    public void print() {
        System.out.println("我是切点啊。。");
    }

    /*@Before注解表示在具体的方法之前执行*/
    @Before("print()")
    public void before(JoinPoint joinPoint) {
        System.out.println("-------------------------");
        System.out.println("前置切面..");
        startTime = System.currentTimeMillis();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        String requestMethod = request.getMethod();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("请求url " + requestURI + ",请求ip " + remoteAddr + ",请求方法 " + requestMethod + ",请求的类名" + declaringTypeName + ",方法名" + methodName + ",参数名" + args);
    }

    /*@After注解表示在方法执行之后执行*/
//    @After("print()")
//    public void after() {
//        System.out.println("-------------------------");
//        endTime = System.currentTimeMillis() - startTime;
//        System.out.println("后置切面..");
//    }

    /*@AfterReturning注解用于获取方法的返回值*/
    @AfterReturning(pointcut = "print()", returning = "object")
    public void getAfterReturn(Object object) {
        System.out.println("-------------------------");
        System.out.println("AfterReturning..");
        System.out.println("本次接口耗时 " + endTime);
        //System.out.println("afterReturning={}" + object.toString());
    }


    @Around("print()")
    public Object test(ProceedingJoinPoint proceeding) {
        System.out.println("-------------------------");
        System.out.println("环绕切面..");
        Object o = null;
        try {
            //执行
            o = proceeding.proceed(proceeding.getArgs());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return o;
    }
}