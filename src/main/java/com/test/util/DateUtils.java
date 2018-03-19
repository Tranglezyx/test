package com.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 把一个字符串转换为指定的日期格式,默认是yyyy-MM-dd HH:mm:ss
     */
    public static Date strToDate(String strDate, String formater) throws ParseException {
        if (strDate == null) {
            return null;
        }
        if (formater == null) {
            formater = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(formater);
        Date date = df.parse(strDate);
        return date;
    }

    /**
     * 把一个日期转换为指定的格式,默认是yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStr(Date date, String formater) {
        if (date == null) {
            return null;
        }
        if (formater == null) {
            formater = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formater);

        String str = null;
        str = sdf.format(date);
        return str;
    }

    public static String getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(2017,11,1);
        SimpleDateFormat firstDay= new SimpleDateFormat("yyyy-MM-dd");
        return  firstDay.format(calendar.getTime());
    }

    /**
     * 得到日期字符串的当月的首天，字符串类型只能为 yyyy-MM-dd 结构
     * @param dateStr
     * @return
     */
    public static Date getFirstDayOfMonth(String dateStr){
        String[] date = dateStr.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date[0]),Integer.parseInt(date[1]) - 1,1,0,0,0);
        return calendar.getTime();
    }
}
