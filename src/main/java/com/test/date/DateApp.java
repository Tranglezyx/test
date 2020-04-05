package com.test.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class DateApp {

    public static void main(String[] args) {
        sayDate(DateUtils.round(System.currentTimeMillis(), Calendar.YEAR));
        sayDate(DateUtils.addYears(DateUtils.round(System.currentTimeMillis(), Calendar.YEAR), -1));
    }

    public static void say(Object o) {
        System.out.println(o);
    }

    public static void sayDate(Date d) {
        System.out.println(DateFormatUtils.format(d, "yyyy-MM-dd HH:mm:ss"));
    }
}
