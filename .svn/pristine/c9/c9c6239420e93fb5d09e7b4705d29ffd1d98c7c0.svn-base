package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.service.BillCustJdbcService;
import com.ntels.ccbs.batch.iv.service.NBlivb01m10Service;

@Component("nBlivb01m10Tasklet02")
@Scope("step")
public class NBlivb01m10Tasklet02 extends LazyLoaderLogingTasklet<BillCust, BillCust> {

	@Autowired
	private NBlivb01m10Service nBlivb01m10JdbcService;
	
	@Autowired
	private BillCustJdbcService billCustJdbcService;
	
	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return true;
	}

	@Override
	protected LazyLoader<BillCust> getLoader() throws Exception {
		BillCust billCust = new BillCust();
		billCust.setSoId(soId);
		billCust.setpSeq(pgmSeq);

		return nBlivb01m10JdbcService.getBillTgtSvcCmpsTargetInfo(billCust);
	}

	@Override
	protected BillCust process(BillCust item) {
		return item;
	}

	@Override
	protected void write(List<BillCust> itemList) {
		billCustJdbcService.insertBillCustDetail(itemList);
	}

	@Override
	protected RepeatStatus end() {
		return null;
	}

}
