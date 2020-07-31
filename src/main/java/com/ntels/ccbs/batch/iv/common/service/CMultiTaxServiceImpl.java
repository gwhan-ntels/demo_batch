/**
 * @FileName
 * CMultiTaxServiceImpl.java
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
package com.ntels.ccbs.batch.iv.common.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.*;
import com.ntels.ccbs.batch.iv.common.dao.CBillWrkDao;
import com.ntels.ccbs.batch.iv.common.dao.CMultiTaxDao;
import com.ntels.ccbs.batch.iv.common.entity.CMultiTax;


public class CMultiTaxServiceImpl implements  CMultiTaxService {

	
	private static final String CUtil = null;
	List<CMultiTax>  list = new ArrayList<CMultiTax>();
	@Autowired
	private CMultiTaxDao clsDao;
	
	@Autowired
	private DataSource dataSource;
	
	
	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.iv.common.service.CMultiTaxService#listJdbcDirect(com.ntels.ccbs.batch.common.entity.Common)
	 */
	@Override
	public int listJdbcDirect(Common comm) {
		
		Connection conn = null;
		int nRet = 0;
		try{
			conn = dataSource.getConnection();
			list = clsDao.listInfoDirect(conn, comm);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
			}
		}
	    return nRet ;
	}

	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.iv.common.service.CMultiTaxService#GetCalTax(java.lang.String, java.lang.String, java.lang.String, double, double)
	 */
	@Override
	public double GetCalTax(String so_id, String chrg_itm_cd,  double dBillAmt, double dVat) {
		
		CMultiTax obj;
		
		double dTax = 0;
		String strVatType = null;
		double dVatRate  = 0;

		int i = 0;

		
		i=1;
		while (i <= list.size() ) 
		{
			
			obj = list.get(i);
			
			if ( obj.getSvcRateItmTypCd().equals(chrg_itm_cd) == true )
			{
				switch ( obj.getVatTyp() )
				{
					case "1":
						dTax = dTax + dBillAmt * dVatRate/100;
					break;
					case "2":
						dTax = dTax + (dBillAmt+dVat) * dVatRate/100;  // Round Down
					break;
					case "3":
						dTax = dTax + dVat * dVatRate/100;  // Round Down
					break;
					default:
						dTax = com.ntels.ccbs.batch.common.CUtil.Round(dTax, -1, 1 );
				}
			}	
			i++;
		}
		dTax = com.ntels.ccbs.batch.common.CUtil.Round(dTax, -1, 1 );
		return dTax;		
	}

}
