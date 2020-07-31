package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.NBlivb01m11Service;

@Component("nBlivb01m11JobReader02")
@Scope("step")
public class NBlivb01m11JobReader02 extends CommonItemReader<BillCust> {
	
	@Autowired
	private NBlivb01m11Service nBlivb01m11Service;

	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}
	
	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}
	
	@Override
	protected LazyLoader<BillCust> getLoader() {
		
		CBillComm searchBill = new CBillComm();
		searchBill.setBillYymm(billYymm);
		searchBill.setSoId(soId);
		
		return nBlivb01m11Service.getBillTargetServiceComposition(searchBill);
	}

	@Override
	protected void setItemDefaultValue(BillCust item) {
		item.setBillYymm(billYymm);
		item.setRegDate(new Timestamp(new Date().getTime()));
	}

}
