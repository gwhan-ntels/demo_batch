package com.ntels.ccbs.batch.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.dao.CommonDao;
import com.ntels.ccbs.batch.common.entity.Common;

/**
 * 공통 코드 Service.
 *
 * <PRE>
 * 1. ClassName: NBliv02m01Service
 * 2. FileName : NBliv02m01Service.java
 * 3. Package  : com.ntels.ccbs.cm.service.configuration
 * 4. 작성자   : smyun@ntels.com
 * 5. 작성일   : 2014. 4. 8. 오후 5:02:49
 * 6. 변경이력
 *		이름  :		일자	: 변경내용
 *     ———————————————————————————————————
 *		smyun :	2014. 4. 8.	: 신규 개발.
 * </PRE>
 */
@Service
public class CommonServiceImpl implements CommonService {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** NBliv02m01Dao Autowired.  */
	@Autowired
	private CommonDao commonDao; 

	private List <Common>  comBillInfo;
	private List <Common>  comExRateInfo;
	
	
	/**
	 * 목록.
	 *
	 * @param condition 조회조건
	 * @return List<NBliv02m01>
	 */
	@Autowired
	private DataSource dataSource;

	public List<Common> commonListExRate() {
		// TODO Auto-generated method stub
		List<Common> list = commonDao.commonListExRate();
		
		if(list == null)
			list = new ArrayList<Common>();
		
		return list;
	}

	public void commonExRateInfo() {
		// TODO Auto-generated method stub
		
		if (comExRateInfo == null)
			comExRateInfo = new  ArrayList<Common>();
		
		comExRateInfo =  commonListExRate();
	}

	public void commonBillInfo(String billYymm, String billCycl, String soId) {
		// TODO Auto-generated method stub

		if (comBillInfo == null)
		    comBillInfo = new  ArrayList<Common>();
		
		comBillInfo =  commonListBillStp(billYymm,billCycl, soId);
	}

	public String getBillVal(String set_itm_id) {
		
		int i = 0;
		Common comm;
		
		
		    while(i < comBillInfo.size() )
		    {
		    	comm = comBillInfo.get(i);
		    	
		    	if ( comm.getSetItmId().equals(set_itm_id) == true )
		    	{
		    		return comm.getSetVal();
		    	}
		    		
		    	i++;
		   }
		   return null;
	}

	
	
	public long getExRateInfo(String crncy_cd) {
		
	
		int i = 0;
		Common comm;
		
		
		    while(i < comExRateInfo.size() )
		    {
		    	comm = comExRateInfo.get(i);
		    	
		    	if ( comm.getCrncyCd().equals(crncy_cd) == true )
		    	{
		    		return comm.getExrate();
		    	}
		    		
		    	i++;
		   }
		   return 1;
	}

   public String getBillSeqNo(String bill_yymm, String bill_cycl, String bill_dt, String pym_acnt_id,  String seq)
   {
		
	    String bill_seq_no  ;
	    String billDt = null;
	    
	    
	        if ( bill_dt.length() > 2 ) billDt = bill_dt.substring(6,8);
	        else billDt = bill_dt;
	        
   	       // bill_yymm(4-yymm)+bill_cycl(2)+ bill_dt( 2-dd) + pym_acnt_id ( 10 ) + seq(2)
	        bill_seq_no = bill_yymm.substring(2, 6) + bill_cycl + billDt + pym_acnt_id + seq;
	        	
	        	
	        return bill_seq_no;
	}
	
	
	
   public List<Common> commonListBillStp(String billYymm, String billCycl, String soId) {
		// TODO Auto-generated method stub

		List<Common> list = commonDao.commonListBillStp(billYymm, billCycl, soId);
		
		if(list == null)
			list = new ArrayList<Common>();
		
		return list;
	}


	public List<Common> commonListClcMain(String clcWrkNo) {
		// TODO Auto-generated method stub
		List<Common> list = commonDao.commonListClcMain(clcWrkNo);
		
		if(list == null)
			list = new ArrayList<Common>();
		
		return list;
	}


	public int commonUpdClcMain(String statCd, String clcWrkNo) {
		// TODO Auto-generated method stub
		int result = commonDao.commonUpdClcMain(statCd, clcWrkNo);

		return result;
	}


	public int commonInsClsInfo(Common common) {
		// TODO Auto-generated method stub
		int result = commonDao.commonInsClsInfo(common);

		return result;
	}


	public int commonUpdClsMain(String clsYn, String clsTskCl, String billYymm, String soId) {
		// TODO Auto-generated method stub
		int result = commonDao.commonUpdClsMain(clsYn, clsTskCl, billYymm, soId);

		return result;
	}

	public List<Common> commonListBatPgm(String batPgmId){
		List<Common> list = commonDao.commonListBatPgm(batPgmId);

		if(list == null)
			list = new ArrayList<Common>();
		
		return list;
	}

	public int commonUpdBatPgmLog(Common common) {
		// TODO Auto-generated method stub
		int result = commonDao.commonUpdBatPgmLog(common);

		return result;
	}

	public int commonInsBatPgmLog(Common common) {
		// TODO Auto-generated method stub
		int result = commonDao.commonInsBatPgmLog(common);

		return result;
	}

	public int createNewSequence(String modCd) {
		// TODO Auto-generated method stub
		int updCnt = commonDao.updateNextSequence(modCd);
		int sequence = commonDao.getSequence(modCd);
		return sequence;
	}
	
	@Override
	public List<Integer> createNewSequence(String modCd, int count) {
		
		List<Integer> seqList = new ArrayList<>();
		
		int seq = createNewSequence(modCd);
		commonDao.updateNextSequenceMulti(modCd, count-1);
		
		for (int i = 0; i < count; i++) {
			seqList.add(seq);
			seq++;
		}
		
		return seqList;
	}
	
	@Override
	public int batPgmLogCount(Common common) {
		return commonDao.batPgmLogCount(common);
	}
	
	@Override
	public String batProcStat(Common common) {
		return commonDao.batProcStat(common);
	}
	
	@Override
	public int updateBatProcStat(Common common) {
		return commonDao.updateBatProcStat(common);
	}

	
	
}