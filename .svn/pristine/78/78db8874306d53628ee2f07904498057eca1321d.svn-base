package com.ntels.ccbs.batch.ch.tasklet;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.service.RateInfoService;
import com.ntels.ccbs.batch.ch.entity.NBlchb10m01;
import com.ntels.ccbs.batch.ch.service.NBlchb10m01Service;

/**
 * <PRE>
 * 1. ClassName: NBlchb10m01Writer
 * 2. FileName : NBlchb10m01Writer.java
 * 3. Package  : com.ntels.ccbs.batch.ch.tasklet
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:17:01
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */
@Component
@Scope("step")
public class NBlchb10m01Writer implements ItemWriter<NBlchb10m01>, StepExecutionListener {
	Logger log = LoggerFactory.getLogger(this.getClass());

	String stepName;
	ItemWriter<NBlchb10m01> itemWriter;

	@Autowired
	private NBlchb10m01Service nBlchb10m01Service;
	
	@Autowired
	private RateInfoService rateInfoService;

	List<Object> objectlist = new ArrayList<Object>();
	private Multi multi = new Multi();

	/**
	 * job step이 시작 되기 전 실행
	 * 
	 * @param stepExecution void
	 */
	@BeforeStep
	public void getInterStepData(StepExecution stepExecution) {
		stepName = stepExecution.getStepName();
	}

	@Value("#{jobParameters['logFileName']}") String logFileName;
	@Value("#{jobParameters['clcWrkNo']}")    String clcWrkNo;
	@Value("#{jobParameters['soId']}")        String soId;
	@Value("#{jobParameters['billYymm']}")    String billYymm;
	@Value("#{jobParameters['billCycl']}")    String billCycl;
	@Value("#{jobParameters['pgmId']}")       String pgmId;
	@Value("#{jobParameters['pgmSeq']}")      String pgmSeq;
	@Value("#{jobParameters['grpId']}")       String grpId;
	@Value("#{jobParameters['pgmExeSeqNo']}") String pgmExeSeqNo;

	public void write(List<? extends NBlchb10m01> items) throws Exception {
		objectlist.clear();

		for (NBlchb10m01 listTemp : items) {
			listTemp.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));
			objectlist.add(listTemp);
		} //리스트 FOR

		System.err.println("objectlist = " + objectlist);
		nBlchb10m01Service.saveCharge(objectlist);
	}

	public void beforeStep(StepExecution stepExecution) {
		multi.setSoId(soId);
		multi.setBillYymm(billYymm);
		multi.setClcWrkNo(clcWrkNo);
		multi.setBillCycl(billCycl);
		multi.setMultiCycl(billCycl.substring(0, 1));
		multi.setSeq(billCycl.substring(1, 2));
		multi.setpSeq("2");
		multi.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));

		try {
			multi = rateInfoService.listMulti(multi);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

}