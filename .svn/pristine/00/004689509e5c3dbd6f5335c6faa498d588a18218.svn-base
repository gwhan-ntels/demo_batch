package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.NBlivb01m32Service;

/**
 *
 * ItemWriter 샘플.
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: NBliv32m01Writer
 * 2. FileName : NBliv32m01Writer.java
 * 3. Package  : com.ntels.ccbs.batch.sample.tasklet
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:24:00
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 * </PRE>
 */

@Component
public class NBlivb01m32Writer01 implements ItemWriter<CBillComm>, StepExecutionListener {

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());

	String stepName;
	ItemWriter<CBillComm> itemWriter;
	

	long j = 0;
	long i = 0;
	List<Object> Objectlist = new ArrayList<Object>();
	
	@Autowired
	private NBlivb01m32Service clsService;

	/**
	 * job step이 시작 되기 전 실행
	 * 
	 * @param stepExecution void
	 */
	@BeforeStep
	public void getInterStepData(StepExecution stepExecution) {
		stepName = stepExecution.getStepName();
	}

//	public void write(List<? extends NBliv32m01> lists) throws Exception {
	public void write(List<? extends CBillComm> items) throws Exception {


		Objectlist.clear();
		
		if ( i == 0 ) System.out.println("[Write Start :" + CUtil.utilGetDateTime(4));
		
		for(CBillComm list : items){
			i++;
			j++;
			
			list.setTimeInfo();
			if(list.getBillAmt() == 0 ) list.setFullPayYn("Y");
			Objectlist.add(list);
		}
		
		clsService.saveJdbcDirect(Objectlist);
		if ( i == 10000) {
			System.out.println("================================================================");
			System.out.println("Write ");
			System.out.println("[" + j + ":" + CUtil.utilGetDateTime(4));
			System.out.println("================================================================");
			i = 0;
		}
	}

	public void beforeStep(StepExecution stepExecution) {
		
		System.out.println("Read Count = "+stepExecution.getReadCount() + " Read Skip =" + stepExecution.getReadSkipCount());
		System.out.println("Write ="+ stepExecution.getWriteCount() + " Write Skip ="+ stepExecution.getWriteSkipCount());
		System.out.println("getCommitCount ="+ stepExecution.getCommitCount() + " getRollbackCount ="+ stepExecution.getRollbackCount());
		System.out.println("getStepName ="+ stepExecution.getStepName() + " getSummary ="+ stepExecution.getSummary());
		System.out.println("getId ="+ stepExecution.getId() + " getJobExecutionId ="+ stepExecution.getJobExecutionId());
		System.out.println("getClass ="+ stepExecution.getClass() + " getExitStatus ="+ stepExecution.getExitStatus());
		System.out.println("getStartTime ="+ stepExecution.getStartTime() + " getEndTime ="+ stepExecution.getEndTime());
		System.out.println("getStatus ="+ stepExecution.getStatus() + " getEndTime ="+ stepExecution.getFailureExceptions());

	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("Read Count = "+stepExecution.getReadCount() + " Read Skip =" + stepExecution.getReadSkipCount());
		System.out.println("Write ="+ stepExecution.getWriteCount() + " Write Skip ="+ stepExecution.getWriteSkipCount());
		System.out.println("getCommitCount ="+ stepExecution.getCommitCount() + " getRollbackCount ="+ stepExecution.getRollbackCount());
		System.out.println("getStepName ="+ stepExecution.getStepName() + " getSummary ="+ stepExecution.getSummary());
		System.out.println("getId ="+ stepExecution.getId() + " getJobExecutionId ="+ stepExecution.getJobExecutionId());
		System.out.println("getClass ="+ stepExecution.getClass() + " getExitStatus ="+ stepExecution.getExitStatus());
		System.out.println("getStartTime ="+ stepExecution.getStartTime() + " getEndTime ="+ stepExecution.getEndTime());
		System.out.println("getStatus ="+ stepExecution.getStatus() + " getEndTime ="+ stepExecution.getFailureExceptions());

		return null;
	}

}