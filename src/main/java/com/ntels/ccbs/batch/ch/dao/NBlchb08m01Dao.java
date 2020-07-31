package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb08m01;
import com.ntels.ccbs.batch.common.LazyLoader;

public interface NBlchb08m01Dao {

	/**
	 * jdbc direct 접속 dao
	 * 
	 * @param conn
	 * @return List<StandardCharge>
	 */
	public LazyLoader<NBlchb08m01> listNBlchb08m01Direct(Multi multi);

	public int saveNBlchb08m01Direct(Connection conn, List<Object> obj);

}