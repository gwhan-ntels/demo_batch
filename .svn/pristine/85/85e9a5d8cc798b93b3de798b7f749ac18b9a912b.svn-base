package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CLog;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.NBlivb01m32Service;

/**
 *
 * ItemReader 샘플.
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: NBliv32m01Reader
 * 2. FileName : NBliv32m01Reader.java
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
public class NBlivb01m32Reader02 extends CommonItemReader<CBillComm>  implements StepExecutionListener {

//	private static final BatchStatus COMPLETED = null;

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/** HistoryService Autowired. */
	@Autowired
	private NBlivb01m32Service clsService;

	@Autowired
	private CommonService commonService;	

	/** 시작여부를 판단하여 최초 데이터 로딩을 처리함. */
	private boolean isStarted = false;

	/** 배치 처리 대상 리스트. */
	LazyLoader<CBillComm> list;

	private Common comm = new Common();

	@Override
		protected boolean isInsertPgmLog() {
			return false;
		}

	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.common.tasklet.CommonItemReader#geLoader()
	 */
	@Override
	protected LazyLoader<CBillComm> getLoader() {
		// TODO Auto-generated method stub
		comm = getCommon();
		
		/*------------------------------------------------------ */
	    //Define Values of Input Param.class
		commonService.commonBillInfo(billYymm, billCycl, soId);
		comm.setExchRateAppDt( commonService.getBillVal(Common.BILL_EXCHRATEDT));
		comm.setBillDt(billYymm + commonService.getBillVal(Common.BILL_BILLDT));
		comm.setPayDueDt(billYymm + commonService.getBillVal(Common.BILL_PAYDUEDT));
		/*------------------------------------------------------ */
		
		return  clsService.listMastJdbcDirect(comm);
	}


	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.common.tasklet.CommonItemReader#setItemDefaultValue(java.lang.Object)
	 */
	@Override
	protected void setItemDefaultValue(CBillComm item) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.common.tasklet.CommonItemReader#lastItem(java.lang.Object)
	 */
	@Override
	protected void lastItem(CBillComm item) {
		// TODO Auto-generated method stub
		
	}
	
}
