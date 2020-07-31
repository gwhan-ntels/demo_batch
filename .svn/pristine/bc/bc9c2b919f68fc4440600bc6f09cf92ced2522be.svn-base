package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.tasklet.CommonItemWriter;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPymProcRslt;
import com.ntels.ccbs.batch.iv.service.GenerateSplitPaymentService;

@Component
public class SaveSpltPymProcRstlWriter extends CommonItemWriter<BillSpltPymProcRslt> {

	@Autowired
	private GenerateSplitPaymentService generateSplitPaymentService;
	
	@Override
	public void write(List<? extends BillSpltPymProcRslt> items) throws Exception {
		List<BillSpltPymProcRslt> itemList = getList(items);
		logger.info("write item size : {}", itemList.size());
		int cnt = generateSplitPaymentService.insertSpltPymProcRstl(itemList);
		logger.info("db insert size : {}", cnt);
	}

}
