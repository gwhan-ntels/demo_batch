package com.ntels.ccbs.batch.ch.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.common.dao.mapper.RateInfoMapper;
import com.ntels.ccbs.batch.ch.common.entity.DiscExcl;
import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.common.entity.RateFctr;
import com.ntels.ccbs.batch.ch.common.entity.RateInfo;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.entity.Common;

/**
 * RateInfoDao 클래스
 * 
 * mybatis만을 사용한다면 QueryUtil 상속할 필요 없음.
 * 
 * <PRE>
 * 1. ClassName: RateInfoDao
 * 2. FileName : RateInfoDao.java
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
public class RateInfoDaoImpl  extends LazyLoadingDao  implements RateInfoDao {

	/** CommonMapper Autowired.  */
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/ch/common/dao/mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	protected String getMapperName() {
		return "rateInfoMapper";
	}

	
	/** RateInfoMapper Autowired.  */
	@Autowired
	private RateInfoMapper rateInfoMapper;
	
	public List<RateInfo> listRateInfo(String chrgCtgry) {
		RateInfo rateInfo = new RateInfo();
		rateInfo.setChrgCtgry(chrgCtgry);
		return getList(dbKind, "listRateInfo", RateInfo.class, rateInfo);
		//return rateInfoMapper.listRateInfo(chrgCtgry);
	}

	public Multi listMulti(Multi multi) {
		//return getOne(dbKind, "listMulti", Multi.class, multi);
		return rateInfoMapper.listMulti(multi);
	}
	
	public List<RateInfo> listRateItm(Multi multi){
		return getList(dbKind, "listRateItm", RateInfo.class, multi);
		//return rateInfoMapper.listRateItm(multi);
	}
	
	public List<RateAttr> listRrateAttr(Multi multi){
		return getList(dbKind, "listRrateAttr", RateAttr.class, multi);
		//return rateInfoMapper.listRrateAttr(multi);
	}
	
	public List<RateAttr> listDrateAttr(Multi multi){
		return getList(dbKind, "listDrateAttr", RateAttr.class, multi);
		//return rateInfoMapper.listDrateAttr(multi);
	}
	
	public Multi listMultiCtrt(Multi multi){
		return getOne(dbKind, "listMultiCtrt", Multi.class, multi);
		//return rateInfoMapper.listMultiCtrt(multi);
	}
	
	public List<DiscExcl> listDiscExcl(Multi multi){
		return getList(dbKind, "listDiscExcl", DiscExcl.class, multi);		
		//return rateInfoMapper.listDiscExcl(multi);
	}

	public List<RateFctr> listRateFctr(Multi multi){
		return getList(dbKind, "listRateFctr", RateFctr.class, multi);		
		//return rateInfoMapper.listRateFctr(multi);
	}

	public List<RateAttr> listCrateAttr(Multi multi){
		return rateInfoMapper.listCrateAttr(multi);
	}


}
