package com.zhpan.bannerview.indicator;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class IndicatorFactory {
    public static BaseIndicatorView createIndicatorView(Context context, int i) {
        if (i == 1) {
            return new DashIndicatorView(context);
        }
        return new CircleIndicatorView(context);
    }
}
