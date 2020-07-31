package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.service.CBLService;
import com.ntels.ccbs.batch.iv.common.service.CBillService;
import com.ntels.ccbs.batch.up.entity.UpymObj;

@Component("nBlivb01m11JobProcessor01")
@Scope("step")
public class NBlivb01m11JobProcessor01 implements ItemProcessor<CBillComm, UpymObj>, StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private CBLService cblService;
	
	@Autowired
	private CBillService cbillService;
	
	@Value("#{jobParameters['soId']}")
	private String soId;
	
	@Value("#{jobParameters['user']}")
	private String user;
	
	@Value("#{jobParameters['billSeqNo']}")
	private String billSeqNo;
	
	@Value("#{jobParameters['workYymm']}")
	private String billYymm;
	
	@Value("#{jobParameters['jobBase']}")
	private String jobBase;
	
	private BillCyclStp billCyclStp;
	
	private String billDt;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		clogService.writeLog("MakeUnpayContentsJobProcessor.afterStep");
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		clogService.writeLog("MakeUnpayContentsJobProcessor.beforeStep");
		billCyclStp = new BillCyclStp();
		billCyclStp.setBillCycl(jobBase);
		billCyclStp.setBillYymm(billYymm);
		billCyclStp.setRegrId(user);
		billCyclStp.setSoId(soId);

		// 청구일자 조회
		clogService.writeLog("Search the billing date.");
		billDt = cblService.getCyclBillDt(billCyclStp);
	}

	@Override
	public UpymObj process(CBillComm bill) throws Exception {
		
		UpymObj upymObj = new UpymObj();
		
		CUtil.copyObjectValue(bill, upymObj);
		upymObj.setUpymCrtYymm(bill.getBillYymm());
		upymObj.setUpymCrtCycl(bill.getBillCycl());
		upymObj.setUpymCrtDt(billDt);
		upymObj.setUpymAmt(bill.getBillAmt() - bill.getRcptAmt());
		
		String billSeqNo = cbillService.getBillSeqNo(billYymm, jobBase, billDt, bill.getPymAcntId(), "00");
		upymObj.setThsBillSeqNo(billSeqNo);
		upymObj.setRegDate(new Timestamp(new Date().getTime()));

		return upymObj;
	}

}
