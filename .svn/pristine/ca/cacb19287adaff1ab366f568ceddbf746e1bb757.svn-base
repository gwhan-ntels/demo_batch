package com.ntels.ccbs.batch.common;
/**
 * 
 */

/**
 * @author ntels_shlee
 *
 */
public class CBlSql {

	public String setTblivExRateInfo() {
		String strBuf;
		strBuf = new String();
		strBuf = "  SELECT  CRNCY_CD, EXRATE  \n" + "  FROM    TBLIV_EXRATE_INFO \n" + "  WHERE   EXRATE_APLY_DT  = \n"
				+ "    ( SELECT MAX(EXRATE_APLY_DT) FROM TBLIV_EXRATE_INFO \n"
				+ "      WHERE EXRATE_APLY_DT <= TO_CHAR(sysdate,'YYYYMMDD') ) \n";
		System.out.println("setTblivExrateInfo-[" + strBuf + "]");
		return strBuf;

	}

	public String selTblivBillStp(String so_id, String bill_yymm, String bill_cycl) {
		String strBuf;
		strBuf = new String();
		strBuf = "  SELECT  SET_ITM_ID, SET_VAL \n" + "  FROM    TBLIV_BILL_CYCL_STP \n" + "  WHERE   bill_yymm  = '"
				+ bill_yymm + "' \n" + "    AND   bill_cycl  = '" + bill_cycl + "' \n" + "    AND   SO_ID      = '"
				+ so_id + "' \n";

		System.out.println("selTblivBillStp-[" + strBuf + "]");
		return strBuf;
	}

	public String updTblcaClcMain(String clcWrkNo, String stat_cd) {
		String strBuf;
		strBuf = new String();
		strBuf = " UPDATE TBLCA_CLC_MAIN " + " SET CLC_PROC_STAT = '" + stat_cd + "',"
				+ " PROC_DTTM = TO_CHAR( SYSDATE, 'YYYYMMDDHH24MISS')" + " WHERE CLC_WRK_NO = '" + clcWrkNo + "'";

		System.out.println("updTblcaClcMain-[" + strBuf + "]");
		return strBuf;
	}

	public String selTblcaClcMain(String clcWrkNo) {
		String strBuf;
		strBuf = new String();
		strBuf = " SELECT BILL_YYMM, BILL_CYCL, TO_NUMBER(CLC_WRK_CL) AS CLC_WRK_CL " + " FROM TBLCA_CLC_MAIN "
				+ " WHERE CLC_WRK_NO = '" + clcWrkNo + "'";

		System.out.println("selTblcaClcMain-[" + strBuf + "]");
		return strBuf;
	}

	public String insTblivClsInfo(String clsTskCl, String bill_yymm, String billCycl, String clsYn, String so_id,
			String reg_id) {
		String strBuf;
		strBuf = new String();
		strBuf = " INSERT INTO TBLIV_CLS_INFO ("
				+ " CLS_TSK_CL, BILL_YYMM, BILL_CYCL, CLS_YN, CLS_DT, SO_ID, REGR_ID, REG_DATE, "
				+ " CHGR_ID, CHG_DATE ) VALUES ( '" + clsTskCl + "','" + bill_yymm + "','" + billCycl + "','" + clsYn
				+ "', " + " to_char(sysdate,'YYYYMMDD'),'" + so_id + "','" + reg_id + "', " + " NULL,NULL) ";

		System.out.println("insTblivClsInfo-[" + strBuf + "]");
		return strBuf;
	}

	public String updTblivClsInfo() {
		String strBuf;
		strBuf = new String();
		strBuf = " UPDATE TBLIV_CLS_INFO " + " SET CLS_YN = ? " + " WHERE CLS_TSK_CL = ? " + " AND   BILL_YYMM  = ? "
				+ " AND   SO_ID      = ? ";
		System.out.println("updTblivClsInfo-[" + strBuf + "]");
		return strBuf;
	}

	public String updTblivClsInfo(String so_id, String yymmdd, String cls_tsk_cl, String clc_yn) {
		String strBuf;
		strBuf = new String();
		strBuf = " UPDATE TBLIV_CLS_INFO " + " SET CLS_YN = '" + clc_yn + "' " + " WHERE CLS_TSK_CL = '" + cls_tsk_cl
				+ "' " + " AND   BILL_YYMM  = '" + yymmdd + "' " + " AND   SO_ID      = '" + so_id + "' ";

		System.out.println("updTblivClsInfo-[" + strBuf + "]");
		return strBuf;
	}

	public String selTblivBatWrkMap(String so_id, String grp_id, String pgm_id) {

		String strBuf;
		strBuf = new String();
		strBuf = " SELECT * FROM TBLIV_BAT_WRK_MAP " + " WHERE BAT_GRP_ID = '" + grp_id + "'" + " AND   BAT_PGM_ID = '"
				+ pgm_id + "'" + " AND   SO_ID      = '" + so_id + "'";

		System.out.println("selTblivBatWrkMap-[" + strBuf + "]");
		return strBuf;
	}

