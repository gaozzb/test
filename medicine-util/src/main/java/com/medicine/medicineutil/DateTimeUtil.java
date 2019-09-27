package com.medicine.medicineutil;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DateTimeUtil {

    public final static String DATE_FORMAT = "yyyy-MM-dd";

    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatFriendlyTime(Date date) {
        String result = "";

        LocalDateTime today = LocalDateTime.now();
        Instant instant = date.toInstant();
        LocalDateTime localDate = instant.atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();

        Duration duration = Duration.between(localDate, today);

        long days = duration.toDays();              // 这段时间的总天数
        long hours = duration.toHours();            // 这段时间的小时数
        long minutes = duration.toMinutes();        // 这段时间的分钟数
        long seconds = duration.getSeconds();       // 这段时间的秒数
        long weeks = duration.toDays() / 7;
        if (seconds < 1) {
            result = "刚刚";
        } else if (seconds >= 1 && seconds < 60) {
            result = seconds + "秒前";
        } else if (seconds >= 60 && seconds < 3600) {
            result = minutes + "分钟前";
        } else if (hours >= 1 && hours < 24) {
            result = hours + "小时前";
        } else if (days >= 1 && days <= 7) {
            result = days + "天前";
        } else if (days > 7 && days < 30) {
            result = weeks + "周前";
        } else if (days > 30 && days < 60) {
            result = "1个月前";
        } else {
            result = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        return result;
    }

    public static String formatTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDate = instant.atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();

        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String formatTime(LocalDateTime dateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = dateTime.format(formatter);

        return formatDateTime;
    }

    /**
     * 获得当前时间的yyyy-MM-dd格式字符串
     *
     * @return String
     */
    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        return LocalDate.now().format(formatter);
    }

    /**
     * 获得当前时间的yyyy-MM-dd格式字符串
     *
     * @return String
     */
    public static String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        return LocalDateTime.now().format(formatter);
    }

    /**
     * LocalDate转化为指定格式字符串
     *
     * @param fromDate
     * @param dateFormat
     * @return
     */
    public static String getLocalDate(LocalDate fromDate, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        if (fromDate != null) {
            String dateStr = fromDate.format(df);
            return dateStr;
        }
        return null;
    }

    public static String getLocalDateTime(LocalDateTime fromDateTime, String dateTimeFotmat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFotmat);
        if (fromDateTime != null) {
            String localTime = fromDateTime.format(df);
            return localTime;
        }

        return null;
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of("Asia/Shanghai"))
                .toLocalDate();
    }

    public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.of("Asia/Shanghai"))
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of("Asia/Shanghai"))
                .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.of("Asia/Shanghai"))
                .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(dateToConvert.getTime()).toLocalDateTime();
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.of("Asia/Shanghai"))
                .toInstant());
    }

    public static LocalDate convertToLocalDateViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    /**
     * 根据格式得到格式化后的时间
     *
     * @param currDate 要格式化的时间
     * @param format   时间格式，如yyyy-MM-dd HH:mm:ss
     * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
     * @see SimpleDateFormat#parse(String)
     */
    public static Date getFormatDateTime(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_TIME_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }

    /**
     * 根据格式得到格式化后的日期
     *
     * @param currDate 要格式化的日期
     * @param format   日期格式，如yyyy-MM-dd
     * @return String 返回格式化后的日期，格式由参数<code>format</code>
     * 定义，如yyyy-MM-dd，如2009-10-15
     * @see SimpleDateFormat#format(Date)
     */
    public static String getFormatDate(Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.format(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }

    /**
     * 两个日期相减得到的秒数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return (int)((date2ms - date1ms)/1000);
    }

    /**
     * 获取明天9:00的开始时间
     * @return
     */
    public static Date getTomorrowAtNine() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.HOUR_OF_DAY,9);
        return cal.getTime();
    }

    /**
     * 获取当天的开始时间
     * @return
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取一天前开始时间
     * @return
     */
    public static Date getOneDayAgoBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return calendar.getTime();
    }

    /**
     * 获取多少天前当前时间
     * @return
     */
    public static Date getOneWeekAgoTime(int dayAgo) {
        long timeAgo = System.currentTimeMillis()- dayAgo * 24 * 60 * 60 * 1000;
        return  new Date(timeAgo);
    }



    /**
     * @descript:计算两个字符串格式时间的时间差，超出时间1秒时间单位也会自增一
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param format 日期格式化方式  format="yyyy-MM-dd" ,yyyy-MM-dd HH:mm:ss
     */
    public static long getTimeDiff(String beginTime, String endTime,String format,String dateType) throws ParseException {

        long company = 0l;
        switch(dateType){
            case "ss" : company = 1000;                  break;   //秒数
            case "mm" : company = 1000 * 60;             break;   //分钟
            case "HH" : company = 1000 * 60 * 60;        break;   //小时
            default:    company = 1000 * 60 * 60 * 24;            //天数
        }
        SimpleDateFormat formater = new SimpleDateFormat(format);
        long begin = formater.parse(beginTime).getTime();
        long end = formater.parse(endTime).getTime();
        long diff = (end - begin) / company;
        long dd   = (end - begin) % company;
        if(dd > 0){ diff++; }
        return  diff;
    }



}
