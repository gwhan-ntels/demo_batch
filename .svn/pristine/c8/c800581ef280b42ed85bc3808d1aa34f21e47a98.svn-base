package com.ntels.ccbs.batch.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

public class JdbcConnectionAdvice implements MethodInterceptor {

	@Autowired
	private JdbcConnectionManager jdbcConnectionManager;
	
	private String[] excludeRules = {
			".*?ClogService.*?"
			, ".*?CommonService.*?"
			, ".*?CommService.*?"
	};
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		
		return methodInvocation.proceed();
//		for (String rule : excludeRules) {
//			if (methodInvocation.getThis().getClass().getName().matches(rule) == true) {
//				return methodInvocation.proceed();
//			}
//		}
//		
//		boolean isNewOpen = jdbcConnectionManager.open();
//		
//		Object returnObject = null;
//		
//		try {
//			returnObject = methodInvocation.proceed();
//			
//			// 이 메소드에서 새로열린 커넥션이라면 닫아준다.
//			if (isNewOpen == true) {
//				jdbcConnectionManager.closeAndCommit();
//			}
//		} catch (Throwable t) {
//			// 예외가 발생하면 롤백한다.
//			if (isNewOpen == true) {
//				jdbcConnectionManager.closeAndRollback();
//			}
//			throw t;
//		}
//		
//		return returnObject;
	}

}
