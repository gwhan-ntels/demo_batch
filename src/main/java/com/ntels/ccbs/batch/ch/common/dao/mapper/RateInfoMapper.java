package com.ntels.ccbs.batch.ch.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.DiscExcl;
import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.common.entity.RateFctr;
import com.ntels.ccbs.batch.ch.common.entity.RateInfo;

@Component
public interface RateInfoMapper {

	public List<RateInfo> listRateInfo(String chrgCtgry);
	public Multi listMulti(Multi multi);
	public List<RateInfo> listRateItm(Multi multi);
	public List<RateAttr> listRrateAttr(Multi multi);
	public List<RateAttr> listDrateAttr(Multi multi);
	public Multi listMultiCtrt(Multi multi);
	public List<DiscExcl> listDiscExcl(Multi multi);
	public List<RateFctr> listRateFctr(Multi multi);
	public List<RateAttr> listCrateAttr(Multi multi);
}
