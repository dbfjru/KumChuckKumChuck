package com.example.sparta.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerfMeasureAOP {

  @Pointcut("@within(MyPerfMeasure) || @annotation(MyPerfMeasure)")
  public void perfPoint() {
  }

  @Around("perfPoint()")
  public void perfMeasureAOP(ProceedingJoinPoint pjp) {
    long begin = System.currentTimeMillis();
    System.out.println("시간 측정 시작");
    try {
      pjp.proceed();
    } catch (Throwable t) {
      System.out.println("예외 발생 : " + t.getMessage());
    }
    long time = System.currentTimeMillis() - begin;
    System.out.println("소요시간 : " + time);
  }
}
