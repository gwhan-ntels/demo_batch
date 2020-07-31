package com.ntels.ccbs.batch.iv.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ntels.ccbs.batch.iv.common.entity.Arrears;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

@Mapper
public interface ArrearsMapper {

	List<Arrears> getArrearsInfoList(Arrears arrears);
	
	int insertArrs(Arrears arrears);
	
	List<BillWork> getArrsAplyInfoList(BillWork billWork);
	
	int updateArrsInfo(BillWork billWork);
	
}
