/**
 * @FileName
 * CMultiTaxDao.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 5. 20.
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
import com.ntels.ccbs.batch.iv.common.entity.CMultiTax;

public interface CMultiTaxDao {
	public List<CMultiTax> listInfoDirect(Connection conn, Common comm);

}
