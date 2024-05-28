package com.sinovatech.unicom.separatemodule.notice;

import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NoticDateUtil {
    private static final int TODAY = 0;
    private static final int WEEKDAY = -6;
    private static final int YESTERDY = -1;

    public static boolean IsToday(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(Long.parseLong(str)));
        return calendar2.get(1) == calendar.get(1) && calendar2.get(6) - calendar.get(6) == 0;
    }

    public static boolean IsYesterday(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(Long.parseLong(str)));
        return calendar2.get(1) == calendar.get(1) && calendar2.get(6) - calendar.get(6) == -1;
    }

    public static boolean IsWeekday(String str) throws ParseException {
        int i;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(Long.parseLong(str)));
        return calendar2.get(1) == calendar.get(1) && (i = calendar2.get(6) - calendar.get(6)) >= -6 && i < -1;
    }

    public static boolean IsOtherday(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(Long.parseLong(str)));
        return (calendar2.get(1) == calendar.get(1) && calendar2.get(6) - calendar.get(6) < -6) || calendar2.get(1) != calendar.get(1);
    }

    public static String todayTime(String str) {
        return new SimpleDateFormat(JtDateUtil.dateFormatHM).format(new Date(Long.parseLong(str)));
    }

    public static String otherTime(String str) {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date(Long.parseLong(str)));
    }

    public static String mainTime(String str) {
        return new SimpleDateFormat("MM-dd").format(new Date(Long.parseLong(str)));
    }

    public static String mainTime2(String str) {
        return new SimpleDateFormat(JtDateUtil.dateFormatHM).format(new Date(Long.parseLong(str)));
    }

    public static String getWeekTime(String str) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        Locale.setDefault(Locale.CHINA);
        calendar.setTime(new Date(Long.parseLong(str)));
        switch (calendar.get(7)) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }
}
