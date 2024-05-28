package com.bytedance.applog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.bytedance.applog.IOaidObserver;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.d3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3572d3 {

    /* renamed from: a */
    public static final String f8426a = C3572d3.class.getSimpleName() + "#";

    /* renamed from: b */
    public static AbstractC3749z2<C3630m3> f8427b = new C3573a();

    /* renamed from: com.bytedance.applog.d3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3573a extends AbstractC3749z2<C3630m3> {
        @Override // com.bytedance.applog.AbstractC3749z2
        /* renamed from: a */
        public C3630m3 mo16989a(Object[] objArr) {
            return new C3630m3((Context) objArr[0]);
        }
    }

    @WorkerThread
    /* renamed from: a */
    public static String m17313a(SharedPreferences sharedPreferences) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String m16988b = C3555b3.f8378a.m16988b(sharedPreferences);
        C3578e3.m17305a("TrackerDr", f8426a + "getCdid takes " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " ms", null);
        return m16988b;
    }

    @Nullable
    @AnyThread
    /* renamed from: a */
    public static String m17311a(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optString("id", null);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0076, code lost:
        if (r4 == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0078, code lost:
        r10.f8589a.unlock();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x006d, code lost:
        if (r4 != false) goto L13;
     */
    @androidx.annotation.Nullable
    @androidx.annotation.WorkerThread
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map m17314a(@androidx.annotation.NonNull android.content.Context r10) {
        /*
            long r0 = android.os.SystemClock.elapsedRealtime()
            com.bytedance.applog.z2<com.bytedance.applog.m3> r2 = com.bytedance.applog.C3572d3.f8427b
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            r3[r4] = r10
            java.lang.Object r10 = r2.m16988b(r3)
            com.bytedance.applog.m3 r10 = (com.bytedance.applog.C3630m3) r10
            boolean r2 = r10.f8591c
            r3 = 0
            if (r2 != 0) goto L1a
            r10 = r3
            goto L9c
        L1a:
            r10.m17226a()
            java.lang.String r2 = com.bytedance.applog.C3630m3.f8586j
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Oaid#getOaid timeoutMills="
            r5.append(r6)
            r6 = 100
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.bytedance.applog.C3578e3.m17306a(r2, r5)
            java.util.Map<java.lang.String, java.lang.String> r2 = r10.f8595g
            if (r2 != 0) goto L86
            long r8 = android.os.SystemClock.elapsedRealtime()
            java.util.concurrent.locks.ReentrantLock r2 = r10.f8589a     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            boolean r4 = r2.tryLock(r6, r5)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r2 = com.bytedance.applog.C3630m3.f8586j     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            r5.<init>()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r6 = "Oaid#getOaid locked="
            r5.append(r6)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            r5.append(r4)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r6 = ", took "
            r5.append(r6)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            long r6 = r6 - r8
            r5.append(r6)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r6 = " ms"
            r5.append(r6)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            com.bytedance.applog.C3578e3.m17305a(r2, r5, r3)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            if (r4 == 0) goto L86
            goto L78
        L70:
            r0 = move-exception
            goto L7e
        L72:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L70
            if (r4 == 0) goto L86
        L78:
            java.util.concurrent.locks.ReentrantLock r2 = r10.f8589a
            r2.unlock()
            goto L86
        L7e:
            if (r4 == 0) goto L85
            java.util.concurrent.locks.ReentrantLock r10 = r10.f8589a
            r10.unlock()
        L85:
            throw r0
        L86:
            java.lang.String r2 = com.bytedance.applog.C3630m3.f8586j
            java.lang.String r4 = "Oaid#getOaid return apiMap="
            java.lang.StringBuilder r4 = com.bytedance.applog.C3535a.m17349a(r4)
            java.util.Map<java.lang.String, java.lang.String> r5 = r10.f8595g
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.bytedance.applog.C3578e3.m17306a(r2, r4)
            java.util.Map<java.lang.String, java.lang.String> r10 = r10.f8595g
        L9c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = com.bytedance.applog.C3572d3.f8426a
            r2.append(r4)
            java.lang.String r4 = "getOaid takes "
            r2.append(r4)
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r4 = r4 - r0
            r2.append(r4)
            java.lang.String r0 = " ms"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "TrackerDr"
            com.bytedance.applog.C3578e3.m17305a(r1, r0, r3)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3572d3.m17314a(android.content.Context):java.util.Map");
    }

    @AnyThread
    /* renamed from: a */
    public static void m17312a(@Nullable IOaidObserver iOaidObserver) {
        C3630m3.f8587k = iOaidObserver;
        String str = C3630m3.f8588l;
        if (str != null) {
            C3630m3.m17225a(new IOaidObserver.Oaid(str));
        }
    }
}
