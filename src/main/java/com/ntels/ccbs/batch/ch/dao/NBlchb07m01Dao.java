package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.entity.NBlchb07m01;
import com.ntels.ccbs.batch.common.LazyLoader;

/**
 * <PRE>
 * 1. ClassName: NBlchb07m01Dao
 * 2. FileName : NBlchb07m01Dao.java
 * 3. Package  : com.ntels.ccbs.batch.ch.dao
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 7. 28. 오전 11:16:55
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 7. 28.	: 신규 개발.
 * </PRE>
 */
public interface NBlchb07m01Dao {

	public LazyLoader<NBlchb07m01> listCharge(Multi multi);
	public int saveCharge(Connection conn, List<Object> obj);		

}