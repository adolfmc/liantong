package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.p0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0588p0 implements InterfaceC0542i {

    /* renamed from: a */
    public C0592r0 f1868a;

    /* renamed from: b */
    public BigInteger f1869b;

    public C0588p0(C0592r0 c0592r0, BigInteger bigInteger) {
        if (!(c0592r0 instanceof C0594s0)) {
            this.f1868a = c0592r0;
            this.f1869b = bigInteger;
            return;
        }
        throw new IllegalArgumentException("RSA parameters should be for a public key");
    }

    /* renamed from: a */
    public BigInteger m22777a() {
        return this.f1869b;
    }

    /* renamed from: b */
    public C0592r0 m22776b() {
        return this.f1868a;
    }
}
