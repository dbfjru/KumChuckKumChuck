package com.example.sparta.global.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class logAOP {

  @Pointcut("@within(MyLogging) || @annotation(MyLogging)")
  private void loggingPoint() {
  }

  @Around("loggingPoint()")
  public void loggingAOP(ProceedingJoinPoint pjp) {
    //실행되는 함수 이름을 가져오고 출력
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    Method method = methodSignature.getMethod();
    System.out.println(method.getName() + " 메서드 실행");

    // 메소드 실행
    try {
      pjp.proceed();
    } catch (Throwable e) {
      System.out.println("Logging 실패 : " + e.getMessage());
    }
    
    System.out.println(method.getName() + " 메서드 종료");
  }
}
