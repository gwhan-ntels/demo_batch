package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.PrintCustInfo;
import com.ntels.ccbs.batch.iv.service.NBlivb01m17Service;

@Component("nBlivb01m17JobWriter01")
public class NBlivb01m17JobWriter01 implements ItemWriter<PrintCustInfo> {

	@Autowired
	private NBlivb01m17Service nBlivb01m17Service;
	
	@Override
	public void write(List<? extends PrintCustInfo> arg0) throws Exception {
		List<PrintCustInfo> insertList = new ArrayList<>();
		
		for (PrintCustInfo printCustInfo : arg0) {
			insertList.add(printCustInfo);
		}
		
		nBlivb01m17Service.insertPrintCustInfo(insertList);
	}

}
