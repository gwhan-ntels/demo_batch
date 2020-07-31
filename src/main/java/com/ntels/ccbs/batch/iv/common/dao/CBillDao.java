/**
 * @FileName
 * CBillDao.java
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

public interface CBillDao {
	
	public int saveInfoDirect(Connection conn, List<Object> obj);
	public int updateInfoDirect(Connection conn, List<Object> obj);
	public int deleteInfoDirect(Connection conn, List<Object> obj);
	
}
