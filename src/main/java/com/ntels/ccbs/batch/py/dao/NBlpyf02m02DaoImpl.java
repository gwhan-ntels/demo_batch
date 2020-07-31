package com.ntels.ccbs.batch.py.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.py.dao.mapper.NBlpyf02m02Mapper;

@Repository
public class NBlpyf02m02DaoImpl implements NBlpyf02m02Dao {

	@Autowired
	private NBlpyf02m02Mapper nBlpyf02m02Mapper;
	
	@Override
	public List<String> getBillSeqByPymAcntId(String pymAcntId) {
		return nBlpyf02m02Mapper.getBillSeqByPymAcntId(pymAcntId);
	}

}
