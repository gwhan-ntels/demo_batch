package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb04m01;
import com.ntels.ccbs.batch.common.LazyLoader;

public interface NBlchb04m01Dao {

	/**
	 * jdbc direct 접속 dao 
	 * 
	 * @param conn
	 * @return List<StandardCharge>
	 */
	public LazyLoader<NBlchb04m01> listNBlchb04m01Direct(Multi multi);
	public int saveNBlchb04m01Direct(Connection conn, List<Object> obj);
	public int saveSvcCmpsMt(Multi multi);

}