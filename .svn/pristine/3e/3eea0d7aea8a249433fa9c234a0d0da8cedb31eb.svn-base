package com.ntels.ccbs.batch.up.tasklet;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.up.common.service.UpCommonService;
import com.ntels.ccbs.batch.up.entity.NBlupb01m02;
import com.ntels.ccbs.batch.up.service.NBlupb01m02Service;

public abstract class NBlupb01m02CommonReader<T> extends CommonItemReader<T> implements StepExecutionListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NBlupb01m02Service nBlupb01m02Service;
	
	@Autowired
	private UpCommonService upCommonService;
	
	@Autowired
	private ClogService clogService;
	
	@Value("#{jobParameters['logFileName']}") 
	private String logFileName;
	
	@Value("#{jobParameters['in_date']}")
	protected String in_date;
	
	@Value("#{jobParameters['so_id']}")
	protected String so_id;
	
	@Value("#{jobParameters['regr_id']}")
	protected String regr_id;

	@Value("#{jobParameters['pSeq']}")
	protected String pSeq;
	
	/**
	 * 해당 스텝의 작업이 이미 완료되었는가를 판단한다.
	 */
	private boolean isAlreadyProcessed;
	
	protected Timestamp now = new Timestamp(new Date().getTime());
	
	/**
	 * 해당 스텝의 이름
	 * @return
	 */
	abstract String getJobName();
	
	/**
	 * 해당 스탭의 프로세스 상태값.
	 * @return
	 */
	abstract String getProcStat();
	
	/**
	 * 해당 스텝의 프로세스 시퀀스 번호를 반환한다.
	 * @return
	 */
	abstract int getProcSeqNo();
	
	/**
	 * <pre>
	 * 반환 값이 true라면 beforeStep에서 TBLUP_UPYM_JOB_DTL테이블에 작업 내역을 저장을 하고,
	 * afterStep에서 작업 성공/실패 여부를 갱신하게 된다.
	 * 반환 값이 false라면 위의 작업을 건너뛴다.
	 * </pre>
	 * @return
	 */
	abstract boolean isHaveToSaveJobDtl();
	
	/**
	 * 스텝을 시작할 때 로그를 남기기위한 메시지
	 * @return
	 */
	abstract String getJobStartMessage();
	
	/**
	 * 스텝이 실패했을 때의 로그를 남기기위한 메시지
	 * @return
	 */
	abstract String getJobErrorMessage();
	
	public void setAlreadyProcessed(boolean isAlreadyProcessed) {
		this.isAlreadyProcessed = isAlreadyProcessed;
	}	
	
	public boolean isAlreadyProcessed() {
		return isAlreadyProcessed;
	}
	
	protected NBlupb01m02Service getService() {
		return nBlupb01m02Service;
	}
	
	protected Logger getLogger() {
		return logger;
	}
	
	/**
	 * NBlupb02m02의 모든 스텝들이 공통으로 사용하는 객체를 생성하여 반환한다.
	 * @return
	 */
	protected NBlupb01m02 getNBlupb01m02() {
		
		NBlupb01m02 nBlupb01m02 = new NBlupb01m02();
		nBlupb01m02.setInDate(in_date);
		nBlupb01m02.setSoId(so_id);
		nBlupb01m02.setProcStat(getProcStat());
		nBlupb01m02.setProcSeqNo(getProcSeqNo());
		nBlupb01m02.setRegrId(regr_id);
		nBlupb01m02.setChgrId(regr_id);

		nBlupb01m02.setRegDate(now);
		nBlupb01m02.setChgDate(now);
		nBlupb01m02.setStartTime(now);
		nBlupb01m02.setEndTime(now);
		nBlupb01m02.setpSeq(pSeq);
		
		return nBlupb01m02;
	}
	
	@Override
	public ExitStatus afterStep(StepExecution execution) {
		logger.debug(getClass().getSimpleName() + ".afterStep isHaveToSaveJobDtl : " + isHaveToSaveJobDtl());
			
		String errCode = "0001";
		String errMsg = "Unknown";
		
		String exitMessage = null;
		
		if (execution.getFailureExceptions().isEmpty() == true) {
			// 아무 이상없이 스텝이 성공하였다.
			errCode = "0000";
			errMsg = "Success";
			
			exitMessage = String.format("[%s][Oracle SUCCESS: TBLUP_UPYM_DTL(Insert [%d])]", getJobName(), execution.getCommitCount()); 
		} else {
			SQLException sqlException = null;
			
			// 데이터베이스 에러를 기록하기위해 던져진 예외 중 SQLException이 있는지 검색한다.
			for (Throwable throwable : execution.getFailureExceptions()) {
				Throwable t = findSQLException(throwable);
				if (t != null && t instanceof SQLException) {
					sqlException = (SQLException) t;
					break;
				}
			}

			try {
				
				if (sqlException != null) {
					errCode = Integer.toString(sqlException.getErrorCode());
					errMsg = sqlException.getMessage();
					
					String failureMessage = String.format("[%s][Oracle Error: TBLUP_UPYM_DTL(Insert [%s])(%s)]", getJobName(), errCode, errMsg);
					clogService.writeLog(failureMessage);
					
					exitMessage = failureMessage + " " + getJobErrorMessage();
					
				} else {
					exitMessage = getJobErrorMessage();
					clogService.writeLog(exitMessage);
				}

			} catch (Exception e) {

			}
			
		}
		
		clogService.writeLog(exitMessage);
		
		if (isHaveToSaveJobDtl() == true) {
			NBlupb01m02 nBlupb01m02 = getNBlupb01m02();
			nBlupb01m02.setEndTime(new Timestamp(new Date().getTime()));
			nBlupb01m02.setDbCnt(execution.getCommitCount());
			nBlupb01m02.setErrorCode(errCode);
			nBlupb01m02.setErrorMsg(errMsg);
			
			upCommonService.updateUpymJobDtl(nBlupb01m02);	
		}
		
		clogService.close();
		
		return new ExitStatus(exitMessage);
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		clogService.setLogFileName(logFileName);
		clogService.setProcessName(getJobName());
 
		try {
		
			if (isHaveToSaveJobDtl() == true) {
				logger.debug("[TBLUP_UPYM_DTL] Select Start....");
				int count = getService().getUpymJobDtlCount(getNBlupb01m02());
				
				if (count > 0) {
					logger.debug(String.format("[%s] [TBLUP_UPYM_DTL This work has already been processed.]", getJobName()));
					clogService.writeLog(String.format("[%s] [TBLUP_UPYM_DTL This work has already been processed.]", getJobName()));
					setAlreadyProcessed(true);
				} else {
					setAlreadyProcessed(false);
					getService().insertUpymJobDtl(getNBlupb01m02());
				}
			}

			clogService.writeLog("==============================================================");
			clogService.writeLog(getJobStartMessage());
			clogService.writeLog("==============================================================");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Throwable findSQLException(Throwable src) {
		if (src instanceof SQLException) {
			return src;
		}
		
		if (src.getCause() == null) {
			return null;
		}
		
		if (src.getCause() instanceof SQLException) {
			return src.getCause();
		} else {
			return findSQLException(src.getCause());
		}
	}
	
}
