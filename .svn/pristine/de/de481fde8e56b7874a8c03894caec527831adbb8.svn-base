package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.Sale;
import com.ntels.ccbs.batch.iv.service.NBlivb01m27Service;

@Component("nBlivb01m27JobReader")
@Scope("step")
public class NBlivb01m27JobReader extends CommonItemReader<Sale> {

	@Autowired
	private NBlivb01m27Service nBlivb01m27Service;

	@Value("#{jobParameters['gubun']}")
	private String gubun;

	@Value("#{jobParameters['workYymm']}")
	private String billYymm;

	@Value("#{jobParameters['soId']}")
	private String soId;

	private String grpId;

	@Override
	protected LazyLoader<Sale> getLoader() {

		clogService.writeLog("가생성자료 삭제처리");
		nBlivb01m27Service.deletePrevData(billYymm, soId);

		if ("1".equals(gubun) == true || "01".equals(gubun) == true) {
			grpId = "01";
		} else if ("2".equals(gubun) == true || "02".equals(gubun) == true) {
			grpId = "02";
		}
		return nBlivb01m27Service.querySale(billYymm, soId);
	}

	@Override
	protected void setItemDefaultValue(Sale item) {
		item.setGrpId(grpId);
		item.setAcctTrnsYn("N");
	}

}
