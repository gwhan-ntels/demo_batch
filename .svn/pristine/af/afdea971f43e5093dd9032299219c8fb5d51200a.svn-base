package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.entity.NBlivs01m02;
import com.ntels.ccbs.batch.iv.service.NBlivs01m02Service;

@Component("nBlivs01m02JobTasklet01")
@Scope("step")
public class NBlivs01m02JobTasklet01 extends LazyLoaderLogingTasklet<Object, Object> {

	@Autowired
	private NBlivs01m02Service nBlivs01m02Service;

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
		NBlivs01m02 nBlivs01m02 = new NBlivs01m02();
		nBlivs01m02.setBillYymm(billYymm);
		nBlivs01m02.setSoId(soId);
		
		nBlivs01m02Service.deleteBillSum(nBlivs01m02);
		return null;
	}

}
