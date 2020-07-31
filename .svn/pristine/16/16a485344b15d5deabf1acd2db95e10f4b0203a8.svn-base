package com.ntels.ccbs.batch.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;

public class CUtil {

	public String buf;

	public CUtil() {
	}

	public static double Round(double d_value, int pos, int mode) {

		double d_ret = 0;
		String s_buf;
		int nlen = 0, npos = 0;
		int i = 0;
		BigDecimal bd = null;
		BigDecimal result = null;

		s_buf = String.valueOf(d_value);
		bd = new BigDecimal(s_buf);

		npos = pos * -1;

		switch (mode) {
		case 1: // Round Down
			result = bd.setScale(npos, BigDecimal.ROUND_DOWN);
			break;

		case 2: // Round Half up
			result = bd.setScale(npos, BigDecimal.ROUND_HALF_UP);
			break;

		case 3: // Round Up
			result = bd.setScale(npos, BigDecimal.ROUND_UP);
			break;

		default:
			d_ret = d_value;
			break;
		}
		d_ret = result.doubleValue();
		return d_ret;
	}

	public static Vector<String> utilToken(String s, String tokenvalue) {
		Vector<String> vRet;
		int i = 0, nRet = 0;
		String value;

		vRet = new Vector<String>();

		value = s;

		StringTokenizer st = new StringTokenizer(value, tokenvalue);

		nRet = st.countTokens();

		while (i < nRet) {
			vRet.add(st.nextToken());
			// System.out.println(i + ":" + st.nextToken());
			i++;
		}
		return vRet;
	}

	public static String utilGetDateTime(int n_mode) {
		String sRet = null;
		SimpleDateFormat sf = null;
		Date date = new Date();

		switch (n_mode) {
		case 1: // DATETIME:
			sf = new SimpleDateFormat("yyyyMMddHHmmss");
			break;
		case 2: // DATE:
			sf = new SimpleDateFormat("yyyyMMdd");
			break;
		case 3: // TIME:
			sf = new SimpleDateFormat("HHmmss");
			break;
		case 4: // PRINT_DATETIME:
			sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			break;
		case 5: // PRINT_DATE:
			sf = new SimpleDateFormat("yyyy/MM/dd");
			break;
		case 6: // PRINT_TIME:
			sf = new SimpleDateFormat("HH:mm:ss");
			break;
		default:
			return null;
		}
		sRet = sf.format(date);
		return sRet;
	}

