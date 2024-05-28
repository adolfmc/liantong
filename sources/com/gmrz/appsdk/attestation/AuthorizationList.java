package com.gmrz.appsdk.attestation;

import java.util.Arrays;

/* renamed from: com.gmrz.appsdk.attestation.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AuthorizationList {

    /* renamed from: a */
    private int[] f10231a;

    /* renamed from: b */
    private int f10232b;

    /* renamed from: c */
    private int f10233c;

    /* renamed from: d */
    private int[] f10234d;

    /* renamed from: e */
    private int[] f10235e;

    /* renamed from: f */
    private int f10236f;

    /* renamed from: g */
    private int f10237g;

    /* renamed from: h */
    private int f10238h;

    /* renamed from: i */
    private int f10239i;

    /* renamed from: j */
    private int f10240j;

    /* renamed from: k */
    private boolean f10241k;

    /* renamed from: l */
    private int f10242l;

    /* renamed from: m */
    private int f10243m;

    /* renamed from: n */
    private boolean f10244n;

    /* renamed from: o */
    private boolean f10245o;

    /* renamed from: p */
    private byte[] f10246p;

    /* renamed from: q */
    private int f10247q;

    /* renamed from: r */
    private int f10248r;

    /* renamed from: s */
    private boolean f10249s;

    /* renamed from: t */
    private RootOfTrust f10250t;

    /* renamed from: u */
    private int f10251u;

    /* renamed from: v */
    private int f10252v;

    /* renamed from: w */
    private int f10253w;

    /* renamed from: x */
    private byte[] f10254x;

    /* renamed from: a */
    public void m15876a(int i) {
        this.f10232b = i;
    }

    /* renamed from: b */
    public void m15870b(int[] iArr) {
        this.f10231a = iArr;
    }

    /* renamed from: c */
    public int[] m15869c() {
        return this.f10231a;
    }

    /* renamed from: d */
    public void m15866d(int i) {
        this.f10233c = i;
    }

    /* renamed from: e */
    public void m15865e(int i) {
        this.f10248r = i;
    }

    /* renamed from: f */
    public void m15864f(int i) {
        this.f10242l = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AuthorizationList{purpose=");
        sb.append(Arrays.toString(this.f10231a));
        sb.append(", algorithm=");
        sb.append(this.f10232b);
        sb.append(", keySize=");
        sb.append(this.f10233c);
        sb.append(", digest=");
        sb.append(Arrays.toString(this.f10234d));
        sb.append(", padding=");
        sb.append(Arrays.toString(this.f10235e));
        sb.append(", ecCurve=");
        sb.append(this.f10236f);
        sb.append(", rsaPublicExponent=");
        sb.append(this.f10237g);
        sb.append(", activeDateTime=");
        sb.append(this.f10238h);
        sb.append(", originationExpireDateTime=");
        sb.append(this.f10239i);
        sb.append(", usageExpireDateTime=");
        sb.append(this.f10240j);
        sb.append(", noAuthRequired=");
        sb.append(this.f10241k);
        sb.append(", userAuthType=");
        sb.append(this.f10242l);
        sb.append(", authTimeout=");
        sb.append(this.f10243m);
        sb.append(", allowWhileOnBody=");
        sb.append(this.f10244n);
        sb.append(", allApplications=");
        sb.append(this.f10245o);
        sb.append(", applicationId='");
        byte[] bArr = this.f10246p;
        sb.append(bArr != null ? new String(bArr) : null);
        sb.append('\'');
        sb.append(", creationDateTime=");
        sb.append(this.f10247q);
        sb.append(", origin=");
        sb.append(this.f10248r);
        sb.append(", rollbackResitant=");
        sb.append(this.f10249s);
        sb.append(", rootOfTrust=");
        sb.append(this.f10250t);
        sb.append(", osVersion=");
        sb.append(this.f10251u);
        sb.append(", osPatchLevel=");
        sb.append(this.f10252v);
        sb.append(", attestationChallenge=");
        sb.append(this.f10253w);
        sb.append(", attestationApplicationId=");
        byte[] bArr2 = this.f10254x;
        sb.append(bArr2 != null ? new String(bArr2) : null);
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: a */
    public void m15874a(int[] iArr) {
        this.f10234d = iArr;
    }

    /* renamed from: b */
    public void m15871b(boolean z) {
        this.f10241k = z;
    }

    /* renamed from: c */
    public void m15868c(int i) {
        this.f10236f = i;
    }

    /* renamed from: d */
    public boolean m15867d() {
        return this.f10245o;
    }

    /* renamed from: a */
    public int m15877a() {
        return this.f10243m;
    }

    /* renamed from: b */
    public void m15872b(int i) {
        this.f10243m = i;
    }

    /* renamed from: a */
    public void m15875a(boolean z) {
        this.f10245o = z;
    }

    /* renamed from: b */
    public int m15873b() {
        return this.f10248r;
    }
}
