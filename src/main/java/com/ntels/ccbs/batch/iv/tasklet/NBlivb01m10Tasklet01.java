package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.service.BillCustJdbcService;
import com.ntels.ccbs.batch.iv.service.NBlivb01m10Service;

/**
 * 청구 대상자 생성 Tasklet
 * 
 * @author Cashyalla
 *
 */
@Component("nBlivb01m10Tasklet01")
@Scope("step")
public class NBlivb01m10Tasklet01 extends LazyLoaderLogingTasklet<BillCust, BillCust> {

	@Autowired
	private NBlivb01m10Service nBlivb01m10JdbcService;

	@Autowired
	private BillCustJdbcService billCustJdbcService;
	private String remark;
	private String oldBillSeqNo = "";
	private String oldProdCmpsId = "";
	private String oldSvcCmpsId = "";

	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}

	@Override
	protected LazyLoader<BillCust> getLoader() throws Exception {

		String billSeqNo = billYymm.substring(2, 6) + billCycl;

//		remark = getRemark();

		BillCust billCust = new BillCust();
		// 청구번호 임시값
		billCust.setBillSeqNo(billSeqNo);
		billCust.setBillYymm(billYymm);
		billCust.setBillCycl(billCycl);
		billCust.setSoId(soId);
		billCust.setpSeq(pgmSeq);
		billCust.setReceivedBillSeqNo(false);

		return nBlivb01m10JdbcService.getBillCustInfo(billCust);
	}

	@Override
	protected BillCust process(BillCust item) {

		item.setRmrk(remark);

		item.setLstReIssSeq(0);
		if (item.getBillSeqNo().equals(oldBillSeqNo) == false) {

			oldBillSeqNo = item.getBillSeqNo();

			if (item.getBillAmt() > item.getSmlBsAmt()) {
				item.setSmlAmtYn("N");
			} else {
				if (item.getCustTp().equals("B") == true || item.getCustTp().equals("D") == true) {
					if (item.getBillAmt() > 0) {
						item.setSmlAmtYn("N");
					} else {
						item.setSmlAmtYn("Y");
					}
				} else {
					item.setSmlAmtYn("Y");
				}
			}

			item.setPymMthd(item.getPayMthd());
			item.setBillFlCrtYn("N");
			item.setLstReIssSeq(0);
			item.setBillExptYn("N");
			item.setRegDate(new Timestamp(new Date().getTime()));

			item.setBillCust(item);

			if (oldProdCmpsId.equals(item.getProdCmpsId()) == false && oldSvcCmpsId.equals(item.getSvcCmpsId()) == false) {

				oldProdCmpsId = item.getProdCmpsId();
				oldSvcCmpsId = item.getSvcCmpsId();

				BillCust billCustDetail = new BillCust();
				billCustDetail.setBillSeqNo(item.getBillSeqNo());
				billCustDetail.setBillYymm(item.getBillYymm());
				billCustDetail.setProdCmpsId(item.getProdCmpsId());
				billCustDetail.setSvcCmpsId(item.getSvcCmpsId());
				billCustDetail.setSoId(item.getSoId());
				billCustDetail.setGrpId(item.getGrpId());
				billCustDetail.setPymAcntId(item.getPymAcntId());
				billCustDetail.setCustId(item.getCustId());
				billCustDetail.setCtrtId(item.getCtrtId());
				billCustDetail.setProdCd(item.getProdCd());
				billCustDetail.setSvcCd(item.getSvcCd());
				billCustDetail.setSvcGrp(item.getSvcGrp());
				billCustDetail.setAtrtCorpId(item.getAtrtCorpId());
				billCustDetail.setAtrtEmpId(item.getAtrtEmpId());
				billCustDetail.setRegDate(new Timestamp(new Date().getTime()));
				billCustDetail.setUseStrtDt(item.getUseStrtDt());
				billCustDetail.setUseEndDt(item.getUseEndDt());

				billCustDetail.setBillFlCrtYn("N");

				item.setBillCustDetail(billCustDetail);

			}
		}

		return item;
	}

	@Override
	protected void write(List<BillCust> itemList) {
		clog.writeLog("NBlivb01m10JobWriter.write");

		List<BillCust> billCustDetailList = new ArrayList<>();
		List<BillCust> billCusts = new ArrayList<>();

		for (BillCust billCust : itemList) {
			if (billCust.getBillCustDetail() != null) {
				billCustDetailList.add(billCust.getBillCustDetail());
			}

			if (billCust.getBillCust() != null) {
				billCusts.add(billCust.getBillCust());
			}
		}

		billCustJdbcService.insertBillCust(billCusts);
		billCustJdbcService.insertBillCustDetail(billCustDetailList);

		clog.writeLog("nBCCnt[{}], nDetailCnt[{}]", billCusts.size(), billCustDetailList.size());
	}

	@Override
	protected RepeatStatus end() {
		return RepeatStatus.FINISHED;
	}

}
