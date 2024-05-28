package com.sinovatech.unicom.basic.p315ui.home.util;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.util.ScreenUtil */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ScreenUtil {
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
}
