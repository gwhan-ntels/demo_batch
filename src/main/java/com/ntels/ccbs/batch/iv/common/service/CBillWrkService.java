/**
 * @FileName
 * CBillWrkService.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 5. 9.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.batch.iv.common.service;

import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.CBillWrkCom;

public interface CBillWrkService {
	public int saveJdbcDirect(List<Object> obj);
	public int saveSimulationJdbcDirect(List<Object> obj);
	public CBillWrkCom selectJdbcDirect(CBillComm obj);
	public int updateJdbcDirect(List<Object> obj);
	public int deleteJdbcDirect(List<Object> obj);
	public int deleteDetailJdbcDirect(List<Object> obj);
	public int updateJdbcOplDirect(List<Object>  obj);
	
}
