package com.ntels.ccbs.batch.ch.dao.mapper;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb06m01;
import com.ntels.ccbs.batch.common.LazyLoader;

@Component
public interface NBlchb06m01Mapper {

	public LazyLoader<NBlchb06m01> listNBlchb06m01(Multi multi);
	public int saveStandardCharge(NBlchb06m01 nBlchb06m01);

}
