package com.baidu.cloud.mediaprocess.utils;

import android.text.TextUtils;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FileUtils {
    public static boolean isExists(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }
}
