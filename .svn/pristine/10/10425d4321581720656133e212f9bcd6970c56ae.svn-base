package com.ntels.ccbs.batch.common.dao;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.mapper.CommonMapper;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;
import com.ntels.ccbs.batch.util.QueryUtil;

/**
 * CommonDao 클래스
 * 
 * mybatis만을 사용한다면 QueryUtil 상속할 필요 없음.
 * 
 * <PRE>
 * 1. ClassName: CommonDao
 * 2. FileName : CommonDao.java
 * 3. Package  : com.ntels.ccbs.batch.sample.dao
 * 4. Comment  :
 * 5. 작성자   : Administrator
 * 6. 작성일   : 2016. 3. 7. 오전 9:03:06
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		Administrator :	2016. 3. 7.	: 신규 개발.
 * </PRE>
 */

@Repository
public class CommonDaoImplMybatis  extends LazyLoadingDao implements CommonDao {

	/** CommonMapper Autowired.  */
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/common/dao/mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	protected String getMapperName() {
		return "CommonMapper";
	}

	@Autowired
	private CommonMapper commonMapper;

	public List<Common> commonListExRate() {
		Common comm = new Common();
		return getList(dbKind, "commonListExRate", Common.class, comm);
		//return commonMapper.commonListExRate();
	}

	public List<Common> commonListBillStp(String billYymm, String billCycl, String soId) {
		Common comm = new Common();
		comm.setBillYymm(billYymm);
		comm.setBillCycl(billCycl);
		comm.setSoId(soId);
		return getList(dbKind, "commonListBillStp", Common.class, comm);
		//return commonMapper.commonListBillStp(billYymm, billCycl, soId);
	}

	public List<Common> commonListClcMain(String clcWrkNo) {
		Common comm = new Common();
		comm.setClcWrkNo(clcWrkNo);
		return getList(dbKind, "commonListClcMain", Common.class, comm);
		//return commonMapper.commonListClcMain(clcWrkNo);
	}

	public int commonUpdClcMain(String statCd, String clcWrkNo) {
	
		Common comm = new Common();
		comm.setStatCd(statCd);
		comm.setClcWrkNo(clcWrkNo);
		return updateOne(dbKind, "commonListExRate", comm);
		//return commonMapper.commonUpdClcMain(statCd, clcWrkNo);
	}

	public int commonInsClsInfo(Common common) {
		return insertOne(dbKind, "commonInsClsInfo", common);
		//return commonMapper.commonInsClsInfo(common);
	}

	public int commonUpdClsMain(String clsYn, String clsTskCl, String billYymm, String soId) {
		Common comm = new Common();
		comm.setClsYn(clsYn);
		comm.setClsTskCl(clsTskCl);
		comm.setBillYymm(billYymm);
		comm.setSoId(soId);
		return updateOne(dbKind, "commonUpdClsMain", comm);
		//return commonMapper.commonUpdClsMain(clsYn, clsTskCl, billYymm, soId);
	}

	public List<Common> commonListBatPgm(String batPgmId) {
		Common comm = new Common();
		comm.setBatPgmId(batPgmId);
		return getList(dbKind, "commonListBatPgm", Common.class, comm);
		//return commonMapper.commonListBatPgm(batPgmId);
	}
	
	public int commonInsBatPgmLog(Common common) {
	    return insertOne(dbKind, "commonInsBatPgmLog",common);
    }
	
	public int commonUpdBatPgmLog(Common common) {
		return updateOne(dbKind, "commonUpdBatPgmLog",common);
	}

	public int updateNextSequence(String modCd) {
		//Common comm = new Common();
		//comm.setModCd(modCd);
		//return updateOne(dbKind, "updateNextSequence",comm);
		return commonMapper.updateNextSequence(modCd);
	}

	public int getSequence(String modCd) {
		return commonMapper.getSequence(modCd);
	}
	
	@Override
	public int updateNextSequenceMulti(String modCd, int count) {
		return commonMapper.updateNextSequenceMulti(modCd, count);
	}
	
	@Override
	public int batPgmLogCount(Common common) {
		Common com;
		com = getOne(dbKind, "batPgmLogCount",Common.class, common);
		return com == null ? 0 : com.getCnt();
		//return commonMapper.batPgmLogCount(common);
	}
	
	@Override
	public String batProcStat(Common common) {
		Common com;
		com = getOne(dbKind, "batProcStat",Common.class, common);
		return com.getBatProcStat();
		//return commonMapper.batProcStat(common);
	}
	
	@Override
	public int updateBatProcStat(Common common) {
		return updateOne(dbKind, "updateBatProcStat",common);
		//return commonMapper.updateBatProcStat(common);
	}
}
