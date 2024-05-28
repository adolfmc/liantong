package com.bytedance.applog;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.w2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3720w2 {

    /* renamed from: a */
    public static AbstractC3724c f8891a = C3722b.C3723a.f8892a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.w2$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C3722b extends AbstractC3724c {

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.bytedance.applog.w2$b$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class C3723a {

            /* renamed from: a */
            public static final C3722b f8892a = new C3722b(null);
        }

        public /* synthetic */ C3722b(C3721a c3721a) {
        }

        @Override // com.bytedance.applog.C3720w2.AbstractC3724c
        /* renamed from: a */
        public void mo17059a(String str, String str2) {
            Log.e(str, str2);
        }
    }

    /* renamed from: com.bytedance.applog.w2$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractC3724c {
        /* renamed from: a */
        public abstract void mo17059a(String str, String str2);

        /* renamed from: a */
        public boolean m17060a(int i) {
            C3720w2.m17062a();
            return 4 <= i;
        }
    }

    /* renamed from: a */
    public static int m17062a() {
        return 4;
    }

    /* renamed from: a */
    public static void m17061a(String str) {
        if (str != null && f8891a.m17060a(6)) {
            f8891a.mo17059a("Logger", str);
        }
    }
}
