package com.xiaomi.push;

import android.util.Log;
import java.io.File;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.v */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11646v {

    /* renamed from: a */
    private static final HashMap<String, String> f23808a = new HashMap<>();

    static {
        f23808a.put("FFD8FF", "jpg");
        f23808a.put("89504E47", "png");
        f23808a.put("47494638", "gif");
        f23808a.put("474946", "gif");
        f23808a.put("424D", "bmp");
    }

    /* renamed from: a */
    public static long m2276a(File file) {
        long j = 0;
        try {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    j += m2276a(listFiles[i]);
                } else {
                    j += listFiles[i].length();
                }
            }
        } catch (Exception e) {
            Log.e("FileUtils", "Get folder size error: " + e.getMessage());
        }
        return j;
    }

    /* renamed from: a */
    public static boolean m2275a(File file) {
        long length;
        if (file == null) {
            return false;
        }
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    length = m2276a(file);
                } else {
                    length = file.length();
                }
                return length < 104857600;
            }
            return true;
        } catch (Exception e) {
            Log.e("FileUtils", "Check if internal file can be written error :" + e.getMessage());
            return false;
        }
    }
}
