package com.ntels.ccbs.batch.iv.common.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.MessageService;
import com.ntels.ccbs.batch.iv.common.dao.CBLDao;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.BillStp;

@Service
public class CBLServiceImpl extends LazyLoadingDao implements CBLService {
	
	@Autowired
	private CBLDao cblDao;

	@Autowired
	protected MessageService messageService;
	
	@Override
	protected String getMapperPath() 
	{
		return "com/ntels/ccbs/batch/iv/common/dao/mapper";
	}
	
	@Override
	protected String getMapperName()
	{
		return "CBLMapper";
		//com.ntels.ccbs.batch.iv.common.dao.mapper.CBLMapper
	}
	/**
	 * 00001
	 */
	@Value("${config.supplybizno}")
	private String configSubblyBizNo;
	
	/**
	 * 00044
	 */
	@Value("${config.remark}")
	private String configRemark;
	
	/**
	 * 00021
	 */
	@Value("${config.vat}")
	private String configVat;
	
	/**
	 * 00057
	 */
	@Value("${cycle.payduedt}")
	private String cyclePayDueDt;
	
	/**
	 * 00076
	 */
	@Value("${cycle.exchratedt}")
	private String cycleExchrateDt;
	
	/**
	 * 00055
	 */
	@Value("${cycle.billdt}")
	private String cycleBillDt;
	
	@Value("${tax.iss.dt}")
	private String taxIssDt;

	/**
	 * 00001
	 */
	@Value("${config.isp.tim.no}")
	private String confIspTimNo;
			
	/**
	 * 00005
	 */
	@Value("${config.isp.name}")
	private String confIspName;
	
	/**
	 * 00007
	 */
	@Value("${config.isp.address}")
	private String confIspAddress;
	
	/**
	 * 00009
	 */
	@Value("${config.isp.url}")
	private String confIspUrl;
	
	/**
	 * 00122
	 */
	@Value("${config.isp.tel}")
	private String confIspTel;
	
	/**
	 * 00123
	 */
	@Value("${config.isp.fax}")
	private String confIspFax;
	
	/**
	 * 00125
	 */
	@Value("${config.isp.email}")
	private String confIspEmail;
	
	@Value("${dbms.kind}")
	String dbKind;	
	
	private <T> T returnFirstObject(List<T> list) {
		
		if (list.isEmpty() == true) {
			return null;
		}
		System.out.println("=============>"+list.get(0).getClass());
		return list.get(0);
	}
	
	private BillCyclStp getCycleStpSetVal(BillCyclStp billCyclStp) {
		//return (BillCyclStp) returnFirstObject(getList(dbKind, "getCyclStpSetVal", BillCyclStp.class, billCyclStp));
		//return returnFirstObject(getList(dbKind, "getCyclStpSetVal", BillCyclStp.class, billCyclStp));
		return returnFirstObject(cblDao.getCyclStpSetVal(billCyclStp));
	}
	
	private String getSetLoc(BillCyclStp billCyclStp) {
	//	return (String) returnFirstObject(getList(dbKind, "getSetLoc", BillCyclStp.class, billCyclStp));
	//	return returnFirstObject(getList(dbKind, "getSetLoc", BillCyclStp.class, billCyclStp));
		return returnFirstObject(cblDao.getSetLoc(billCyclStp));
	}
	
