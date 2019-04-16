package com.airlab.musicplayer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateTools {

	private static final Log log=LogFactory.getLog(DateTools.class);

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_FORMAT_MS = "yyyy/MM/dd HH:mm:ss,SSS";
	public static final String DATE_FORMAT_M = "yyyy/MM/dd HH:mm";
	public static final String DATE_FORMAT_LONG = "yyyy/MM/dd HH:mm:ss,SSSSSSS";
	public static final String MYSQL_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_HH_MM_SS = "HH:mm:ss";
	public static final String DATE_FORMAT_MM_DD_HH_MM="MM-dd HH:mm";
	public static final String DATE_FORMAT_YYYYMM="yyyyMM";
	public static final String DATE_FORMAT_LOCAL_MM_DD_HH_MM="MM月dd日 HH:mm";
	public static final String DATE_FORMAT_LOCAL_YYYY_MM_DD_HH_MM="yyyy年MM月dd日 HH:mm";
	public static final String DATE_FORMAT_YYYYMMDDHH_MM_SS="yyyyMMddHH:mm:ss";
	public static final String DATA_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS="yyyyMMddHHmmss";
	
	public static final long MILLIS_ONE_DAY = 1000*24*60*60;
	public static final long MILLIS_ONE_HOUR = 1000*60*60;
	public static final long MILLIS_ONE_MIN = 1000*60;
	public static final long MILLIS_ONE_SEC = 1000;

	/**
	 * 将字符串按照指定的格式转化为Date类型，采用系统默认时区
	 * @param dataStr
	 * @param format
	 * @return
	 */
	public static Date parse(String dataStr,String format){
		return parse(dataStr, format, TimeZone.getDefault());
	}
	/**
	 * 将字符串按照指定的格式转化为Date类型，指定时区
	 * @param dataStr
	 * @param format
	 * @param timeZone
	 * @return
	 */
	public static Date parse(String dataStr,String format,TimeZone timeZone){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		sdf.setTimeZone(timeZone);
		Date date;
		try {
			date = sdf.parse(dataStr);
			return date;
		} catch (ParseException e) {
			log.error(e);
			return null;
		}
	}
	
	/**
	 * 将时间按照指定的格式转化为字符串,采用系统默认时区
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date,String format){
		return format(date, format, TimeZone.getDefault());
	}
	
	public static String format(Date date){
		return format(date, DATE_FORMAT_MS, TimeZone.getDefault());
	}
	/**
	 * 将当前时间格式化为字符串，采用系统默认时区
	 * @param format
	 * @return
	 */
	public static String formatNow(String format){
		return formatNow(format, TimeZone.getDefault());
	}
	
	/**
	 * 将时间按照指定的格式转化为字符串,采用系统默认时区
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date,String format,TimeZone zone){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		sdf.setTimeZone(zone);
		return sdf.format(date);
	}
	/**
	 * 将当前时间格式化为字符串，采用系统默认时区
	 * @param format
	 * @return
	 */
	public static String formatNow(String format,TimeZone zone){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		sdf.setTimeZone(zone);
		return sdf.format(new Date());
	}
	
	/**
	 * 将Date实例转换为短日期字符串表示形式 by zhujing 2011-4-27
	 * 
	 * @param date
	 * @return
	 */
	public static String toShortDateString(Date date) {
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd");
		return sDate.format(date);
	}

	public static boolean dateBigEqual(Date compareObject, Date comparedObject) {
		if (compareObject.getTime() / 1000 >= comparedObject.getTime() / 1000)
			return true;
		return false;
	}
	
	public static boolean dateSmallEqual(Date compareObject, Date comparedObject) {
		if (compareObject.getTime() / 1000 <= comparedObject.getTime() / 1000)
			return true;
		return false;
	}
	
	public static Date addDays(Date date, int amount) {
		if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
	}
	
	public static Date addHours(Date date,int hour){
		if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hour);
        return c.getTime();
	}
	
	public static Date addMunites(Date date,int amount){
		if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, amount);
        return c.getTime();
	}
	
	public static Date addMonth(Date date, int amount) {
		if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, amount);
        return c.getTime();
	}
	
	public static Date addSeconds(Date date, int amount) {
		if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, amount);
        return c.getTime();
	}
	
	public static int getAbsTimeDiff(Date beforeDate,Date afterDate){
		Long diff = (afterDate.getTime() - beforeDate.getTime()) /MILLIS_ONE_DAY;
		return Math.abs(diff.intValue());
	}
	public static int getHoursAbsTimeDiff(Date beforeDate,Date afterDate){
		Long diff = (afterDate.getTime() - beforeDate.getTime()) /MILLIS_ONE_HOUR;
		return Math.abs(diff.intValue());
	}
	public static String displaySeconds(int durationSeconds){
		int hours = durationSeconds /(60*60);  
		int leftSeconds = durationSeconds % (60*60);  
		int minutes = leftSeconds / 60;  
		int seconds = leftSeconds % 60;
		StringBuffer buffer = new StringBuffer();
		if ( hours > 0 ) {
			buffer.append(hours).append("小时");
		} 
		if (minutes > 0 ) {
			buffer.append(minutes).append("分钟");
		}
		if ( seconds > 0 || (hours < 0 && minutes < 0 ) ) {
			buffer.append(seconds).append("秒");
		}
		return buffer.toString();
	}
	
	public static Date getMonthEndDate(Date billDate){
		Calendar calendar=Calendar.getInstance(); 		
		calendar.setTime(billDate);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY,23);		
		calendar.set(Calendar.MINUTE,59);	
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,999);
		return calendar.getTime();
	}
	
	public static Date getDayStartDate(Date billDate){
		Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(billDate);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);	
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	public static Date getWeekStartDate(Date billDate){
		Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(billDate);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);	
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	public static Date getMonthStartDate(Date billDate){
		Calendar calendar=Calendar.getInstance(); 		
		calendar.setTime(billDate);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);	
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	public static Date getYearStartDate(Date billDate){
		Calendar calendar=Calendar.getInstance(); 		
		calendar.setTime(billDate);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);	
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	public static Date getQuartStartDate(Date billDate){
		Calendar calendar=Calendar.getInstance(); 		
		calendar.setTime(billDate);
		int month = Calendar.JANUARY;
		int curMonth = calendar.get(Calendar.MONTH);
		if(curMonth > Calendar.MARCH && curMonth < Calendar.JULY){
			month = Calendar.APRIL;
		}else if(curMonth > Calendar.JUNE && curMonth < Calendar.OCTOBER){
			month = Calendar.JULY;
		}else if(curMonth > Calendar.SEPTEMBER ){
			month = Calendar.OCTOBER;
		}
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);	
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
}
