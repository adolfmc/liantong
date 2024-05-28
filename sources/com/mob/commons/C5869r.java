package com.mob.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.mob.commons.r */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5869r {

    /* renamed from: a */
    private static C5869r f14480a;

    /* renamed from: b */
    private HashMap<String, Object> f14481b;

    private C5869r() {
        this.f14481b = m12197c();
        if (this.f14481b == null) {
            this.f14481b = new HashMap<>();
        }
        ArrayList<MobProduct> m12121b = C5895z.m12121b();
        if (m12121b == null || m12121b.isEmpty()) {
            return;
        }
        Iterator<MobProduct> it = m12121b.iterator();
        while (it.hasNext()) {
            MobProduct next = it.next();
            if (!this.f14481b.containsKey(next.getProductTag())) {
                this.f14481b.put(next.getProductTag(), 0);
            }
        }
    }

    /* renamed from: a */
    public static C5869r m12202a() {
        if (f14480a == null) {
            synchronized (C5869r.class) {
                if (f14480a == null) {
                    f14480a = new C5869r();
                }
            }
        }
        return f14480a;
    }

    /* renamed from: a */
    public void m12201a(MobProduct mobProduct, int i) {
        if (mobProduct != null) {
            this.f14481b.put(mobProduct.getProductTag(), Integer.valueOf(i));
            m12199a(this.f14481b);
        }
    }

    /* renamed from: b */
    public HashMap<String, Object> m12198b() {
        return this.f14481b;
    }

    /* renamed from: c */
    private HashMap<String, Object> m12197c() {
        try {
            return C5741aa.m12650a().m12617g();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private void m12199a(HashMap<String, Object> hashMap) {
        try {
            C5741aa.m12650a().m12630b(hashMap);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static String m12200a(String str) {
        return C5873u.m12180a(str, 99);
    }
}
