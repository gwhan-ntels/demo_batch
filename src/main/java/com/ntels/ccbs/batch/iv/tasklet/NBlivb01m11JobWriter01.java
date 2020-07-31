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
import com.ntels.ccbs.batch.up.entity.UpymObj;
import com.ntels.ccbs.batch.up.unpay.service.UnpayService;

@Component("nBlivb01m11JobWriter01")
public class NBlivb01m11JobWriter01 implements ItemWriter<UpymObj>, StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private UnpayService unpayService;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public void write(List<? extends UpymObj> upymObjList) throws Exception {
		
		List<UpymObj> list = new ArrayList<>();
		
		for (UpymObj upymObj : upymObjList) {
			list.add(upymObj);
		}
		
		int count = unpayService.insertUnpaymentObj(list);
		clogService.writeLog(String.format("[Insert Success: TBLUP_UPYM_OBJ(Insert) [%d]]", count));
	}

}
