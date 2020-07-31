package com.ntels.ccbs.batch.ch.standard.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.standard.entity.StandardCharge;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.sample.vo.SearchParam;

@Component
public interface StandardChargeMapper {

	public List<StandardCharge> listStandardCharge(Common comm);
	public int saveStandardCharge(StandardCharge standardcharge);

}
