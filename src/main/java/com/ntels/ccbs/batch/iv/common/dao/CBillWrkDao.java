/**
 * @FileName
 * CBillWrkDao.java
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
package com.ntels.ccbs.batch.iv.common.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.CBillWrkCom;
import com.ntels.ccbs.batch.iv.common.entity.CMultiTax;

public interface CBillWrkDao {

	public int saveInfoDirect(Connection conn, List<Object> obj);
	public int saveSimulationInfo(Connection conn, List<Object> obj);
	public CBillWrkCom selectInfoDirect(Connection conn, CBillComm comm);
	public int updateInfoDirect(Connection conn, List<Object> obj);
	public int deleteInfoDirect(Connection conn, List<Object> obj);
	public int deleteDetailInfoDirect(Connection conn, List<Object> obj);
	public int updateOplInfoDirect(Connection conn, List<Object> obj);
	
	/**
	 * @Method
	 * updateOplInfoDirect
	 * @Return
	 * int
	 * @Date
	 * 2016. 6. 26.오후 8:39:55
	 * @Writter
	 * ntels_shlee
	 * @EditHistory
	 *
	 * @Discript
	 */

}
