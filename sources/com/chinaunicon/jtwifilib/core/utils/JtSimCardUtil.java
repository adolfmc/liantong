package com.chinaunicon.jtwifilib.core.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtSimCardUtil {
    public static boolean hasSimCard(Context context) {
        switch (((TelephonyManager) context.getSystemService("phone")).getSimState()) {
            case 0:
            case 1:
                return false;
            default:
                return true;
        }
    }
}
