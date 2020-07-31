package com.ntels.ccbs.batch.ch.dao.mapper;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb11m01;
import com.ntels.ccbs.batch.common.LazyLoader;

@Component
public interface NBlchb11m01Mapper {

	public LazyLoader<NBlchb11m01> listNBlchb11m01(Multi multi);
	public int saveChargeHist(NBlchb11m01 nBlchb11m01);
	public int saveChargeHist1(Multi multi);
}
