package com.miniHr.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public abstract class DateUtil {

	/**
	 * 共有多少张历史表
	 */
	public static final Integer MAN_INDEX = new Integer(53);

	/**
	 * 历史交易信息记录周期
	 */
	public static final int CYCLE = 7;

	/**
	 * SimpleDateFormat
	 */
	private static SimpleDateFormat DATEFORMAT = new SimpleDateFormat(
			"yyyyMMdd");

	private static SimpleDateFormat DATEFORMAT2 = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private static SimpleDateFormat TIMESTAMP_FORMAT2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat TIMESTAMP_FORMAT_ALL = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	private static SimpleDateFormat TIMESTAMP_FORMAT_ALL2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss:SSS");

	/**
	 * 日期格式:yyyy-mm-dd.
	 */
	public static String DF_YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * 日期格式:YYYYMMDD.
	 */
	public static String DF_YMD = "yyyyMMdd";

	/**
	 * 日期格式:yyyy/MM/dd.
	 */
	public static String DF_YMD_CN = "yyyy/MM/dd";

	/**
	 * 日期格式:MMdd.
	 */
	public static String DF_MMDD = "MMdd";

	/**
	 * 日期格式:yyMM.
	 */
	public static String DF_YYMM = "yyMM";
	
	// add by sandyWu started
	/**
	 * 日期格式:yyMMdd.
	 */
	public static String DF_YYMMDD = "yyMMdd";
	//add by sandyWu end
	/**
	 * 日期格式:yyMM.
	 */
	public static String DF_YYYY_MM = "yyyy-MM";

	/**
	 * 日期时间格式,24小时制:yyyy-MM-dd HH:mm:ss.
	 */
	public static String DF_Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期时间格式,24小时制:yyyyMMddHHmmss.
	 */
	public static String DF_YMDHMS = "yyyyMMddHHmmss";

	/**
	 * 日期时间格式12小时制:MMDDhhmmss
	 */	
	public static String DF_MMDDhhmmss = "MMddhhmmss";
	
	/**
	 * 日期时间格式24小时制:MMDDHHmmss
	 */
	public static String DF_MMDDHHmmss = "MMddHHmmss";
	
	/**
	 * 日期时间格式24小时制:yyyyMMddHHmmssSSS
	 */
	public static String DF_YMDHMSS = "yyyyMMddHHmmssSSS";

	/**
	 * 时间格式24小时制:hh:mi:ss.
	 */
	public static String DF_HH_MI_SS = "HH:mm:ss";

	/**
	 * 时间格式24小时制:hhmiss.
	 */
	public static String DF_hhmmss = "HHmmss";

	private static int weeks = 0;

	/**
	 * 格式化当前时间为参数pattern指定的时间格式字符串.
	 * 
	 * @param pattern
	 * @return the formatted date-time string
	 * @see java.text.SimpleDateFormat
	 */
	public static String formatCurrDateTime(String pattern) {
		return formatDateTime(pattern, new Date());
	}
	
	/**
	 * 格式化指定时间为参数pattern指定的时间格式字符串
	 * 
	 * @param pattern
	 * @param stringDate(yyyyMMddHHmmss格式)
	 * @return
	 */
	public static String formatCurrDateTime2(String pattern, String stringDate) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(parse("yyyyMMddHHmmss", stringDate));
			Date date = c.getTime();
			return formatDateTime(pattern, date);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 格式化指定时间为参数pattern指定的时间格式字符串
	 * 
	 * @param pattern
	 * @param stringDate(yyyyMMdd格式)
	 * @return
	 */
	public static String formatCurrDateTime(String pattern, String stringDate) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(parse("yyyyMMdd", stringDate));
			Date date = c.getTime();
			return formatDateTime(pattern, date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化指定时间(格式为inputDatePattern)为参数pattern指定的时间格式字符串
	 * 
	 * @param pattern(输出格式)
	 * @param stringDate(指定日期时间)
	 * @param inputDatePattern(指定日期时间的输入格式)
	 * @return
	 */
	public static String formatCurrDateTime(String pattern, String stringDate,String inputDatePattern) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(parse(inputDatePattern, stringDate));
			Date date = c.getTime();
			return formatDateTime(pattern, date);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 格式化当前时间前一天为参数pattern指定的时间格式字符串.
	 * 
	 * @param pattern
	 * @return the formatted date-time string
	 * @see java.text.SimpleDateFormat
	 */
	public static String formatBeforeDateTime(String pattern) {
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -1);
			Date before = c.getTime();
			return formatDateTime(pattern, before);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化指定时间前一天为参数pattern指定的时间格式字符串.
	 * 
	 * @param pattern
	 * @param stringDate
	 *            yyyyMMdd 格式字符串
	 * @return
	 */
	public static String formatBeforeDateTime(String pattern, String stringDate) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(parse("yyyyMMdd", stringDate));
			c.add(Calendar.DAY_OF_MONTH, -1);
			Date before = c.getTime();
			return formatDateTime(pattern, before);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化当前时间后一天为参数pattern指定的时间格式字符串.
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String formatAfterDateTime(String pattern) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		Date before = c.getTime();
		return formatDateTime(pattern, before);
	}

	/**
	 * 格式化指定时间后一天为参数pattern指定的时间格式字符串.
	 * 
	 * @param pattern
	 * @param stringDate
	 *            yyyyMMdd 格式字符串
	 * @return
	 */
	public static String formatAfterDateTime(String pattern, String stringDate) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(parse("yyyyMMdd", stringDate));
			c.add(Calendar.DAY_OF_MONTH, 1);
			Date before = c.getTime();
			return formatDateTime(pattern, before);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 把参数date指定的日期格式化为参数pattern指定的日期格式字符串.
	 * 
	 * @param pattern
	 * @param date
	 * @return the formatted date-time string
	 * @see java.text.SimpleDateFormat
	 */
	public static String formatDateTime(String pattern, Date date) {
		try {
			String strDate = null;
			String strFormat = pattern;
			SimpleDateFormat dateFormat = null;

			if (date == null)
				return "";

			dateFormat = new SimpleDateFormat(strFormat);
			strDate = dateFormat.format(date);

			return strDate;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 把参数date指定的日期格式化为locale指定的本地语言格式的参数pattern指定的日期格式字符串.
	 * 
	 * @param pattern
	 * @param date
	 * @param locale
	 *            - the locale whose date format symbols should be used
	 * @return the formatted date-time string
	 * @see java.text.SimpleDateFormat
	 */
	public static String formatDateTime(String pattern, java.util.Date date,
			Locale locale) {
		String strDate = null;
		String strFormat = pattern;
		SimpleDateFormat dateFormat = null;

		if (date == null)
			return "";

		dateFormat = new SimpleDateFormat(strFormat, locale);
		strDate = dateFormat.format(date);

		return strDate;
	}

	/**
	 * Parses a string to produce a Date.
	 * 
	 * @param pattern
	 *            - the pattern of the string
	 * @param strDateTime
	 *            - the string to be parsed
	 * @return A Date parsed from the string. In case of error, returns null.
	 */
	public static java.util.Date parse(String pattern, String strDateTime) {
		return parse(pattern, strDateTime, Locale.getDefault());
	}

	/**
	 * 解析27/Feb/2008:10:12:35 +0800类似时间字符串.
	 * 
	 * @param pattern
	 * @param strDateTime
	 * @param locale
	 * @return
	 */
	public static java.util.Date parse(String pattern, String strDateTime,
			Locale locale) {
		if (strDateTime == null || pattern == null)
			return null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
			formatter.setLenient(false);
			return formatter.parse(strDateTime);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断日期格式是否正确YYYYMMDD
	 */
	public static boolean isDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat(DF_YMD);
		formatter.setLenient(false);
		try {
			formatter.format(formatter.parse(dateString));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断日期格式是否正确YYYY-MM-DD
	 */
	public static boolean isFullDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat(DF_YYYY_MM_DD);
		formatter.setLenient(false);
		try {
			formatter.format(formatter.parse(dateString));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 判断日期时间格式是否正确YYYY-MM-DD HH:MI:SS
	 */
	public static boolean isFullDateTime1(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat(DF_YMDHMS);
		formatter.setLenient(false);
		try {
			formatter.format(formatter.parse(dateString));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 判断日期时间格式是否正确YYYY-MM-DD HH:MI:SS
	 */
	public static boolean isFullDateTime(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat(DF_Y_M_D_HMS);
		formatter.setLenient(false);
		try {
			formatter.format(formatter.parse(dateString));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 转换成timestamp格式的日期字符串
	 * 
	 * @author LEO.YAN
	 */
	public static String convert2Timestamp(String strDate) {
		try {
			Date date = DATEFORMAT.parse(strDate);
			return TIMESTAMP_FORMAT2.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 转换成timestamp格式的日期字符串
	 * 
	 * @author LEO.YAN
	 */
	public static String convert3Timestamp(String strDate) {
		try {
			Date date = TIMESTAMP_FORMAT.parse(strDate);
			return TIMESTAMP_FORMAT2.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * timestamp格式的日期转成成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2string(Date date) {
		if (date == null) {
			return null;
		}
		return DATEFORMAT.format(date);
	}

	/**
	 * date格式的日期转成成yyyymmddhhmmsssss字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2timeString(Date date) {
		if (date == null) {
			return null;
		}
		return TIMESTAMP_FORMAT_ALL.format(date);
	}
	
	/**
	 * 日期显示yyyy-MM-dd格式
	 */
	public static String date2showString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(DF_YYYY_MM_DD);
		return format.format(date);
	}
	
	/**
	 * 显示 yyyymmddhhmmss格式
	 * @param date
	 * @return
	 */
	public static String date3showString(Date date){
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(DF_YMDHMS);
		return format.format(date);
	}
	
	/**
	 * 取后四位获取的时间
	 * 
	 * @param str
	 *            参数为8位的时间格式
	 * @return
	 */
	public static String proDateTime(String str) {
		try {
			String strTime = str.substring(4, str.length());
			// strTime = stringTime(strTime);
			return strTime;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 获取当年的是第几年
	 * 
	 * @param str
	 *            参数为8位的时间格式即年月日格式
	 * @return yyyy
	 */
	public static String getDate(String str) {
		try {
			Calendar cd = Calendar.getInstance();
			int year = cd.get(Calendar.YEAR);
			str = year + str.substring(0, 4);
			return stringdate(str);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 获取某个时间的表index(1--x)<br/>
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Integer getTableNameIndex(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int days = calendar.get(Calendar.DAY_OF_YEAR);
		int index = (days + (CYCLE - 1)) / CYCLE;
		return new Integer(index);
	}

	/**
	 * 获取时间范围内表的index(1--x)<br/>
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static List getTableNameIndexs(String beginDate, String endDate) {
		List result = new ArrayList();
		Integer beginIndex = new Integer(01);// 默认
		Integer endIndex = MAN_INDEX;// 默认
		/**
		 * 如果都为空，则查询所有的历史表
		 */
		if (StringUtil.isEmpty(beginDate) && StringUtil.isEmpty(endDate)) {
			for (int i = beginIndex.intValue(); i <= MAN_INDEX.intValue(); i++) {
				result.add(new Integer(i));
			}
			return result;
		}
		String startYear = null;
		String endYear = null;
		if (beginDate != null) {
			beginIndex = getTableNameIndex(string2date(beginDate));
			startYear = beginDate.substring(0, 4);
		}
		if (endDate != null) {
			endIndex = getTableNameIndex(string2date(endDate));
			endYear = endDate.substring(0, 4);
		}
		boolean sameYear = true;
		if (startYear != null && endYear != null && startYear.equals(endYear)) {
			sameYear = true;
		} else {
			sameYear = false;
		}
		if (sameYear) {
			for (int i = beginIndex.intValue(); i <= endIndex.intValue(); i++) {
				result.add(new Integer(i));
			}
		} else {
			for (int i = 1; i <= endIndex.intValue(); i++) {
				result.add(new Integer(i));
			}
			for (int i = beginIndex.intValue(); i <= MAN_INDEX.intValue(); i++) {
				result.add(new Integer(i));
			}
		}

		return result;
	}

	/**
	 * 
	 * @param sDate
	 * @return
	 */
	public static Date string2date(String sDate) {
		return string2date(sDate, true);
	}
	/**
	 * 根据传入的参数拼装成日期时间 参数格式yyyyMMddHHmmss
	 * 
	 * @param String
	 *            sDate
	 * @return Date
	 */
	public static Date string2datetime1(String sDate) {
		if (!isFullDateTime1(sDate)) {
			return null;
		}
		try {
			String strBeginDate = sDate;
			Date date = TIMESTAMP_FORMAT.parse(strBeginDate);
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 根据传入的参数拼装成日期时间 参数格式yyyyMMddHHmmssSSS
	 * 
	 * @param String
	 *            sDate, boolean startDay
	 * @return Date
	 */
	public static Date string2date(String sDate, boolean isStartDay) {
		if (!isDate(sDate)) {
			return null;
		}
		try {
			if (isStartDay) {
				String strBeginDate = sDate + "000000000";
				Date date = TIMESTAMP_FORMAT_ALL.parse(strBeginDate);
				return date;
			} else {
				String strEndDate = sDate + "235959999";
				Date date = TIMESTAMP_FORMAT_ALL.parse(strEndDate);
				return date;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据传入的参数拼装成日期时间 参数格式yyyy-MM-dd
	 * 
	 * @param String
	 *            sDate, boolean startDay
	 * @return Date
	 */
	public static Date string2date2(String sDate, boolean isStartDay) {
		if (!isFullDate(sDate)) {
			return null;
		}
		try {
			if (isStartDay) {
				String strBeginDate = sDate + " 00:00:00:000";
				Date date = TIMESTAMP_FORMAT_ALL2.parse(strBeginDate);
				return date;
			} else {
				String strEndDate = sDate + " 23:59:59:999";
				Date date = TIMESTAMP_FORMAT_ALL2.parse(strEndDate);
				return date;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据传入的参数（yyyy-MM-dd HH:mm:ss）拼装成日期时间 参数格式yyyy-MM-dd HH:mm:ss:SSS
	 * 
	 * @param String
	 *            sDate, boolean startDay
	 * @return Date
	 */
	public static Date string2date3(String sDate, boolean isStartDay) {
		if (!isFullDate(sDate)) {
			return null;
		}
		try {
			if (isStartDay) {
				String strBeginDate = sDate + ":000";
				Date date = TIMESTAMP_FORMAT_ALL2.parse(strBeginDate);
				return date;
			} else {
				String strEndDate = sDate + ":999";
				Date date = TIMESTAMP_FORMAT_ALL2.parse(strEndDate);
				return date;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据传入的参数拼装成日期时间 参数格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 *            sDate
	 * @return Date
	 */
	public static Date string2datetime(String sDate) {
		if (!isFullDateTime(sDate)) {
			return null;
		}
		try {
			String strBeginDate = sDate;
			Date date = TIMESTAMP_FORMAT2.parse(strBeginDate);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据传入的参数组装日期(格式：hour 00-23 minute 00-59 second 00-59)
	 * 
	 * @param String
	 *            sDate, String hour,String minute,String second
	 * @return Date
	 */
	public static Date string2datetime(String sDate, String hour,
			String minute, String second) {
		if (!isDate(sDate)) {
			return null;
		}
		try {

			String strBeginDate = sDate + hour + minute + second + "000";
			Date date = TIMESTAMP_FORMAT_ALL.parse(strBeginDate);
			return date;

		} catch (Exception e) {
			return null;
		}
	}

	public static String stringdate(String str) {
		try {
			String year = str.substring(0, 4);
			String moth = str.substring(4, 6);
			String day = str.substring(6, 8);
			String date = year + "-" + moth + "-" + day;
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * DF_HH_MI_SS
	 * 
	 * @param str
	 * @return
	 */
	public static String stringTime(String str) {
		try {
			String hour = str.substring(0, 2);
			String minute = str.substring(2, 4);
			String second = str.substring(4, 6);
			String date = hour + ":" + minute + ":" + second;
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	/***
	 * 将YYYY-MM-DD HH:MM:SS字符串转换成YYYYMMDDHHMMSS格式字符串
	 * @param str
	 * @return
	 */
	public static String stringToString(String str){
		String year = str.substring(0, 4);
		String month = str.substring(5, 7);
		String day = str.substring(8, 10);
		String hour = str.substring(11, 13);
		String min = str.substring(14, 16);
		String seconds = str.substring(17, 19);
		return year+month+day+hour+min+seconds;
	}

	/**
	 * 获得上周星期日的日期
	 * 
	 * @return
	 */
	public static String getPreviousWeekSunday() {
		weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat(DF_YMD);
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获取本周一的日期
	 * 
	 * @return
	 */
	public static String getMondayOFWeek() {
		weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat(DF_YMD);
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得上周星期一的日期
	 * 
	 * @return
	 */
	public static String getPreviousWeekday() {
		weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat(DF_YMD);
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得当前日期与本周日相差的天数
	 * 
	 * @return
	 */
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 某年某月某日与所在周日相差的天数
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static int getMondayPlus(String year, String month, String day) {
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(new Integer(year).intValue(),
				new Integer(month).intValue() - 1,
				new Integer(day).intValue() - 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 某年某月某日与所在周周一的日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getMondayOFWeek(String year, String month, String day) {
		try {
			weeks = 0;
			int mondayPlus = getMondayPlus(year, month, day);
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(new Integer(year).intValue(),
					new Integer(month).intValue() - 1,
					new Integer(day).intValue() - 1);
			cal.add(GregorianCalendar.DATE, mondayPlus);
			Date monday = cal.getTime();
			SimpleDateFormat df = new SimpleDateFormat(DF_YMD);
			String preMonday = df.format(monday);
			return preMonday;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 某年某月某日所在周周日的日期
	 * 
	 * @return
	 */
	public static String getWeekSunday(String year, String month, String day) {
		try {
			int mondayPlus = getMondayPlus(year, month, day);
			GregorianCalendar currentDate = new GregorianCalendar();

			currentDate.set(new Integer(year).intValue(),
					new Integer(month).intValue() - 1,
					new Integer(day).intValue() - 1);
			currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
			Date Sunday = currentDate.getTime();
			SimpleDateFormat df = new SimpleDateFormat(DF_YMD);
			String sunday = df.format(Sunday);
			return sunday;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取当月
	 * 
	 * @return
	 */
	public static String getCurrenMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);
		str = sdf.format(lastDate.getTime());
		return str.substring(str.length()-2);
	}
	
	/**
	 * 返回当日
	 * @return
	 */
	public static String getCurrenDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar lastDate = Calendar.getInstance();
		str = sdf.format(lastDate.getTime());
		return str.substring(str.length()-2);
	}
	
	public static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);
		cd.roll(Calendar.DAY_OF_YEAR, -1);
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	public static int getYearPlus(String year, String month, String day) {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.set(new Integer(year).intValue(),
				new Integer(month).intValue() - 1, new Integer(day).intValue());
		int yearOfNumber = currentDate.get(Calendar.DAY_OF_YEAR);// 获得某天是一年中的第几天
		currentDate.set(Calendar.DAY_OF_YEAR, 1);
		currentDate.roll(Calendar.DAY_OF_YEAR, -1);
		int MaxYear = currentDate.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	/**
	 * 
	 * 获取某年6月最后一天的日期
	 * 
	 * @return
	 */
	public static String getYearOfJun(String years) {
		if (StringUtil.isEmpty(years)) {
			return null;
		}
		return years + "0630";
	}

	/**
	 * 获取某年的7月的第一天
	 * 
	 * @param years
	 * @return
	 */
	public static String getFirstDayOfJul(String years) {
		try {
			int years_value = Integer.parseInt(years);
			return years_value + "0701";
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @param dateformat
	 * @return
	 */
	public static String getNowDay() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DF_YMD);
		String current = dateFormat.format(now);
		return current;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DF_Y_M_D_HMS);
		String current = dateFormat.format(now);
		return current;
	}

	// /**
	// * // * 获取某年最后一天的日期 //
	// */
	// public static String getYearEnd(String year, String month, String day) {
	// int yearPlus = getYearPlus(year, month, day);
	// GregorianCalendar currentDate = new GregorianCalendar();
	// currentDate.set(new Integer(year).intValue(), new Integer(month)
	// .intValue() - 1, new Integer(day).intValue());
	// // currentDate.add(currentDate.DAY_OF_YEAR, yearPlus + MaxYear +
	// // (MaxYear));
	// currentDate.add(GregorianCalendar.DATE, yearPlus + MaxYear * weeks
	// + (MaxYear - 1));
	// Date yearDay = currentDate.getTime();
	// DateFormat df = DateFormat.getDateInstance();
	// String preYearDay = df.format(yearDay);
	// getThisSeasonTime(11);
	// return preYearDay;
	// }
	/**
	 * 获得本季度
	 * 
	 * @param month
	 * @return
	 */
	public static String getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
		String end_days = getLastDayOfMonth(years_value + "", end_month + "");
		String seasonDate = years_value + start_month + start_days + ";"
				+ years_value + end_month + end_days;
		return seasonDate;
		// return "";

	}

	/**
	 * 获得星期几的汉字描述
	 * 
	 * @param month
	 * @return
	 */
	public static String getWeekDescByNo(int weekno) {

		if (weekno == 1) {
			return "星期一";
		}
		if (weekno == 2) {
			return "星期二";
		}
		if (weekno == 3) {
			return "星期三";
		}
		if (weekno == 4) {
			return "星期四";
		}
		if (weekno == 5) {
			return "星期五";
		}
		if (weekno == 6) {
			return "星期六";
		}
		if (weekno == 7) {
			return "星期天";
		}

		return "";

	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	public static String getLastDayOfMonth(String year, String month) {

		if (year == null || year.equals("") || month == null
				|| month.equals("")) {
			return "00";
		}

		int iyear = 0;
		try {
			iyear = new Integer(year).intValue();
		} catch (Exception e) {
			return "00";
		}

		String day = "";
		if (month.equals("01") || month.equals("03") || month.equals("05")
				|| month.equals("07") || month.equals("08")
				|| month.equals("10") || month.equals("12")) {
			day = "31";

		} else if (month.equals("04") || month.equals("06")
				|| month.equals("09") || month.equals("11")) {
			day = "30";
		} else if (month.equals("02")) {
			if (isLeapYear(iyear)) {
				day = "29";
			} else {
				day = "28";
			}
		} else {
			return "00";
		}
		return year + month + day;
	}

	/**
	 * 判断是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 获取本年是第几年
	 * 
	 * @return
	 */

	public static String getCurrentYear() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		return years_value + "";
	}

	/**
	 * 返回制定日期在某年中是几个周
	 * 
	 * @param year
	 * @param month
	 *            范围1-12
	 * @param day
	 * @return int
	 */
	public static int getWeekOfYear(String year, String month, String day) {
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(new Integer(year).intValue(),
				new Integer(month).intValue() - 1,
				new Integer(day).intValue() - 1);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static String getpreviousDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat(DF_YMD);
		String day = dateFormat.format(cal.getTime());
		return day;

	}

	/**
	 * 返回制定日期前后多少分钟的时间
	 * 
	 * @param curDatetime
	 *            yyyyMMddHHmmss
	 * @param minutes分钟
	 * @return String yyyyMMddHHmmss
	 */
	public static String getDatetimeByMinutes(String curDatetime, int minutes) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DF_YMDHMS);

			if (curDatetime.length() == 8) {
				formatter = new SimpleDateFormat(DF_YMD);
			}

			java.util.Date theDate = formatter.parse(curDatetime);

			theDate.setMinutes(theDate.getMinutes() + minutes);
			return formatter.format(theDate);

		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 开始日期<=结束日期 true
	 * 
	 * @param beginDate
	 *            yyyy-MM-dd HH:mm:ss
	 * @param endDate
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean isgreaterForDate2(String beginDate, String endDate) {
		try {

			Date d_beginDate = TIMESTAMP_FORMAT2.parse(beginDate);
			Date d_endDate = TIMESTAMP_FORMAT2.parse(endDate);

			if (d_beginDate.compareTo(d_endDate) <= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}
	/**
	 * 开始日期是否大于结束日期
	 * 
	 * @param beginDate
	 *            yyyy-MM-dd hh:mi:ss
	 * @param endDate
	 *            yyyy-MM-dd hh:mi:ss
	 * @return
	 */
	public static boolean isgreaterForDateTime(String beginDateTime,
			String endDateTime) {
		try {

			Date beginDate = TIMESTAMP_FORMAT2.parse(beginDateTime);
			Date endDate = TIMESTAMP_FORMAT2.parse(endDateTime);

			if (beginDate.compareTo(endDate) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * 开始日期是否大于结束日期
	 * 
	 * @param beginDate
	 *            yyyy-MM-dd
	 * @param endDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static boolean isgreaterForDate(String beginDate, String endDate) {
		try {

			Date d_beginDate = DATEFORMAT2.parse(beginDate);
			Date d_endDate = DATEFORMAT2.parse(endDate);

			if (d_beginDate.compareTo(d_endDate) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * 时间间隔是否大于1个月
	 * 
	 * @param beginDate
	 *            yyyyMMdd
	 * @param endDate
	 *            yyyyMMdd
	 * @return
	 */
	public static boolean greaterOneMonth(String beginDate, String endDate) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(parse("yyyyMMdd", endDate));
			end.add(Calendar.MONTH, -1);
			String endString = DATEFORMAT.format(end.getTime());
			if (Integer.parseInt(beginDate) <= Integer.parseInt(endString)) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}
	
	/**
	 * 时间间隔是否大于三个月(yyyyMMdd)
	 * 
	 * @param beginDate
	 *            yyyyMMdd
	 * @param endDate
	 *            yyyyMMdd
	 * @return
	 */
	public static boolean greaterThreeMonthFor_yyyyMMdd(String beginDate, String endDate) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(parse("yyyyMMdd", endDate));
			end.add(Calendar.MONTH, -3);
			String endString = DATEFORMAT.format(end.getTime());
			if (Integer.parseInt(beginDate) <= Integer.parseInt(endString)) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}
	
	/**
	 * 时间间隔是否大于i个月
	 * 
	 * @param beginDate
	 *            yyyyMMdd
	 * @param endDate
	 *            yyyyMMdd
	 * @param i   i为负数           
	 * @return
	 */
	public static boolean greaterIMonth(String beginDate, String endDate,int i ) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(parse("yyyyMMdd", endDate));
			end.add(Calendar.MONTH, i);
			String endString = DATEFORMAT.format(end.getTime());
			if (Integer.parseInt(beginDate) <= Integer.parseInt(endString)) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	/**
	 * 获取距离今天相隔i个月份的日期
	 * 
	 * @param i
	 * @return
	 */
	public static String getMonthsDay(int i) {
		try {
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MONTH, i);
			return DATEFORMAT2.format(end.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 时间间隔是否大于1个月
	 * 
	 * @param beginDate
	 *            yyyy-MM-dd
	 * @param endDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static boolean greaterOneMonth2(String beginDate, String endDate) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(parse("yyyy-MM-dd", endDate));
			end.add(Calendar.MONTH, -1);
			String endString = DATEFORMAT.format(end.getTime());
			if (Integer.parseInt(beginDate.replace("-", "")) <= Integer
					.parseInt(endString)) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	/**
	 * 时间间隔是否大于3个月
	 * 
	 * @param beginDate
	 *            yyyy-MM-dd
	 * @param endDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static boolean greaterThreeMonth(String beginDate, String endDate) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(parse("yyyy-MM-dd", endDate));
			end.add(Calendar.MONTH, -3);
			String endString = DATEFORMAT.format(end.getTime());
			if (Integer.parseInt(beginDate.replace("-", "")) <= Integer
					.parseInt(endString)) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	/**
	 * 时间间隔是否大于一个月
	 * 
	 * @param beginDate
	 *            yyyy-MM-dd HH:mm:ss
	 * @param endDate
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean greaterOneMonthForDateTime(String beginDateTime,
			String endDateTime) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(parse(DF_Y_M_D_HMS, endDateTime));
			end.add(Calendar.MONTH, -1);
			Date newEndDate = end.getTime();
			Date newBeginDate = TIMESTAMP_FORMAT2.parse(beginDateTime);
			if (newEndDate.compareTo(newBeginDate) > 0) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	/**
	 * 比较beginDate与endDate之间相差的天数 与 days的大小
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param days
	 * @return
	 */
	public static int compare(String beginDate, String endDate, int days) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(parse("yyyyMMdd", endDate));
			end.add(Calendar.DAY_OF_MONTH, days);

			Calendar begin = Calendar.getInstance();
			begin.setTime(parse("yyyyMMdd", beginDate));

			return begin.compareTo(end);

		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 比较beginDate与endDate之间相差的秒数 与 mins的大小
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param mins
	 * @return
	 */
	public static int compareDatetimeForMin(Date beginDate, Date endDate,
			long mins) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			long endMillis = end.getTimeInMillis();

			Calendar begin = Calendar.getInstance();
			begin.setTime(beginDate);
			long beginMillis = begin.getTimeInMillis();

			if (beginMillis - endMillis > mins * 60 * 1000) {
				return 1;
			} else if (beginMillis - endMillis == mins * 60 * 1000) {
				return 0;
			} else {
				return -1;
			}

		} catch (Exception e) {
			return -1;
		}
	}

	
	
	
	
	/**
	 * 比较beginDate与endDate之间相差的秒数 与 seconds的大小
	 * @param beginDate
	 * @param endDate
	 * @param mins
	 * @return
	 */
	public static int compareDatetimeForSeconds(Date beginDate, Date endDate,
			long secs) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			long endMillis = end.getTimeInMillis();

			Calendar begin = Calendar.getInstance();
			begin.setTime(beginDate);
			long beginMillis = begin.getTimeInMillis();

			if (beginMillis - endMillis > secs  * 1000) {
				return 1;
			} else if (beginMillis - endMillis == secs * 1000) {
				return 0;
			} else {
				return -1;
			}

		} catch (Exception e) {
			return -1;
		}
	}
	/**
	 * 比较d1与d2之间相差的秒数 与 mins的大小
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param mins
	 * @return
	 * @author nilomiao
	 */
	public static int compareDatetimeWithMins(Date d1, Date d2, long mins) {
		try {
			Calendar end = Calendar.getInstance();
			end.setTime(d1);
			long millis1 = end.getTimeInMillis();

			Calendar begin = Calendar.getInstance();
			begin.setTime(d2);
			long millis2 = begin.getTimeInMillis();

			if (Math.abs(millis1 - millis2) > mins * 60 * 1000) {
				return 1;
			} else if (millis1 - millis2 == mins * 60 * 1000) {
				return 0;
			} else {
				return -1;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 日期操作
	 * 
	 * 比如，加几天，减几天
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date operateDate(Date date, Integer i) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.DAY_OF_MONTH, i);
		return cd.getTime();
	}

	/**
	 * 返回指定日期之后最近的一个周几点日期
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date priorDayOfWeek(Date date, Integer i) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) + 1;
		if (dayOfWeek < i) {
			cd.add(Calendar.DAY_OF_MONTH, i - dayOfWeek);
		} else {
			cd.add(Calendar.DAY_OF_MONTH, 7 - (dayOfWeek - i));
		}
		return cd.getTime();
	}
	
	/**
	 * 获取当天0点时间对象 例如20110919000000 24小时制	
	 * @return
	 */
	public static Date getZeroHour24Date(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date zero24Date = new Date(cal.getTimeInMillis());
		return zero24Date;
	}
	
	
	
	public static int getYearFromDate(Date date){
		java.util.Calendar calendar = java.util.GregorianCalendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(java.util.Calendar.YEAR);
		
	}
	
	public static int getMonthFromDate(Date date){
		java.util.Calendar calendar = java.util.GregorianCalendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(java.util.Calendar.MONTH) + 1;
		
	}
	
	public static int getDayFromDate(Date date){
		java.util.Calendar calendar = java.util.GregorianCalendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(java.util.Calendar.DAY_OF_MONTH);
		
	}
//	public static void main(String[] args) {
////		System.out.println("compare: "
////				+ DateUtil.compare("20100510", "20100501", 9));
////		
////		System.out.println(DateUtil.getYearFromDate(new Date(System.currentTimeMillis())));
////		System.out.println(DateUtil.getMonthFromDate(new Date(System.currentTimeMillis())));
////		System.out.println(DateUtil.getDayFromDate(new Date(System.currentTimeMillis())));
//		System.out.println(getCurrenDay());
//		
//	}
	/**
	 * 获取两个时间的秒差  date-date1
	 * @param date
	 * @param date1
	 * @return
	 */
	public static long getDiffTime(java.util.Date date, java.util.Date date1) {
		return (date.getTime() - date1.getTime()) / 1000;
	}
	/**
	 * 在日期上增加n分钟
	 * @param date
	 * @param n
	 * @return 
	 */
	public static Date addMinute(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}
	
	/**
	 * 得到当前日期时间 
	 * @return yyyyMMddHHmmss
	 */
	public static String getCurrDateTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	public static Date getNewDate(){
		return new Date();
	}
	
	/**
	 * 为时间增加年份
	 * @author xugr
	 * @param transDate 需要转换的日期
	 * @param format 传入的参数格式，如:MMddHHmmss
	 * @return
	 */
	public static String addYear(String transDate, String format) {
		String sysoDate = new SimpleDateFormat("yyyy"+format).format(new Date());// 系统当前年月日
		String year = sysoDate.substring(0, 4);
		if(!StringUtil.isEmpty(transDate)){
			if((year + transDate).compareTo(sysoDate)<=0){
				return year+transDate;
			}else{
				return (Integer.parseInt(year)-1)+transDate;
			}
		}
		return null;
	}
}

