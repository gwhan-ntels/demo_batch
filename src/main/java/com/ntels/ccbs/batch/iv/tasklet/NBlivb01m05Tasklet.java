package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.Arrears;
import com.ntels.ccbs.batch.iv.service.ArrearsService;

@Component("nBlivb01m05Tasklet")
@Scope("step")
public class NBlivb01m05Tasklet extends LazyLoaderLogingTasklet<Arrears, Arrears> {

	@Autowired
	private ArrearsService arrearsService;

	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}
	
	@Override
	protected boolean isUpdatePgmLog() {
		return true;
	}
	
	@Override
	public Arrears process(Arrears item) {
		
		item.setRegDate(now());
		item.setUpayAmt(item.getBillAmt());
		item.setBillAplyYn("N");
		
		return item;
	}

	@Override
	public void write(List<Arrears> itemList) {
		clog.writeLog("itenList size : {}", itemList.size());
		int cnt = arrearsService.insertArrs(itemList);
		clog.writeLog("insert cnt : {}", cnt);
	}

	@Override
	protected LazyLoader<Arrears> getLoader() throws Exception {
		Arrears arrears = new Arrears();
		arrears.setSoId(soId);
		arrears.setBillYymm(billYymm);
		arrears.setBillCycl(billCycl);
		arrears.setBillDt(getBillDt());
		
		arrears.setBefBillYymm(getUseYymm());
		arrears.setBefPayDueDt(arrears.getBefBillYymm() + CUtil.getLastDay(arrears.getBefBillYymm()));
		
		arrears.setChAppYn("Y");
		arrears.setChAppYymm(billYymm);
		arrears.setChDelayImposeYn("Y");
		arrears.setpSeq(pgmSeq);
		
		clog.writeLog("NBlivb01m05Tasklet.getLoader arrears\n{}", clog.objectToString(arrears));
		
		return arrearsService.getArrearsInfoList(arrears);
	}

	@Override
	protected RepeatStatus end() {
		return RepeatStatus.FINISHED;
	}

}
