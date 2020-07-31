package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.entity.NBlivs02m02;
import com.ntels.ccbs.batch.iv.service.NBlivs02m02Service;

@Component("nBlivs02m02JobWriter02")
public class NBlivs02m02JobWriter02 implements ItemWriter<NBlivs02m02> {

	@Autowired
	private NBlivs02m02Service nBlivs02m02Service;
	
	@Override
	public void write(List<? extends NBlivs02m02> arg0) throws Exception {
		
		List<NBlivs02m02> insertList = new ArrayList<>();
		
		for (NBlivs02m02 nBlivs02m02 : arg0) {
			insertList.add(nBlivs02m02);
		}
		
		nBlivs02m02Service.insertSaleSum(insertList);
		
	}

}
