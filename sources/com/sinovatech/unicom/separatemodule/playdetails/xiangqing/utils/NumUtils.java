package com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils;

import android.annotation.SuppressLint;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NumUtils {
    @SuppressLint({"DefaultLocale"})
    public static String stringForTime(int i) {
        if (i <= 0 || i >= 86400000) {
            return "00:00";
        }
        int i2 = i / 1000;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / 3600;
        return i5 > 0 ? String.format("%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)) : String.format("%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3));
    }

    public static String getBoFangNum(String str) {
        int parseInt = Integer.parseInt(str);
        if (parseInt < 10000) {
            return String.valueOf(parseInt);
        }
        return parseInt < 100000000 ? String.format(Locale.getDefault(), "%d.%02d万", Integer.valueOf(parseInt / 10000), Integer.valueOf((parseInt % 10000) / 100)) : parseInt > 100000000 ? String.format(Locale.getDefault(), "%d.%02d亿", Integer.valueOf(parseInt / 100000000), Integer.valueOf((parseInt % 100000000) / 1000000)) : "";
    }

    public static String getFsNum(String str) {
        int parseInt = Integer.parseInt(str);
        if (parseInt < 10000) {
            return String.valueOf(parseInt);
        }
        if (parseInt < 100000000) {
            return String.format(Locale.getDefault(), "%d.%02d万", Integer.valueOf(parseInt / 10000), Integer.valueOf((parseInt % 10000) / 100));
        }
        if (parseInt > 100000000) {
            return String.format(Locale.getDefault(), "%d.%02d亿", Integer.valueOf(parseInt / 100000000), Integer.valueOf((parseInt % 100000000) / 1000000));
        }
        return null;
    }

    public static String getDianZanNum(String str) {
        int parseInt = Integer.parseInt(str);
        if (parseInt < 10000) {
            return String.valueOf(parseInt);
        }
        if (parseInt < 100000000) {
            return String.format(Locale.getDefault(), "%d.%02d万", Integer.valueOf(parseInt / 10000), Integer.valueOf((parseInt % 10000) / 100));
        }
        if (parseInt > 100000000) {
            return String.format(Locale.getDefault(), "%d.%02d亿", Integer.valueOf(parseInt / 100000000), Integer.valueOf((parseInt % 100000000) / 1000000));
        }
        return null;
    }
}
