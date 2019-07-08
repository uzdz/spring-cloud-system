package com.uzdz.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class Case {
    public static void main(String[] args) throws ParseException {

        List<Object> objects = Collections.emptyList();
        objects.add("a");
        objects.add("b");

        Object remove = objects.remove(1);

        System.out.println(remove);


//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        // 当前时间
//        String current = "2019-7-15 23:01:00";
//
//        // 开始时间
//        String start = "2019-6-15 23:00:00";
//
//        calculation(format.parse(current), format.parse(start), 1, true, 2);
    }

    /**
     * 计算周期
     *
     * @param currentDate 当前时间
     * @param startDate   计划开始时间
     * @param month       每几个月
     * @param flag        是正数还是倒数
     * @param day         正数或倒数第几天
     * @return
     */
    private static Long calculation(Date currentDate, Date startDate, int month, boolean flag, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 如果开始时间大于当前时间，则第一次执行时间点为 startDate - currentDate
        if (currentDate.getTime() <= startDate.getTime()) {
            System.out.println("下一次执行时间：" + format.format(startDate));
            return startDate.getTime() - currentDate.getTime();
        }

        // 当前时间日历
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);

        // 计划开始时间日历
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        // 下一次计划执行时间日历
        // 这里使用currentDate当前时间主要是提前对(年/月/日)进行赋值，下面会通过计划开始时间对(时/分/秒)重新设置
        Calendar nextCalendar = Calendar.getInstance();
        nextCalendar.setTime(currentDate);

        nextCalendar.set(Calendar.HOUR, startCalendar.get(Calendar.HOUR));
        nextCalendar.set(Calendar.MINUTE, startCalendar.get(Calendar.MINUTE));
        nextCalendar.set(Calendar.SECOND, startCalendar.get(Calendar.SECOND));

        setNextDate(currentCalendar, startCalendar, nextCalendar, flag, month, day, 0);

        System.out.println("下一次执行时间：" + format.format(nextCalendar.getTime()));

        return nextCalendar.getTime().getTime() - startCalendar.getTime().getTime();
    }

    public static void setNextDate(Calendar currentCalendar, Calendar startCalendar, Calendar nextCalendar, boolean flag, int month, int day, int incr) {

        int computeMonth = (currentCalendar.get(Calendar.MONTH) + 1) - (startCalendar.get(Calendar.MONTH) + 1);

        if (computeMonth % month == 0) {
            computeMonth = 0;
        } else {
            computeMonth = month - computeMonth;
        }

        computeMonth = computeMonth + incr;

        nextCalendar.add(Calendar.MONTH, computeMonth);

        if (flag) {
            getStartDay(nextCalendar, day);
        } else {
            getEndDay(nextCalendar, day);
        }

        if (currentCalendar.getTime().getTime() > nextCalendar.getTime().getTime()) {
            setNextDate(currentCalendar, startCalendar, nextCalendar, flag, month, day, incr + month);
        }
    }

    // 计算当月倒数第 ？天
    public static void getEndDay(Calendar time, int day) {
        time.set(Calendar.DATE, 1);
        time.add(Calendar.MONTH, 1);
        time.add(Calendar.DATE, -day);
    }

    // 计算当月正数第 ？天
    public static void getStartDay(Calendar time, int day) {
        time.set(Calendar.DATE, 1);
        time.add(Calendar.MONTH, 0);
        time.add(Calendar.DATE, day - 1);
    }
}
