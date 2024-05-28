package com.bytedance.pangle.log;

/* renamed from: com.bytedance.pangle.log.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3870a {

    /* renamed from: a */
    private String f9230a;

    /* renamed from: b */
    private String f9231b;

    /* renamed from: c */
    private String f9232c;

    /* renamed from: d */
    private long f9233d;

    /* renamed from: e */
    private long f9234e;

    /* renamed from: a */
    public static C3870a m16784a(String str, String str2, String str3) {
        return new C3870a(str, str2, str3);
    }

    private C3870a(String str, String str2, String str3) {
        this.f9230a = str;
        this.f9231b = str2;
        this.f9232c = str3;
        long currentTimeMillis = System.currentTimeMillis();
        this.f9234e = currentTimeMillis;
        this.f9233d = currentTimeMillis;
        String str4 = this.f9230a;
        ZeusLogger.m16792i(str4, this.f9231b + String.format(" watcher[%s]-start", str3));
    }

    /* renamed from: a */
    public final long m16785a(String str) {
        long currentTimeMillis = System.currentTimeMillis() - this.f9234e;
        long currentTimeMillis2 = System.currentTimeMillis() - this.f9233d;
        String str2 = this.f9230a;
        ZeusLogger.m16792i(str2, this.f9231b + String.format(" watcher[%s]-%s cost=%s, total=%s", this.f9232c, str, Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis2)));
        return currentTimeMillis2;
    }

    /* renamed from: a */
    public final long m16786a() {
        return System.currentTimeMillis() - this.f9233d;
    }
}
