package com.ntels.ccbs.batch.ch.dao.mapper;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb10m01;
import com.ntels.ccbs.batch.ch.entity.NBlchb11m01;
import com.ntels.ccbs.batch.common.LazyLoader;

@Component
public interface NBlchb10m01Mapper {

	public LazyLoader<NBlchb10m01> listNBlchb10m01(Multi multi);
	public int saveCharge(NBlchb10m01 nBlchb10m01);
	public int saveChargeUsage(NBlchb10m01 nBlchb10m01);

}
