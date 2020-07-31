package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.service.BillCustJdbcService;

@Component("nBlivb01m11JobWriter02")
public class NBlivb01m11JobWriter02 implements ItemWriter<BillCust>, StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private BillCustJdbcService billCustJdbcService;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public void write(List<? extends BillCust> billCustList) throws Exception {
		
		clogService.writeLog("MakeBillTargetServiceCompositionJobWriter.write list size : " + billCustList.size());
		
		List<BillCust> list = new ArrayList<>();
		
		for (BillCust billCust : billCustList) {
			list.add(billCust);
		}
		
//		int count = billCustJdbcService.insertBillCustDetail(list);
		
//		clogService.writeLog(count + " records are added to TBLIV_BILL_TGT_SVC_CMPS.");
	}

}
