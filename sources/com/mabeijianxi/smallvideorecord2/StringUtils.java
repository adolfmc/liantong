package com.mabeijianxi.smallvideorecord2;

import android.text.TextPaint;
import android.text.TextUtils;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StringUtils {
    public static final SimpleDateFormat DATE_FORMAT_PART = new SimpleDateFormat(JtDateUtil.dateFormatHM);
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_FILE_PATTERN = "yyyy-MM-dd-HH-mm-ss";
    public static final String EMPTY = "";

    /* renamed from: GB */
    private static final double f12221GB = 1.073741824E9d;

    /* renamed from: KB */
    private static final double f12222KB = 1024.0d;

    /* renamed from: MB */
    private static final double f12223MB = 1048576.0d;

    public static String makeSafe(String str) {
        return str == null ? "" : str;
    }

    public static String currentTimeString() {
        return DATE_FORMAT_PART.format(Calendar.getInstance().getTime());
    }

    public static char chatAt(String str, int i) {
        if (str == null || str.length() <= 0) {
            return ' ';
        }
        return str.charAt(i);
    }

    public static float GetTextWidth(String str, float f) {
        if (isEmpty(str)) {
            return 0.0f;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(f);
        return textPaint.measureText(str.trim()) + ((int) (f * 0.1d));
    }

    public static String formatDate(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static String formatDate(long j, String str) {
        return new SimpleDateFormat(str).format(new Date(j));
    }

    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String formatDate(long j) {
        return formatDate(new Date(j), "yyyy-MM-dd");
    }

    public static String getDate() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    public static String createFileName() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date(System.currentTimeMillis()));
    }

    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd hh:mm:ss");
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }

    public static String formatDateTime(long j) {
        return formatDate(new Date(j), "yyyy-MM-dd hh:mm:ss");
    }

    public static String formatGMTDate(String str) {
        return formatDate(Calendar.getInstance(TimeZone.getTimeZone(str)).getTimeInMillis());
    }

    public static String join(ArrayList<String> arrayList, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuffer.append(it.next());
                stringBuffer.append(str);
            }
            stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        }
        return stringBuffer.toString();
    }

    public static String join(Iterator<String> it, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (it != null) {
            while (it.hasNext()) {
                stringBuffer.append(it.next());
                stringBuffer.append(str);
            }
            if (stringBuffer.length() > 0) {
                stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
            }
        }
        return stringBuffer.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    public static String generateTime(long j) {
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        int i4 = i / 3600;
        return i4 > 0 ? String.format("%02d:%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2)) : String.format("%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2));
    }

    public static boolean isBlank(String str) {
        return TextUtils.isEmpty(str);
    }

    public static String gennerTime(int i) {
        return String.format("%02d:%02d", Integer.valueOf((i / 60) % 60), Integer.valueOf(i % 60));
    }

    public static String generateFileSize(long j) {
        double d = j;
        if (d < 1024.0d) {
            return j + "B";
        } else if (d < 1048576.0d) {
            return String.format("%.1f", Double.valueOf(d / 1024.0d)) + "KB";
        } else if (d < 1.073741824E9d) {
            return String.format("%.1f", Double.valueOf(d / 1048576.0d)) + "MB";
        } else {
            return String.format("%.1f", Double.valueOf(d / 1.073741824E9d)) + "GB";
        }
    }

    public static String findString(String str, String str2, String str3) {
        int length = str2.length();
        int indexOf = isEmpty(str2) ? 0 : str.indexOf(str2);
        if (indexOf > -1) {
            int indexOf2 = isEmpty(str3) ? -1 : str.indexOf(str3, length + indexOf);
            return indexOf2 > -1 ? str.substring(indexOf + str2.length(), indexOf2) : "";
        }
        return "";
    }

    public static String substring(String str, String str2, String str3, String str4) {
        int length = str2.length();
        int indexOf = isEmpty(str2) ? 0 : str.indexOf(str2);
        if (indexOf > -1) {
            int indexOf2 = isEmpty(str3) ? -1 : str.indexOf(str3, length + indexOf);
            if (indexOf2 > -1) {
                return str.substring(indexOf + str2.length(), indexOf2);
            }
            return str.substring(indexOf + str2.length());
        }
        return str4;
    }

    public static String substring(String str, String str2, String str3) {
        return substring(str, str2, str3, "");
    }

    public static String concat(String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (strArr != null) {
            for (String str : strArr) {
                if (str != null) {
                    stringBuffer.append(str);
                }
            }
        }
        return stringBuffer.toString();
    }
}