	private BillStp getStpVal(BillCyclStp billCyclStp) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++"+billCyclStp.toString());
		//return  returnFirstObject(getList(dbKind, "getStpSetVal", BillCyclStp.class, billCyclStp));
		//return returnFirstObject(getList(dbKind, "getStpSetVal", BillCyclStp.class, billCyclStp));
		return returnFirstObject(cblDao.getStpSetVal(billCyclStp));
	}
	
	/**
	 * 청구일자를 조회한다.
	 * @return
	 */
	@Override
	public String getCyclBillDt(BillCyclStp billCyclStp) {
		billCyclStp.setSetItmId(cycleBillDt);
		BillCyclStp bcs = getCycleStpSetVal(billCyclStp);
		
		if (bcs != null) {
			return billCyclStp.getBillYymm() + bcs.getSetVal();
		}
		
		return null;
	}
	
	
	@Override
	public String getCyclExrateAplyDt(BillCyclStp billCyclStp) {
		 billCyclStp.setSetItmId(cycleExchrateDt);
		 BillCyclStp bcs = getCycleStpSetVal(billCyclStp);
			
			if (bcs != null) {
				return bcs.getSetVal();
			}
			
			return null;
	}
	
	/**
	 * 납기일자를 조회한다.
	 * @return
	 */
	@Override
	public String getCyclPayDueDt(BillCyclStp billCyclStp) {
		billCyclStp.setSetItmId(cyclePayDueDt);
		BillCyclStp bcs = getCycleStpSetVal(billCyclStp);
		
		if (bcs != null) {
			return bcs.getSetVal();
		}
		
		return null;
	}
	
	private String getStpSetVal(BillCyclStp billCyclStp) {
		checkEmpty(billCyclStp.getSoId(), "SO_ID");
		checkEmpty(billCyclStp.getSetItmId(), "SET_ITM_ID");
		checkEmpty(billCyclStp.getBillCycl(), "BILL_CYCL");
//		
//		String setLoc = getSetLoc(billCyclStp);
//		
//		billCyclStp.setSetLoc(setLoc);
		
		BillStp billStp = getStpVal(billCyclStp);
		
		if (billStp == null) {
			BillCyclStp bcs = new BillCyclStp();
			CUtil.copyObjectValue(billCyclStp, bcs);
			bcs.setSoId("00");
			billStp = getStpVal(billCyclStp);
		}
		
		if (billStp == null) {
			return "0";
		}
		
		return billStp.getSetVal();
	}

	/**
	 * 공급자사업자번호를 조회한다.
	 * @param billCyclStp
	 * @return
	 */
	@Override
	public String getSupplyBizNo(BillCyclStp billCyclStp) {
		billCyclStp.setSetItmId(configSubblyBizNo);
//		return getOne(dbKind, "getStpSetVal", BillCyclStp.class, billCyclStp);
		return getStpSetVal(billCyclStp);
	}
	
	/**
	 * 부가세율을 조회한다.
	 * @param billCyclStp
	 * @return
	 */
	@Override
	public String getVatRate(BillCyclStp billCyclStp) {
		billCyclStp.setSetItmId(configVat);
		//return getOne(dbKind, "getStpSetVal", BillCyclStp.class, billCyclStp);
		return getStpSetVal(billCyclStp);
	}
	
	/**
	 * 세금계산서 품목(적요)을 조회한다.
	 * @param billCyclStp
	 * @return
	 */
	@Override
	public String getRemark(BillCyclStp billCyclStp) {
		billCyclStp.setSetItmId(configRemark);
		return getStpSetVal(billCyclStp);
	}
	
	@Override
	public String getTaxIssDt(BillCyclStp billCyclStp) {
		billCyclStp.setSetItmId(taxIssDt);
		return getStpSetVal(billCyclStp);
	}
	
	/**
	 * ISP정보 Tim no를 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	 @Override
	public String getIspInfoTimNo(BillCyclStp billCyclStp) {
		 billCyclStp.setSetItmId(confIspTimNo);
		 return getStpSetVal(billCyclStp);
	}
	
	/**
	 * ISP정보 사업자 명을 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	 @Override
	public String getIspInfoName(BillCyclStp billCyclStp) {
		 billCyclStp.setSetItmId(confIspName);
		 return getStpSetVal(billCyclStp);
	}
	
	/**
	 * ISP정보 주소를 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	 @Override
	public String getIspInfoAddress(BillCyclStp billCyclStp) {
		 billCyclStp.setSetItmId(confIspAddress);
		 return getStpSetVal(billCyclStp);
	}
	
	/**
	 * ISP정보 url을 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	 @Override
	public String getIspInfoUrl(BillCyclStp billCyclStp) {
		 billCyclStp.setSetItmId(confIspTimNo);
		 return getStpSetVal(billCyclStp);
	}
	
	/**
	 * ISP정보 사업자 전화번호를 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	 @Override
	public String getIspInfoTel(BillCyclStp billCyclStp) {
		 billCyclStp.setSetItmId(confIspTel);
		 return getStpSetVal(billCyclStp);
	}
	
	/**
	 * ISP정보 Fax를 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	 @Override
	public String getIspInfoFax(BillCyclStp billCyclStp) {
		 billCyclStp.setSetItmId(confIspFax);
		 return getStpSetVal(billCyclStp);
	}
	
	/**
	 * ISP정보 Email을 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	@Override
	public String getIspInfoEmail(BillCyclStp billCyclStp) {
		billCyclStp.setSetItmId(confIspEmail);
		return getStpSetVal(billCyclStp);
	}
	 
	@Override
	public String getExrateAplyDt() {
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		return cblDao.getExrateAplyDt(currentDate);
	}
	

	
	 
	protected void checkEmpty(String value, String valName) {
		if (StringUtils.isEmpty(value) == true) {
			throw new RuntimeException(messageService.getMessage("log.err.empty.value", valName));
		}
	}
}
