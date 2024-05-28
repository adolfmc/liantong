package com.gmrz.fido.offline;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.fido.offline.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DebugSwitch {
    /* renamed from: a */
    public static boolean m15738a(Context context) {
        return context.getSharedPreferences("fido_cost", 0).getBoolean("flag", false);
    }
}
