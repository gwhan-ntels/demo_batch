package com.ntels.ccbs.batch.iv.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m04Mapper;

/**
 * NBliv04m01Dao 클래스
 * 
 * mybatis만을 사용한다면 QueryUtil 상속할 필요 없음.
 * 
 * <PRE>
 * 1. ClassName: NBliv04m01Dao
 * 2. FileName : NBliv04m01Dao.java
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
@Component
public class NBlivb01m04DaoImpl extends LazyLoadingDao implements NBlivb01m04Dao {

	@Autowired
	private NBlivb01m04Mapper nBlivb01m04Mapper;

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlivb01m04Mapper";
	}

	@Override
	public LazyLoader<CBillComm> listInfoDirect(Common comm) {
		return getLazyLoader("listInfo", CBillComm.class, comm);
	}

	@Override
	public LazyLoader<CBillComm> listDivInfoDirect(Common comm) {
		return getLazyLoader("listDivInfo", CBillComm.class, comm);
	}

	@Override
	public int saveInfoDirect(List<Object> obj) {
		return insert("saveInfo", new CBillComm(), obj);
	}

	@Override
	public int saveDivInfoDirect(List<Object> obj) {
		return insert("saveDivInfo", new CBillComm(), obj);
	}

	public int deleteInfoDirect(List<Object> obj) {
		return delete("deleteInfo", CBillComm.class, obj);
	}

	public int saveWrkDirect(List<Object> obj) {
		return insert("saveWrkDirect", CBillComm.class, obj);
	}
	
}
