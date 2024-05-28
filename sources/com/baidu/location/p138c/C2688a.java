package com.baidu.location.p138c;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.c.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2688a {

    /* renamed from: a */
    public int f5489a;

    /* renamed from: b */
    public long f5490b;

    /* renamed from: c */
    public int f5491c;

    /* renamed from: d */
    public int f5492d;

    /* renamed from: e */
    public int f5493e;

    /* renamed from: f */
    public int f5494f;

    /* renamed from: g */
    public long f5495g;

    /* renamed from: h */
    public int f5496h;

    /* renamed from: i */
    public char f5497i;

    /* renamed from: j */
    public int f5498j;

    /* renamed from: k */
    public int f5499k;

    /* renamed from: l */
    public int f5500l;

    /* renamed from: m */
    public String f5501m;

    /* renamed from: n */
    public String f5502n;

    /* renamed from: o */
    public String f5503o;

    /* renamed from: p */
    private boolean f5504p;

    public C2688a() {
        this.f5489a = -1;
        this.f5490b = -1L;
        this.f5491c = -1;
        this.f5492d = -1;
        this.f5493e = Integer.MAX_VALUE;
        this.f5494f = Integer.MAX_VALUE;
        this.f5495g = 0L;
        this.f5496h = -1;
        this.f5497i = '0';
        this.f5498j = Integer.MAX_VALUE;
        this.f5499k = 0;
        this.f5500l = 0;
        this.f5501m = null;
        this.f5502n = null;
        this.f5503o = null;
        this.f5504p = false;
        this.f5495g = System.currentTimeMillis();
    }

    public C2688a(int i, long j, int i2, int i3, int i4, char c, int i5) {
        this.f5489a = -1;
        this.f5490b = -1L;
        this.f5491c = -1;
        this.f5492d = -1;
        this.f5493e = Integer.MAX_VALUE;
        this.f5494f = Integer.MAX_VALUE;
        this.f5495g = 0L;
        this.f5496h = -1;
        this.f5497i = '0';
        this.f5498j = Integer.MAX_VALUE;
        this.f5499k = 0;
        this.f5500l = 0;
        this.f5501m = null;
        this.f5502n = null;
        this.f5503o = null;
        this.f5504p = false;
        this.f5489a = i;
        this.f5490b = j;
        this.f5491c = i2;
        this.f5492d = i3;
        this.f5496h = i4;
        this.f5497i = c;
        this.f5495g = System.currentTimeMillis();
        this.f5498j = i5;
    }

    public C2688a(C2688a c2688a) {
        this(c2688a.f5489a, c2688a.f5490b, c2688a.f5491c, c2688a.f5492d, c2688a.f5496h, c2688a.f5497i, c2688a.f5498j);
        this.f5495g = c2688a.f5495g;
        this.f5501m = c2688a.f5501m;
        this.f5499k = c2688a.f5499k;
        this.f5503o = c2688a.f5503o;
        this.f5500l = c2688a.f5500l;
        this.f5502n = c2688a.f5502n;
    }

    /* renamed from: a */
    public boolean m19269a() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.f5495g;
        return currentTimeMillis - j > 0 && currentTimeMillis - j < 3000;
    }

    /* renamed from: a */
    public boolean m19268a(C2688a c2688a) {
        if (this.f5489a == c2688a.f5489a && this.f5490b == c2688a.f5490b && this.f5492d == c2688a.f5492d && this.f5491c == c2688a.f5491c) {
            String str = this.f5502n;
            if (str == null || !str.equals(c2688a.f5502n)) {
                return this.f5502n == null && c2688a.f5502n == null;
            }
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public boolean m19267b() {
        return this.f5489a > -1 && this.f5490b > 0;
    }

    /* renamed from: c */
    public boolean m19266c() {
        return this.f5489a == -1 && this.f5490b == -1 && this.f5492d == -1 && this.f5491c == -1;
    }

    /* renamed from: d */
    public boolean m19265d() {
        return this.f5489a > -1 && this.f5490b > -1 && this.f5492d == -1 && this.f5491c == -1;
    }

    /* renamed from: e */
    public boolean m19264e() {
        return this.f5489a > -1 && this.f5490b > -1 && this.f5492d > -1 && this.f5491c > -1;
    }

    /* renamed from: f */
    public void m19263f() {
        this.f5504p = true;
    }

    /* renamed from: g */
    public String m19262g() {
        return String.format(Locale.CHINA, "%d|%d|%d|%d", Integer.valueOf(this.f5491c), Integer.valueOf(this.f5492d), Integer.valueOf(this.f5489a), Long.valueOf(this.f5490b));
    }

    /* renamed from: h */
    public String m19261h() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(this.f5497i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d&clp=%d", Integer.valueOf(this.f5491c), Integer.valueOf(this.f5492d), Integer.valueOf(this.f5489a), Long.valueOf(this.f5490b), Integer.valueOf(this.f5496h), Integer.valueOf(this.f5499k)));
        stringBuffer.append("&cl_t=");
        stringBuffer.append(this.f5495g);
        if (this.f5498j != Integer.MAX_VALUE) {
            stringBuffer.append("&cl_cs=");
            stringBuffer.append(this.f5498j);
        }
        if (this.f5504p) {
            stringBuffer.append("&newcl=1");
        }
        stringBuffer.append("&cl_api=");
        stringBuffer.append(this.f5500l);
        if (this.f5503o != null) {
            stringBuffer.append("&clnrs=");
            stringBuffer.append(this.f5503o);
        }
        stringBuffer.append("&cl_list=");
        stringBuffer.append(C2689b.m19234i());
        return stringBuffer.toString();
    }

    /* renamed from: i */
    public String m19260i() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw2=");
        stringBuffer.append(this.f5497i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl2=%d|%d|%d|%d&cl_s2=%d&clp2=%d&cl_t2=%d", Integer.valueOf(this.f5491c), Integer.valueOf(this.f5492d), Integer.valueOf(this.f5489a), Long.valueOf(this.f5490b), Integer.valueOf(this.f5496h), Integer.valueOf(this.f5499k), Long.valueOf(this.f5495g)));
        if (this.f5498j != Integer.MAX_VALUE) {
            stringBuffer.append("&cl_cs2=");
            stringBuffer.append(this.f5498j);
        }
        if (this.f5503o != null) {
            stringBuffer.append("&clnrs2=");
            stringBuffer.append(this.f5503o);
        }
        return stringBuffer.toString();
    }
}
