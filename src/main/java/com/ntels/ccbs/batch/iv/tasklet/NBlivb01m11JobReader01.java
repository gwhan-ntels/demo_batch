package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.NBlivb01m11Service;

@Component("nBlivb01m11JobReader01")
@Scope("step")
public class NBlivb01m11JobReader01 extends CommonItemReader<CBillComm> {

	@Autowired
	private NBlivb01m11Service nBlivb01m11Service;

	/**
	 * <pre>
	 * 스텝의 작업을 수행하기 전 TB_BAT_PGM_LOG에 로그를 남길것인가 여부
	 * 기본적으로 true의 상태이므로 로그를 남기지 말아야 하는 상황이라면
	 * 이 메소드를 상속하여 false를 넘기도록 한다.
	 * </pre>
	 * @return
	 */
	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}
	
	/**
	 * <pre>
	 * 스텝의 작업이 끝난 후 TB_BAT_PGM_LOG에 작업 상태를 변경할것인가 여부
	 * 기본적으로 true의 상태이므로 로그를 변경하지 말아야 하는 상황이라면
	 * 이 메소드를 상속하여 false를 넘기도록 한다.
	 * </pre>
	 * @return
	 */
	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}
	
	/**
	 * <pre>
	 * 스텝이 실행되기 전 TBLIV_BAT_PGM_LOG테이블에 작업 이력을 남긴다.
	 * 만약 같은 파라미터의 작업 내역이 있다면 이전 상태값을 확인하여
	 * 재요청 상태(batProcStat : 5)라면 작업 중(batProcStat : 0)으로 변경하고
	 * 나머지 상태(0, 1, 9)라면 잘못된 접근이므로 예외를 발생시킨다.
	 * </pre> 
	 */
	@Override
	public void beforeStep(StepExecution stepExecution) {
		// 반드시 호출
		super.beforeStep(stepExecution);
		
		// 나머지 작업
	}
	
	/**
	 * 스텝이 종료되면 성공/실패 여부를 기록한다.
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// 반드시 호출
		return super.afterStep(stepExecution);
		
		// 나머지 작업
	}
	
	@Override
	protected LazyLoader<CBillComm> getLoader() {
		
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setPayDueDt(CUtil.utilGetDateTime(2));
		bill.setSoId(soId);
		
		return nBlivb01m11Service.getBillInfoForUnpayTarget(bill);
	}

	@Override
	protected void setItemDefaultValue(CBillComm item) {
		item.setBillYymm(billYymm);
		item.setBillCycl(billCycl);	
	}

}
