package com.xiaomi.push;

import java.util.Map;

/* renamed from: com.xiaomi.push.fb */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11358fb implements Cloneable {

    /* renamed from: a */
    public static String f22258a = "wcc-ml-test10.bj";

    /* renamed from: b */
    public static String f22259b;

    /* renamed from: a */
    private int f22260a;

    /* renamed from: a */
    private InterfaceC11361fe f22261a;

    /* renamed from: a */
    private boolean f22262a = AbstractC11356fa.f22238a;

    /* renamed from: b */
    private boolean f22263b = true;

    /* renamed from: c */
    private String f22264c;

    /* renamed from: d */
    private String f22265d;

    /* renamed from: e */
    private String f22266e;

    /* renamed from: a */
    public byte[] mo2828a() {
        return null;
    }

    /* renamed from: a */
    public static final String m3870a() {
        String str = f22259b;
        return str != null ? str : C11649x.m2264a() ? "sandbox.xmpush.xiaomi.com" : C11649x.m2262b() ? "10.38.162.35" : "app.chat.xiaomi.net";
    }

    /* renamed from: a */
    public static final void m3868a(String str) {
        if (C11649x.m2262b()) {
            return;
        }
        f22259b = str;
    }

    public C11358fb(Map<String, Integer> map, int i, String str, InterfaceC11361fe interfaceC11361fe) {
        m3867a(map, i, str, interfaceC11361fe);
    }

    /* renamed from: a */
    private void m3867a(Map<String, Integer> map, int i, String str, InterfaceC11361fe interfaceC11361fe) {
        this.f22260a = i;
        this.f22264c = str;
        this.f22261a = interfaceC11361fe;
    }

    /* renamed from: b */
    public void m3864b(String str) {
        this.f22266e = str;
    }

    /* renamed from: b */
    public String m3865b() {
        return this.f22266e;
    }

    /* renamed from: a */
    public int m3871a() {
        return this.f22260a;
    }

    /* renamed from: c */
    public String m3863c() {
        if (this.f22265d == null) {
            this.f22265d = m3870a();
        }
        return this.f22265d;
    }

    /* renamed from: c */
    public void m3862c(String str) {
        this.f22265d = str;
    }

    /* renamed from: a */
    public boolean m3869a() {
        return this.f22262a;
    }

    /* renamed from: a */
    public void m3866a(boolean z) {
        this.f22262a = z;
    }
}
