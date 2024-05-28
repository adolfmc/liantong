package com.baidu.platform.comjni.map.favorite;

import android.os.Bundle;
import com.baidu.platform.comjni.JNIBaseApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NAFavorite extends JNIBaseApi {

    /* renamed from: a */
    private long f8126a = 0;

    private native boolean nativeAdd(long j, String str, String str2);

    private native boolean nativeClear(long j);

    private native boolean nativeCloseCache(long j);

    private native long nativeCreate();

    private native boolean nativeDelete(long j);

    private native int nativeGetAll(long j, Bundle bundle);

    private native int nativeGetLength(long j);

    private native int nativeGetRelations(long j, String str, Bundle bundle, int i);

    private native String nativeGetValue(long j, String str);

    private native boolean nativeIsExist(long j, String str);

    private native boolean nativeLoad(long j, String str, String str2, String str3, int i, int i2, int i3);

    private native int nativeRelease(long j);

    private native boolean nativeRemove(long j, String str);

    private native boolean nativeResumeCache(long j);

    private native boolean nativeSaveCache(long j);

    private native boolean nativeSetType(long j, int i);

    private native boolean nativeUpdate(long j, String str, String str2);

    private native boolean nativeUpdateInOrder(long j, String str, String str2);

    /* renamed from: a */
    public int m17641a(Bundle bundle) {
        try {
            return nativeGetAll(this.f8126a, bundle);
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* renamed from: a */
    public long m17643a() {
        this.f8126a = nativeCreate();
        return this.f8126a;
    }

    /* renamed from: a */
    public boolean m17642a(int i) {
        return nativeSetType(this.f8126a, i);
    }

    /* renamed from: a */
    public boolean m17640a(String str) {
        return nativeRemove(this.f8126a, str);
    }

    /* renamed from: a */
    public boolean m17639a(String str, String str2) {
        return nativeAdd(this.f8126a, str, str2);
    }

    /* renamed from: a */
    public boolean m17638a(String str, String str2, String str3, int i, int i2, int i3) {
        return nativeLoad(this.f8126a, str, str2, str3, i, i2, i3);
    }

    /* renamed from: b */
    public int m17637b() {
        return nativeRelease(this.f8126a);
    }

    /* renamed from: b */
    public String m17636b(String str) {
        try {
            return nativeGetValue(this.f8126a, str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public boolean m17635b(String str, String str2) {
        return nativeUpdate(this.f8126a, str, str2);
    }

    /* renamed from: c */
    public boolean m17634c() {
        return nativeClear(this.f8126a);
    }

    /* renamed from: c */
    public boolean m17633c(String str) {
        try {
            return nativeIsExist(this.f8126a, str);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: d */
    public boolean m17632d() {
        return nativeSaveCache(this.f8126a);
    }
}
