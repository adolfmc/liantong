package p000;

import android.text.TextUtils;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: LogUtil.java */
/* renamed from: v */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C14231v {

    /* renamed from: a */
    public static boolean f27720a = true;

    /* renamed from: a */
    public static void m74a(String str, String str2) {
        if (!f27720a || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, str2);
    }

    /* renamed from: b */
    public static void m72b(String str, String str2) {
        if (!f27720a || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, str2);
    }

    /* renamed from: c */
    public static void m71c(String str, String str2) {
        if (!f27720a || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, str2);
    }

    /* renamed from: a */
    public static void m75a(String str) {
        m74a("LogUtil", str);
    }

    /* renamed from: b */
    public static void m73b(String str) {
        m72b("LogUtil", str);
    }
}
