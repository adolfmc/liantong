package com.networkbench.agent.impl.p252e;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import java.io.FileOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6356g {
    /* renamed from: a */
    private static boolean m10304a() {
        return Environment.getExternalStorageState() != null && Environment.getExternalStorageState().equals("mounted");
    }

    /* renamed from: a */
    public static String m10303a(Context context) {
        if (context == null) {
            throw new NullPointerException("context is null point");
        }
        if (m10304a()) {
            return context.getExternalFilesDir("").getAbsolutePath();
        }
        return context.getFilesDir().getAbsolutePath();
    }

    /* renamed from: a */
    public static void m10302a(String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (Exception unused) {
            fileOutputStream = null;
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
        } catch (Exception unused2) {
            if (fileOutputStream == null) {
                return;
            }
            fileOutputStream.close();
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
        try {
            fileOutputStream.close();
        } catch (Exception unused4) {
        }
    }
}
