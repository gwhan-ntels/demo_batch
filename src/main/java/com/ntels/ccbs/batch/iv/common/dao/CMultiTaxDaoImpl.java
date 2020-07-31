/**
 * @FileName
 * CMultiTaxDaoImpl.java
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
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CMultiTax;
import com.ntels.ccbs.batch.util.QueryUtil;

public class CMultiTaxDaoImpl  extends QueryUtil implements CMultiTaxDao {

	@Autowired

	public List<CMultiTax> listInfo(Connection conn, Common comm){
		
		List<CMultiTax> list = new ArrayList<CMultiTax>();

		
		try{
			list = QueryUtil.getListFromXML("com/ntels/ccbs/batch/iv/NBliv02m01/dao/mapper"
					,"NBliv02m01Mapper"
					,"select"
					,"listInfo"
					,CMultiTax.class
					,comm
					,conn) ;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			if(rs != null){
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}

//			if(pstmt != null){
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}			
//		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.iv.common.dao.CMultiTaxDao#listInfoDirect(java.sql.Connection, com.ntels.ccbs.batch.common.entity.Common)
	 */
	@Override
	public List<CMultiTax> listInfoDirect(Connection conn, Common comm) {
		// TODO Auto-generated method stub
		return null;
	}

}
