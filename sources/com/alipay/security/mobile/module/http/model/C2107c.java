package com.alipay.security.mobile.module.http.model;

import com.alipay.security.mobile.module.p110a.C2081a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.http.model.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2107c extends C2105a {

    /* renamed from: c */
    public static final int f4016c = 1;

    /* renamed from: d */
    public static final int f4017d = 2;

    /* renamed from: e */
    public static final int f4018e = 3;

    /* renamed from: f */
    public static final String f4019f = "APPKEY_ERROR";

    /* renamed from: g */
    public static final String f4020g = "SUCCESS";

    /* renamed from: h */
    public String f4021h;

    /* renamed from: i */
    public String f4022i;

    /* renamed from: j */
    public String f4023j;

    /* renamed from: k */
    public String f4024k;

    /* renamed from: l */
    public String f4025l;

    /* renamed from: m */
    public String f4026m;

    /* renamed from: n */
    public String f4027n;

    /* renamed from: o */
    public String f4028o;

    /* renamed from: p */
    public String f4029p = "";

    /* renamed from: a */
    public int m20465a() {
        return this.f4014a ? C2081a.m20577a(this.f4021h) ? 2 : 1 : "APPKEY_ERROR".equals(this.f4015b) ? 3 : 2;
    }

    /* renamed from: b */
    public boolean m20464b() {
        return "1".equals(this.f4023j);
    }

    /* renamed from: c */
    public String m20463c() {
        String str = this.f4024k;
        return str == null ? "0" : str;
    }
}
