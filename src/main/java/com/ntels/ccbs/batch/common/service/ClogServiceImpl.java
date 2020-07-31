package com.ntels.ccbs.batch.common.service;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.CLog;

@Service
public class ClogServiceImpl implements ClogService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private CLog clsLog;
	
	private String logFileName;
	
	private String processName = "";
	
	/**
	 * XML에서 DI로 로그파일의 이름을 설정함
	 */
	public void setLogFileName(String logFileName) {
		logger.debug("setLogFileName() : " + logFileName);
		this.logFileName = logFileName;
		openLog();
	}
	
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public void openLog() {
		logger.debug("openLog()");

		// 아직 로그파일 이름이 설정되지 않았거나 이미 로그가 열려있는 경우는
		// CLog를 오픈할 수 없다.
		if (logFileName == null || clsLog != null) {
			return;
		}

		clsLog = new CLog();
//		clsLog.OpenLog(logFileName);
	}

	public void writeLog(String message) {
		logger.debug(processName + message);
		
		if (clsLog == null) {
			return;
		}

		writeClog(processName + message);
	}
	
	@Override
	public void writeLog(String format, Object arg) {
		logger.debug(format, arg);
		
		if (clsLog == null) {
			return;
		}
		
		FormattingTuple ft = MessageFormatter.format(format, arg);
		writeClog(ft.getMessage());
	}
	
	@Override
	public void writeLog(String format, Object arg1, Object arg2) {
		logger.debug(format, arg1, arg2);
		
		if (clsLog == null) {
			return;
		}
		
		FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
		writeClog(ft.getMessage());
	}
	
	@Override
	public void writeLog(String format, Object... args) {
		logger.debug(format, args);
		
		if (clsLog == null) {
			return;
		}
		
		FormattingTuple ft = MessageFormatter.format(format, args);
		writeClog(ft.getMessage());
	}
	
	private void writeClog(String message) {
		try {
//			clsLog.WriteLog(message);
		} catch (Exception e) {}		
	}
	
	public void writeAfterStepLog(StepExecution stepExecution) {
		writeLog("Read Count = {}, Read Skip = {}", stepExecution.getReadCount(), stepExecution.getReadSkipCount());
		writeLog("Write = {}, Write Skip = {}", stepExecution.getWriteCount(), stepExecution.getWriteSkipCount());
		writeLog("getCommitCount = {}, getRollbackCount = {}", stepExecution.getCommitCount(), stepExecution.getRollbackCount());
		writeLog("getStepName = {}, getSummary = {}", stepExecution.getStepName(), stepExecution.getSummary());
		writeLog("getId = {}, getJobExecutionId = {}", stepExecution.getId(), stepExecution.getJobExecutionId());
		writeLog("getClass = {}, getExitStatus = {}", stepExecution.getClass(), stepExecution.getExitStatus());
		writeLog("getStartTime = {}, getEndTime = {}", stepExecution.getStartTime(), stepExecution.getEndTime());
		writeLog("getStatus = {}, getEndTime = {}", stepExecution.getStatus(), stepExecution.getFailureExceptions());
	}
	
	@Override
	public void error(Throwable throwable) {
		logger.error(throwable.getMessage(), throwable);
	}
	
	@Override
	public String objectToString(Object arg) {
		return ToStringBuilder.reflectionToString(arg, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public void close() {
		logger.debug("close()");
		
		if (clsLog == null) {
			return;
		}
		
//		clsLog.CloseLog();
	}
	
}
