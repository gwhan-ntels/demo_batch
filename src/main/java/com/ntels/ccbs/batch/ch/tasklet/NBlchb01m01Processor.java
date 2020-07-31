package com.ntels.ccbs.batch.ch.tasklet;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.service.RateInfoService;
import com.ntels.ccbs.batch.ch.entity.NBlchb01m01;
import com.ntels.ccbs.batch.common.CUtil;



@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class NBlchb01m01Processor implements ItemProcessor<NBlchb01m01, NBlchb01m01>, StepExecutionListener{

	/** logger **/
	Logger log = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private RateInfoService rateInfoService;
	
	private Multi  multi = new Multi();

	String prevCtrtId = null;
	String prevProd = null;
	String prevActDt = null;
	String prevInactDt = null;
	int i = 0;		
	int skip = 0;

	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		multi.setSoId(soId);
		multi.setMultiCycl(billCycl);
		multi.setBillYymm(billYymm);	
		multi.setClcWrkNo(clcWrkNo);
        multi.setBillCycl(billCycl);
        try {
			multi = rateInfoService.listMulti(multi);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Value("#{jobParameters['logFileName']}") String logFileName;
	@Value("#{jobParameters['clcWrkNo']}") String clcWrkNo;
	@Value("#{jobParameters['soId']}") String soId;
	@Value("#{jobParameters['billYymm']}") String billYymm;
	@Value("#{jobParameters['billCycl']}") String billCycl;
	@Value("#{jobParameters['pSeq']}") String pSeq;
	public NBlchb01m01 process(NBlchb01m01 item) throws Exception {
		//계약과 상품구성정보가 동일하면 INACT 일자를 변경한다.
		//신규시 전계약 정보 저장
		if(i == 0){
			prevCtrtId = item.getCtrtId();
			prevProd = item.getProdCmpsId();
			prevActDt = item.getActDt();
			prevInactDt = item.getInactDt();
		}
		//최초가 아닌 계약과 상품구성id가 동일한 경우 act, inact를 비교한다.
		if(i != 0 && prevCtrtId.equals(item.getCtrtId()) && prevProd.equals(item.getProdCmpsId())){
			//inact일자는 99991231일 수 있으므로 체크 제외
			if(prevActDt.equals(item.getActDt())){
				log.debug("동일계약, ACTDT중복"+item.getCtrtId()+","+item.getActDt());
				skip = 1;
			}else{
				//act가 다르면 기존 act와 현재 inact와 비교하여 작은 값을 inact에 넣는다.
				prevInactDt = item.getInactDt();
				item.setInactDt(CUtil.getCompDate(prevActDt, item.getInactDt() , "MIN"));
				prevActDt = item.getActDt();
				skip = 0;
			}
			i++; 
		//계약과 상품구성id가 다르면 전 구성정보를 저장을 위해 초기화	
		}else{
			prevCtrtId = item.getCtrtId();
			prevProd = item.getProdCmpsId();
			prevActDt = item.getActDt();
			prevInactDt = item.getInactDt();
			skip = 0;
			i++;
		}
		if(skip == 0){
			item.setActDt(CUtil.getCompDate(item.getActDt(), multi.getStartDate(), "MAX"));
			item.setInactDt(CUtil.getCompDate(item.getInactDt(), item.getTermDt(), multi.getEndDate(), "MIN"));
			item.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));
			if(item.getActDt().equals(item.getInactDt())){
				log.debug("ACTDT,INACT 동일일자 SKIP : "+item.getCtrtId()+","+item.getActDt()+","+item.getInactDt());
			}
		} else{
			return null;
		}
		return item;
	} 

}
