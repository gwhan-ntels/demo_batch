package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.JdbcDao;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;

@Repository
public class BillCustJdbcDaoImpl extends JdbcDao implements BillCustJdbcDao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;	
	
	
	@Override
	protected String getMapperName() {
		return "BillCustMapper";
	}
	
	@Override
	public int insertBillCust(List<BillCust> billCustList) {
		return insert(dbKind,"insertBillCust", BillCust.class, billCustList);
	}
	
	@Override
	public int insertBillCustDetail(List<BillCust> billCustDetailList) {
		return insert(dbKind,"insertBillCustDetail", BillCust.class, billCustDetailList);
	}

}
