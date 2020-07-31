package com.ntels.ccbs.batch.ch.common.service;

import java.text.ParseException;
import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.DiscExcl;
import com.ntels.ccbs.batch.ch.common.entity.IntergratedList;
import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.common.entity.RateFctr;
import com.ntels.ccbs.batch.ch.common.entity.RateInfo;

public interface RateInfoService {

	public List<RateInfo> list(String chrgCtgry);
	public Multi listMulti(Multi multi) throws ParseException;
	public List<RateInfo> listRateItm(Multi multi);
	public List<RateAttr> listRrateAttr(Multi multi);
	public List<RateAttr> listDrateAttr(Multi multi);
	public Multi listMultiCtrt(Multi multi);
	public List<DiscExcl> listDiscExcl(Multi multi);
	public List<RateFctr> listRateFctr(Multi multi);
	
	public boolean getOprndChk(RateInfo listRate, IntergratedList list, String listFctrVal);
	public boolean getFctrChk(RateInfo listRate, IntergratedList list, String listFctrVal, Long rateFctrNo);
	
	public int getDelimiterCnt(String oprnd1, String delimiter);
	public String[] splitString(String oprnd1, String delimiter);

	public List<RateAttr> listCrateAttr(Multi multi);
}