package com.ntels.ccbs.batch.common.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public abstract class CommonItemWriter<T> implements ItemWriter<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected List<T> getList(List<? extends T> items) {
		List<T> list = new ArrayList<>();
		
		for (T item : items) {
			list.add(item);
		}
		
		return list;
	}
	
}
