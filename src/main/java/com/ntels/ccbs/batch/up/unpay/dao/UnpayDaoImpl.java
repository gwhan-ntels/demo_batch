package com.ntels.ccbs.batch.up.unpay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.BatchDao;
import com.ntels.ccbs.batch.up.entity.UpymObj;
import com.ntels.ccbs.batch.up.unpay.dao.mapper.UnpayMapper;

@Repository
public class UnpayDaoImpl extends BatchDao implements UnpayDao {

//	@Autowired
//	private UnpayMapper unpayMapper;
	
	/**
	 * 미수금 내역을 저장한다.
	 * @param upymObjList
	 * @return
	 */
	@Override
	public int insertUnpaymentObj(final List<UpymObj> upymObjList) {
		int insertCount = batchJob(UnpayMapper.class, new BatchJob<UnpayMapper>() {

			@Override
			public int job(UnpayMapper mapper) {
				int cnt = 0;
				
				for (UpymObj upymObj : upymObjList) {
					mapper.insertUnpaymentObj(upymObj);
					cnt++;
				}
				
				return cnt;
			}
		});
		
		return insertCount;
	}
	
}
