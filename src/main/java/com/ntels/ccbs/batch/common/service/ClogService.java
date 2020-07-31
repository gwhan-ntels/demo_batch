package com.ntels.ccbs.batch.common.service;

import org.springframework.batch.core.StepExecution;

public interface ClogService {
	
	void setLogFileName(String logFileName);
	
	void setProcessName(String processName);
	
	void writeLog(String message);
	
	void writeLog(String format, Object arg);
	
	void writeLog(String format, Object arg1, Object arg2);
	
	void writeLog(String format, Object... args);
	
	void writeAfterStepLog(StepExecution stepExecution);
	
	void error(Throwable throwable);
	
	String objectToString(Object arg);
	
	void close();
	
}
