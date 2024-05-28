package com.baidu.p120ar.recorder.utils;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.utils.FileUtil */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static void mkDirExist(String str) {
        File parentFile = new File(str).getParentFile();
        if (parentFile.exists()) {
            return;
        }
        parentFile.mkdirs();
    }

    public static boolean deleteIfExist(String str) {
        return new File(str).delete();
    }
}
