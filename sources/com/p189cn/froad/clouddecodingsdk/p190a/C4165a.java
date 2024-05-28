package com.p189cn.froad.clouddecodingsdk.p190a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;

/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4165a {

    /* renamed from: a */
    private static SharedPreferences f9722a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.cn.froad.clouddecodingsdk.a.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4166a {

        /* renamed from: a */
        public static C4165a f9723a = new C4165a();

        private C4166a() {
        }
    }

    private C4165a() {
    }

    /* renamed from: a */
    private SharedPreferences m16327a() {
        return f9722a;
    }

    /* renamed from: a */
    public static C4165a m16326a(Context context) {
        if (f9722a == null) {
            f9722a = context.getSharedPreferences(context.getPackageName() + ".cacheData", 0);
        }
        return C4166a.f9723a;
    }

    /* renamed from: a */
    public int m16324a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return m16327a().getInt(str, i);
    }

    /* renamed from: a */
    public String m16325a(String str) {
        return TextUtils.isEmpty(str) ? "" : m16322a(str, "");
    }

    /* renamed from: a */
    public String m16322a(String str, String str2) {
        return TextUtils.isEmpty(str) ? "" : m16327a().getString(str, str2);
    }

    /* renamed from: a */
    public void m16323a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = m16327a().edit();
        edit.putLong(str, j);
        TMKeyLog.m16310d("SPManager", "setLong:" + str + "-" + j);
        edit.apply();
    }

    /* renamed from: a */
    public void m16320a(String str, byte[] bArr) {
        if (bArr == null) {
            return;
        }
        SharedPreferences.Editor edit = m16327a().edit();
        edit.putString(str, new String(bArr));
        edit.apply();
    }

    /* renamed from: a */
    public boolean m16321a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean z2 = m16327a().getBoolean(str, z);
        TMKeyLog.m16310d("SPManager", "getBoolean:" + str + "-" + z2);
        return z2;
    }

    /* renamed from: b */
    public int m16319b(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return m16324a(str, -1);
    }

    /* renamed from: b */
    public long m16317b(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return j;
        }
        long j2 = m16327a().getLong(str, j);
        TMKeyLog.m16310d("SPManager", "getLong:" + str + "-" + j2);
        return j2;
    }

    /* renamed from: b */
    public void m16318b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = m16327a().edit();
        edit.putInt(str, i);
        edit.commit();
    }

    /* renamed from: b */
    public synchronized void m16316b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = m16327a().edit();
        edit.putString(str, str2);
        edit.commit();
    }

    /* renamed from: b */
    public void m16315b(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = m16327a().edit();
        edit.putBoolean(str, z);
        TMKeyLog.m16310d("SPManager", "setBoolean:" + str + "-" + z);
        edit.commit();
    }

    /* renamed from: c */
    public boolean m16314c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return m16321a(str, false);
    }

    /* renamed from: d */
    public byte[] m16313d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String string = m16327a().getString(str, "");
        TMKeyLog.m16310d("SPManager", "getBytes:" + str + "-" + string);
        return string.getBytes();
    }

    /* renamed from: e */
    public void m16312e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = m16327a().edit();
        edit.remove(str);
        edit.apply();
    }

    /* renamed from: f */
    public boolean m16311f(String str) {
        return m16327a().contains(str);
    }
}
