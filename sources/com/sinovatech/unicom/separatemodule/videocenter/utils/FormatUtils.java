package com.sinovatech.unicom.separatemodule.videocenter.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FormatUtils {
    public static String str2Wan(String str) {
        return str2Unit(str, "w");
    }

    public static String str2Unit(String str, String str2) {
        BigDecimal bigDecimal = new BigDecimal(str);
        BigDecimal bigDecimal2 = new BigDecimal(10000);
        if (bigDecimal.compareTo(bigDecimal2) > -1) {
            String format = new DecimalFormat("#.00").format(bigDecimal.divide(bigDecimal2));
            return format + str2;
        }
        return str;
    }

    public static String str2Time(String str) {
        Object obj;
        Object obj2;
        try {
            int parseInt = Integer.parseInt(str);
            int i = parseInt / 60;
            int i2 = parseInt % 60;
            if (i > 9) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append(":");
                if (i2 > 9) {
                    obj2 = Integer.valueOf(i2);
                } else {
                    obj2 = "0" + i2;
                }
                sb.append(obj2);
                return sb.toString();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("0");
            sb2.append(i);
            sb2.append(":");
            if (i2 > 9) {
                obj = Integer.valueOf(i2);
            } else {
                obj = "0" + i2;
            }
            sb2.append(obj);
            return sb2.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "00:00";
        }
    }
}
