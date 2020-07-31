package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.service.CBLService;
import com.ntels.ccbs.batch.iv.service.NBlivb01m11Service;

@Component("nBlivb01m11JobReader03")
@Scope("step")
public class NBlivb01m11JobReader03 extends CommonItemReader<BillCust> {

	@Autowired
	private NBlivb01m11Service nBlivb01m11Service;
	
	@Autowired
	private CBLService cblService;
	
	private String billDt;
	private String payDueDt;
	
	private Timestamp now;
	
	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}

	@Override
	protected LazyLoader<BillCust> getLoader() {
		
		now = new Timestamp(new Date().getTime());
		
		BillCyclStp billCyclStp = new BillCyclStp();
		billCyclStp.setBillCycl(billCycl);
		billCyclStp.setBillYymm(billYymm);
		billCyclStp.setSoId(soId);
		
		billDt = cblService.getCyclBillDt(billCyclStp);
		payDueDt = cblService.getCyclPayDueDt(billCyclStp);
		
		CBillComm searchBill = new CBillComm();
		searchBill.setBillYymm(billYymm);
		searchBill.setSoId(soId);
		try {
			searchBill.setBefore2Yymm(CUtil.addMonths(billYymm, -2));	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return nBlivb01m11Service.getBillTargetCust(searchBill);
	}

	@Override
	protected void setItemDefaultValue(BillCust item) {
		item.setBillYymm(billYymm);
		item.setBillCycl("01");
		item.setBillDt(billDt);
		item.setPayDueDt(payDueDt);
		item.setBillFlCrtYn("N");
		item.setSmlAmtYn("N");
		item.setRegDate(now);
		item.setChgDate(now);
	}

}
