package com.ntels.ccbs.batch.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.common.entity.Common;

public interface CommonMapper {
	public List<Common> commonListExRate();
	public List<Common> commonListBillStp(@Param("billYymm") String billYymm,@Param("billCycl") String billCycl,@Param("soId") String soId);
	public List<Common> commonListClcMain(@Param("clcWrkNo") String clcWrkNo);
	public int commonUpdClcMain(@Param("statCd") String statCd,@Param("clcWrkNo") String clcWrkNo);
	public int commonInsClsInfo(Common common);
//			@Param("clsTskCl") String clsTskCl,@Param("billYymm") String billYymm,@Param("billCycl") String billCycl
//			,@Param("clsYn") String clsYn,@Param("soId") String soId,@Param("regId") String regId
	public int commonUpdClsMain(@Param("clsYn") String clsYn,@Param("clsTskCl") String clsTskCl,@Param("billYymm") String billYymm,@Param("soId") String soId);
	public List<Common> commonListBatPgm(@Param("batPgmId") String batPgmId);
	public int commonUpdBatPgmLog(Common common);
//			@Param("readCnt") long readCnt,@Param("procCnt") long procCnt,@Param("errCnt") long errCnt,@Param("writeCnt") long writeCnt,
//			@Param("batProcStat") String batProcStat,@Param("grpId") String grpId,@Param("workYymm") String workYymm,@Param("pgmId") String pgmId,
//			@Param("soId") String soId,@Param("batchSeq") String batchSeq
	public int commonInsBatPgmLog(Common common);
//			@Param("grpId") String grpId,@Param("bsYyMm") String bsYyMm,@Param("batPgmId") String batPgmId,@Param("soId") String soId,
//			@Param("pgmExeSeqNo") String pgmExeSeqNo,@Param("billCycl") String billCycl,@Param("batProcStat") String batProcStat,@Param("logFilePath") String logFilePath,
//			@Param("logFileNm") String logFileNm	
	public int updateNextSequence(@Param("modCd") String modCd);
	public int getSequence(@Param("modCd") String modCd);
	int updateNextSequenceMulti(@Param("modCd") String modCd, @Param("size") int size);
	int batPgmLogCount(Common common);
	String batProcStat(Common common);
	int updateBatProcStat(Common common);
}
