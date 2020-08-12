package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.service.NBlivb01m08Service;

/**
 * 청구 전 요금 조정 Tasklet
 * 
 * @author Cashyalla
 *
 */
@Component("nBlivb01m08Tasklet01")
@Scope("step")
public class NBlivb01m08Tasklet01 extends LazyLoaderLogingTasklet<BillWork, BillWork> {

	@Autowired
	private NBlivb01m08Service nBlivb01m08Service;

	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}

	@Override
	protected LazyLoader<BillWork> getLoader() throws Exception {

		BillWork billWork = new BillWork();
		billWork.setSoId(soId);
		billWork.setBillYymm(billYymm);
		billWork.setBillCycl(billCycl);
		billWork.setClcWrkNo(clcWrkNo);
		billWork.setpSeq(pgmSeq);

		billWork.setExchRateAppDt(getExchRateAplyDt());
		billWork.setBillDt(getBillDt());
		billWork.setPayDueDt(getPayDueDt());
		billWork.setUseYymm(getUseYymm());

		return nBlivb01m08Service.getAdjInfoBeforeBillList(billWork);
	}

	@Override
	protected BillWork process(BillWork item) {
		item.setInptMenuId(pgmId);
		item.setChgrId(pgmId);
		item.setRegDate(now());
		item.setChgDate(now());
		return item;
	}

	@Override
	protected void write(List<BillWork> itemList) {
		int cnt = nBlivb01m08Service.updateAplyAdjBeforeBill(itemList);

		if (cnt > 0) {
			nBlivb01m08Service.updateAdjAply(itemList);
		}
	}

	@Override
	protected RepeatStatus end() {
		return RepeatStatus.FINISHED;
	}

}
