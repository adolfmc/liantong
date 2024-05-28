package com.alipay.apmobilesecuritysdk.p105b;

import com.alipay.security.mobile.module.http.C2104d;
import com.alipay.security.mobile.module.p110a.C2081a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1960a {

    /* renamed from: b */
    private static C1960a f3478b = new C1960a();

    /* renamed from: a */
    private int f3479a = 0;

    /* renamed from: a */
    public static C1960a m21052a() {
        return f3478b;
    }

    /* renamed from: a */
    public final void m21051a(int i) {
        this.f3479a = i;
    }

    /* renamed from: b */
    public final int m21050b() {
        return this.f3479a;
    }

    /* renamed from: c */
    public final String m21049c() {
        String m20470a = C2104d.m20470a();
        if (C2081a.m20573b(m20470a)) {
            return m20470a;
        }
        switch (this.f3479a) {
            case 1:
                return "http://mobilegw.stable.alipay.net/mgw.htm";
            case 2:
                return "https://mobilegw.alipay.com/mgw.htm";
            case 3:
                return "http://mobilegw-1-64.test.alipay.net/mgw.htm";
            case 4:
                return "http://mobilegw.aaa.alipay.net/mgw.htm";
            default:
                return "https://mobilegw.alipay.com/mgw.htm";
        }
    }
}
