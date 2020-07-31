package com.ntels.ccbs.batch.iv.common.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.iv.common.dao.mapper.BillCommMapper;

@Repository
public class BillCommDaoImpl implements BillCommDao {

	@Autowired
	private BillCommMapper billCommMapper;

	@Override
	public int updateChrgAdjAply(String dcsnProcStat, String adjNo) {
		return billCommMapper.updateChrgAdjAply(dcsnProcStat, adjNo, new Timestamp(new Date().getTime()));
	}

}
