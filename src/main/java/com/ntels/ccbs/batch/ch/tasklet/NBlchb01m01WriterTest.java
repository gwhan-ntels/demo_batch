package com.ntels.ccbs.batch.ch.tasklet;

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

import com.ntels.ccbs.batch.ch.entity.NBlchb01m01;
import com.ntels.ccbs.batch.ch.service.NBlchb01m01ServiceTest;

/**
 * <PRE>
 * 1. ClassName: NBlchb01m01Writer
 * 2. FileName : NBlchb01m01Writer.java
 * 3. Package  : com.ntels.ccbs.batch.ch.tasklet
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:16:24
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */

@Component
public class NBlchb01m01WriterTest implements ItemWriter<NBlchb01m01>, StepExecutionListener {

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());

	String stepName;
	ItemWriter<NBlchb01m01> itemWriter;
	
	@Autowired
	private NBlchb01m01ServiceTest nBlchb01m01ServiceTest;

	List<Object> Objectlist = new ArrayList<Object>();
	
	/**
	 * job step이 시작 되기 전 실행
	 * 
	 * @param stepExecution void
	 */
	@BeforeStep
	public void getInterStepData(StepExecution stepExecution) {
		stepName = stepExecution.getStepName();
	}

	public void write(List<? extends NBlchb01m01> items) throws Exception {

		System.out.println("Writer Start");
		System.out.println("WRITE LIST = " +  items);
		
		Objectlist.clear();
		
		for(NBlchb01m01 list : items){
//			nBlchb01m01Service.saveJdbcDirect(list);
				Objectlist.add(list);
		}
//		nBlchb01m01Service.saveJdbcDirect(Objectlist);
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