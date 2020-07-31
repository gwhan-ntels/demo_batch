package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.entity.NBlivs01m02;
import com.ntels.ccbs.batch.iv.service.NBlivs01m02Service;

@Component("nBlivs01m02JobReader02")
@Scope("step")
public class NBlivs01m02JobReader02 extends CommonItemReader<NBlivs01m02> {

	@Autowired
	private NBlivs01m02Service nBlivs01m02Service;
	
	private String procDt;

	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}
	
	@Override
	protected LazyLoader<NBlivs01m02> getLoader() {
		
		procDt = CUtil.utilGetDateTime(2);
		
		NBlivs01m02 nBlivs01m02 = new NBlivs01m02();
		nBlivs01m02.setBillYymm(billYymm);
		nBlivs01m02.setSoId(soId);
		
		return nBlivs01m02Service.getBillSum(nBlivs01m02);
	}
	
	@Override
	protected void setItemDefaultValue(NBlivs01m02 item) {
		item.setAreaDeptCd(" ");
		item.setProcDt(procDt);
	}

}
