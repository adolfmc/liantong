package com.sdk.base.framework.utils.log;

import android.content.Context;
import com.sdk.p290f.C6998d;
import com.sdk.p295k.C7010b;
import java.io.File;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LogFile {
    private static final String CHARSET = "UTF-8";
    public static final String LOG_SUFFIX = ".log";
    private static final String TAG = "com.sdk.base.framework.utils.log.LogFile";
    private static boolean isDebug = C6998d.f18135a;

    public static void delete(Context context) {
        String str = C7010b.m8152a("yyyy-MM-dd") + ".log";
        File[] listFiles = new File(context.getFilesDir(), "/zzx_log/").listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return;
        }
        for (File file : listFiles) {
            if (file.getName().equals(str)) {
                file.delete();
            }
        }
    }

    public static ArrayList<File> get(Context context) {
        String str = C7010b.m8152a("yyyy-MM-dd") + ".log";
        ArrayList<File> arrayList = new ArrayList<>();
        File[] listFiles = new File(context.getFilesDir(), "/zzx_log/").listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return null;
        }
        for (File file : listFiles) {
            if (file.isFile() && !file.getName().equals(str)) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    public static boolean save2Internal(Context context, Throwable th) {
        return true;
    }
}
