package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.entity.NBlivs01m02;
import com.ntels.ccbs.batch.iv.service.NBlivs01m02Service;

@Component("nBlivs01m02JobWriter02")
public class NBlivs01m02JobWriter02 implements ItemWriter<NBlivs01m02> {

	@Autowired
	private NBlivs01m02Service nBlivs01m02Service;
	
	@Override
	public void write(List<? extends NBlivs01m02> arg0) throws Exception {
		
		List<NBlivs01m02> insertList = new ArrayList<>();
		
		for (NBlivs01m02 nBlivs01m02 : arg0) {
			insertList.add(nBlivs01m02);
		}
		
		nBlivs01m02Service.insertBillSum(insertList);
		
	}

}
