package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.mipush.sdk.p */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11111p {

    /* renamed from: a */
    private static volatile C11111p f21402a;

    /* renamed from: a */
    private Context f21403a;

    /* renamed from: a */
    private List<C11109n> f21404a = new ArrayList();

    private C11111p(Context context) {
        this.f21403a = context.getApplicationContext();
        if (this.f21403a == null) {
            this.f21403a = context;
        }
    }

    /* renamed from: a */
    public static C11111p m5047a(Context context) {
        if (f21402a == null) {
            synchronized (C11111p.class) {
                if (f21402a == null) {
                    f21402a = new C11111p(context);
                }
            }
        }
        return f21402a;
    }

    /* renamed from: a */
    public void m5043a(String str) {
        synchronized (this.f21404a) {
            C11109n c11109n = new C11109n();
            c11109n.f21398a = 0;
            c11109n.f21399a = str;
            if (this.f21404a.contains(c11109n)) {
                this.f21404a.remove(c11109n);
            }
            this.f21404a.add(c11109n);
        }
    }

    /* renamed from: b */
    public void m5041b(String str) {
        synchronized (this.f21404a) {
            C11109n c11109n = new C11109n();
            c11109n.f21399a = str;
            if (this.f21404a.contains(c11109n)) {
                Iterator<C11109n> it = this.f21404a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    C11109n next = it.next();
                    if (c11109n.equals(next)) {
                        c11109n = next;
                        break;
                    }
                }
            }
            c11109n.f21398a++;
            this.f21404a.remove(c11109n);
            this.f21404a.add(c11109n);
        }
    }

    /* renamed from: a */
    public int m5044a(String str) {
        synchronized (this.f21404a) {
            C11109n c11109n = new C11109n();
            c11109n.f21399a = str;
            if (this.f21404a.contains(c11109n)) {
                for (C11109n c11109n2 : this.f21404a) {
                    if (c11109n2.equals(c11109n)) {
                        return c11109n2.f21398a;
                    }
                }
            }
            return 0;
        }
    }

    /* renamed from: c */
    public void m5040c(String str) {
        synchronized (this.f21404a) {
            C11109n c11109n = new C11109n();
            c11109n.f21399a = str;
            if (this.f21404a.contains(c11109n)) {
                this.f21404a.remove(c11109n);
            }
        }
    }

    /* renamed from: a */
    public boolean m5042a(String str) {
        synchronized (this.f21404a) {
            C11109n c11109n = new C11109n();
            c11109n.f21399a = str;
            return this.f21404a.contains(c11109n);
        }
    }

    /* renamed from: a */
    public synchronized String m5046a(EnumC11125v enumC11125v) {
        return this.f21403a.getSharedPreferences("mipush_extra", 0).getString(enumC11125v.name(), "");
    }

    /* renamed from: a */
    public synchronized void m5045a(EnumC11125v enumC11125v, String str) {
        SharedPreferences sharedPreferences = this.f21403a.getSharedPreferences("mipush_extra", 0);
        sharedPreferences.edit().putString(enumC11125v.name(), str).apply();
    }
}
