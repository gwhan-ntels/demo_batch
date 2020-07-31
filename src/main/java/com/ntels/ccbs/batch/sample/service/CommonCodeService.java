package com.ntels.ccbs.batch.sample.service;

import java.util.List;

import com.ntels.ccbs.batch.entity.CommonCode;
import com.ntels.ccbs.batch.sample.vo.SearchParam;

public interface CommonCodeService {

	public List<CommonCode> list(SearchParam param);
	
	public boolean printCommonCode(CommonCode commonCode);

	public List<CommonCode> listJdbcTemplate(SearchParam param);
	
	public List<CommonCode> listJdbcDirect(SearchParam param);
	
}