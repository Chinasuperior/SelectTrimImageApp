package com.img.selecttrimimageapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/**
 *	@Title:			日期工具类
 *	@File:			DateUtil.java 
 *	@Package 		club.codeapes.web.base.util 
 *	@Description: 	
 *	@date 			2014年11月4日 下午5:29:18 
 *	@version 		V2.0
 */
public class DateUtil{

	public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

	public static String FORMAT_LINE_YMDHMS = "yyyyMMddHHmmss";

	public static String FORMAT_YMDHMSS = "yyyy-MM-dd HH:mm:ss.S";
	
	public static String FORMAT_YMD = "yyyy-MM-dd";
	
	public static String FORMAT_YM = "yyyy-MM";
	
	public static String FORMAT_MD = "MM-dd";
	
	public static String FORMAT_HMS = "HH:mm:ss";
	
	public static String FORMAT_HM = "HH:mm";
	
	public static String DAYS_CODE = "days";
	
	public static String HOURS_CODE = "hours";
	
	public static String MINUTES_CODE = "minutes";
	
	public static String SECONDS_CODE = "seconds";
	
	/**
	 *	@Title: 		获得今天
	 *	@MethodName:	getNow 
	 *	@Description:	
	 *	@param 			@return	{yyyy-MM-dd HH:mm:ss}
	 *	@return 		String	
	 *	@throws
	 */
	public static String getNow(){
		return format(new Date(),FORMAT_YMDHMS);
	}
	/**
	 *	@Title: 		获得今天
	 *	@MethodName:	getNow 
	 *	@Description:	
	 *	@param 			@param pattern
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getNow(String pattern){
		return format(new Date(),pattern);
	}
	/**
	 *	@Title: 		获得昨天
	 *	@MethodName:	getYesterday 
	 *	@Description:	
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getYesterday(){
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_YEAR, -1);
	    Date d = cal.getTime();
		return format(d,FORMAT_YMDHMS);
    }
	/**
	 *	@Title: 		获得昨天
	 *	@MethodName:	getYesterday 
	 *	@Description:	
	 *	@param 			@param format
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getYesterday(String format){
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_YEAR, -1);
	    Date d = cal.getTime();
		return format(d,format);
    }
	
	/**
	 *	@Title: 		获得明天
	 *	@MethodName:	getTomorrow 
	 *	@Description:	
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, +1);
		return format(cal.getTime(), FORMAT_YMDHMS);
	}

	/**
	 *	@Title: 		获得名
	 *	@MethodName:	getTomorrow 
	 *	@Description:	
	 *	@param 			@param format
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getTomorrow(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, +1);
		return format(cal.getTime(), format);
	}
	
	/**
	 *	@Title: 		获得指定日期前一天
	 *	@MethodName:	getBefore 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getBefore(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, -1);
		return format(cal.getTime(),FORMAT_YMDHMS);
	}
	/**
	 *	@Title: 		获得指定日期前一天
	 *	@MethodName:	getBefore 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@param format
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getBefore(Date date,String format){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, -1);
		return format(cal.getTime(),format);
	}
	
	/**
	 *	@Title: 		获得指定日期后一天
	 *	@MethodName:	getAfter 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getAfter(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, +1);
		return format(cal.getTime(),FORMAT_YMDHMS);
	}
	
	/**
	 *	@Title: 		获得指定日期后一天
	 *	@MethodName:	getAfter 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@param format
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getAfter(Date date,String format){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, +1);
		return format(cal.getTime(),format);
	}
	
	
    /**
     * @Title			获得本周日期集合
     * @param format	日期格式
     */
    public static List<String> getAllDaysForWeek(String format) {
    	final int FIRST_DAY = Calendar.MONDAY;
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
        	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            list.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }
    
    
    /**
     * 获取本月所有日期
     * @param format
     * @return
     */
    public static List<String> getAllDaysForMonth(String format) {
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	List<String> list = new ArrayList<String>();
        Date d = new Date();
        Date date = getMonthStart(d);
        Date monthEnd = getMonthEnd(d);
        while (!date.after(monthEnd)) {
            list.add(sdf.format(date));
            date = getNext(date);
        }
        return list;
    }
 
