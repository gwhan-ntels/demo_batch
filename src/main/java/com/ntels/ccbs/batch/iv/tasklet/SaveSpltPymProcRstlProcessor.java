package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPymProcRslt;

@Component
public class SaveSpltPymProcRstlProcessor extends BaseService implements ItemProcessor<BillSpltPym, BillSpltPymProcRslt> {

	@Override
	public BillSpltPymProcRslt process(BillSpltPym item) throws Exception {
		
		BillSpltPymProcRslt billSpltPymProcRslt = new BillSpltPymProcRslt();
		
		billSpltPymProcRslt.setBillSeqNo(item.getBillSeqNo());
		billSpltPymProcRslt.setUseYymm(item.getUseYymm());
		billSpltPymProcRslt.setProdCmpsId(item.getProdCmpsId());
		billSpltPymProcRslt.setSvcCmpsId(item.getSvcCmpsId());
		billSpltPymProcRslt.setChrgItmCd(item.getChrgItmCd());
		billSpltPymProcRslt.setBillYymm(item.getBillYymm());
		billSpltPymProcRslt.setBillCycl(item.getBillCycl());
		billSpltPymProcRslt.setBillDt(item.getBillDt());
		billSpltPymProcRslt.setSoId(item.getSoId());
		billSpltPymProcRslt.setGrpId(item.getGrpId());
		billSpltPymProcRslt.setPymAcntId(item.getPymAcntId());
		billSpltPymProcRslt.setCtrtId(item.getCtrtId());
		billSpltPymProcRslt.setProdCd(item.getProdCd());
		billSpltPymProcRslt.setSvcCd(item.getSvcCd());
		billSpltPymProcRslt.setBizCl(item.getBizCl());
		billSpltPymProcRslt.setProdGrp(item.getProdGrp());
		billSpltPymProcRslt.setSvcGrp(item.getSvcGrp());
		billSpltPymProcRslt.setBillMmTp(item.getBillMmTp());
		billSpltPymProcRslt.setSaleTp(item.getSaleTp());
		billSpltPymProcRslt.setUsgCnt(item.getUsgCnt());
		billSpltPymProcRslt.setUsgAmt(item.getUsgAmt());
		billSpltPymProcRslt.setAdjPrvBillAmt(item.getAdjPrvBillAmt());
		billSpltPymProcRslt.setAdjAmt(item.getAdjAmt());
		billSpltPymProcRslt.setBillAmt(item.getBillAmt());
		billSpltPymProcRslt.setPayDueDt(item.getPayDueDt());
		billSpltPymProcRslt.setSpltPymTp(item.getSpltPymTp());
		billSpltPymProcRslt.setChrgCtgry(item.getChrgCtgry());
		billSpltPymProcRslt.setAtrtCorpId(item.getAtrtCorpId());
		billSpltPymProcRslt.setAtrtEmpId(item.getAtrtEmpId());
		billSpltPymProcRslt.setWonBillAmt(item.getWonBillAmt());
		billSpltPymProcRslt.setCrncyCd(item.getCrncyCd());
		billSpltPymProcRslt.setExrate(item.getExrate());
		billSpltPymProcRslt.setExrateAplyDt(item.getExrateAplyDt());
		billSpltPymProcRslt.setRegDate(now());
		
		return billSpltPymProcRslt;
	}

}
