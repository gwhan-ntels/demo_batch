package com.ntels.ccbs.batch.up.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.common.entity.UpymBigBsAmt;
import com.ntels.ccbs.batch.up.common.service.UpBaseService;
import com.ntels.ccbs.batch.up.dao.NBlupb01m02Dao;
import com.ntels.ccbs.batch.up.entity.CtrtDetail;
import com.ntels.ccbs.batch.up.entity.DebtTgtPsn;
import com.ntels.ccbs.batch.up.entity.NBlupb01m02;
import com.ntels.ccbs.batch.up.entity.UpymCtrtMngTp;
import com.ntels.ccbs.batch.up.entity.UpymDetail;
import com.ntels.ccbs.batch.up.entity.UpymEntrustMast;

@Service
public class NBlupb01m02ServiceImpl extends UpBaseService implements NBlupb01m02Service {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NBlupb01m02Dao nBlupb01m02Dao;
	
	@Override
	public int getUpymJobDtlCount(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.getUpymJobDtlCount(nBlupb01m02);
	}
	
	@Override
	public int insertUpymJobDtl(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.insertUpymJobDtl(nBlupb01m02);
	}
	
	@Override
	public UpymBigBsAmt getUpymBigBsAtm(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.getUpymBigBsAmt(nBlupb01m02);
	}
	
	@Override
	public int checkClaimEnd(String inDate, String soId) {
		return nBlupb01m02Dao.checkClaimEnd(inDate, soId);
	}
	
	@Override
	public int insertUpymJobMst(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.insertUpymJobMst(nBlupb01m02);
	}
	
	@Override
	public LazyLoader<UpymDetail> getUpymDetailList(NBlupb01m02 nBlupb01m02) {
		
		UpymBigBsAmt upymBigBsAmt = getUpymBigBsAtm(nBlupb01m02);

		nBlupb01m02.setUpymAmtFrom(upymBigBsAmt.getUpymAmtFrom());
		nBlupb01m02.setUpymAmtTo(upymBigBsAmt.getUpymAmtTo());
		
		return nBlupb01m02Dao.getUpymDtlList(nBlupb01m02);
	}
	
	@Override
	public int insertUpymDtl(UpymDetail upymDetail) {
		return nBlupb01m02Dao.insertUpymDtl(upymDetail);
	}
	
	@Override
	public int insertUpymDtl(List<UpymDetail> upymDetailList) {
		return nBlupb01m02Dao.insertUpymDtl(upymDetailList);
	}
	
	@Override
	public LazyLoader<UpymDetail> getUpymList(NBlupb01m02 nBlupb01m02) {
		
		UpymBigBsAmt upymBigBsAmt = getUpymBigBsAtm(nBlupb01m02);
		
		nBlupb01m02.setUpymAmtFrom(upymBigBsAmt.getUpymAmtFrom());
		nBlupb01m02.setUpymAmtTo(upymBigBsAmt.getUpymAmtTo());
		
		return nBlupb01m02Dao.getUpymList(nBlupb01m02);
	}
	
	@Override
	public int insertUpym(UpymDetail upymDetail) {
		return nBlupb01m02Dao.insertUpym(upymDetail);
	}
	
	@Override
	public int insertUpym(List<UpymDetail> upymDetailList) {
		return nBlupb01m02Dao.insertUpym(upymDetailList);
	}
	
	@Override
	public LazyLoader<CtrtDetail> getUpymCtrtDetail(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.getUpymCtrtDetail(nBlupb01m02);
	}

	@Override
	public int insertCtrtDetail(CtrtDetail ctrtDetail) {
		return nBlupb01m02Dao.insertUpymCtrt(ctrtDetail);
	}
	
	@Override
	public int insertCtrtDetail(List<CtrtDetail> ctrtDetailList) {
		return nBlupb01m02Dao.insertUpymCtrt(ctrtDetailList);
	}
	
	@Override
	public LazyLoader<UpymEntrustMast> getUpymEntrustMastList(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.getUpymEntrustMastList(nBlupb01m02);
	}
	
	@Override
	public int insertUpymEntrustMast(UpymEntrustMast upymEntrustMast) {
		return nBlupb01m02Dao.insertUpymEntrustMast(upymEntrustMast);
	}
	
	@Override
	public int insertUpymEntrustMast(List<UpymEntrustMast> upymEntrustMastList) {
		return nBlupb01m02Dao.insertUpymEntrustMast(upymEntrustMastList);
	}
	