    /**
     * @Title 		本月开始第一天
     * @param date
     * @return
     */
    private static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }
 
    /**
     * @Title 		本月最后一天
     * @param date
     * @return
     */
    private static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }
 
    /**
     * 下一天
     * @param date
     * @return
     */
    private static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
 
 
	/**
	 * 
	 *	@Title: 		获得目标日期
	 *	@MethodName:	getTargetDate 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@param range	+ 明天以后 - 昨天之前
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getTargetDay(Date date,int range){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, range);
	    return format(cal.getTime(),FORMAT_YMDHMS);
	}
	
	/**
	 *	@Title: 		日期格式化
	 *	@MethodName:	format 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String format(Date date){
		String rs = "";
		if(date!=null){
			SimpleDateFormat df = new SimpleDateFormat(FORMAT_YMDHMS);
            rs = df.format(date);
		}
		return rs;
	}
	
	/**
	 *	@Title: 		日期格式化
	 *	@MethodName:	format 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@param pattern
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String format(Date date,String pattern){
		String rs = "";
		if(date!=null){
			SimpleDateFormat df = new SimpleDateFormat(pattern);
            rs = df.format(date);
		}
		return rs;
	}
	public static String format(Long date){
		String rs = "";
		if(date!=null){
			SimpleDateFormat df = new SimpleDateFormat(FORMAT_YMDHMS);
			rs = df.format(date);
		}
		return rs;
	}
	
	/**
	 *	@Title: 		字符串转日期
	 *	@MethodName:	parse 
	 *	@Description:	
	 *	@param 			@param strDate
	 *	@param 			@param pattern
	 *	@param 			@return
	 *	@return 		Date
	 *	@throws
	 */
	public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 *	@Title: 		字符串转日期（默认格式 yyyy-MM-dd HH:mm:ss）
	 *	@MethodName:	parse 
	 *	@Description:	
	 *	@param 			@param strDate
	 *	@param 			@param pattern
	 *	@param 			@return
	 *	@return 		Date
	 *	@throws
	 */
	public static Date parse(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_YMDHMS);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	/**
	 *	@Title: 		通过指定日期获得上（-）下（+）几个月的日期
	 *	@MethodName:	getMonth 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@param n
	 *	@param 			@return
	 *	@return 		Date
	 *	@throws
	 */
	public static Date getTargetMonth(Date date, int range) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, range);
        return cal.getTime();
    }
	/**
	 *	@Title: 		通过指定日期获得上（-）下（+）几年的日期
	 *	@MethodName:	getTargetYear 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@param range
	 *	@param 			@return
	 *	@return 		Date
	 *	@throws
	 */
	public static Date getTargetYear(Date date, int range) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, range);
        return cal.getTime();
    }
	
	/**
	 *	@Title: 		获得目标日期
	 *	@MethodName:	getTargetDate 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@param range	+ 明天以后 - 昨天之前
	 *	@param 			@param format
	 *	@param 			@return
	 *	@return 		String
	 *	@throws
	 */
	public static String getTargetDate(Date date,int range,String format){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, range);
	    return format(cal.getTime(),format);
	}
	/**
	 *	@Title: 		获得年份
	 *	@MethodName:	getYear 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@return
	 *	@return 		int
	 *	@throws
	 */
	public static int getYear(Date date){
		return Integer.parseInt(format(date).substring(0, 4));
	}
	
	/**
	 *	@Title: 		获得月份
	 *	@MethodName:	getMonth 
	 *	@Description:	
	 *	@param 			@param date
	 *	@param 			@return
	 *	@return 		int
	 *	@throws
	 */
    public static int getMonth(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
    /**
     *	@Title: 		获得日
     *	@MethodName:	getDay 
     *	@Description:	
     *	@param 			@param date
     *	@param 			@return
     *	@return 		int
     *	@throws
     */
    public static int getDay(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    /**
     *	@Title: 		获得小时
     *	@MethodName:	getHour 
     *	@Description:	
     *	@param 			@param date
     *	@param 			@return
     *	@return 		int
     *	@throws
     */
    public static int getHour(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    /**
     *	@Title: 		获得分
     *	@MethodName:	getMinute 
     *	@Description:	
     *	@param 			@param date
     *	@param 			@return
     *	@return 		int
     *	@throws
     */
    public static int getMinute(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
    /**
     *	@Title: 		获得秒
     *	@MethodName:	getSecond 
     *	@Description:	
     *	@param 			@param date
     *	@param 			@return
     *	@return 		int
     *	@throws
     */
    public static int getSecond(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }
    /**
     *	@Title: 		获得毫秒
     *	@MethodName:	getMillis 
     *	@Description:	
     *	@param 			@param date
     *	@param 			@return
     *	@return 		long
     *	@throws
     */
    public static long getMillis(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }
    /**
     *	@Title: 		获得指定日期离今天的统计
     *	@MethodName:	getCountByDate2Today 
     *	@Description:	
     *	@param 			@param date
     *	@param 			@return
     *	@return 		int
     *	@throws
     */
    public static int getCountByDate2Today (String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int)(t/1000 - t1/1000)/3600/24;
    }
    /**
     *	@Title: 		获得指定日期离今天的统计
     *	@MethodName:	getCountByDate2Today 
     *	@Description:	
     *	@param 			@param date
     *	@param 			@param format
     *	@param 			@return
     *	@return 		int
     *	@throws
     */
    public static int getCountByDate2Today (String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int)(t/1000 - t1/1000)/3600/24;
    }
    /**
     *	@Title: 		判断源日期是否大于目标日期
     *	@MethodName:	isBefore 
     *	@Description:	
     *	@param 			@param sourceDate
     *	@param 			@param destDate
     *	@param 			@return 源>=目标 true (else false)
     *	@param 			@throws ParseException
     *	@return 		boolean
     *	@throws
     */
    public static boolean isBefore(String sourceDate,String targetDate)throws ParseException {
    	if( parse(sourceDate).getTime()-parse(targetDate).getTime()>=0){
    		return true;
    	}else{
    		return false;
    	}
    }
    /**
     *	@Title: 		判断源日期是否大于目标日期
     *	@MethodName:	isBefore 
     *	@Description:	
     *	@param 			@param sourceDate
     *	@param 			@param targetDate
     *	@param 			@return
     *	@return 		boolean
     *	@throws
     */
    public static boolean isBefore(Date sourceDate,Date targetDate) {
    	if( sourceDate.getTime()-targetDate.getTime()>=0){
    		return true;
    	}else{
    		return false;
    	}
    }
    /**
     *	@Title: 		获得两日期之间的日期列表
     *	@MethodName:	getBetweenDate 
     *	@Description:	
     *	@param 			@param start
     *	@param 			@param end
     *	@param 			@return
     *	@return 		List<String>
     *	@throws
     */
    public static List<String> getBetweenDate(String start,String end,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format); 
        List<String> list = new ArrayList<String>();
		try {
			Date date_start = sdf.parse(start);
			Date date_end = sdf.parse(end); 
			Date date =date_start;
		    Calendar cd = Calendar.getInstance();   
         
			while (date.getTime()<=date_end.getTime()){
				 list.add(sdf.format(date));  
			      cd.setTime(date);   
	              cd.add(Calendar.DATE, 1);//增加一天   
	              date=cd.getTime();
		     }
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        return list;
	}
    
    public static long getTimeDifference(Date date1,Date date2,String by){
    	long diff = date1.getTime() - date2.getTime();
    	by = by == null ? "day" : by;
    	if("day".equals(by)){
    		return diff / (1000 * 60 * 60 * 24);
    	}else if("hour".equals(by)){
    		return diff / (1000 * 60 * 60);
    	}else if("minute".equals(by)){
    		return diff / (1000 * 60);
    	}else if("second".equals(by)){
    		return diff / (1000);
    	}else return diff;
    }
    
    /**
     *	@Title: 		获得两日期之间的日期列表
     *	@MethodName:	getBetweenDate 
     *	@Description:	
     *	@param 			@param start
     *	@param 			@param end
     *	@param 			@return
     *	@return 		List<String>
     *	@throws
     */
    public static List<String> getBetweenDate(String start,String end){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMD); 
        List<String> list = new ArrayList<String>();
		try {
			Date date_start = sdf.parse(start);
			Date date_end = sdf.parse(end); 
			Date date =date_start;
		    Calendar cd = Calendar.getInstance();   
         
			while (date.getTime()<=date_end.getTime()){
				 list.add(sdf.format(date));  
			      cd.setTime(date);   
	              cd.add(Calendar.DATE, 1);//增加一天   
	              date=cd.getTime();
		     }
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        return list;
	}
    /**
     *	@Title: 		获得两日期之间的间隔天数
     *	@MethodName:	getCountByBetweenDates 
     *	@Description:	
     *	@param 			@param date1
     *	@param 			@param date2
     *	@param 			@return
     *	@param 			@throws ParseException
     *	@return 		int
     *	@throws
     */
    public static int getBetweenDaysCount(String date1, String date2)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(date1));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(date2));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
    /**
     *	@Title: 		获得两日期之间的间隔月数
     *	@MethodName:	getBetweenMonthsCount 
     *	@Description:	
     *	@param 			@param date1
     *	@param 			@param date2
     *	@param 			@return
     *	@return 		int
     *	@throws
     */
    @SuppressWarnings("unused")
	public static int getBetweenMonthsCount(String date1, String date2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dbeginDate = null;
		Date dendDate = null;
		try {
			dbeginDate = formatter.parse(date1);
			dendDate = formatter.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getBetweenMonthsCount(date1, date2);
	}
    /**
     *	@Title: 		获得两日期之间的间隔月数
     *	@MethodName:	getBetweenMonthsCount 
     *	@Description:	
     *	@param 			@param date1
     *	@param 			@param date2
     *	@param 			@return
     *	@return 		int
     *	@throws
     */
    public static int getBetweenMonthsCount(Date date1, Date date2) {
		Calendar calbegin = Calendar.getInstance();
		Calendar calend = Calendar.getInstance();
		calbegin.setTime(date1);
		calend.setTime(date2);
		int m_begin = calbegin.get(Calendar.MONTH) + 1; // 获得合同开始日期月份
		int m_end = calend.get(Calendar.MONTH) + 1;
		int checkmonth = m_end - m_begin
				+ (calend.get(Calendar.YEAR) - calbegin.get(Calendar.YEAR))
				* 12;
		return checkmonth;
	}
    
    /**
	 * 得到某年某月的具体天数
	 * @param month
	 * @return
	 */
	public static int getDayOfMonth(String month) {
		int day = 0;
		Calendar c = null;
		if(month!=null&&month.length()>0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(month));
				day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			c = Calendar.getInstance(Locale.CHINA);
			day = c.getActualMaximum(Calendar.DATE);
		}
		return day;
	}
	
	
	/**
	 * 时间或者日期相减计算
	 * @param calculation_type [Calendar.YEAR, Calendar.MONTH, Calendar.DAY, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND ]
	 * @param num 要相加或者相减的数字
	 * @param format 如果传null或者""即使用默认格式，格式为 FORMAT_YMDHMS
	 * @return String[] 0当前时间   1计算后的时间
	 * @throws Exception
	 */
	public static String[] addOrMinusDateTime(int calculation_type, int num ,String format) throws Exception {
		Calendar nowTime = Calendar.getInstance();
		Date nowDate = (Date) nowTime.getTime(); // 得到当前时间
		Calendar afterTime = Calendar.getInstance();
		afterTime.add(calculation_type, num);
		Date afterDate = (Date) afterTime.getTime();
		String times[] = null;
		if(format!=null&&format.length()>0){
			times = new String[] { DateUtil.format(nowDate), DateUtil.format(afterDate) };
		}else{
			times = new String[] { DateUtil.format(nowDate,format), DateUtil.format(afterDate,format) };	
		}
		return times;
	}
	
	/**
	 * 两时间相减，根据传递的类型得到相差的时、分、秒
	 * @param calculation_type [days, hours, minutes]
	 * @param largeDateTime 大的时间
	 * @param smallDateTime 小的时间
	 * @return
	 */
	public static long addOrMinusDateTime(String calculation_type,String largeDateTime, String smallDateTime){
		long seconds = parse(largeDateTime).getTime() - parse(smallDateTime).getTime();
		if(DAYS_CODE.equals(calculation_type)){
			long days = seconds / (1000 * 60 * 60 * 24);
			return days;
		}else if(HOURS_CODE.equals(calculation_type)){
			long hours = seconds / (1000* 60 * 60); 
			return hours;
		}else if(MINUTES_CODE.equals(calculation_type)){
			long minutes = seconds /(1000 * 60);
			return minutes;
		}else if(SECONDS_CODE.equals(calculation_type)){
			return seconds;
		}else{
			return 0;
		}
	}
	
	public static String getWeekByDate(Date dt) {
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
		w = 0;
		return weekDays[w];
	}
	
	public static int getWeekNumByDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
		w = 0;
		return w;
	}
	
