package com.xiaocui.platform.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final int DEFAULT = 0;
	public static final int YM = 1;
	public static final int YMR_SLASH = 11;
	public static final int NO_SLASH = 2;
	public static final int YM_NO_SLASH = 3;
	public static final int DATE_TIME = 4;
	public static final int DATE_TIME_NO_SLASH = 5;
	public static final int DATE_HM = 6;
	public static final int TIME = 7;
	public static final int HM = 8;
	public static final int LONG_TIME = 9;
	public static final int SHORT_TIME = 10;
	public static final int DATE_TIME_LINE = 12;

	public static String dateToStr(Date date, String pattern) {
		if ((date == null) || (date.equals("")))
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static String dateToStr(Date date) {
		return dateToStr(date, "yyyy/MM/dd");
	}

	public static Date getDate(long mil) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(mil);
		return c.getTime();
	}

	public static String longTimeToYMD(long mil) {
		Date dt = getDate(mil);
		String result = dateToStr(dt, YMR_SLASH);
		return result;
	}

	public static String dateToStr(Date date, int type) {
		switch (type) {
		case 0:
			return dateToStr(date);
		case 1:
			return dateToStr(date, "yyyy/MM");
		case 2:
			return dateToStr(date, "yyyyMMdd");
		case 11:
			return dateToStr(date, "yyyy-MM-dd");
		case 3:
			return dateToStr(date, "yyyyMM");
		case 4:
			return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
		case 5:
			return dateToStr(date, "yyyyMMddHHmmss");
		case 6:
			return dateToStr(date, "yyyy/MM/dd HH:mm");
		case 7:
			return dateToStr(date, "HH:mm:ss");
		case 8:
			return dateToStr(date, "HH:mm");
		case 9:
			return dateToStr(date, "HHmmss");
		case 10:
			return dateToStr(date, "HHmm");
		case 12:
			return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
		case 13:
			return dateToStr(date, "yyyy-MM");
		}
		throw new IllegalArgumentException("Type undefined : " + type);
	}

	/**
	 * 得到几天前的时间
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

}