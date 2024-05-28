package com.baidu.p122b.p123a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.a.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2364f {

    /* renamed from: a */
    public static int f4105a = 5;

    /* renamed from: b */
    public static int f4106b = 40;

    /* renamed from: c */
    private C2359b f4107c = new C2359b(f4106b);

    public C2364f() {
        this.f4107c.m20408a(0, f4106b, true);
    }

    /* renamed from: a */
    public void m20372a(C2359b c2359b, int i, int i2, int i3) {
        C2359b m20398c = this.f4107c.m20398c(i, i + i2);
        switch (i3) {
            case 0:
                m20398c.m20406a(c2359b);
                break;
            case 1:
            default:
                m20398c.m20397c(c2359b);
                break;
            case 2:
                m20398c.m20393d(c2359b);
                break;
            case 3:
                m20398c.m20401b(c2359b);
                break;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            this.f4107c.m20407a(i + i4, m20398c.m20399c(i4));
        }
    }

    /* renamed from: a */
    public byte[] m20373a() {
        return this.f4107c.m20411a();
    }
}