	@Override
	public int updateEntrustMast(String upym_mng_tp, Timestamp now, String so_id, String in_date) {
		return nBlupb01m02Dao.updateEntrustMast(upym_mng_tp, now, so_id, in_date);
	}
	
	@Override
	public LazyLoader<AuthChgAppl> getAuthChgApplList(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.getAuthChgApplList(nBlupb01m02);
	}
	
	@Override
	public int insertAuthChgAppl(AuthChgAppl authChgAppl) {
		return nBlupb01m02Dao.insertAuthChgAppl(authChgAppl);
	}
	
	@Override
	public int insertAuthChgAppl(List<AuthChgAppl> authChgApplList) {
		return nBlupb01m02Dao.insertAuthChgAppl(authChgApplList);
	}
	
	@Override
	public LazyLoader<DebtTgtPsn> getDebtTgtPsnList(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.getDebtTgtPsnList(nBlupb01m02);
	}
	
	@Override
	public int insertDebtTgtPsn(DebtTgtPsn debtTgtPsn) {
		return nBlupb01m02Dao.insertDebtTgtPsn(debtTgtPsn);
	}
	
	@Override
	public int insertDebtTgtPsn(List<DebtTgtPsn> debtTgtPsnList) {
		return nBlupb01m02Dao.insertDebtTgtPsn(debtTgtPsnList);
	}
	
	@Override
	public String getUpymMngTp(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.getUpymMngTp(nBlupb01m02.getSoId(), nBlupb01m02.getInDate());
	}
	
	@Override
	public LazyLoader<UpymCtrtMngTp> getUpymCtrtMngTpList(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Dao.getUpymCtrtMngTpList(nBlupb01m02);
	}
	
	@Override
	public int insertUpymCtrtMngTp(UpymCtrtMngTp upymCtrtMngTp) {
		return nBlupb01m02Dao.insertUpymCtrtMngTp(upymCtrtMngTp);
	}
	
	@Override
	public int insertUpymCtrtMngTp(List<UpymCtrtMngTp> upymCtrtMngTpList) {
		return nBlupb01m02Dao.insertUpymCtrtMngTp(upymCtrtMngTpList);
	}

	@Override
	public int monthsBetween(String startDate, String endDate) {
		
		// 기존에는 둘 중 하나가 비어있다면 예외를 발생시켰으나
		// 경우에 따라 하나의 값이 비어있는 상황이 발생할 수 있다.
		// 그런 경우 두 날짜 사이의 개월수를 0으로 반환한다.
		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
			return 0;
		}
		
		if (startDate.length() < 4 || endDate.length() < 4) {
			throw new RuntimeException("startDate, endDate's length must longer than 4.");
		}
		
		if (startDate.length() != endDate.length()) {
			throw new RuntimeException("startDate's length and endDate's length must be same.");
		}
		
		int strtYear;
		int strtMonth;
		
		int endYear;
		int endMonth;
		
		if (startDate.length() == 6) {
			strtYear = Integer.parseInt(startDate.substring(0,4));
			strtMonth = Integer.parseInt(startDate.substring(4,6));
		} else if (startDate.length() == 4) {
			strtYear = Integer.parseInt(startDate.substring(0,2));
			strtMonth = Integer.parseInt(startDate.substring(2,4));			
		} else {
			throw new RuntimeException("startDate's length must be 4 or 6 ex) 1604 or 201604");
		}
		
		if (endDate.length() == 6) {
			endYear = Integer.parseInt(endDate.substring(0,4));
			endMonth = Integer.parseInt(endDate.substring(4,6));
		} else if (endDate.length() == 4) {
			endYear = Integer.parseInt(endDate.substring(0,2));
			endMonth = Integer.parseInt(endDate.substring(2,4));			
		} else {
			throw new RuntimeException("endDate length must be 4 or 6 ex) 1604 or 201604");
		}
		 
		if (strtYear > endYear) {
			// 시작연도가 종료연도보다 클 경우 두 날짜를 바꾼다.
			int temp = strtYear;
			strtYear = endYear;
			endYear = temp;
			
			temp = strtMonth;
			strtMonth = endMonth;
			endMonth = temp;
		} else if (strtYear == endYear && strtMonth > endMonth) {
			// 시작연도와 종료연도가 같지만 시작 월이 종료 월보다 크다면 두 날짜를 바꾼다.
			int temp = strtMonth;
			strtMonth = endMonth;
			endMonth = temp;
		}

		int months = (endYear - strtYear)* 12 + (endMonth - strtMonth);
		
		return months;
	}
	
}
