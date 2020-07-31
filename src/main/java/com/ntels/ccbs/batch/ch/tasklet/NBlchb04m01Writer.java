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
import com.ntels.ccbs.batch.ch.entity.NBlchb04m01;
import com.ntels.ccbs.batch.ch.service.NBlchb04m01Service;
import com.ntels.ccbs.batch.common.CUtil;

/**
 * <PRE>
 * 1. ClassName: NBlchb04m01Writer
 * 2. FileName : NBlchb04m01Writer.java
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
public class NBlchb04m01Writer implements ItemWriter<NBlchb04m01>, StepExecutionListener {
	Logger log = LoggerFactory.getLogger(this.getClass());

	String stepName;
	ItemWriter<NBlchb04m01> itemWriter;

	@Autowired
	private NBlchb04m01Service nBlchb04m01Service;

	@Autowired
	private RateInfoService rateInfoService;

	String prevCtrtId = null;
	String prevProdCmpsId = null;
	String prevActDt = null;
	String prevInactDt = null;
	String chkMultiStDt = null;
	int i = 0;
	int skip = 0;
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

	public void write(List<? extends NBlchb04m01> items) throws Exception {
		objectlist.clear();

		for (NBlchb04m01 list : items) {
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
				String useYYmm = list.getUseYymm();

				Multi prevMulti = new Multi();
				CUtil.copyObjectValue(multi, prevMulti);

				while (CUtil.getIntCompdate(list.getUseEdDate(), prevMulti.getEndDate()) < 0) {

					NBlchb04m01 prevList = new NBlchb04m01();
					CUtil.copyObjectValue(list, prevList);

					chkMultiStDt = CUtil.getCompDate(prevMulti.getStartDate(), list.getUseEdDate(), "MAX");
					prevList.setActDt(CUtil.getCompDate(list.getActDt(), chkMultiStDt, "MAX"));

					prevList.setInactDt(CUtil.getCompDate(list.getInactDt(), prevMulti.getEndDate(), "MIN"));

					prevList.setUseYymm(useYYmm);
					prevList.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));

					if (prevList.getActDt().equals(prevList.getInactDt())) {
						log.debug("ACTDT,INACT 동일일자 SKIP : " + prevList.getCtrtId() + "," + prevList.getActDt() + "," + prevList.getInactDt());
					} else if (!CUtil.isExistBetweenDays(prevMulti.getStartDate(), prevMulti.getEndDate(), prevList.getActDt())) { // 당납으로 인해 추가 
						log.debug("ACTDT가 멀티 주기안에 존재하지 않아  SKIP : " + prevList.getCtrtId() + "," + prevMulti.getStartDate() + "," + prevMulti.getEndDate() + "," + prevList.getActDt());
					} else {
						objectlist.add(prevList);
					}

					//기존 청구년월일 이전의 경우에 대해서는 이전 해당 청구년월일을 가져와서 사용기간을 구한다.
					prevMulti.setBillYymm(CUtil.addMonths(prevMulti.getBillYymm(), -1)); //원 청구년월의 기간별 이전 청구년월로 셋팅한다.
					prevMulti = rateInfoService.listMulti(prevMulti);

					useYYmm = CUtil.addMonths(prevList.getUseYymm(), -1);
				}
			}
			skip = 0;
			i++;
		}
		System.err.println("objectlist = " + objectlist);
		nBlchb04m01Service.saveJdbcDirect(objectlist);
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