package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb06m01;
import com.ntels.ccbs.batch.common.LazyLoader;

public interface NBlchb06m01Dao {

	/**
	 * jdbc direct 접속 dao 
	 * 
	 * @param conn
	 * @return List<StandardCharge>
	 */
	public LazyLoader<NBlchb06m01> listNBlchb06m01Direct(Multi multi);
	public int saveNBlchb06m01Direct(Connection conn, List<Object> obj);

}