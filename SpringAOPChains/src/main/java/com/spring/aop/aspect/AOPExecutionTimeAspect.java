package com.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AOPExecutionTimeAspect {

	@Around("@annotation(AOPExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		long start = System.currentTimeMillis();
		String methodName = joinPoint.getSignature().toShortString();
		Object output = joinPoint.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		logger.info("{} - Time taken by method: {} : {} milliseconds", joinPoint.getTarget().getClass().getName(),
				methodName, elapsedTime);
		return output;
	}

}
