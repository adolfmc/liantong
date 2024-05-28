package cn.finalteam.toolsfinal;

import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DateUtils {
    public static Calendar calendar;
    public static Date date;
    public static DateFormat dateFormat;

    public static Date parseDate(String str, String str2) {
        try {
            dateFormat = new SimpleDateFormat(str2);
            String replaceAll = str.replaceAll("-", "/");
            if (!replaceAll.equals("") && replaceAll.length() < str2.length()) {
                replaceAll = replaceAll + str2.substring(replaceAll.length()).replaceAll("[YyMmDdHhSs]", "0");
            }
            date = dateFormat.parse(replaceAll);
        } catch (Exception unused) {
        }
        return date;
    }

    public static Date parseDate(String str) {
        return parseDate(str, "yyyy/MM/dd");
    }

    public static String format(Date date2, String str) {
        if (date2 != null) {
            try {
                dateFormat = new SimpleDateFormat(str);
                return dateFormat.format(date2);
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    public static String format(Date date2) {
        return format(date2, "yyyy/MM/dd");
    }

    public static int getYear(Date date2) {
        calendar = Calendar.getInstance();
        calendar.setTime(date2);
        return calendar.get(1);
    }

    public static int getMonth(Date date2) {
        calendar = Calendar.getInstance();
        calendar.setTime(date2);
        return calendar.get(2) + 1;
    }

    public static int getDay(Date date2) {
        calendar = Calendar.getInstance();
        calendar.setTime(date2);
        return calendar.get(5);
    }

    public static int getHour(Date date2) {
        calendar = Calendar.getInstance();
        calendar.setTime(date2);
        return calendar.get(11);
    }

    public static int getMinute(Date date2) {
        calendar = Calendar.getInstance();
        calendar.setTime(date2);
        return calendar.get(12);
    }

    public static int getSecond(Date date2) {
        calendar = Calendar.getInstance();
        calendar.setTime(date2);
        return calendar.get(13);
    }

    public static long getMillis(Date date2) {
        calendar = Calendar.getInstance();
        calendar.setTime(date2);
        return calendar.getTimeInMillis();
    }

    public static String getDate(Date date2) {
        return format(date2, "yyyy/MM/dd");
    }

    public static String getTime(Date date2) {
        return format(date2, JtDateUtil.dateFormatHMS);
    }

    public static String getDateTime(Date date2) {
        return format(date2, "yyyy/MM/dd HH:mm:ss");
    }

    public static Date addDate(Date date2, int i) {
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillis(date2) + (i * 24 * 3600 * 1000));
        return calendar.getTime();
    }

    public static int diffDate(Date date2, Date date3) {
        return (int) ((getMillis(date2) - getMillis(date3)) / 86400000);
    }

    public static String getMonthBegin(String str) {
        date = parseDate(str);
        return format(date, JtDateUtil.dateFormatYM) + "-01";
    }

    public static String getMonthEnd(String str) {
        date = parseDate(getMonthBegin(str));
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        calendar.add(6, -1);
        return formatDate(calendar.getTime());
    }

    public static String formatDate(Date date2) {
        return formatDateByFormat(date2, "yyyy-MM-dd");
    }

    public static String formatDateByFormat(Date date2, String str) {
        if (date2 != null) {
            try {
                return new SimpleDateFormat(str).format(date2);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public static String getMonthDayWeek(Date date2) {
        String str;
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar2.get(1);
        int i = calendar2.get(2) + 1;
        int i2 = calendar2.get(5);
        switch (calendar2.get(7)) {
            case 1:
                str = "周日";
                break;
            case 2:
                str = "周一";
                break;
            case 3:
                str = "周二";
                break;
            case 4:
                str = "周三";
                break;
            case 5:
                str = "周四";
                break;
            case 6:
                str = "周五";
                break;
            case 7:
                str = "周六";
                break;
            default:
                str = null;
                break;
        }
        return i + "月" + i2 + "日(" + str + ")";
    }

    public static Date formatStringByFormat(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTimeInterval(String str) {
        Date formatStringByFormat = formatStringByFormat(str, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        int i = calendar2.get(1);
        int i2 = calendar2.get(2);
        int i3 = calendar2.get(4);
        int i4 = calendar2.get(7);
        int i5 = calendar2.get(11);
        int i6 = calendar2.get(12);
        Calendar calendar3 = Calendar.getInstance();
        if (formatStringByFormat != null) {
            calendar3.setTime(formatStringByFormat);
        } else {
            calendar3.setTime(new Date());
        }
        int i7 = calendar3.get(1);
        int i8 = calendar3.get(2);
        int i9 = calendar3.get(4);
        int i10 = calendar3.get(7);
        int i11 = calendar3.get(11);
        int i12 = calendar3.get(12);
        if (i7 != i) {
            return new SimpleDateFormat("yyyy-MM-dd").format(formatStringByFormat);
        }
        if (i8 != i2) {
            return new SimpleDateFormat("M月dd日").format(formatStringByFormat);
        }
        if (i9 != i3) {
            return new SimpleDateFormat("M月dd日").format(formatStringByFormat);
        }
        if (i10 != i4) {
            if (i10 + 1 == i4) {
                return "昨天" + formatDateByFormat(formatStringByFormat, JtDateUtil.dateFormatHM);
            } else if (i10 + 2 == i4) {
                return "前天" + formatDateByFormat(formatStringByFormat, JtDateUtil.dateFormatHM);
            } else {
                return new SimpleDateFormat("M月dd日").format(formatStringByFormat);
            }
        }
        int i13 = i5 - i11;
        if (i13 == 0) {
            int i14 = i6 - i12;
            if (i14 < 1) {
                return "刚刚";
            }
            return i14 + "分钟前";
        } else if (i13 >= 1 && i13 <= 12) {
            return i13 + "小时前";
        } else {
            return new SimpleDateFormat("今天 HH:mm").format(formatStringByFormat);
        }
    }

    public static String reformatTime(String str, String str2) {
        return new SimpleDateFormat(str2).format(parseToDate(str, "yyyy-MM-dd HH:mm:ss"));
    }

    public static Date parseToDate(String str, String str2) {
        try {
            return new SimpleDateFormat((str2 == null || "".equals(str2)) ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(str);
        } catch (ParseException unused) {
            return new Date();
        }
    }

    public static String getTimeInterval(Date date2) {
        return getTimeInterval(format(date2, "yyyy-MM-dd HH:mm:ss"));
    }
}
