package org.p415a.p436e.p437a.p442b;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.b.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12848d {

    /* renamed from: a */
    protected final BigInteger f26060a;

    /* renamed from: b */
    protected final BigInteger f26061b;

    /* renamed from: c */
    protected final BigInteger f26062c;

    /* renamed from: d */
    protected final BigInteger f26063d;

    /* renamed from: e */
    protected final BigInteger f26064e;

    /* renamed from: f */
    protected final BigInteger f26065f;

    /* renamed from: g */
    protected final BigInteger f26066g;

    /* renamed from: h */
    protected final BigInteger f26067h;

    /* renamed from: i */
    protected final int f26068i;

    public C12848d(BigInteger bigInteger, BigInteger bigInteger2, BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2, BigInteger bigInteger3, BigInteger bigInteger4, int i) {
        m944a(bigIntegerArr, "v1");
        m944a(bigIntegerArr2, "v2");
        this.f26060a = bigInteger;
        this.f26061b = bigInteger2;
        this.f26062c = bigIntegerArr[0];
        this.f26063d = bigIntegerArr[1];
        this.f26064e = bigIntegerArr2[0];
        this.f26065f = bigIntegerArr2[1];
        this.f26066g = bigInteger3;
        this.f26067h = bigInteger4;
        this.f26068i = i;
    }

    /* renamed from: a */
    private static void m944a(BigInteger[] bigIntegerArr, String str) {
        if (bigIntegerArr == null || bigIntegerArr.length != 2 || bigIntegerArr[0] == null || bigIntegerArr[1] == null) {
            throw new IllegalArgumentException("'" + str + "' must consist of exactly 2 (non-null) values");
        }
    }

    /* renamed from: a */
    public BigInteger m945a() {
        return this.f26060a;
    }

    /* renamed from: b */
    public BigInteger m943b() {
        return this.f26062c;
    }

    /* renamed from: c */
    public BigInteger m942c() {
        return this.f26063d;
    }

    /* renamed from: d */
    public BigInteger m941d() {
        return this.f26064e;
    }

    /* renamed from: e */
    public BigInteger m940e() {
        return this.f26065f;
    }

    /* renamed from: f */
    public BigInteger m939f() {
        return this.f26066g;
    }

    /* renamed from: g */
    public BigInteger m938g() {
        return this.f26067h;
    }

    /* renamed from: h */
    public int m937h() {
        return this.f26068i;
    }
}
