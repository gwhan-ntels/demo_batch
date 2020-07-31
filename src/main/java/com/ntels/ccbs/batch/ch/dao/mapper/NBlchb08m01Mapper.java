package com.ntels.ccbs.batch.ch.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb08m01;

@Component
public interface NBlchb08m01Mapper {

	public List<NBlchb08m01> listNBlchb08m01(Multi multi);

	public int saveNBlchb08m01(NBlchb08m01 nBlchb08m01);

}
