package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.Arrears;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.dao.mapper.ArrearsMapper;

@Repository
public class ArrearsDaoImpl extends LazyLoadingDao implements ArrearsDao {

	@Autowired
	private ArrearsMapper arrearsMapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	protected String getMapperName() {
		return "ArrearsMapper";
	}
	
	@Override
	public List<Arrears> getArrearsInfoList(Arrears arrears) {
		return arrearsMapper.getArrearsInfoList(arrears);
	}
	
	@Override
	public LazyLoader<Arrears> getArrearsInfoListLazy(Arrears arrears) {
		return getLazyLoader(dbKind,"getArrearsInfoList", Arrears.class, arrears);
	}
	
	@Override
	public int insertArrs(Arrears arrears) {
		return arrearsMapper.insertArrs(arrears);
	}
	
	@Override
	public int insertArrs(List<Arrears> arrearList) {
		return insert(dbKind,"insertArrs", Arrears.class, arrearList);
	}
	
	@Override
	public List<BillWork> getArrsAplyInfoList(BillWork billWork) {
		return arrearsMapper.getArrsAplyInfoList(billWork);
	}
	
	@Override
	public LazyLoader<BillWork> getArrsAplyInfoListLazy(BillWork billWork) {
		return getLazyLoader(dbKind,"getArrsAplyInfoList", BillWork.class, billWork);
	}
	
	@Override
	public int updateArrsInfo(BillWork billWork) {
		return arrearsMapper.updateArrsInfo(billWork);
	}
	
	@Override
	public int updateArrsInfo(List<BillWork> billWorkList) {
		return update(dbKind,"updateArrsInfo", BillWork.class, billWorkList);
	}

}
