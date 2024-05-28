package com.chinaunicon.jtwifilib.core.crashreport;

import android.os.Environment;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AppSettings {
    public static String getGlobalCrashPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "moping_lib_crash" + File.separator;
    }
}
