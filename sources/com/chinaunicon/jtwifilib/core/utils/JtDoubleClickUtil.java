package com.chinaunicon.jtwifilib.core.utils;

import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtDoubleClickUtil {
    private static long mLastClick;

    public static boolean isDoubleClick(long j) {
        if (System.currentTimeMillis() - mLastClick <= j) {
            return true;
        }
        mLastClick = System.currentTimeMillis();
        return false;
    }

    public static void shakeClick(final View view, long j) {
        view.setClickable(false);
        view.postDelayed(new Runnable() { // from class: com.chinaunicon.jtwifilib.core.utils.JtDoubleClickUtil.1
            @Override // java.lang.Runnable
            public void run() {
                view.setClickable(true);
            }
        }, j);
    }
}
