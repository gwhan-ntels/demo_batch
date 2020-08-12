package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.service.GenerateBillWorkService;
import com.ntels.ccbs.batch.iv.service.NBlivb01m08Service;

/**
 * 청구 후 요금 조정 Tasklet
 * 
 * @author Cashyalla
 *
 */
@Component("nBlivb01m08Tasklet02")
@Scope("step")
public class NBlivb01m08Tasklet02 extends LazyLoaderLogingTasklet<BillWork, BillWork> {

	@Autowired
	private NBlivb01m08Service nBlivb01m08Service;

	@Autowired
	private CommonService commonService;

	private List<BillWork> adjBillWorkList = new ArrayList<>();
	private List<BillWork> updateBillWorkList = new ArrayList<>();

	@Override
	protected boolean isInsertPgmLog() {
		return false;
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
		billWork.setUseYymm(getUseYymm());

		return nBlivb01m08Service.getAdjInfoAfterBillList(billWork);
	}

	@Override
	protected BillWork process(BillWork item) {
		item.setInptMenuId(pgmId);
		item.setRegrId(pgmId);
		item.setRegDate(now());
		item.setChgrId(pgmId);
		item.setChgDate(now());

		if (StringUtils.isEmpty(item.getBillSeqNo()) == true) {
			String billSeqNo = commonService.getBillSeqNo(item.getBillYymm(), item.getBillCycl(), item.getBillDt(),
					item.getPymAcntId(), "00");
			item.setBillSeqNo(billSeqNo);
		}

		BillWork adjBill = nBlivb01m08Service.getBillWorkForAdj(item);

		if (adjBill == null) {
			adjBillWorkList.add(item);
		} else {
			updateBillWorkList.add(item);
		}

		return item;
	}

	@Override
	protected void write(List<BillWork> itemList) {
		if (adjBillWorkList.isEmpty() == false) {
			nBlivb01m08Service.insertBillWork(adjBillWorkList);
			adjBillWorkList.clear();
		}

		if (updateBillWorkList.isEmpty() == false) {
			nBlivb01m08Service.updateAdjAfterBill(updateBillWorkList);
			updateBillWorkList.clear();
		}

		nBlivb01m08Service.updateAdjAply(itemList);
	}

	@Override
	protected RepeatStatus end() {
		return RepeatStatus.FINISHED;
	}

}
