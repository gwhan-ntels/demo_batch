package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb05m01;
import com.ntels.ccbs.batch.common.LazyLoader;

public interface NBlchb05m01Dao {

	/**
	 * jdbc direct 접속 dao 
	 * 
	 * @param conn
	 * @return List<StandardCharge>
	 */
	public LazyLoader<NBlchb05m01> listNBlchb05m01Direct(Multi multi);
	public int saveNBlchb05m01Direct(Connection conn, List<Object> obj);

}