package com.ntels.ccbs.batch.up.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.up.service.NBlupb01m02Service;

public class NBlupb01m02CommonWriter implements StepExecutionListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NBlupb01m02Service nBlupb01m02Service;
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		logger.debug(getClass().getSimpleName() + ".afterStep");
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		logger.debug(getClass().getSimpleName() + ".beforeStep");
	}
	
	protected<T> List<T> getObjectList(List<? extends T> list) {
		
		List<T> objectList = new ArrayList<>();
		
		for (T obj : list) {
			objectList.add(obj);
		}
		
		return objectList;
	}
	
	protected NBlupb01m02Service getService() {
		return nBlupb01m02Service;
	}

}
