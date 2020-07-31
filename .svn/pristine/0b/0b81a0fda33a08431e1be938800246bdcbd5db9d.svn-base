package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.service.ArrearsService;
import com.ntels.ccbs.batch.iv.service.GenerateBillWorkService;

/**
 * 연체료내역반영 Tasklet
 * @author Cashyalla
 *
 */
@Component("nBlivb01m06Tasklet")
@Scope("step")
public class NBlivb01m06Tasklet extends LazyLoaderLogingTasklet<BillWork, BillWork> {

	@Autowired
	private ArrearsService arrearsService;
	
	@Autowired
	private CommonService commonService;
	
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
		billWork.setpSeq(pgmSeq);
		billWork.setBillDt(getBillDt());
		billWork.setPayDueDt(getPayDueDt());
		
		clog.writeLog("NBlivb01m06Tasklet.getLoader billWork\n{}", clog.objectToString(billWork));

		return arrearsService.getArrsAplyInfoList(billWork);
	}
	
	@Override
	public BillWork process(BillWork item) {
		
		String billSeqNo = commonService.getBillSeqNo(item.getBillYymm(), item.getBillCycl(), item.getBillDt(), item.getPymAcntId(), "00");
		
		item.setOldBillSeqNo(item.getBillSeqNo());
		item.setBillSeqNo(billSeqNo);
		item.setSaleTp("01");
		item.setFullPayYn("N");
		
		return item;
	}
	
	@Override
	public void write(List<BillWork> itemList) {
		int cnt = arrearsService.updateArrsInfo(itemList);
		
		if (cnt > 0) {
			generateBillWorkService.insertBillWork(itemList);
		}
	}
	
	@Override
	protected RepeatStatus end() {
		return RepeatStatus.FINISHED;
	}

}
