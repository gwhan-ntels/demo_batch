package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.entity.NBlchb00m01;
import com.ntels.ccbs.batch.common.LazyLoader;

public interface NBlchb00m01Dao {

	/**
	 * jdbc direct 접속 dao 
	 * 
	 * @param conn
	 * @return List<StandardCharge>
	 */
	public LazyLoader<NBlchb00m01> listNBlchb00m01Direct(Multi multi);
	//public List<NBlchb00m01> listNBlchb00m01Direct(Multi multi);
	public LazyLoader<NBlchb00m01> listNBlchb00m01DirectAll(Multi multi);
	public int saveNBlchb00m01Direct(Connection conn, List<Object> obj);
	public int saveNBlchb00m01(NBlchb00m01 nblchb00m01);
	public int saveIfCustInfoAll(Multi multi);
	public int saveIfCustInfo(Multi multi);
	public Multi getColId(Multi multi);
	public Multi getFunc(Multi multi);
}