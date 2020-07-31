package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.NBlivb01m03Service;

/**
 *
 * ItemReader 샘플.
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: NBliv03m01Reader
 * 2. FileName : NBliv03m01Reader.java
 * 3. Package  : com.ntels.ccbs.batch.sample.tasklet
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:22:46
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 * </PRE>
 */
@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class NBlivb01m03Reader02 extends CommonItemReader<CBillComm> {

	/** HistoryService Autowired. */
	@Autowired
	private NBlivb01m03Service clsService;	
	
	/**
	 *
	 * 데이터 로딩 메소드.
	 * @throws Exception 
	 *
	 * @warning	[Optional]함수의 제약사항이나 주의해야 할 점
	 * @see	[Optional]관련 정보(관련 함수, 관련 모듈)
	 */
	@Autowired
	protected LazyLoader<CBillComm> getLoader () {
		Common comm = getCommon();
		
		clogService.writeLog("NBlivb01m03Reader02.getLoader comm\n{}", clogService.objectToString(comm));
		
		/*------------------------------------------------------ */
	    //Define Values of Input Param.class
		commonService.commonBillInfo(billYymm, billCycl, soId);
		comm.setExchRateAppDt( commonService.getBillVal(Common.BILL_EXCHRATEDT));
		comm.setBillDt(billYymm + commonService.getBillVal(Common.BILL_BILLDT));
		comm.setPayDueDt(billYymm + commonService.getBillVal(Common.BILL_PAYDUEDT));
		try {
			comm.setUseYymm(CUtil.addMonths(billYymm, -1) );
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*------------------------------------------------------ */
		
		 return clsService.listHotBillJdbcDirect(comm);
	}
	
	
	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}
	
}
