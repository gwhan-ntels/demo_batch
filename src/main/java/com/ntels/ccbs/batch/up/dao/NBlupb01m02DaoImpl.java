package com.ntels.ccbs.batch.up.dao;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.common.entity.UpymBigBsAmt;
import com.ntels.ccbs.batch.up.dao.mapper.NBlupb01m02Mapper;
import com.ntels.ccbs.batch.up.entity.CtrtDetail;
import com.ntels.ccbs.batch.up.entity.DebtTgtPsn;
import com.ntels.ccbs.batch.up.entity.NBlupb01m02;
import com.ntels.ccbs.batch.up.entity.UpymCtrtMngTp;
import com.ntels.ccbs.batch.up.entity.UpymDetail;
import com.ntels.ccbs.batch.up.entity.UpymEntrustMast;

@Component
public class NBlupb01m02DaoImpl extends LazyLoadingDao implements NBlupb01m02Dao {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NBlupb01m02Mapper nBlupb01m02Mapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/up/dao/mapper";
	}
	
	@Override
	protected String getMapperName() {
		return "NBlupb01m02Mapper";
	}
	
	@Override
	public int checkClaimEnd(String inDate, String soId) {
		return nBlupb01m02Mapper.checkClaimEnd(inDate, soId);
	}

	@Override
	public int getUpymJobDtlCount(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Mapper.getUpymJobDtlCount(nBlupb01m02);
	}

	/**
	 * 미납기준년월을 이용하여 미납대응유형기준에서 고액대상기준액을 추출한다
	 */
	@Override
	public UpymBigBsAmt getUpymBigBsAmt(NBlupb01m02 nBlupb01m02) {
		return nBlupb01m02Mapper.getTargetAmt(nBlupb01m02.getInDate(), nBlupb01m02.getSoId());
	}
	
	@Override
	public String getUpymMngTp(String soId, String inDate) {
		return nBlupb01m02Mapper.getUpymMngTp(soId, inDate);
	}

	@Override
	public LazyLoader<UpymDetail> getUpymDtlList(NBlupb01m02 nBlupb01m02) {
		return getLazyLoader("getUpymDtlList", UpymDetail.class, nBlupb01m02);
	}

	@Override
	public LazyLoader<UpymDetail> getUpymList(NBlupb01m02 nBlupb01m02) {
		return getLazyLoader("getUpymList", UpymDetail.class, nBlupb01m02);
	}

	@Override
	public int insertUpymJobMst(NBlupb01m02 nBlupb01m02) {
		return insertOne("insertUpymJobMst", nBlupb01m02);
	}

	@Override
	public int insertUpymJobDtl(NBlupb01m02 nBlupb01m02) {
		return insertOne("insertUpymJobDtl", nBlupb01m02);
	}

	@Override
	public int insertUpymDtl(UpymDetail upymDetail) {
		return insertOne("insertUpymDtl", upymDetail);
	}
	
	@Override
	public int insertUpymDtl(List<UpymDetail> upymDetailList) {
		return insert("insertUpymDtl", UpymDetail.class, upymDetailList);
	}

	@Override
	public int insertUpym(UpymDetail upymDetail) {
		return insertOne("insertUpym", upymDetail);
		
	}
	
	@Override
	public int insertUpym(List<UpymDetail> upymDetailList) {
		return insert("insertUpym", UpymDetail.class, upymDetailList);
	}
	
	@Override
	public LazyLoader<CtrtDetail> getUpymCtrtDetail(NBlupb01m02 nBlupb01m02) {
		return getLazyLoader("getUpymCtrtDetail", CtrtDetail.class, nBlupb01m02);
	}
	
	@Override
	public int insertUpymCtrt(CtrtDetail ctrtDetail) {
		return insertOne("insertUpymCtrt", ctrtDetail);
		
	}
	
	@Override
	public int insertUpymCtrt(List<CtrtDetail> ctrtDetailList) {
		return insert("insertUpymCtrt", CtrtDetail.class, ctrtDetailList);
	}
	
	@Override
	public LazyLoader<UpymEntrustMast> getUpymEntrustMastList(NBlupb01m02 nBlupb01m02) {
		return getLazyLoader("getUpymEntrustMastList", UpymEntrustMast.class, nBlupb01m02);
	}
	
	@Override
	public int insertUpymEntrustMast(UpymEntrustMast upymEntrustMast) {
		return insertOne("insertUpymEntrustMast", upymEntrustMast);
	}
	
	@Override
	public int insertUpymEntrustMast(List<UpymEntrustMast> upymEntrustMastList) {
		return insert("insertUpymEntrustMast", UpymEntrustMast.class, upymEntrustMastList);
	}
	
	@Override
	public LazyLoader<AuthChgAppl> getAuthChgApplList(NBlupb01m02 nBlupb01m02) {
		return getLazyLoader("getAuthChgApplList", AuthChgAppl.class, nBlupb01m02);
	}
	
	@Override
	public int insertAuthChgAppl(AuthChgAppl authChgAppl) {
		return insertOne("insertAuthChgAppl", authChgAppl);
	}
	
	@Override
	public int insertAuthChgAppl(List<AuthChgAppl> authChgApplList) {
		return insert("insertAuthChgAppl", AuthChgAppl.class, authChgApplList);
	}
	
	@Override
	public LazyLoader<DebtTgtPsn> getDebtTgtPsnList(NBlupb01m02 nBlupb01m02) {
		return getLazyLoader("getDebtTgtPsnList", DebtTgtPsn.class, nBlupb01m02);
	}
	
	@Override
	public int insertDebtTgtPsn(DebtTgtPsn debtTgtPsn) {
		return insertOne("insertDebtTgtPsn", debtTgtPsn);
	}
	
	@Override
	public int insertDebtTgtPsn(List<DebtTgtPsn> debtTgtPsnList) {
		return insert("insertDebtTgtPsn", DebtTgtPsn.class, debtTgtPsnList);
	}
	
	@Override
	public LazyLoader<UpymCtrtMngTp> getUpymCtrtMngTpList(NBlupb01m02 nBlupb01m02) {
		return getLazyLoader("", UpymCtrtMngTp.class, nBlupb01m02);
	}
	
	@Override
	public int insertUpymCtrtMngTp(UpymCtrtMngTp upymCtrtMngTp) {
		return insertOne("insertUpymCtrtMngTp", upymCtrtMngTp);
	}
	
	@Override
	public int insertUpymCtrtMngTp(List<UpymCtrtMngTp> upymCtrtMngTpList) {
		return insert("insertUpymCtrtMngTp", UpymCtrtMngTp.class, upymCtrtMngTpList);
	}
	
	@Override
	public int updateEntrustMast(String upym_mng_tp, Timestamp now, String so_id, String in_date) {
		return nBlupb01m02Mapper.updateEntrustMast(upym_mng_tp, now, so_id, in_date);
	}

}
