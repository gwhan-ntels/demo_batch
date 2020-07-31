package com.ntels.ccbs.batch.up.dao.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.common.entity.UpymBigBsAmt;
import com.ntels.ccbs.batch.up.entity.CtrtDetail;
import com.ntels.ccbs.batch.up.entity.DebtTgtPsn;
import com.ntels.ccbs.batch.up.entity.NBlupb01m02;
import com.ntels.ccbs.batch.up.entity.UpymCtrtMngTp;
import com.ntels.ccbs.batch.up.entity.UpymDetail;
import com.ntels.ccbs.batch.up.entity.UpymEntrustMast;

@Component
public interface NBlupb01m02Mapper {

	int checkClaimEnd(@Param("inDate") String inDate, @Param("soId") String soId);
	
	UpymBigBsAmt getTargetAmt(@Param("inDate") String inDate, @Param("soId") String soId);
	
	int insertUpymJobMst(NBlupb01m02 nBlupb01m02);
	
	int getUpymJobDtlCount(@Param("nBlupb01m02") NBlupb01m02 nBlupb01m02);
	
	String getUpymMngTp(@Param("soId") String soId, @Param("inDate") String inDate);
	
	int insertUpymJobDtl(NBlupb01m02 nBlupb01m02);
	
	List<UpymDetail> getUpymDtlList(NBlupb01m02 nBlupb01m02);
	
	int insertUpymDtl(UpymDetail upymDetail);
	
	List<UpymDetail> getUpymList(NBlupb01m02 nBlupb01m02);
	
	int insertUpym(UpymDetail upymDetail);
	
	List<CtrtDetail> getCollectionCtrtDetailList(NBlupb01m02 nBlupb01m02);
	
	int insertUpymCtrt(CtrtDetail ctrtDetail);
	
	List<AuthChgAppl> getAuthChgApplList(NBlupb01m02 nBlupb01m02);
	
	int insertAuthChgAppl(AuthChgAppl authChgAppl);
	
	List<DebtTgtPsn> getDebtTgtPsnList(NBlupb01m02 nBlupb01m02);
	
	int insertDebtTgtPsn(DebtTgtPsn debtTgtPsn);
	
	List<UpymCtrtMngTp> getUpymCtrtMngTpList(NBlupb01m02 nBlupb01m02);
	
	int insertUpymCtrtMngTp(UpymCtrtMngTp upymCtrtMngTp);
	
	List<UpymEntrustMast> getUpymEntrustMastList(NBlupb01m02 nBlupb01m02);
	
	int insertUpymEntrustMast(UpymEntrustMast upymEntrustMast);
	
	int updateEntrustMast(@Param("upymMngTp") String upymMngTp, @Param("now") Timestamp now, @Param("soId") String so_id, @Param("inDate") String inDate);
	
}