//	public static void main(String[] args) {
//		System.out.println(addOrMinusDateTime(HOURS_CODE, "2016-08-10 23:59:59", "2016-08-10 00:00:00"));
//		System.out.println(addOrMinusDateTime(DAYS_CODE, "2016-08-10 23:59:59", "2016-08-10 00:00:00"));
//		System.out.println(addOrMinusDateTime(MINUTES_CODE, "2016-08-10 23:59:59", "2016-08-10 21:00:00"));
//	}
	
	
	/**
	 * 求时差
	 * @param 结束时间
	 * @param 开始时间
	 * @return
	 */
	public static String CalculationTimeDifference(Date date1, Date date2){
		
		long l = date1.getTime() - date2.getTime();
		long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long se = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        System.out.println("：" + hour + "小时" + min + "分" + se + "秒 ");
        return "：" + hour + "小时" + min + "分" + se + "秒 ";
	}
	
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
	
	public static void main(String[] args) {
		CalculationTimeDifference(DateUtil.parse("2016-08-10 03:23:13"),DateUtil.parse("2016-08-10 01:41:23"));
	}




	public static String formatFormLong(Long dateLong) {
		String returnValue = "";
		if (dateLong != null) {
			Date date = new Date(dateLong);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			returnValue = df.format(date);
		}
		return returnValue;
	}
	public static String formatFormLong(Long dateLong, String pattern) {
		String returnValue = "";
		if (dateLong != null) {
			Date date = new Date(dateLong);
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return returnValue;
	}
}
