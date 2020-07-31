package com.ntels.ccbs.batch.py.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.py.dao.NBlpyf02m02Dao;

@Service
public class NBlpyf02m02ServiceImpl implements NBlpyf02m02Service {

	@Autowired
	private NBlpyf02m02Dao nBlpyf02m02Dao;
	
	@Override
	public List<String> getBillSeqByPymAcntId(String pymAcntId) {
		return nBlpyf02m02Dao.getBillSeqByPymAcntId(pymAcntId);
	}
	
}
