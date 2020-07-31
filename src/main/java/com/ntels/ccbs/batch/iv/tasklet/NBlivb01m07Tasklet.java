package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.service.GenerateBillWorkService;

/**
 * 자동이체 할인 반영 Tasklet
 * @author Cashyalla
 *
 */
@Component("nBlivb01m07Tasklet")
@Scope("step")
public class NBlivb01m07Tasklet extends LazyLoaderLogingTasklet<BillWork, BillWork> {

	@Autowired
	private GenerateBillWorkService generateBillWorkService;
	
	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}
	
	@Override
	protected boolean isUpdatePgmLog() {
		return true;
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
		
		clog.writeLog("NBlivb01m07Tasklet.getLoader billWork\n{}", clog.objectToString(billWork));
		
		return generateBillWorkService.getAutoTransferDiscountInfoList(billWork);
	}

	@Override
	protected BillWork process(BillWork item) {
		item.setRegDate(now());
		item.setChgDate(now());
		item.setSaleTp("01");
		item.setFullPayYn("N");
		return item;
	}

	@Override
	protected void write(List<BillWork> itemList) {
		logger.info("write itemList size : {}", itemList.size());
		int cnt = generateBillWorkService.insertBillWork(itemList);
		logger.info("insert item cnt : {}", cnt);
	}

	@Override
	protected RepeatStatus end() {
		return RepeatStatus.FINISHED;
	}

}
