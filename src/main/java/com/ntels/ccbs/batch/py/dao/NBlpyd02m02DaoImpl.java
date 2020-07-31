package com.ntels.ccbs.batch.py.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.py.dao.mapper.NBlpyd02m02Mapper;

@Repository
public class NBlpyd02m02DaoImpl implements NBlpyd02m02Dao {

	@Autowired
	private NBlpyd02m02Mapper nBlpyd02m02Mapper;
	
	@Override
	public List<String> getBillSeqByPymAcntId(String pymAcntId) {
		return nBlpyd02m02Mapper.getBillSeqByPymAcntId(pymAcntId);
	}

}
