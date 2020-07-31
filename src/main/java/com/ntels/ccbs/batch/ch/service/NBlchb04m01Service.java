package com.ntels.ccbs.batch.ch.service;

import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb04m01;
import com.ntels.ccbs.batch.common.LazyLoader;

public interface NBlchb04m01Service {

	public LazyLoader<NBlchb04m01> listJdbcDirect(Multi multi);
	public int saveJdbcDirect(List<Object> obj);
	public int saveSvcCmpsMt(Multi multi);
	
}