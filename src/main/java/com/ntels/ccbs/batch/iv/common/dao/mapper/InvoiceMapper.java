package com.ntels.ccbs.batch.iv.common.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.iv.common.entity.Invoice;

public interface InvoiceMapper {

	Integer getInvcRissSeq(@Param("invoice") Invoice invoice);
	
}
