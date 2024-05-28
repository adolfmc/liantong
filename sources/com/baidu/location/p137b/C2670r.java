package com.baidu.location.p137b;

import android.content.Context;
import android.content.SharedPreferences;
import com.baidu.location.ServiceC2737f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.r */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2670r {

    /* renamed from: a */
    private static Object f5397a = new Object();

    /* renamed from: b */
    private static C2670r f5398b = null;

    /* renamed from: c */
    private SharedPreferences f5399c;

    /* renamed from: d */
    private SharedPreferences f5400d = null;

    public C2670r() {
        this.f5399c = null;
        if (this.f5399c == null) {
            try {
                if (ServiceC2737f.getServiceContext() != null) {
                    this.f5399c = ServiceC2737f.getServiceContext().getSharedPreferences("MapCoreServicePreIA", 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.f5399c = null;
            }
        }
    }

    /* renamed from: a */
    public static C2670r m19351a() {
        C2670r c2670r;
        synchronized (f5397a) {
            if (f5398b == null) {
                f5398b = new C2670r();
            }
            c2670r = f5398b;
        }
        return c2670r;
    }

    /* renamed from: a */
    public SharedPreferences m19350a(Context context) {
        if (this.f5400d == null && context != null) {
            try {
                this.f5400d = context.getSharedPreferences("MapCoreServicePregck", 0);
            } catch (Exception e) {
                e.printStackTrace();
                this.f5400d = null;
            }
        }
        return this.f5400d;
    }
}
