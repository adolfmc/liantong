package com.mob.mgs;

import com.mob.mgs.impl.C5965b;
import com.mob.mgs.impl.C5997g;
import com.mob.tools.proguard.EverythingKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MobMGS implements EverythingKeeper {
    public static final String MGS_TAG = "MOBGUARD";

    public static void setOnAppActiveListener(OnAppActiveListener onAppActiveListener) {
        if (onAppActiveListener != null) {
            C5997g.m11844a(onAppActiveListener);
        }
    }

    public static void setDS(boolean z) {
        C5965b.m11926a(z);
    }

    public static boolean getDS() {
        return C5965b.m11924b();
    }
}
