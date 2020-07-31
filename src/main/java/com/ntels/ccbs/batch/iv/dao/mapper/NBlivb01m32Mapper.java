package com.ntels.ccbs.batch.iv.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

@Component
public interface NBlivb01m32Mapper {

	public List<CBillComm> listInfo(Common comm);
	public int saveInfo(CBillComm obj);
	public List<CBillComm> listMastInfo(Common comm);
	public int saveMastInfo(CBillComm obj);

}
