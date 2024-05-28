package com.baidu.p116a.p117a.p118a.p119a;

import android.content.Context;
import com.baidu.p122b.C2393c;
import com.baidu.p122b.C2421f;
import com.baidu.p122b.C2425g;
import com.baidu.p122b.C2426h;
import com.baidu.p122b.p131e.C2419a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.a.a.a.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2130a {

    /* renamed from: a */
    public static boolean f4066a = true;

    /* renamed from: e */
    private static volatile C2130a f4067e;

    /* renamed from: b */
    private final Context f4068b;

    /* renamed from: c */
    private C2426h f4069c;

    /* renamed from: d */
    private C2425g f4070d;

    /* renamed from: f */
    private C2393c f4071f = new C2393c();

    /* renamed from: g */
    private C2426h.C2427a f4072g;

    /* renamed from: h */
    private C2426h.C2427a f4073h;

    /* renamed from: i */
    private long f4074i;

    private C2130a(Context context) {
        this.f4068b = context.getApplicationContext();
        this.f4069c = new C2426h(this.f4068b, new C2419a(this.f4068b), this.f4071f);
        this.f4070d = new C2425g(this.f4068b, this.f4071f);
    }

    /* renamed from: a */
    private C2426h.C2427a m20445a() {
        C2426h.C2427a c2427a = this.f4073h;
        if (c2427a != null) {
            return c2427a;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.f4074i) > 3600000) {
            this.f4073h = m20442b();
            this.f4074i = currentTimeMillis;
        }
        C2426h.C2427a c2427a2 = this.f4073h;
        if (c2427a2 != null) {
            return c2427a2;
        }
        if (this.f4072g == null) {
            this.f4073h = m20439c(null);
        }
        return this.f4073h;
    }

    /* renamed from: a */
    private C2426h.C2427a m20443a(String str) {
        C2426h.C2427a m20168a = this.f4069c.m20168a();
        return m20168a == null ? m20440b(str) : m20168a;
    }

    /* renamed from: a */
    public static String m20444a(Context context) {
        String m20155b;
        synchronized (C2130a.class) {
            m20155b = m20441b(context).m20445a().m20155b();
        }
        return m20155b;
    }

    /* renamed from: b */
    static C2130a m20441b(Context context) {
        C2130a c2130a;
        synchronized (C2421f.class) {
            if (f4067e == null) {
                f4067e = new C2130a(context);
            }
            c2130a = f4067e;
        }
        return c2130a;
    }

    /* renamed from: b */
    private C2426h.C2427a m20442b() {
        return m20443a((String) null);
    }

    /* renamed from: b */
    private C2426h.C2427a m20440b(String str) {
        C2421f m20174a = this.f4070d.m20174a(str);
        if (m20174a != null) {
            return this.f4069c.m20165a(m20174a);
        }
        return null;
    }

    /* renamed from: c */
    private C2426h.C2427a m20439c(String str) {
        return this.f4069c.m20162b(str);
    }
}
