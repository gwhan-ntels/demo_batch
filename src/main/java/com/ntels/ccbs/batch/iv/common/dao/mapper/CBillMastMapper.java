/**
 * @FileName
 * CBillMastMapper.java
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
package com.ntels.ccbs.batch.iv.common.dao.mapper;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface CBillMastMapper {
	public int saveInfo(CBillComm cbillmast);
	public int updateInfo(CBillComm cbillmast);
	public int deleteInfo(CBillComm cbillmast);

}
