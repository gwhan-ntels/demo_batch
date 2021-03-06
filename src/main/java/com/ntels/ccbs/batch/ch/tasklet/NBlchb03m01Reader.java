package com.ntels.ccbs.batch.ch.tasklet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.service.RateInfoService;
import com.ntels.ccbs.batch.ch.entity.NBlchb03m01;
import com.ntels.ccbs.batch.ch.service.NBlchb03m01Service;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;

/**
 * <PRE>
 * 1. ClassName: NBlchb03m01Reader
 * 2. FileName : NBlchb03m01Reader.java
 * 3. Package  : com.ntels.ccbs.batch.ch.tasklet
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:16:49
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */
@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class NBlchb03m01Reader extends CommonItemReader<NBlchb03m01> implements StepExecutionListener {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NBlchb03m01Service nBlchb03m01Service;

	@Autowired
	private RateInfoService rateInfoService;

	List<NBlchb03m01> list;

	private Multi multi = new Multi();

	public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) {
		super.beforeStep(stepExecution);

		try {
			multi.setSoId(soId);
			multi.setBillYymm(billYymm);
			multi.setClcWrkNo(clcWrkNo);
			multi.setBillCycl(billCycl);
			multi.setMultiCycl(billCycl.substring(0, 1));
			multi.setSeq(billCycl.substring(1, 2));
			multi.setpSeq("2");
			multi.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));

			multi = rateInfoService.listMulti(multi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected LazyLoader<NBlchb03m01> getLoader() {
		return nBlchb03m01Service.listJdbcDirect(multi);
	}

	@Override
	protected void setItemDefaultValue(NBlchb03m01 item) {

	}

	@Override
	protected void lastItem(NBlchb03m01 item) {

	}
}
