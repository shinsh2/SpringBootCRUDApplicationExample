package com.websystique.springboot.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	public static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@AfterReturning("execution(* com..*Controller.*(..)) || execution(* com..*ServiceImpl.*(..))")
	public void logServiceAccess(JoinPoint joinPoint){
	
		StringBuffer result = new StringBuffer("");
		
//		result += "<br>*****<br>";
		result.append(joinPoint.getTarget().getClass().getName())
		.append(".")
		.append(joinPoint.getSignature().getName())
		.append("(");
		
		int index = joinPoint.getArgs().length;
		for( Object o : joinPoint.getArgs() ){
			
			result.append(o);
			if( --index != 0 ){
				result.append(", ");
			}
			
		}
		result.append(")");
		
		result.append("Completed:").append(joinPoint);
//		result += "*****<br>";		
		
		logger.debug(result.toString());
	}
}