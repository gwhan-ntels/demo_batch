/**
 * @FileName
 * CBillWrkDaoImpl.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 5. 9.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.batch.iv.common.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.dao.mapper.CBillWrkMapper;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.CBillWrkCom;

@Component
public class CBillWrkDaoImpl extends LazyLoadingDao implements CBillWrkDao {
	/** StandardChargeMapper Autowired. */
	@SuppressWarnings("unused")
	@Autowired
	private CBillWrkMapper clsMapper;

	@Override
	protected String getMapperPath() {
		// TODO Auto-generated method stub
		return "com/ntels/ccbs/batch/iv/common/dao/mapper";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ntels.ccbs.batch.common.dao.JdbcDao#getMapperName()
	 */
	@Override
	protected String getMapperName() {
		// TODO Auto-generated method stub
		return "CBillWrkMapper";
	}

	public int saveInfoDirect(Connection conn, List<Object> obj) {
		return insert("saveInfo", CBillComm.class, obj);
	}

	public int saveSimulationInfo(Connection conn, List<Object> obj) {
		return insert("saveSimulationInfo", CBillComm.class, obj);
	}

	public int updateOplInfoDirect(Connection conn, List<Object> obj) {
		return update("updateOplInfo", CBillComm.class, obj);
	}

	public int updateInfoDirect(Connection conn, List<Object> obj) {
		return update("updateInfo", CBillComm.class, obj);
	}

	public int deleteInfoDirect(Connection conn, List<Object> obj) {
		return delete("deleteInfo", CBillComm.class, obj);
	}

	public int deleteDetailInfoDirect(Connection conn, List<Object> obj) {
		return delete("deleteDetailInfo", CBillComm.class, obj);

	}

	public CBillWrkCom selectInfoDirect(Connection conn, CBillComm comm) {

		List<CBillWrkCom> list = new ArrayList<CBillWrkCom>();
		list = getList("selectInfo", CBillWrkCom.class, comm);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

}
