package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.PrintCt;
import com.ntels.ccbs.batch.iv.service.NBlivb01m17Service;

@Component("nBlivb01m17JobWriter02")
public class NBlivb01m17JobWriter02 implements ItemWriter<PrintCt> {

	@Autowired
	private NBlivb01m17Service nBlivb01m17Service;
	
	@Override
	public void write(List<? extends PrintCt> arg0) throws Exception {
		List<PrintCt> insertList = new ArrayList<>();
		
		for (PrintCt printCt : arg0) {
			insertList.add(printCt);
		}
		
		nBlivb01m17Service.insertPrintCt(insertList);
	}

}
