package com.sinovatech.unicom.basic.view.decklayout;

import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LayoutParamsUtils {
    public static void invalidTopAndBottomMargin(ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (marginLayoutParams != null) {
            marginLayoutParams.topMargin = 0;
            marginLayoutParams.bottomMargin = 0;
        }
    }
}
