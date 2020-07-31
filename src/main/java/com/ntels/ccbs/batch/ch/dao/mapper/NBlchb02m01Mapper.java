package com.ntels.ccbs.batch.ch.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb02m01;

@Component
public interface NBlchb02m01Mapper {

	public List<NBlchb02m01> listNBlchb02m01(Multi multi);
	public int saveStandardCharge(NBlchb02m01 nBlchb02m01);

}
