package com.chinaunicon.jtwifilib.core.utils;

import android.os.Bundle;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtDateUtil {

    /* renamed from: AM */
    public static final String f9701AM = "AM";

    /* renamed from: PM */
    public static final String f9702PM = "PM";
    public static final String dateFormatHM = "HH:mm";
    public static final String dateFormatHMS = "HH:mm:ss";
    public static final String dateFormatMD = "MM/dd";
    public static final String dateFormatMDHM = "MM-dd HH:mm";
    public static final String dateFormatM_D = "MM月dd日";
    public static final String dateFormatYM = "yyyy-MM";
    public static final String dateFormatYMD = "yyyy-MM-dd";
    public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";
    public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String dateFormatYMDHM_line = "yyyy/MM/dd HH:mm";

    public static Date getDateByFormat(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (ParseException unused) {
            return null;
        }
    }

    public Date getDateByOffset(Date date, int i, int i2) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(date);
            gregorianCalendar.add(i, i2);
        } catch (Exception unused) {
        }
        return gregorianCalendar.getTime();
    }

    public static String getStringByOffset(String str, String str2, int i, int i2) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
            gregorianCalendar.setTime(simpleDateFormat.parse(str));
            gregorianCalendar.add(i, i2);
            return simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (ParseException unused) {
            return null;
        }
    }

    public static String getStringByOffset(Date date, String str, int i, int i2) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            gregorianCalendar.setTime(date);
            gregorianCalendar.add(i, i2);
            return simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getStringByFormat(Date date, String str) {
        try {
            return new SimpleDateFormat(str).format(date);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String date2yyyyMMdd(Date date) {
        return new SimpleDateFormat(dateFormatYMDHM_line).format(date);
    }

    public static String getStringByFormat(String str, String str2) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
            return new SimpleDateFormat(str2).format(gregorianCalendar.getTime());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getStringByFormat(long j, String str) {
        try {
            return new SimpleDateFormat(str).format(Long.valueOf(j));
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getCurrentDate(String str) {
        try {
            return new SimpleDateFormat(str).format(new GregorianCalendar().getTime());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getCurrentDateByOffset(String str, int i, int i2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.add(i, i2);
            return simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception unused) {
            return null;
        }
    }

    public static int getOffectDay(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        int i = calendar.get(1);
        int i2 = calendar2.get(1);
        int i3 = calendar.get(6);
        int i4 = calendar2.get(6);
        int i5 = i - i2;
        if (i5 > 0) {
            return (i3 - i4) + calendar2.getActualMaximum(6);
        } else if (i5 < 0) {
            return (i3 - i4) - calendar.getActualMaximum(6);
        } else {
            return i3 - i4;
        }
    }

    public static int getOffectHour(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        return (calendar.get(11) - calendar2.get(11)) + (getOffectDay(j, j2) * 24);
    }

    public static int getOffectMinutes(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        return (calendar.get(12) - calendar2.get(12)) + (getOffectHour(j, j2) * 60);
    }

    public static String getFirstDayOfWeek(String str) {
        return getDayOfWeek(str, 2);
    }

    public static String getLastDayOfWeek(String str) {
        return getDayOfWeek(str, 1);
    }

    private static String getDayOfWeek(String str, int i) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            int i2 = gregorianCalendar.get(7);
            if (i2 == i) {
                return simpleDateFormat.format(gregorianCalendar.getTime());
            }
            int i3 = i - i2;
            if (i == 1) {
                i3 = 7 - Math.abs(i3);
            }
            gregorianCalendar.add(5, i3);
            return simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getFirstDayOfMonth(String str) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            gregorianCalendar.set(5, 1);
            return simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getLastDayOfMonth(String str) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            gregorianCalendar.set(5, 1);
            gregorianCalendar.roll(5, -1);
            return simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception unused) {
            return null;
        }
    }

    public static long getFirstTimeOfDay() {
        try {
            String currentDate = getCurrentDate("yyyy-MM-dd");
            return getDateByFormat(currentDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime();
        } catch (Exception unused) {
            return -1L;
        }
    }

    public static long getLastTimeOfDay() {
        try {
            String currentDate = getCurrentDate("yyyy-MM-dd");
            return getDateByFormat(currentDate + " 24:00:00", "yyyy-MM-dd HH:mm:ss").getTime();
        } catch (Exception unused) {
            return -1L;
        }
    }

    public static boolean isLeapYear(int i) {
        return (i % 4 == 0 && i % 400 != 0) || i % 400 == 0;
    }

    public static String formatDateStr2Desc(String str, String str2) {
        String stringByFormat;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            calendar2.setTime(simpleDateFormat.parse(str));
            calendar.setTime(new Date());
            int offectDay = getOffectDay(calendar.getTimeInMillis(), calendar2.getTimeInMillis());
            if (offectDay == 0) {
                int offectHour = getOffectHour(calendar.getTimeInMillis(), calendar2.getTimeInMillis());
                if (offectHour > 0) {
                    return "今天" + getStringByFormat(str, dateFormatHM);
                } else if (offectHour >= 0 && offectHour == 0) {
                    int offectMinutes = getOffectMinutes(calendar.getTimeInMillis(), calendar2.getTimeInMillis());
                    if (offectMinutes > 0) {
                        return offectMinutes + "分钟前";
                    } else if (offectMinutes >= 0) {
                        return "刚刚";
                    }
                }
            } else if (offectDay > 0) {
                if (offectDay == 1) {
                }
            } else if (offectDay < 0) {
            }
            stringByFormat = getStringByFormat(str, str2);
        } catch (Exception unused) {
        }
        return !JtStrUtil.isEmpty(stringByFormat) ? stringByFormat : str;
    }

    public static String getWeekNumber(String str, String str2) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(new SimpleDateFormat(str2).parse(str));
            switch (gregorianCalendar.get(7) - 1) {
                case 0:
                    return "星期日";
                case 1:
                    return "星期一";
                case 2:
                    return "星期二";
                case 3:
                    return "星期三";
                case 4:
                    return "星期四";
                case 5:
                    return "星期五";
                case 6:
                    return "星期六";
                default:
                    return "星期日";
            }
        } catch (Exception unused) {
            return "错误";
        }
    }

    public static int getWeekInt(String str, String str2) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(new SimpleDateFormat(str2).parse(str));
            switch (gregorianCalendar.get(7) - 1) {
                case 0:
                    return 7;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                    return 6;
                default:
                    return -1;
            }
        } catch (Exception unused) {
            return -1;
        }
    }

    public static int getMonthInt(String str, String str2) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(new SimpleDateFormat(str2).parse(str));
            return gregorianCalendar.get(5);
        } catch (Exception unused) {
            return -1;
        }
    }

    public static String getTimeQuantum(String str, String str2) {
        return getDateByFormat(str, str2).getHours() >= 12 ? f9702PM : f9701AM;
    }

    public static String getTimeDescription(long j) {
        if (j > 1000) {
            long j2 = j / 1000;
            long j3 = j2 / 60;
            if (j3 > 1) {
                return j3 + "分" + (j2 % 60) + "秒";
            }
            return j2 + "秒";
        }
        return j + "毫秒";
    }

    public static String formatTime(long j) {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        String str3;
        StringBuilder sb4;
        String str4;
        String str5;
        long j2 = 86400000;
        long j3 = j / j2;
        long j4 = j - (j2 * j3);
        long j5 = 3600000;
        long j6 = j4 / j5;
        long j7 = j4 - (j5 * j6);
        long j8 = 60000;
        long j9 = j7 / j8;
        long j10 = j7 - (j8 * j9);
        long j11 = 1000;
        long j12 = j10 / j11;
        long j13 = j10 - (j11 * j12);
        if (j3 < 10) {
            sb = new StringBuilder();
            str = "0";
        } else {
            sb = new StringBuilder();
            str = "";
        }
        sb.append(str);
        sb.append(j3);
        String sb5 = sb.toString();
        if (j6 < 10) {
            sb2 = new StringBuilder();
            str2 = "0";
        } else {
            sb2 = new StringBuilder();
            str2 = "";
        }
        sb2.append(str2);
        sb2.append(j6);
        String sb6 = sb2.toString();
        if (j9 < 10) {
            sb3 = new StringBuilder();
            str3 = "0";
        } else {
            sb3 = new StringBuilder();
            str3 = "";
        }
        sb3.append(str3);
        sb3.append(j9);
        String sb7 = sb3.toString();
        if (j12 < 10) {
            sb4 = new StringBuilder();
            str4 = "0";
        } else {
            sb4 = new StringBuilder();
            str4 = "";
        }
        sb4.append(str4);
        sb4.append(j12);
        sb4.toString();
        if (j13 < 10) {
            str5 = "0" + j13;
        } else {
            str5 = "" + j13;
        }
        if (j13 < 100) {
            String str6 = "0" + str5;
        } else {
            String str7 = "" + str5;
        }
        return sb5 + "天" + sb6 + "时" + sb7 + " 分钟 ";
    }

    public static String formatSecond(Double d) {
        String str;
        Object[] objArr;
        Integer valueOf = Integer.valueOf((int) (d.doubleValue() / 3600.0d));
        Integer valueOf2 = Integer.valueOf((int) ((d.doubleValue() / 60.0d) - (valueOf.intValue() * 60)));
        Integer valueOf3 = Integer.valueOf((int) ((d.doubleValue() - (valueOf2.intValue() * 60)) - ((valueOf.intValue() * 60) * 60)));
        if (valueOf.intValue() > 0) {
            str = "%1$,d时%2$,d分%3$,d秒";
            objArr = new Object[]{valueOf, valueOf2, valueOf3};
        } else if (valueOf2.intValue() > 0) {
            str = "%1$,d分%2$,d秒";
            objArr = new Object[]{valueOf2, valueOf3};
        } else {
            str = "%1$,d秒";
            objArr = new Object[]{valueOf3};
        }
        return String.format(str, objArr);
    }

    public static String[] formatTimeToSfm(long j) {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        String str3;
        StringBuilder sb4;
        String str4;
        String[] strArr = new String[4];
        long j2 = 86400000;
        long j3 = j / j2;
        long j4 = j - (j2 * j3);
        long j5 = 3600000;
        long j6 = j4 / j5;
        long j7 = j4 - (j5 * j6);
        long j8 = 60000;
        long j9 = j7 / j8;
        long j10 = (j7 - (j8 * j9)) / 1000;
        if (j3 < 10) {
            sb = new StringBuilder();
            str = "0";
        } else {
            sb = new StringBuilder();
            str = "";
        }
        sb.append(str);
        sb.append(j3);
        strArr[0] = sb.toString();
        if (j6 < 10) {
            sb2 = new StringBuilder();
            str2 = "0";
        } else {
            sb2 = new StringBuilder();
            str2 = "";
        }
        sb2.append(str2);
        sb2.append(j6);
        strArr[1] = sb2.toString();
        if (j9 < 10) {
            sb3 = new StringBuilder();
            str3 = "0";
        } else {
            sb3 = new StringBuilder();
            str3 = "";
        }
        sb3.append(str3);
        sb3.append(j9);
        strArr[2] = sb3.toString();
        if (j10 < 10) {
            sb4 = new StringBuilder();
            str4 = "0";
        } else {
            sb4 = new StringBuilder();
            str4 = "";
        }
        sb4.append(str4);
        sb4.append(j10);
        strArr[3] = sb4.toString();
        for (int i = 0; i < strArr.length && Double.parseDouble(strArr[i]) == 0.0d; i++) {
            if (Double.parseDouble(strArr[i]) == 0.0d && i == strArr.length - 1) {
                return null;
            }
        }
        return strArr;
    }

    public static void main(String[] strArr) {
        System.out.println(Date_timeString("2017-01-08 00:00:00", "yyyy-MM-dd"));
    }

    public static String Date_timeString(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).format(new SimpleDateFormat(str2).parse(str));
        } catch (ParseException unused) {
            return "";
        }
    }

    public static long Date_time(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str).getTime();
        } catch (ParseException unused) {
            return 0L;
        }
    }

    public static String getDateToString(long j) {
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(j));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getDateToString(long j, String str) {
        try {
            return new SimpleDateFormat(str).format(new Date(j));
        } catch (Exception unused) {
            return "";
        }
    }

    public static Bundle getWeekStar_End(String str, String str2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(str));
        PrintStream printStream = System.out;
        printStream.println("要计算日期为:" + simpleDateFormat.format(calendar.getTime()));
        Bundle bundle = new Bundle();
        if (1 == calendar.get(7)) {
            calendar.add(5, -1);
        }
        calendar.setFirstDayOfWeek(2);
        int i = calendar.get(7);
        calendar.add(5, calendar.getFirstDayOfWeek() - i);
        PrintStream printStream2 = System.out;
        printStream2.println("所在周星期一的日期：" + simpleDateFormat.format(calendar.getTime()));
        bundle.putString("start", simpleDateFormat.format(calendar.getTime()));
        PrintStream printStream3 = System.out;
        printStream3.println(calendar.getFirstDayOfWeek() + "-" + i + "+6=" + ((calendar.getFirstDayOfWeek() - i) + 6));
        calendar.add(5, 6);
        PrintStream printStream4 = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("所在周星期日的日期：");
        sb.append(simpleDateFormat.format(calendar.getTime()));
        printStream4.println(sb.toString());
        bundle.putString("end", simpleDateFormat.format(calendar.getTime()));
        return bundle;
    }

    public static Bundle getLastWeek(String str, int i) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, i * 7);
        calendar.set(7, 2);
        return getWeekStar_End(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()), str);
    }

    public static int getIntByFormat(long j) {
        String str;
        try {
            str = new SimpleDateFormat("HH").format(Long.valueOf(Long.parseLong(j + "000")));
        } catch (Exception unused) {
            str = null;
        }
        return Integer.parseInt(str);
    }

    public static long getTime() {
        long time = new Date().getTime();
        return Long.parseLong((time + "").substring(0, 10));
    }

    public static Bundle getMonthStar_End(String str, String str2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(str));
        calendar.set(5, 1);
        Bundle bundle = new Bundle();
        PrintStream printStream = System.out;
        printStream.println("这个月的第一天:" + simpleDateFormat.format(calendar.getTime()));
        bundle.putString("month_start", simpleDateFormat.format(calendar.getTime()));
        calendar.set(5, 1);
        calendar.roll(5, -1);
        PrintStream printStream2 = System.out;
        printStream2.println("这个月的最后一天:" + simpleDateFormat.format(calendar.getTime()));
        bundle.putString("month_end", simpleDateFormat.format(calendar.getTime()));
        return bundle;
    }

    public static List<String> getSevendate() {
        String valueOf;
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            String valueOf2 = String.valueOf(calendar.get(1));
            String valueOf3 = String.valueOf(calendar.get(2) + 1);
            if (calendar.get(5) + i2 + 2 > getMonthCount()) {
                if (valueOf3.equals("12")) {
                    String.valueOf(Integer.parseInt(valueOf2) + 1);
                    valueOf3 = "1";
                } else {
                    valueOf3 = String.valueOf(Integer.parseInt(valueOf3) + 1);
                }
                i++;
                valueOf = String.valueOf(i);
            } else {
                valueOf = String.valueOf(calendar.get(5) + i2 + 2);
            }
            String str = valueOf3 + "月" + valueOf + "日";
            System.out.println(str);
            arrayList.add(str);
        }
        return arrayList;
    }

    public static List<String> getSevendateBase(Date date) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            gregorianCalendar.add(5, i - 1);
            arrayList.add(new SimpleDateFormat("yyyy-MM-dd").format(gregorianCalendar.getTime()));
        }
        return arrayList;
    }

    public static boolean isYesterday(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.add(5, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(gregorianCalendar.getTime()).equals(format);
    }

    public static boolean isToday(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.add(5, 0);
        return new SimpleDateFormat("yyyy-MM-dd").format(gregorianCalendar.getTime()).equals(format);
    }

    public static boolean isTomorrow(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.add(5, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(gregorianCalendar.getTime()).equals(format);
    }

    public static int getMonthCount() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.set(5, 1);
        calendar.set(5, 1);
        calendar.roll(5, -1);
        return calendar.get(5);
    }

    public static String getMyDate(String str) {
        String valueOf;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        if ("紧急".equals(str) || "今天".equals(str)) {
            return String.valueOf(calendar.get(1)) + "-" + String.valueOf(calendar.get(2) + 1) + "-" + String.valueOf(calendar.get(5));
        } else if ("明天".equals(str)) {
            String valueOf2 = String.valueOf(calendar.get(1));
            String valueOf3 = String.valueOf(calendar.get(2) + 1);
            if (calendar.get(5) > getMonthCount()) {
                if (valueOf3.equals("12")) {
                    valueOf2 = String.valueOf(Integer.parseInt(valueOf2) + 1);
                    valueOf3 = "1";
                } else {
                    valueOf3 = String.valueOf(Integer.parseInt(valueOf3) + 1);
                }
                valueOf = String.valueOf(1);
            } else {
                valueOf = String.valueOf(calendar.get(5) + 1);
            }
            return valueOf2 + "-" + valueOf3 + "-" + valueOf;
        } else {
            String valueOf4 = String.valueOf(calendar.get(1));
            String valueOf5 = String.valueOf(calendar.get(2) + 1);
            String.valueOf(calendar.get(5));
            String substring = str.substring(0, str.indexOf("月"));
            String substring2 = str.substring(str.indexOf("月") + 1, str.indexOf("日"));
            if (Integer.parseInt(valueOf5) == Integer.parseInt(substring)) {
                return valueOf4 + "-" + substring + "-" + substring2;
            } else if (Integer.parseInt(valueOf5) < Integer.parseInt(substring)) {
                return valueOf4 + "-" + substring + "-" + substring2;
            } else {
                return (Integer.parseInt(valueOf4) + 1) + "-" + substring + "-" + substring2;
            }
        }
    }

    public static String getMyTimeBegin(String str) {
        return str.substring(0, str.indexOf("--"));
    }

    public static String getMyTimeEnd(String str) {
        return str.substring(str.indexOf("--") + 2, str.length());
    }

    public static Date parse(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str);
    }

    public static int getAge(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (calendar.before(date)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        calendar.setTime(date);
        int i4 = calendar.get(1);
        int i5 = calendar.get(2);
        int i6 = i - i4;
        return i2 <= i5 ? (i2 != i5 || i3 < calendar.get(5)) ? i6 - 1 : i6 : i6;
    }

    public static String timeStamp2Date(String str, String str2) {
        if (str == null || str.isEmpty() || str.equals("null")) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat((str2 == null || str2.isEmpty()) ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(Long.valueOf(str + "000").longValue()));
    }

    public static String date2TimeStamp(String str, String str2) {
        try {
            return String.valueOf(new SimpleDateFormat(str2).parse(str).getTime() / 1000);
        } catch (Exception unused) {
            return "";
        }
    }

    public static long date2TimeStampLong(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str).getTime();
        } catch (Exception unused) {
            return 0L;
        }
    }
}
