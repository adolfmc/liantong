package com.sinovatech.unicom.separatemodule.templateholder;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RVLogUtil {
    public static void error(String str, String str2) {
        Log.e(str, "***********************************************************\n **********************************************************\n  *********************************************************\n   ********************************************************\n    *******************************************************\n");
        Log.e(str, str2);
        Log.e(str, "    *******************************************************\n   ********************************************************\n  *********************************************************\n **********************************************************\n***********************************************************\n");
    }

    public static void debug(String str, String str2) {
        Log.d(str, str2);
    }
}
