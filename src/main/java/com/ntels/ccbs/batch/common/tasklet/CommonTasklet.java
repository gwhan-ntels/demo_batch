package com.ntels.ccbs.batch.common.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.service.CBLService;

public abstract class CommonTasklet extends BaseService implements Tasklet {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public enum BAT_PROC_STAT {
		IN("0"), CMPL("1"), RETRY("5"), ERR("9");
		
		private String code;
		
		BAT_PROC_STAT(String code) {
			this.code = code;
		}
		
		public String code() {
			return code;
		}
	}
	
	@Autowired
	protected ClogService clog;
	
	@Autowired
	protected CBLService cblService;
	
	@Value("${fetchSize}")
	private int fetchSize;

	@Value("#{jobParameters['clcWrkNo']}")
	protected String clcWrkNo;

	@Value("#{jobParameters['soId']}")
	protected String soId;

	@Value("#{jobParameters['billYymm']}")
	protected String billYymm;

	@Value("#{jobParameters['billCycl']}")
	protected String billCycl;

	@Value("#{jobParameters['pgmId']}")
	protected String pgmId;

	@Value("#{jobParameters['pgmSeq']}")
	protected String pgmSeq;

	@Value("#{jobParameters['grpId']}")
	protected String grpId;

	@Value("#{jobParameters['pgmExeSeqNo']}")
	protected String pgmExeSeqNo;

	@Value("#{jobParameters['user']}")
	protected String user;

	@Value("#{jobParameters['workYymm']}")
	protected String workYymm;

	@Value("#{jobParameters['logFileName']}")
	protected String logFileName;
	
	@Value("${interval}")
	protected int interval;
	
	private BillCyclStp billCyclStp;

	private boolean initialized = false;
	
	protected void init() {
		
		billCyclStp = new BillCyclStp();
		
		billCyclStp.setBillCycl(billCycl);
		billCyclStp.setBillYymm(billYymm);
		billCyclStp.setRegrId(user);
		billCyclStp.setSoId(soId);
		
		initialized = true;
	}
	
	private void checkInit() {
		if (initialized == false) {
			throw new RuntimeException("init()메소드를 먼저 호출해주세요.");
		}
	}
	
	protected BillCyclStp getBillCyclStp() {
		return this.billCyclStp;
	}
	
	protected String getExchRateAplyDt() {
		checkInit();
		return cblService.getExrateAplyDt();
	}
	
	protected String getExchRateAplyDtStp() {
		checkInit();
		return cblService.getCyclExrateAplyDt(billCyclStp);
	}
	
	
	protected String getBillDt() {
		checkInit();
		return cblService.getCyclBillDt(billCyclStp);
	}
	
	protected String getPayDueDt() {
		checkInit();
		return cblService.getCyclPayDueDt(billCyclStp);
	}
	
	protected String getUseYymm() {
		
		String useYymm;
		
		try {
			useYymm = CUtil.addMonths(billYymm, -1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return useYymm;
	}
	
	protected String getRemark() {
		checkInit();
		return cblService.getRemark(billCyclStp);
	}
	
	protected String getTaxIssDt() {
		checkInit();
		return cblService.getTaxIssDt(billCyclStp);
	}
	
}
