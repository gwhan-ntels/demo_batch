package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.iv.service.NBlivb01m15Service;

@Component("nBlivb01m15JobReader")
@Scope("step")
public class NBlivb01m15JobReader extends CommonItemReader<OverpayBillAply> {

	@Autowired
	private NBlivb01m15Service nBlivb01m15Service;

	@Override
	protected LazyLoader<OverpayBillAply> getLoader() {
		
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setBillCycl(billCycl);
		bill.setSoId(soId);
		bill.setpSeq(pgmSeq);
		
		return nBlivb01m15Service.getOverpayBillAplyList(bill);
	}

}
