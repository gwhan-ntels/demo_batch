package com.ntels.ccbs.batch.iv.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.iv.common.entity.Sale;

public interface NBlivb01m27Mapper {

	List<Sale> querySale(@Param("billYymm") String billYymm
						, @Param("useYymm") String useYymm
						, @Param("soId") String soId);
	
	int insertSaleTemp(@Param("sale") Sale sale);
	
	int insertSale(@Param("sale") Sale sale);
	
	int deletePrevData(@Param("billYymm") String billYymm, @Param("soId") String soId);
	
}
