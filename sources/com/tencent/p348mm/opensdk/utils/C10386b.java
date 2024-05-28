package com.tencent.p348mm.opensdk.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.utils.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10386b {

    /* renamed from: a */
    public static Context f19988a;

    /* renamed from: b */
    private static final int f19989b;

    /* renamed from: c */
    private static final int f19990c;

    /* renamed from: d */
    private static final int f19991d;

    /* renamed from: e */
    public static ThreadPoolExecutor f19992e;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f19989b = availableProcessors;
        f19990c = availableProcessors + 1;
        f19991d = (availableProcessors * 2) + 1;
        f19992e = new ThreadPoolExecutor(f19990c, f19991d, 1L, TimeUnit.SECONDS, new LinkedBlockingDeque());
    }

    /* renamed from: a */
    public static int m6205a(ContentResolver contentResolver, Uri uri) {
        C10384Log.m6209i("MicroMsg.SDK.Util", "getFileSize with content url");
        if (contentResolver == null || uri == null) {
            C10384Log.m6207w("MicroMsg.SDK.Util", "getFileSize fail, resolver or uri is null");
            return 0;
        }
        InputStream inputStream = null;
        try {
            try {
                InputStream openInputStream = contentResolver.openInputStream(uri);
                if (openInputStream == null) {
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    return 0;
                }
                int available = openInputStream.available();
                try {
                    openInputStream.close();
                } catch (IOException unused2) {
                }
                return available;
            } catch (Exception e) {
                C10384Log.m6207w("MicroMsg.SDK.Util", "getFileSize fail, " + e.getMessage());
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return 0;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static int m6204a(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        File file = new File(str);
        if (file.exists()) {
            return (int) file.length();
        }
        if (f19988a != null && str.startsWith("content")) {
            try {
                return m6205a(f19988a.getContentResolver(), Uri.parse(str));
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m6203a(String str, int i) {
        if (str != null) {
            try {
                return str.length() <= 0 ? i : Integer.parseInt(str);
            } catch (Exception unused) {
                return i;
            }
        }
        return i;
    }

    /* renamed from: a */
    public static boolean m6206a(int i) {
        return i == 36 || i == 46;
    }

    /* renamed from: b */
    public static boolean m6202b(String str) {
        return str == null || str.length() <= 0;
    }
}
