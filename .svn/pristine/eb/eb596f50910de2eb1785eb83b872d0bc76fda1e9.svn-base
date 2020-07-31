package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;
import com.ntels.ccbs.batch.iv.common.tasklet.BillCommonItemReader;
import com.ntels.ccbs.batch.iv.service.GenerateSplitPaymentService;

/**
 * NBlivb01m04 두번째 스텝
 * 분할납부계정 처리 결과 저장하기위한 데이터 조회
 * @author Cashyalla
 *
 */
@Component
@Scope("step")
public class SaveSpltPymProcRstlReader extends BillCommonItemReader<BillSpltPym> {

	@Autowired
	private GenerateSplitPaymentService generateSplitPaymentService;
	
	@Value("#{jobParameters['logFileName']}")
	private String logFileName;
	@Value("#{jobParameters['clcWrkNo']}")
	private String clcWrkNo;
	@Value("#{jobParameters['pgmId']}")
	private String pgmId;
	@Value("#{jobParameters['pgmSeq']}")
	private String pgmSeq;
	@Value("#{jobParameters['grpId']}")
	private String grpId;
	@Value("#{jobParameters['pgmExeSeqNo']}")
	private String pgmExeSeqNo;
	
	private BillSpltPym billSpltPym;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		super.beforeStep(stepExecution);
		
		billSpltPym = new BillSpltPym();
		billSpltPym.setSoId(soId);
		billSpltPym.setBillYymm(billYymm);
		billSpltPym.setBillCycl(billCycl);
		billSpltPym.setpSeq(pgmSeq);
		billSpltPym.setBillDt(billYymm + getBillDt());
	}
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		return null;
	}

	@Override
	protected LazyLoader<BillSpltPym> getLoader() throws Exception {
		return generateSplitPaymentService.getSpltPymResultList(billSpltPym);
	}

}