	public static String getLastDay(String yyyyMm){
		Calendar cal = Calendar.getInstance();

		int year = Integer.parseInt(yyyyMm.substring(0, 4));
		int month = Integer.parseInt(yyyyMm.substring(4,yyyyMm.length()));

		cal.set(year, month-1, 1); // 0~11개월로 셋팅

		return String.format("%02d", cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
	public static String getNextDay(String yyyyMmDd) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar cal = GregorianCalendar.getInstance();

		cal.setTime(format.parse(yyyyMmDd));
		
		cal.add(Calendar.DATE, 1);

		return format.format(cal.getTime());
	}

	public static String getCompDate(String day1, String day2, String max){
		Date dt1 = null;
		Date dt2 = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			dt1 = format.parse(day1);
			dt2 = format.parse(day2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (max == "MAX"){
			int compare = dt1.compareTo(dt2);
			if(compare >0 ){
				return format.format(dt1);				
			} else {
				return format.format(dt2);
			}
		}
		else{
			int compare = dt1.compareTo(dt2);
			if(compare < 0 ){
				return format.format(dt1);				
			} else {
				return format.format(dt2);
			}
		}
	}

	public static String getCompDate(String day1, String day2, String day3, String max){
		Date dt1 = null;
		Date dt2 = null;
		Date dt3 = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			dt1 = format.parse(day1);
			dt2 = format.parse(day2);
			dt3 = format.parse(day3);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (max == "MAX"){
			int compare = dt1.compareTo(dt2);
			if(compare >0 ){
				compare = dt1.compareTo(dt3);
				if(compare >0){
					return format.format(dt1);	
				}
				else{
					return format.format(dt3);
				}
			} else {
				compare = dt2.compareTo(dt3);
				if(compare >0){
					return format.format(dt2);	
				}
				else{
					return format.format(dt3);
				}
			}
		}
		else{
			int compare = dt1.compareTo(dt2);
			if(compare < 0 ){
				compare = dt1.compareTo(dt3);
				if(compare < 0){
					return format.format(dt1);	
				}
				else{
					return format.format(dt3);
				}
			} else {
				compare = dt2.compareTo(dt3);
				if(compare < 0){
					return format.format(dt2);	
				}
				else{
					return format.format(dt3);
				}
			}
		}
	}

	
	public static String getUseDay(String day1, String day2, int attr){
		String stDt = getCompDate(day1, day2, "");
		String endDt = getCompDate(day1, day2, "MAX");
		long diff=0;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			diff = (format.parse(endDt).getTime() - format.parse(stDt).getTime()) / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String days = Integer.toString((int) (diff+attr));

		return days;
	}
	
	public static Calendar getCalendarFromYyyyMM(String yyyyMM) throws ParseException {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		c.setTime(format.parse(yyyyMM));
		return c;
	}
	
	public static String addMonths(String yyyyMM, int months) throws ParseException {
		Calendar c = getCalendarFromYyyyMM(yyyyMM);
		c.add(Calendar.MONTH, months);
		
		String strYear = Integer.toString(c.get(Calendar.YEAR));
		
		int month = c.get(Calendar.MONTH) + 1;
		String strMonth = month < 10 ? "0" + month : Integer.toString(month);
		
		return strYear + strMonth;
	}
	
	public static int getLastDayOfMonth(String yyyyMM) throws ParseException {
		Calendar c = getCalendarFromYyyyMM(yyyyMM);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static void copyObjectValue(Object src, Object dest) {
		Field[] bFields = dest.getClass().getDeclaredFields();
		
		for (Field field : bFields) {
			String name = field.getName();
//			name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

			if(Character.isUpperCase(name.charAt(1))){
				name = name.substring(0);
			}else{
				name = Character.toUpperCase(name.charAt(0))+name.substring(1);
			}
			
			try {
				
				Method get = src.getClass().getDeclaredMethod("get" + name);
				
				Object value = get.invoke(src);
				
				if (value == null) {
					continue;
				}
				
				Method set = dest.getClass().getDeclaredMethod("set" + name, get.getReturnType());

				set.invoke(dest, value);
			} catch (Exception e) {
				// 에러 무시		
			}
		}
	}

	public static int getIntCompdate(String day1, String day2){
		Date dt1 = null;
		Date dt2 = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			dt1 = format.parse(day1);
			dt2 = format.parse(day2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int compare = dt1.compareTo(dt2);
		return compare;			
	}	

	public static boolean isExistBetweenDays(String stDay, String endDay, String midDay) {
		Date stDate = null;
		Date endDate = null;
		Date midDate = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = GregorianCalendar.getInstance();

		try {
			// 하루 전 일자로 변경
			stDate = format.parse(stDay);

			cal.setTime(stDate);
			cal.add(Calendar.DATE, -1);
			String stDayTemp = format.format(cal.getTime());

			stDate = format.parse(stDayTemp);

			endDate = format.parse(endDay);
			midDate = format.parse(midDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (stDate.before(midDate) && endDate.after(midDate));
	}

	/*
	 * public static void utilTokenRelease(char *p_arch_src[], int n_count);
	 * public static char utilGetTokenAt(char *p_ch_src, char *p_ch_dst, char
	 * c_delimiter, int n_pos); public static char utilGetToken(char *p_ch_src,
	 * char *p_ch_dst, char c_delimiter); public static int utilHalfToFull(char
	 * *p_ch_half, char *p_ch_full); public static int utilFullToHalf(char
	 * *p_ch_full, char *p_ch_half); public static int utilRTrim(char
	 * *p_ch_buff, int n_len); public static int utilLTrim(char *p_ch_buff, int
	 * n_len); public static char* utilRTrim( char *string) ; public static int
	 * utilTrim(char *p_ch_buff, int n_len); public static int
	 * utilCorrectKorean(char* p_ch_src, char* p_ch_dst); public static int
	 * NumStrMove(char *apcSrc,char *apcDes,int nLen); public static int
	 * NumStrMoveSp(char *apcSrc,char *apcDes,int nLen); public static int
	 * DoubleChar(char *apcSrc,char *apcDes,int nLen); public static int
	 * utilIsYearGood(int n_year); public static int utilIsMonthGood(int
	 * n_month); public static int utilIsLeapyear(int n_year); public static int
	 * utilGetDate(char* p_ch_yearmonth); public static int utilGetJulian(char*
	 * p_ch_yearmonthday); public static int utilGetCalYM(char* p_ch_src_ym,
	 * char* p_ch_dst_ym, int n_add_month); public static int
	 * utilGetCalYMD(char* p_ch_src_ymd, char* p_ch_dst_ymd, int n_add_day);
	 * public static int utilGetCalYMDHMS(char* p_ch_src_ymd, char*
	 * p_ch_src_hms, char* p_ch_dst_ymdhms, int n_add, int mode); public static
	 * long utilConvToSec(char* p_ch_src_hms); public static char
	 * utilToWon(double d_value); public static int SplitKey(ST_LOGKEY*
	 * p_st_logkey, char* p_ch_data); public static int SplitKeySt(ST_LOGKEY*
	 * p_st_logkey, char* p_ch_data); public static int SplitKeySt2(ST_LOGKEY*
	 * p_st_logkey, char* p_ch_data); public static char* GetArgu( char
	 * *p_ch_buff, int npos ) ; public static void BL_time_display(char*
	 * ptrDate); public static double RoundDown2( double d_value );
	 */

}