	public String selTblivBatWrkMap() {

		String strBuf;
		strBuf = new String();
		strBuf = " SELECT * FROM TBLIV_BAT_WRK_MAP " + " WHERE BAT_GRP_ID = ? " + " AND   BAT_PGM_ID = ? "
				+ " AND   SO_ID      = ? ";
		System.out.println("selTblivBatWrkMap-[" + strBuf + "]");
		return strBuf;
	}

	public String selTblivBatPgm(String pgm_id) {

		String strBuf;
		strBuf = new String();
		strBuf = " SELECT BAT_PGM_ID, PGM_NM,EXEC_OBJ,CLS_STP_CL,CLS_TSK_CL,	PGM_CT " + " FROM TBLIV_BAT_PGM "
				+ " WHERE BAT_PGM_ID = '" + pgm_id + "'";

		System.out.println("selTblivBatPgm-[" + strBuf + "]");
		return strBuf;
	}

	public String updTblivBatPgmLog(long readCnt, long procCnt, long errCnt, long writeCnt, String grpId, String soId,
			String pgmId, String batchSeq, String workYymm, String bat_proc_stat) {
		String strBuf;
		strBuf = new String();
		strBuf = " UPDATE TBLIV_BAT_PGM_LOG " + " SET READ_CNT = " + readCnt + "," + " PROC_CNT     = " + procCnt + ","
				+ " ERR_CNT      = " + errCnt + "," + " CMPL_CNT     = " + writeCnt + ","
				+ " PGM_END_DTTM = TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS')," + " BAT_PROC_STAT = '" + bat_proc_stat + "'"
				+ " WHERE BAT_GRP_ID    = '" + grpId + "'" + "  AND bs_yymm        = '" + workYymm + "'"
				+ "  AND BAT_PGM_ID     = '" + pgmId + "'" + "  AND SO_ID          = '" + soId + "'"
				+ "  AND PGM_EXE_SEQ_NO = '" + batchSeq + "'";
		System.out.println("updTblivBatPgmLog-[" + strBuf + "]");

		return strBuf;
	}

	public String insTblivBatPgmLog(String log_file_path, String log_file_nm, String bat_proc_stat, String grp_id,
			String so_id, String bat_pgm_id, String pgm_exe_seq_no, String bs_yy_mm, String bill_cycl, String reg_id) {
		String strBuf;
		strBuf = new String();
		strBuf = " INSERT INTO TBLIV_BAT_PGM_LOG ( " + " BAT_GRP_ID, BS_YYMM,  BAT_PGM_ID, SO_ID, PGM_EXE_SEQ_NO, "
				+ " BILL_CYCL, BAT_PROC_STAT,  LOG_FILE_PTH, LOG_FILE_NM, " + " PGM_STRT_DTTM, REG_DATE ) VALUES ( "
				+ " '" + grp_id + "'," + " '" + bs_yy_mm + "'," + " '" + bat_pgm_id + "'," + " '" + so_id + "'," + " '"
				+ pgm_exe_seq_no + "'," + " '" + bill_cycl + "'," + " '" + bat_proc_stat + "'," + " '" + log_file_path
				+ "'," + " '" + log_file_nm + "'," + " to_char(sysdate,'YYYYMMDDHH24MISS')," + " sysdate)";

		System.out.println("insTblivBatPgmLog-[" + strBuf + "]");
		return strBuf;
	}

	public String selTblivBatPgmLog(String pgm_id) {
		String strBuf;
		strBuf = new String();
		strBuf = " SELECT * FROM TBLIV_BAT_PGM " + " WHERE BAT_PGM_ID = '" + pgm_id + "'";

		System.out.println("selTblivBatPgmLog-[" + strBuf + "]");
		return strBuf;
	}

	public String updTblivBatPgmLog() {
		String strBuf;
		strBuf = new String();
		strBuf = " UPDATE TBLIV_BAT_PGM_LOG " + " SET READ_CNT        = ?, " + " PROC_CNT            = ?, "
				+ " ERR_CNT             = ?, " + " CMPL_CNT            = ?, " + " LOG_FILE_PTH        = ?, "
				+ " LOG_FILE_NM         = ?, " + " BAT_PROC_STAT       = ?, "
				+ " PGM_END_DTM = TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS') ," + " WRK_FR_DT           = ?, "
				+ " WRK_TO_DT           = ?, " + " WHERE BAT_GRP_ID    = ?  " + " AND SO_ID           = ?  "
				+ " AND BAT_PGM_ID      = ?  " + " AND PGM_EXE_SEQ_NO  = ?  ";

		System.out.println("updTblivBatPgmLog-[" + strBuf + "]");
		return strBuf;
	}

}
