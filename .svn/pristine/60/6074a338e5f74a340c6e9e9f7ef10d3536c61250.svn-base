package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.entity.NBlivs02m02;
import com.ntels.ccbs.batch.iv.service.NBlivs02m02Service;

@Component("nBlivs02m02JobReader02")
@Scope("step")
public class NBlivs02m02JobReader02 extends CommonItemReader<NBlivs02m02> {

	@Autowired
	private NBlivs02m02Service nBlivs02m02Service;
	
	@Value("#{jobParameters['billYymm']}")
	private String billYymm;
	
	@Value("#{jobParameters['soId']}")
	private String soId;
	
	private String useYymm;
	
	private String procDt;
	
	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}

	@Override
	protected LazyLoader<NBlivs02m02> getLoader() {
		
		procDt = CUtil.utilGetDateTime(2);
		
		try {
			useYymm = CUtil.addMonths(billYymm, -1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		NBlivs02m02 nBlivs02m02 = new NBlivs02m02();
		nBlivs02m02.setUseYymm(useYymm);
		nBlivs02m02.setSoId(soId);
		
		return nBlivs02m02Service.getSaleSum(nBlivs02m02);
	}
	
	@Override
	protected void setItemDefaultValue(NBlivs02m02 item) {
		item.setProcDt(procDt);
	}

}
