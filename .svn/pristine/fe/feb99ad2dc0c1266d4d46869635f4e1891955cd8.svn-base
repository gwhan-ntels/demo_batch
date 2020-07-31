package com.ntels.ccbs.batch.iv.tasklet;

import java.text.ParseException;
import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.entity.NBlivs02m02;
import com.ntels.ccbs.batch.iv.service.NBlivs02m02Service;

@Component("nBlivs02m02JobTasklet01")
@Scope("step")
public class NBlivs02m02JobTasklet01 extends LazyLoaderLogingTasklet<Object, Object> {

	@Autowired
	private NBlivs02m02Service nBlivs02m02Service;
	
	@Value("#{jobParameters['billYymm']}")
	private String billYymm;
	
	@Value("#{jobParameters['soId']}")
	private String soId;
	
	private String useYymm;

	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}

	@Override
	protected LazyLoader<Object> getLoader() throws Exception {
		return null;
	}

	@Override
	protected Object process(Object item) {
		return null;
	}

	@Override
	protected void write(List<Object> itemList) {
		
	}

	@Override
	protected RepeatStatus end() {
		try {
			useYymm = CUtil.addMonths(billYymm, -1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		NBlivs02m02 nBlivs02m02 = new NBlivs02m02();
		nBlivs02m02.setUseYymm(useYymm);
		nBlivs02m02.setSoId(soId);
		
		nBlivs02m02Service.deleteSaleSum(nBlivs02m02);
		
		return null;
	}

}
