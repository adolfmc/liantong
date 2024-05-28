package com.sinovatech.unicom.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DateUtils {
    public static String timeStamp2Date(String str) {
        return (str == null || str.isEmpty() || str.equals("null")) ? "" : new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(Long.valueOf(str).longValue()));
    }

    public static String timeStamp2Dategeshi(String str) {
        return (str == null || str.isEmpty() || str.equals("null")) ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-SSS").format(new Date(Long.valueOf(str).longValue()));
    }

    public static String timeStamp2DateRequset() {
        try {
            return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss:SSS").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
            return System.currentTimeMillis() + "";
        }
    }

    public static String timeStamp2DateMonth(String str) {
        return (str == null || str.isEmpty() || str.equals("null")) ? "" : new SimpleDateFormat("yyyyMM").format(new Date(Long.valueOf(str).longValue()));
    }

    public static String date2TimeStamp(String str, String str2) {
        try {
            return String.valueOf(new SimpleDateFormat(str2).parse(str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static List<Integer> getNowToCertainYear(int i) {
        try {
            ArrayList arrayList = new ArrayList();
            for (int i2 = Calendar.getInstance().get(1); i2 >= i; i2--) {
                arrayList.add(Integer.valueOf(i2));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Integer> getDaysInMonth(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2 - 1);
        int actualMaximum = calendar.getActualMaximum(5);
        for (int i3 = 1; i3 <= actualMaximum; i3++) {
            arrayList.add(Integer.valueOf(i3));
        }
        return arrayList;
    }

    public static long getYearTimeStamp(int i) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(1, i);
            calendar.set(2, 0);
            calendar.set(5, 1);
            return calendar.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
