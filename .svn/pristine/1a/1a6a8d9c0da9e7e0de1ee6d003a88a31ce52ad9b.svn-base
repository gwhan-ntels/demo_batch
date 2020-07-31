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
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.common.entity.RateInfo;
import com.ntels.ccbs.batch.ch.common.service.RateInfoService;
import com.ntels.ccbs.batch.ch.entity.NBlchb08m01;
import com.ntels.ccbs.batch.ch.service.NBlchb08m01Service;
import com.ntels.ccbs.batch.common.CUtil;

/**
 * <PRE>
 * 1. ClassName: NBlchb08m01Writer
 * 2. FileName : NBlchb08m01Writer.java
 * 3. Package  : com.ntels.ccbs.batch.ch.tasklet
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:16:24
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */

@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class NBlchb08m01Writer implements ItemWriter<NBlchb08m01>, StepExecutionListener {
	Logger log = LoggerFactory.getLogger(this.getClass());

	String stepName;
	ItemWriter<NBlchb08m01> itemWriter;

	@Autowired
	private NBlchb08m01Service nBlchb08m01Service;

	@Autowired
	private RateInfoService rateInfoService;

	String prevCtrtId = null;
	String prevProdCmpsId = null;
	String prevActDt = null;
	String prevInactDt = null;
	
	int i = 0;
	int skip = 0;
	double useAmt = 0.0;
	double vat = 0.0;
	double rateVal = 0.0;
	String val1 = null;

	List<RateAttr> listCrateAttr;
	
	List<Object> objectlist = new ArrayList<Object>();
	private Multi multi = new Multi();
	
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

	public void write(List<? extends NBlchb08m01> items) throws Exception {
		objectlist.clear();

		for (NBlchb08m01 list : items) {
			//계약과 상품구성정보가 동일하면 INACT 일자를 변경한다.
			//신규시 전계약 정보 저장
			if (i == 0) {
				prevCtrtId = list.getCtrtId();
				prevProdCmpsId = list.getProdCmpsId();
				prevActDt = list.getActDt();
				prevInactDt = list.getInactDt();
			}
			//최초가 아닌 계약과 상품구성id가 동일한 경우 act, inact를 비교한다.
			if (i != 0 && prevCtrtId.equals(list.getCtrtId()) && prevProdCmpsId.equals(list.getProdCmpsId())) {
				//inact일자는 99991231일 수 있으므로 체크 제외
				if (prevActDt.equals(list.getActDt())) {
					log.debug("동일계약, ACTDT중복" + list.getCtrtId() + "," + list.getActDt());
					skip = 1;
				} else {
					//act가 다르면 기존 act와 현재 inact와 비교하여 작은 값을 inact에 넣는다.
					prevInactDt = list.getInactDt();
					list.setInactDt(CUtil.getCompDate(prevActDt, list.getInactDt(), "MIN"));
					prevActDt = list.getActDt();
				}
				//계약과 상품구성id가 다르면 전 구성정보를 저장을 위해 초기화
			} else {
				prevCtrtId = list.getCtrtId();
				prevProdCmpsId = list.getProdCmpsId();
				prevActDt = list.getActDt();
				prevInactDt = list.getInactDt();
			}

			if (skip == 0) {				
				//Double rateVal = list.getRateVal();
				//int useMonths = list.getUseMonths();
				//CancleCharge = rateVal *(24 - useMonths) /24;

				for (int j = 0; j < listCrateAttr.size(); j++) {
					if (list.getRateItmCd().equals(listCrateAttr.get(j).getRateItmCd())) {
						//VAL1 : AT039	PP00307	위약금 계산 01 일정요율
						val1 = listCrateAttr.get(j).getVal1();
						break;
					}
				}

				rateVal = list.getRateVal();

				if (val1.equals("01") || val1.equals("1")) {
					useAmt = rateVal;
				}

				vat = CUtil.Round(useAmt / 10, 0, 2);

				System.err.println("useAmt = " + useAmt);
				System.err.println("vat    = " + vat);

				list.setUseAmt(useAmt);
				list.setVat(vat);

				list.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));

				if (list.getUseAmt() != 0) {
					objectlist.add(list);
				}
			}
			skip = 0;
			useAmt = 0.0;
			i++;
		}
		System.err.println("objectlist = " + objectlist);
		nBlchb08m01Service.saveJdbcDirect(objectlist);
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
		listCrateAttr = rateInfoService.listCrateAttr(multi);
	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

}