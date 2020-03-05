package cn.cicoding.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* @Description: 时间工具类
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class DateUtil {
	public final static String yyyy_MM_dd="yyyy-MM-dd";
	public final static String yyyy_MM_dd_HH_mm_ss="yyyy-MM-dd HH:mm:ss";
	public final static String yyyyMMddHHmmss="yyyyMMddHHmmss";
	
	private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

	
	/**
	 * @description: string 转 Date
	 * @param dateString
	 * @param pattern
	 * @return
	 * @time:2018年9月4日 上午9:39:14
	 * @user:WangPan
	 */
	public static Date string2Date(String dateString, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @description: Date 转 string
	 * @param dateString
	 * @param pattern
	 * @return
	 * @time:2018年9月4日 上午9:39:14
	 * @user:WangPan
	 */
	public static String date2String(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * @description:获取某月最后一天
	 * @param dateString
	 * @param pattern
	 * @return
	 * @time:2018年9月4日 上午9:41:43
	 * @user:WangPan
	 */
	public static int maxMonth(String dateString, String pattern) {
		Calendar cal = Calendar.getInstance();
		Date date = DateUtil.string2Date(dateString, pattern);
		cal.setTime(date);
		int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return max;
	}
	

	/**
	 * @description:指定时间加减--如"2018-09-04", 2, -2, "yyyy-MM-dd" -- > 2018-07-04
	 * @param dateString 时间
	 * @param field 游标
	 * @param amount 加减量
	 * @param pattern 时间格式
	 * @return 
	 * @time:2018年9月4日 上午9:45:41
	 * @user:WangPan
	 */
	public static String beforeDate2String(String dateString, int field,int amount, String pattern) {
		Date date = DateUtil.string2Date(dateString, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		String s = DateUtil.date2String(cal.getTime(), pattern);
		return s;
	}

	/**
	 * @description:string 转 String
	 * @param dateString
	 * @param fromPattern
	 * @param toPattern
	 * @return
	 * @time:2018年9月4日 上午9:43:16
	 * @user:WangPan
	 */
	public static String string2String(String dateString, String fromPattern,String toPattern) {
		DateFormat df = null;
		Date date = null;
		String dateStr = null;
		try {
			df = new SimpleDateFormat(fromPattern);
			date = df.parse(dateString);
			df = new SimpleDateFormat(toPattern);
			dateStr = df.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateStr;
	}
	/**
	 * @description:获取今天的字符串日期
	 * @return
	 * @time:2018年9月4日 上午9:48:06
	 * @user:WangPan
	 */
	public static String getToday() {
		return DateUtil.date2String(new Date(), yyyy_MM_dd);
	}
	
	/**
	 * @description:在指定的时间上加减
	 * @param datetime
	 * @param calendarType
	 * @param count
	 * @return
	 * @time:2018年9月4日 上午9:48:35
	 * @user:WangPan
	 */
	public static String addTime(String datetime,int calendarType,int count) {
		// TODO Auto-generated method stub
		Date date = DateUtil.string2Date(datetime, yyyy_MM_dd);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarType, count);    
		return DateUtil.date2String(c.getTime(), yyyy_MM_dd);
	}
	
	/**
	 * @description:获取某一天的昨天
	 * @param datetime
	 * @return
	 * @time:2018年9月4日 上午9:48:57
	 * @user:WangPan
	 */
	public static String beforeDay(String datetime) {
		// TODO Auto-generated method stub
		Date date = DateUtil.string2Date(datetime, yyyy_MM_dd);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);    
		return DateUtil.date2String(c.getTime(), yyyy_MM_dd);
	}
	
	/**
	 * @description:获取某个时间的 上个月
	 * @param datetime
	 * @return
	 * @time:2018年9月4日 上午9:49:12
	 * @user:WangPan
	 */
	public static String beforeMonth(String datetime) {
		// TODO Auto-generated method stub
		Date date = DateUtil.string2Date(datetime, yyyy_MM_dd);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);    
		return DateUtil.date2String(c.getTime(), yyyy_MM_dd);
	}
	/**
	 * @description:获取某个时间的 上n个月
	 * @param datetime
	 * @param n
	 * @return
	 * @time:2018年9月4日 上午9:49:27
	 * @user:WangPan
	 */
	public static String beforeMonth(String datetime,int n) {
		Date date = DateUtil.string2Date(datetime, yyyy_MM_dd);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,n);    
		return DateUtil.date2String(c.getTime(), yyyy_MM_dd);
	}
	
	/**
	 * @description:获取当前时间戳
	 * @return
	 * @time:2018年9月4日 上午9:49:38
	 * @user:WangPan
	 */
	public static Timestamp getTimestamp(){
		Date date = new Date();  
		Timestamp ts = new Timestamp(date.getTime());
		return ts ;
	}

	/**
	 * @description:返回当前字符串时间
	 * @return
	 * @time:2018年9月4日 上午9:49:59
	 * @user:WangPan
	 */
	public static String getCurrentTime(){
		SimpleDateFormat sdf=new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
		return sdf.format(new Date()) ;
	}
	/**
	 * @description:返回当前时间,自定义格式
	 * @param pattern
	 * @return
	 * @time:2018年9月4日 上午9:50:21
	 * @user:WangPan
	 */
	public static String getCurrentTime(String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(new Date()) ;
	}
	
	/**
	 * @description:获得两个时间相差的小时数
	 * @param date1
	 * @param date2
	 * @return
	 * @time:2018年9月4日 上午9:50:35
	 * @user:WangPan
	 */
	public static Long diffTimeHours(Date date1,Date date2){
		Long time1=date1.getTime();
		Long time2=date2.getTime();
		Long diffTime=time1>time2 ? time1-time2:time2-time1;
		double hours=new Double(diffTime)/(1000*60*60);
		return Math.round(hours);
	}
	
	/**
	 * @Title: addAndSubtractHours
	 * @param hours
	 * @return
	 * @Description: 时间加减(小时)
	 * @author wangp
	 * @date 2015-7-20 下午01:48:10   
	 */
	public static String addAndSubtractHours(int hours){
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);      
		cd.add(Calendar.HOUR, hours);
		String nowTime = sdf.format(cd.getTime()); 
		return nowTime;
	}
	
	/**
	 * @Title: addAndSubtractHours
	 * @param hours
	 * @return
	 * @Description: 时间加减(小时)
	 * @author wangp
	 * @date 2015-7-20 下午01:48:10   
	 */
	public static String addAndSubtractMinute(int minute){
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);      
		cd.add(Calendar.MINUTE, minute);
		String nowTime = sdf.format(cd.getTime()); 
		return nowTime;
	}
	
	
	/**
	* @Description: TODO增补缺失的时间
	* @param @param dateType
	* @param @param startTime
	* @param @param endTime
	* @param @return    
	* @return List<String>
	* @date 2017-2-24 下午05:05:43 
	* @author wangp 
	*/ 
	public static List<String> supplementDateTime(String dateType,String startTime, String endTime) {
		List<String> dates = null;
		if (dateType != null && dateType.trim().length() > 0
				&& startTime != null && startTime.trim().length() > 0
				&& endTime != null && endTime.trim().length() > 0) {
			dates = new ArrayList<String>();
			String pattern = null;
			Integer flag = null;
			if (dateType.equalsIgnoreCase("year")) {
				pattern = "yyyy";
				flag = Calendar.YEAR;
			} else if (dateType.equalsIgnoreCase("month")) {
				pattern = "yyyy-MM";
				flag = Calendar.MONTH;
			} else if (dateType.equalsIgnoreCase("day")) {
				pattern = "yyyy-MM-dd";
				flag = Calendar.DAY_OF_YEAR;
			} else if (dateType.equalsIgnoreCase("hour")) {
				pattern = "yyyy-MM-dd HH:mm:ss";
				flag = Calendar.HOUR_OF_DAY;
			}
			Calendar start = Calendar.getInstance();
			start.setTime(DateUtil.string2Date(startTime, pattern));
			Calendar end = Calendar.getInstance();
			if (dateType.equalsIgnoreCase("hour")) {
				endTime = endTime.substring(0, 14) + "00:00";
			}
			end.setTime(DateUtil.string2Date(endTime, pattern));

			dates.add(DateUtil.date2String(start.getTime(), pattern));
			start.add(flag, 1);
			while (start.before(end)) {
				if (dateType.equalsIgnoreCase("hour")) {
					pattern = "yyyy-MM-dd HH:mm:ss";
				}
				dates.add(DateUtil.date2String(start.getTime(), pattern));
				start.add(flag, 1);
			}
			dates.add(DateUtil.date2String(end.getTime(), pattern));
		}
		return dates;
	}
	
	/**
	* @Description: TODO 获取某个月的第一天和最后一天
	* @param @param date
	* @param @return    
	* @return String[]
	* @date 2017-2-24 下午05:08:48 
	* @author wangp 
	*/ 
	public static String[] getFirstAndLastDayOfMonth(String date){
		String[] dateArray = new String[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.string2Date(date, "yyyy-MM"));
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
		dateArray[0] = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd");
		
		calendar.setTime(DateUtil.string2Date(date, "yyyy-MM"));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));//获取月份最后一天
		dateArray[1] = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd");
		
		return dateArray;
	}
	
    /**
     * @description:时间戳转换成日期格式字符串 
     * @param seconds
     * @param format
     * @return
     * @time:2018年9月4日 上午9:51:19
     * @user:WangPan
     */
    public static Date timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return null;  
        }  
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        String strDate = sdf.format(new Date(Long.valueOf(seconds)));
        return string2Date(strDate, "yyyy-MM-dd HH:mm:ss");
    }  
    

    /**
     * @description:日期格式字符串转换成时间戳 
     * @param date_str
     * @param format
     * @return
     * @time:2018年9月4日 上午9:51:25
     * @user:WangPan
     */
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
    /**
     * @description:取得当前时间戳（精确到秒） 
     * @return
     * @time:2018年9月4日 上午9:51:33
     * @user:WangPan
     */
    public static String timeStamp(){  
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    } 
	
	/**
	 * @description:从身份证号中获取生日日期
	 * @param idCard
	 * @return
	 * @time:2018年9月4日 上午9:51:43
	 * @user:WangPan
	 */
	public static String getBirthByIdCard(String idCard) {
		int i = 6;//截取字符的起点
		String year = idCard.substring(i, i+4);//截取年
		String yue = idCard.substring(i+4, i+6);//截取月
		String ri = idCard.substring(i+6, i+8);//截取日
		
		return year+"-"+yue+"-"+ri;
    }
	
	/**
	 * @description:获取几日前，几月前，几年前，
	 * @param time
	 * @return
	 * @time:2018年9月4日 上午9:51:53
	 * @user:WangPan
	 */
	public static String relativeDateFormat(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

	public static void main(String[] args) {
		String tt = "1529648861000";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String strDate = sdf.format(new Date(Long.valueOf(tt)));
        String a = "2018-06-21 14:27:41";
        String b = "2017-06-20 14:27:41";
        String c = "2018-05-22 14:27:41";
        String d = "2018-03-22 14:27:41";
        String e = "2018-06-23 11:25:41";
        a = relativeDateFormat(a);
        b = relativeDateFormat(b);
        c = relativeDateFormat(c);
        d = relativeDateFormat(d);
        e = relativeDateFormat(e);
		System.out.println("=================" + a);
		System.out.println("=================" + b);
		System.out.println("=================" + c);
		System.out.println("=================" + d);
		System.out.println("=================" + e);
		
		String aa = beforeDate2String("2018-09-04", 2, -2, "yyyy-MM-dd");
		System.out.println("--------" + aa);
	}
}
