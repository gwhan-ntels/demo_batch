package com.ntels.ccbs.batch.common.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.common.CLog;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.CommonService;

public abstract class LazyLoaderLogingTasklet<I, O> extends LazyLoaderTasklet<I, O> implements StepExecutionListener {

	@Autowired
	private CommonService commonService;
	
	protected CLog clslog = new CLog();
	private Common common;
	
	/**
	 * 스텝을 진행하기 전 배치 프로그램 로그를 쌓을것인지의 여부
	 * @return
	 */
	protected abstract boolean isInsertPgmLog();
	
	/**
	 * 스텝 실행이 끝이난 후 배치 프로그램 로그의 정보를 변경할것인지의 여부
	 * @return
	 */
	protected abstract boolean isUpdatePgmLog();
	
	@Override
	public void beforeStep(StepExecution stepExecution) {

		// 배치 실행 로그 생성
		common = new Common();
		common.setLogFile(logFileName);
	    common.setGrpId(grpId);
		common.setBsYymm(workYymm);
		common.setBillYymm(workYymm);
		common.setBatPgmId(pgmId);
		common.setSoId(soId);
		common.setClcWrkNo(clcWrkNo);
		common.setpSeq(pgmSeq);
		common.setPgmExeSeqNo(pgmExeSeqNo);
		common.setBillCycl(billCycl);
		common.setBatProcStat(BAT_PROC_STAT.IN.code());
		common.setLogFilePath(common.LOG_LOCAL);;

		if (isInsertPgmLog() == true) {

			common.setReadCnt(stepExecution.getReadCount());
			common.setWriteCnt(stepExecution.getWriteCount());
			
			// 기존 로그가 존재하면
			// 1. 배치 진행 상태가 재요청일 경우
			//	-> 배치 진행 상태를 작업중으로 변경 후 배치 작업 진행
			// 2. 그 외의 경우
			//	-> 잘못된 접근으로 에러 발생
			if (commonService.batPgmLogCount(common) > 0) {
				String batProcStat = commonService.batProcStat(common);
				
				if (batProcStat.equals(BAT_PROC_STAT.RETRY.code()) == true) {
					commonService.updateBatProcStat(common);	
				} else {
					throw new RuntimeException("잘못된 작업 요청입니다. bat_proc_stat : " + batProcStat);
				}
			} else {
				commonService.commonInsBatPgmLog(common);	
			}
		}
		
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (isUpdatePgmLog() == true) {
			common.setReadCnt(getReadCnt());
			common.setWriteCnt(getWriteCnt());
			common.setErrCnt(0);
			common.setProcCnt(0);
			common.setCmplCnt(0);

			if( stepExecution.getStatus() .toString() == "COMPLETED"){
				common.setBatProcStat(BAT_PROC_STAT.CMPL.code());
				System.out.println("STATUS CHK 정상");
			} else{
				System.out.println("STATUS CHK 비정상");
				common.setBatProcStat(BAT_PROC_STAT.ERR.code());
			}
			
			commonService.commonUpdBatPgmLog(common);
		}
		
		return null;
	}
	
}
