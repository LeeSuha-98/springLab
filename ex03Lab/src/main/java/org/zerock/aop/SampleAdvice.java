package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {

  private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);

  //@Before("execution(* org.zerock.service.MessageService*.*(..))")
  public void startLog(JoinPoint jp) {

    logger.info("----------------------------");
    logger.info("----------------------------");
    logger.info(Arrays.toString(jp.getArgs()));

  }
  
  
  @Around("execution(* org.zerock.service.MessageService*.*(..))") //모든 메소드 
  public Object timeLog(ProceedingJoinPoint pjp)throws Throwable{
	long startTime = System.currentTimeMillis();
	logger.info("=============================================");
    logger.info(Arrays.toString(pjp.getArgs())); //메세지vo 객체의 내용을 출력하라.
    
    Object result = pjp.proceed();  //addMessage
    
    long endTime = System.currentTimeMillis();
    logger.info( pjp.getSignature().getName()+ " : " + (endTime - startTime) );
    logger.info("=============================================");
    
    return result;
  }   

}
