package com.networkbench.agent.impl.p252e;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.w */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6377w {

    /* renamed from: j */
    private static final String f16066j = "\\.";

    /* renamed from: a */
    private String f16067a;

    /* renamed from: b */
    private String f16068b;

    /* renamed from: c */
    private String f16069c;

    /* renamed from: d */
    private String f16070d;

    /* renamed from: e */
    private String f16071e;

    /* renamed from: f */
    private String f16072f;

    /* renamed from: g */
    private String f16073g;

    /* renamed from: h */
    private String f16074h;

    /* renamed from: i */
    private String f16075i;

    /* renamed from: a */
    public String m10237a() {
        return this.f16067a;
    }

    /* renamed from: a */
    public void m10236a(String str) {
        this.f16073g = str;
    }

    /* renamed from: b */
    public void m10234b(String str) {
        this.f16075i = str;
        String[] split = str.split("\\.");
        if (split != null && split.length == 2) {
            m10232c(split[1]);
        } else {
            m10232c("0");
        }
    }

    /* renamed from: c */
    public void m10232c(String str) {
        this.f16067a = str;
    }

    /* renamed from: d */
    public void m10230d(String str) {
        if (str == null) {
            str = "";
        }
        this.f16068b = str;
    }

    /* renamed from: b */
    public String m10235b() {
        if (TextUtils.isEmpty(this.f16072f)) {
            this.f16072f = this.f16069c;
        }
        return this.f16072f;
    }

    /* renamed from: c */
    public String m10233c() {
        return this.f16068b;
    }

    /* renamed from: d */
    public String m10231d() {
        return this.f16069c;
    }

    /* renamed from: e */
    public String m10229e() {
        return this.f16070d;
    }

    /* renamed from: f */
    public String m10227f() {
        return this.f16073g;
    }

    /* renamed from: g */
    public String m10225g() {
        if (TextUtils.isEmpty(this.f16071e)) {
            this.f16071e = this.f16068b;
        }
        return this.f16071e;
    }

    /* renamed from: e */
    public void m10228e(String str) {
        this.f16069c = str;
    }

    /* renamed from: f */
    public void m10226f(String str) {
        this.f16070d = str;
    }

    /* renamed from: g */
    public void m10224g(String str) {
        this.f16072f = str;
    }

    /* renamed from: h */
    public void m10222h(String str) {
        this.f16071e = str;
    }

    /* renamed from: i */
    public void m10221i(String str) {
        this.f16074h = str;
    }

    /* renamed from: h */
    public String m10223h() {
        return this.f16074h;
    }

    public String toString() {
        return "appId:" + this.f16067a + ", className:" + this.f16068b + ", methodName:" + this.f16069c + ", optTypeId:" + this.f16070d + ", vcName:" + this.f16071e + ", acName:" + this.f16072f + ", token:" + this.f16073g + ", imgPath:" + this.f16074h;
    }
}
