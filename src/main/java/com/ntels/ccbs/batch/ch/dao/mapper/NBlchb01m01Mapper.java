package com.ntels.ccbs.batch.ch.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb01m01;

@Component
public interface NBlchb01m01Mapper {

	public List<NBlchb01m01> listNBlchb01m01(Multi multi);
	public int saveStandardCharge(NBlchb01m01 nBlchb01m01);

}
