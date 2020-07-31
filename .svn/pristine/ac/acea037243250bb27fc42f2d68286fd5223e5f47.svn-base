package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.service.BillCustJdbcService;

@Component("nBlivb01m11JobWriter03")
public class NBlivb01m11JobWriter03 implements ItemWriter<BillCust>, StepExecutionListener {

	@Autowired
	private BillCustJdbcService billCustJdbcService;
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		
	}

	@Override
	public void write(List<? extends BillCust> arg0) throws Exception {
		
		List<BillCust> list = new ArrayList<>();
		
		for (BillCust billCust : arg0) {
			list.add(billCust);
		}
		
		billCustJdbcService.insertBillCust(list);
		
	}

}
