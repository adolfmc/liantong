package com.networkbench.agent.impl.util;

import android.util.Log;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6641j {

    /* renamed from: a */
    public static final int f17188a = 1;

    /* renamed from: b */
    public static final int f17189b = 2;

    /* renamed from: c */
    public static final int f17190c = 4;

    /* renamed from: d */
    public static final int f17191d = 8;

    /* renamed from: e */
    public static final int f17192e = 16;

    /* renamed from: f */
    public static final int f17193f = 32;

    /* renamed from: g */
    public static final int f17194g = 64;

    /* renamed from: h */
    public static final int f17195h = 128;

    /* renamed from: i */
    public static final int f17196i = 256;

    /* renamed from: j */
    public static final int f17197j = 512;

    /* renamed from: x */
    private static final InterfaceC6393e f17198x = C6394f.m10150a();

    /* renamed from: l */
    private boolean f17200l;

    /* renamed from: m */
    private boolean f17201m;

    /* renamed from: n */
    private boolean f17202n;

    /* renamed from: o */
    private boolean f17203o;

    /* renamed from: k */
    private boolean f17199k = false;

    /* renamed from: p */
    private boolean f17204p = true;

    /* renamed from: q */
    private boolean f17205q = true;

    /* renamed from: r */
    private boolean f17206r = true;

    /* renamed from: s */
    private boolean f17207s = false;

    /* renamed from: t */
    private boolean f17208t = false;

    /* renamed from: u */
    private boolean f17209u = true;

    /* renamed from: v */
    private boolean f17210v = true;

    /* renamed from: w */
    private boolean f17211w = true;

    /* renamed from: y */
    private boolean f17212y = false;

    /* renamed from: a */
    public void m8952a(int i) {
        m8951a(i, false);
    }

    /* renamed from: a */
    public void m8953a() {
        C6396h.m10127o("setModuleSwitch : \n" + ("Network Switch is " + this.f17199k + "\nUI Switch is " + this.f17206r + "\nCrash Switch is " + this.f17204p + "\nWebView Switch is " + this.f17205q + "\nsocketdata Switch is " + this.f17200l + "\ncross_app Switch is " + this.f17207s + "\nANR monitor Switch is " + this.f17201m + "\nUserAction Switch  is " + this.f17202n + "\ncdnSwitch Switch  is " + this.f17203o + "\nbetaOn Switch is " + this.f17210v));
    }

    /* renamed from: a */
    public void m8951a(int i, boolean z) {
        if (!z && (i < 0 || this.f17212y)) {
            f17198x.mo10122a("启动的时候已经设置过了, 忽略这一次features设置! ");
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10127o("启动的时候已经设置过了, 忽略这一次features设置! ");
                return;
            }
            return;
        }
        boolean z2 = (i & 1) != 0;
        m8948b(z2);
        Log.d("TingYun", "Network Switch is " + z2);
        boolean z3 = (i & 2) != 0;
        m8934i(z3);
        Log.d("TingYun", "UI Switch is " + z3);
        boolean z4 = (i & 4) != 0;
        m8938g(z4);
        Log.d("TingYun", "Crash Switch is " + z4);
        boolean z5 = (i & 8) != 0;
        m8936h(z5);
        Log.d("TingYun", "WebView Switch is " + z5);
        boolean z6 = (i & 16) != 0;
        m8946c(z6);
        Log.d("TingYun", "socketdata Switch is " + z6);
        boolean z7 = (i & 32) != 0;
        m8932j(z7);
        Log.d("TingYun", "cross_app Switch is " + z7);
        boolean z8 = (i & 64) != 0;
        m8944d(z8);
        Log.d("TingYun", "ANR monitor Switch is " + z8);
        boolean z9 = (i & 128) != 0;
        m8942e(z9);
        Log.d("TingYun", "UserAction Switch  is " + z9);
        boolean z10 = (i & 256) != 0;
        m8940f(z10);
        Log.d("TingYun", "cdnSwitch Switch  is " + z10);
        if (C6638h.m8963w().m8979n()) {
            C6396h.m10127o("setModuleSwitch : \n" + ("Network Switch is " + z2 + "\nUI Switch is " + z3 + "\nCrash Switch is " + z4 + "\nWebView Switch is " + z5 + "\nsocketdata Switch is " + z6 + "\ncross_app Switch is " + z7 + "\nANR monitor Switch is " + z8 + "\nUserAction Switch  is " + z9 + "\ncdnSwitch Switch  is " + z10 + "\nbetaOn Switch is " + this.f17210v));
        }
        this.f17212y = true;
    }

    /* renamed from: a */
    public void m8950a(boolean z) {
        this.f17209u = z;
    }

    /* renamed from: b */
    public boolean m8949b() {
        return this.f17209u;
    }

    /* renamed from: b */
    public void m8948b(boolean z) {
        this.f17199k = z;
    }

    /* renamed from: c */
    public boolean m8947c() {
        return this.f17209u && this.f17199k;
    }

    /* renamed from: c */
    public void m8946c(boolean z) {
        this.f17200l = z;
    }

    /* renamed from: d */
    public boolean m8945d() {
        return this.f17209u && this.f17200l;
    }

    /* renamed from: d */
    public void m8944d(boolean z) {
        this.f17201m = z;
    }

    /* renamed from: e */
    public boolean m8943e() {
        return this.f17209u && this.f17201m;
    }

    /* renamed from: e */
    public void m8942e(boolean z) {
        this.f17202n = z;
    }

    /* renamed from: f */
    public boolean m8941f() {
        return this.f17209u && this.f17202n;
    }

    /* renamed from: f */
    public void m8940f(boolean z) {
        this.f17203o = z;
    }

    /* renamed from: g */
    public boolean m8939g() {
        return this.f17209u && this.f17203o;
    }

    /* renamed from: h */
    public boolean m8937h() {
        return this.f17209u && this.f17204p;
    }

    /* renamed from: i */
    public boolean m8935i() {
        return this.f17209u && this.f17211w && this.f17210v;
    }

    /* renamed from: j */
    public boolean m8933j() {
        return this.f17209u && this.f17206r;
    }

    /* renamed from: g */
    public void m8938g(boolean z) {
        this.f17204p = z;
    }

    /* renamed from: h */
    public void m8936h(boolean z) {
        this.f17205q = z;
    }

    /* renamed from: k */
    public boolean m8931k() {
        return this.f17209u && this.f17205q;
    }

    /* renamed from: l */
    public boolean m8929l() {
        return this.f17209u && this.f17208t && this.f17210v;
    }

    /* renamed from: m */
    public boolean m8927m() {
        return this.f17209u && this.f17206r;
    }

    /* renamed from: i */
    public void m8934i(boolean z) {
        this.f17206r = z;
    }

    /* renamed from: n */
    public boolean m8925n() {
        return this.f17209u && this.f17207s;
    }

    /* renamed from: j */
    public void m8932j(boolean z) {
        this.f17207s = z;
    }

    /* renamed from: k */
    public void m8930k(boolean z) {
        this.f17210v = z;
    }

    /* renamed from: o */
    public boolean m8924o() {
        return this.f17210v;
    }

    /* renamed from: l */
    public void m8928l(boolean z) {
        this.f17208t = z;
    }

    /* renamed from: m */
    public void m8926m(boolean z) {
        this.f17211w = z;
    }
}
