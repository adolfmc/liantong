package com.froad.froadeid.base.libs.utils;

import android.content.Context;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CommonUtils {
    public static final String CALLBACK_ERRCODE = "errCode";
    public static final String CALLBACK_ERRMSG = "errMsg";
    public static final String CALLBACK_REMAINCOUNT = "remainingCount";
    private static final String TAG = "CommonUtils";
    private static long lastClickTime;

    public static boolean isAssetsFileExists(Context context, String str) {
        try {
            for (String str2 : context.getAssets().list("")) {
                if (str2.equals(str.trim())) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFastDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - lastClickTime;
        lastClickTime = currentTimeMillis;
        if (0 >= j || j >= 800) {
            TMKeyLog.m16310d("CommonUtils", "isFastDoubleClick false");
            return false;
        }
        TMKeyLog.m16310d("CommonUtils", "isFastDoubleClick true");
        return true;
    }
}
