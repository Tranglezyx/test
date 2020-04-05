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

    /**
     * 获得当前月的起始日期
     *
     * @return
     */
    public static Date getNowMonthStartDay() {
        return getMonthStartDay(new Date());
    }

    /**
     * 获得参数时间所在月的起始日期
     *
     * @param date 时间
     * @return
     */
    public static Date getMonthStartDay(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }
}
