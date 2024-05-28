package com.gmrz.asm.p198fp.utils;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.utils.UiUtil */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UiUtil {
    public static float dip2px(Context context, float f) {
        return (f * context.getResources().getDisplayMetrics().density) + 0.5f;
    }
}
