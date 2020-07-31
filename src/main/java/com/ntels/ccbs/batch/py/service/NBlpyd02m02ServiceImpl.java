package com.ntels.ccbs.batch.py.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.py.dao.NBlpyd02m02Dao;

@Service
public class NBlpyd02m02ServiceImpl implements NBlpyd02m02Service {

	@Autowired
	private NBlpyd02m02Dao nBlpyd02m02Dao;
	
	@Override
	public List<String> getBillSeqByPymAcntId(String pymAcntId) {
		return nBlpyd02m02Dao.getBillSeqByPymAcntId(pymAcntId);
	}
	
}
