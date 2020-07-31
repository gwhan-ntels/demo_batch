package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.BillingUtilService;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.common.service.CBillService;
import com.ntels.ccbs.batch.iv.service.GenerateBillWorkService;

@Component("nBlivb01m02Tasket")
@Scope("step")
public class NBlivb01m02Tasket extends LazyLoaderLogingTasklet<BillWork, BillWork> {

	@Autowired
	private GenerateBillWorkService generateBillWorkService;
	
	@Autowired
	private BillingUtilService billingUtilService;
	
	@Autowired
	private CBillService cBillService;
	
	private BillWork billWork;
	
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

		billWork = new BillWork();
		billWork.setSoId(soId);
		billWork.setClcWrkNo(clcWrkNo);
		billWork.setBillYymm(billYymm);
		billWork.setBillCycl(billCycl);
		billWork.setGrpId(grpId);
		billWork.setpSeq(pgmSeq);

		billWork.setExchRateAppDt(getExchRateAplyDtStp());
		billWork.setBillDt(getBillDt());
		billWork.setPayDueDt(getPayDueDt());
		billWork.setUseYymm(getUseYymm());

		return generateBillWorkService.getChInfoListForBillWorkLazy(billWork);
	}

	@Override
	protected BillWork process(BillWork item) {
		
		if (StringUtils.isEmpty(item.getBillDt()) == true) {
			item.setBillDt(billWork.getBillDt());
		}
		
		// 청구번호 생성
		clog.writeLog("NBlivb01m02Tasketl.process item\n{}", clog.objectToString(item));
		String billSeqNo = cBillService.getBillSeqNo(item.getBillYymm(), item.getBillCycl(), item.getBillDt(), item.getPymAcntId(), "00");
		
//		// 금액 조정(
		double billAmt = item.getBillAmt();
//		// 원화로 계산한 금액
		double wonBillAmt = 0;
		wonBillAmt = CUtil.Round(billAmt * item.getExrate(),0,3);
		
		item.setBillSeqNo(billSeqNo);
		item.setAdjPrvBillAmt(item.getBillAmt());
		item.setAdjAmt(0.0);
		item.setRcptAmt(0.0);
		item.setFullPayYn("N");
		item.setWonBillAmt(wonBillAmt);
		item.setRegDate(now());
		item.setRegrId(user);
		item.setChgDate(now());
		item.setChgrId(user);
		
		return item;
	}

	@Override
	protected void write(List<BillWork> itemList) {
		logger.info("write list size : {}", itemList.size());
		generateBillWorkService.insertBillWork(itemList);
		for (BillWork billWork : itemList) {
			logger.info("\nBILL_WORK\n{}", ToStringBuilder.reflectionToString(billWork, ToStringStyle.MULTI_LINE_STYLE));
		}
	}

	@Override
	protected RepeatStatus end() {
		return RepeatStatus.FINISHED;
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		super.beforeStep(stepExecution);
		
		// 나머지 작업
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return super.afterStep(stepExecution);
		
		// 나머지 작업
	}

}
