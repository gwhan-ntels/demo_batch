/**
 * @FileName
 * CBillService.java
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

public interface CBillService {
	public int saveJdbcDirect(List<Object> obj);
	public int updateJdbcDirect(List<Object> obj);
	public int deleteJdbcDirect(List<Object> obj);
	public String getBillSeqNo(String bill_yymm, String bill_cycl, String bill_dt, String pym_acnt_id,  String seq);
	
}
