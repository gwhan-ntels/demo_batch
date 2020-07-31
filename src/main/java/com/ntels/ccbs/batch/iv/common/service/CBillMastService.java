/**
 * @FileName
 * CBillMastService.java
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

public interface CBillMastService {
	public int saveJdbcDirect(List<Object> obj);
	public int updateJdbcDirect(List<Object> obj);
	public int deleteJdbcDirect(List<Object> obj);
	

}
