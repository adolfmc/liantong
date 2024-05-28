package android.net.http;

import android.os.SystemClock;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
class HttpLog {
    private static final boolean DEBUG = false;
    private static final String LOGTAG = "http";
    static final boolean LOGV = false;

    HttpLog() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: v */
    public static void m22213v(String str) {
        Log.v("http", SystemClock.uptimeMillis() + " " + Thread.currentThread().getName() + " " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static void m22214e(String str) {
        Log.e("http", str);
    }
}
