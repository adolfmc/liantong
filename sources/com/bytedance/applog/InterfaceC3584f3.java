package com.bytedance.applog;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.f3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3584f3 {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.f3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3585a implements InterfaceC3584f3 {
        /* renamed from: a */
        public void m17299a(String str, String str2, Throwable th) {
            Log.d(str, str2, th);
        }

        /* renamed from: b */
        public void m17298b(String str, String str2, Throwable th) {
            Log.e(str, str2, th);
        }

        /* renamed from: c */
        public void m17297c(String str, String str2, Throwable th) {
            Log.v(str, str2, th);
        }
    }
}
