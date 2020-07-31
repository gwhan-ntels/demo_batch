package com.ntels.ccbs.batch.ch.service;

import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb09m01;
import com.ntels.ccbs.batch.common.LazyLoader;

public interface NBlchb09m01Service {

	public LazyLoader<NBlchb09m01> listCharge(Multi multi);
	public int saveCharge(List<Object> obj);
	public int updateThrwy(List<Object> obj);
	public int saveSvcCmpsMt(List<Object> obj);	
}