package com.ntels.ccbs.batch.up.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.service.NBlupb02m08Service;

@Component(value="nBlupb02m08Writer")
public class NBlupb02m08Writer implements ItemWriter<AuthChgAppl>, StepExecutionListener {

	@Autowired
	private NBlupb02m08Service nBlupb02m08Service;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public void write(List<? extends AuthChgAppl> authChgApplList) throws Exception {
		
		List<AuthChgAppl> list = new ArrayList<>();
		
		for (AuthChgAppl authChgAppl : authChgApplList) {
			list.add(authChgAppl);
		}
		
		nBlupb02m08Service.insertAuthChgAppl(list);
		
	}

}
