package com.ntels.ccbs.batch.ch.dao.mapper;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb09m01;
import com.ntels.ccbs.batch.common.LazyLoader;

@Component
public interface NBlchb09m01Mapper {

	public LazyLoader<NBlchb09m01> listNBlchb09m01(Multi multi);
	public int saveCharge(NBlchb09m01 nBlchb09m01);
	public int updateThrwy(NBlchb09m01 nBlchb09m01);
	public int saveSvcCmpsMt(NBlchb09m01 nBlchb09m01);

}
