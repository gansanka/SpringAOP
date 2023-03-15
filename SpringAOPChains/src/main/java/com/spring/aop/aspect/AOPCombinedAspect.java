package com.spring.aop.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPCombinedAspect {

	@Around("@annotation(AOPExecutionOne) or @annotation(AOPExecutionTwo)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

		AOPExecutionTwo executionTwo;
		boolean execute = false;

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		boolean isExecutionOnePresent = method.isAnnotationPresent(AOPExecutionOne.class);
		boolean isExecutionTwoPresent = method.isAnnotationPresent(AOPExecutionTwo.class);

		if (isExecutionTwoPresent) {
			executionTwo = method.getAnnotation(AOPExecutionTwo.class);
			execute = executionTwo.execute();
		}

		logger.info("Is ExecuteOne Present: {} - Is ExecuteTwo Present: {} : Do Execute {} ", isExecutionOnePresent,
				isExecutionTwoPresent, execute);

		Object output = joinPoint.proceed();
		return output;
	}

}
