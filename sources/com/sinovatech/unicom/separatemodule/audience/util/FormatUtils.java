package com.sinovatech.unicom.separatemodule.audience.util;

import android.text.TextUtils;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FormatUtils {
    public static String getShowString(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "0";
            }
            BigDecimal bigDecimal = new BigDecimal(str);
            BigDecimal bigDecimal2 = new BigDecimal(10000);
            if (bigDecimal.compareTo(bigDecimal2) > -1) {
                String format = new DecimalFormat("#.0").format(bigDecimal.divide(bigDecimal2));
                return format + "w";
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static String getShowString(int i) {
        try {
            BigDecimal bigDecimal = new BigDecimal(i);
            BigDecimal bigDecimal2 = new BigDecimal(10000);
            if (bigDecimal.compareTo(bigDecimal2) > -1) {
                String format = new DecimalFormat("#.0").format(bigDecimal.divide(bigDecimal2));
                return format + "w";
            }
            return i + "";
        } catch (Exception e) {
            e.printStackTrace();
            return i + "";
        }
    }
}
