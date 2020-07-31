package com.ntels.ccbs.batch.ch.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb00m01;

@Component
public interface NBlchb00m01Mapper {

	public List<NBlchb00m01> listNBlchb00m01All();
	public List<NBlchb00m01> listNBlchb00m01(Multi multi);
	public int saveNBlchb00m01(NBlchb00m01 nBlchb00m01);
	public int saveNBlchb00m01M(NBlchb00m01 nBlchb00m01);
	public int saveIfCustInfoAll(Multi multi);
	public int saveIfCustInfo(Multi multi);

}
