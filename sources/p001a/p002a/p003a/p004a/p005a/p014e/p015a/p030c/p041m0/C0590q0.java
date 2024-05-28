package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0639t;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.q0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0590q0 extends C0639t {

    /* renamed from: c */
    public BigInteger f1875c;

    /* renamed from: d */
    public int f1876d;

    public C0590q0(BigInteger bigInteger, SecureRandom secureRandom, int i, int i2) {
        super(secureRandom, i);
        if (i >= 12) {
            if (bigInteger.testBit(0)) {
                this.f1875c = bigInteger;
                this.f1876d = i2;
                return;
            }
            throw new IllegalArgumentException("public exponent cannot be even");
        }
        throw new IllegalArgumentException("key strength too small");
    }

    /* renamed from: c */
    public int m22770c() {
        return this.f1876d;
    }

    /* renamed from: d */
    public BigInteger m22769d() {
        return this.f1875c;
    }
}
