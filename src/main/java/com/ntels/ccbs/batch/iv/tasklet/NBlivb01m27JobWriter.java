package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.Sale;
import com.ntels.ccbs.batch.iv.service.NBlivb01m27Service;

@Component("nBlivb01m27JobWriter")
@Scope("step")
public class NBlivb01m27JobWriter implements ItemWriter<Sale> {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private NBlivb01m27Service nBlivb01m27Service;
	
	@Value("#{jobParameters['gubun']}")
	private String gubun;

	@Override
	public void write(List<? extends Sale> saleList) throws Exception {
		
		List<Sale> insertList = new ArrayList<>();
		
		for (Sale sale : saleList) {
			insertList.add(sale);
		}
		
		int insertCnt = 0;
		
		if ("1".equals(gubun) == true || "01".equals(gubun) == true) {
			insertCnt = nBlivb01m27Service.insertSaleTemp(insertList);
		} else {
			insertCnt = nBlivb01m27Service.insertSale(insertList);
		}
		
		clogService.writeLog("NBlivb01m27JobWriter INSERT COUHT : " + insertCnt);
		
	}

}
