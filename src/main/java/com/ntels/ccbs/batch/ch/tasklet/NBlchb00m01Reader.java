package com.ntels.ccbs.batch.ch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.service.RateInfoService;
import com.ntels.ccbs.batch.ch.entity.NBlchb00m01;
import com.ntels.ccbs.batch.ch.service.NBlchb00m01Service;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;

/**
 * <PRE>
 * 1. ClassName: NBlchb00m01Reader
 * 2. FileName : NBlchb00m01Reader.java
 * 3. Package  : com.ntels.ccbs.batch.ch.tasklet
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2017. 3. 16. 오후 3:16:09
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2017. 3. 16.	: 신규 개발.
 * </PRE>
 */
@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class NBlchb00m01Reader extends CommonItemReader<NBlchb00m01> implements StepExecutionListener {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NBlchb00m01Service nBlchb00m01Service;

	@Autowired
	private RateInfoService rateInfoService;

	LazyLoader<NBlchb00m01> list;

	@Value("${dbms.kind}")
	String dbKind;	
	
	private Multi multi = new Multi();

	public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) {
		super.beforeStep(stepExecution);

		try {
			multi.setSoId(soId);
			multi.setBillYymm(billYymm);
			multi.setClcWrkNo(clcWrkNo);
			multi.setBillCycl(billCycl);
			multi.setMultiCycl(billCycl.substring(0, 1));
			multi.setSeq(billCycl.substring(1, 2));
			multi.setpSeq("2");
			multi.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));

			multi = rateInfoService.listMulti(multi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected LazyLoader<NBlchb00m01> getLoader() {
		Multi multiTmp = nBlchb00m01Service.getColId(multi);
		// LazyLoader<NBlchb00m01> colID = nBlchb00m01Service.getColId(multi);

		System.err.println("multiTmp.getColId()====================>" + multiTmp.getColId());

		// 오라클하고 mysql 분리 할 것
		String str[] = multiTmp.getColId().split(",");
		String colId = ", CONCAT(";
		String orgColId = "";
		String val = "";

		for (int i = 0; i < str.length; i++) {

			orgColId = str[i];
			String str1[] = orgColId.split("\\.");

			if (dbKind == "mys" || dbKind.equals("mys")) {
				if (i == str.length - 1) {
					colId += "'" + str1[1] + "=', IFNULL(" + orgColId + ",''),'|'," + " 'BILL_YYMM=','" + multi.getBillYymm() + "') AS COLUNM_ID";
				} else {
					colId += "'" + str1[1] + "=', IFNULL(" + orgColId + ",''),'|',";
				}
			} else {
				val = val + ")";
				if (i == str.length - 1) {
					colId += "CONCAT('" + str1[1] + "=', CONCAT(" + orgColId + ",'|'))," + " CONCAT('BILL_YYMM=','" + multi.getBillYymm() + "')" + val + "AS COLUNM_ID";
				} else {
					colId += "CONCAT('" + str1[1] + "=', CONCAT(" + orgColId + ",'|')),CONCAT(";
				}
			}
		}

		System.err.println("colId = " + colId);

		multi.setColId(multiTmp.getColId() + ",'" + multi.getBillYymm() + "' AS BILL_YYMM" + colId);

		System.err.println("getMultiCycl=>" + multi.getMultiCycl());

		if (multi.getMultiCycl().equals("Z")) {
			return nBlchb00m01Service.listJdbcDirectAll(multi);
		} else {
			return nBlchb00m01Service.listJdbcDirect(multi);
		}
	}

	public ExitStatus afterStep(org.springframework.batch.core.StepExecution stepExecution) {
		if (multi.getMultiCycl().equals("Z")) {
			nBlchb00m01Service.saveIfCustInfoAll(multi);
		} else {
			nBlchb00m01Service.saveIfCustInfo(multi);
		}
		super.afterStep(stepExecution);

		return null;
	}

	@Override
	protected void setItemDefaultValue(NBlchb00m01 item) {

	}

	@Override
	protected void lastItem(NBlchb00m01 item) {

	}
}
