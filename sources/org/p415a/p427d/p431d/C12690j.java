package org.p415a.p427d.p431d;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.p415a.p427d.InterfaceC12630a;
import org.p415a.p427d.InterfaceC12696f;
import org.p415a.p427d.p435h.C12721q;
import org.p415a.p427d.p435h.C12722r;
import org.p415a.p427d.p435h.C12723s;
import org.p415a.p448g.C12966b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.d.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12690j implements InterfaceC12630a {

    /* renamed from: a */
    private static final BigInteger f25843a = BigInteger.valueOf(1);

    /* renamed from: b */
    private C12691k f25844b = new C12691k();

    /* renamed from: c */
    private C12722r f25845c;

    /* renamed from: d */
    private SecureRandom f25846d;

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public int mo1399a() {
        return this.f25844b.m1395a();
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public void mo1398a(boolean z, InterfaceC12696f interfaceC12696f) {
        SecureRandom secureRandom;
        this.f25844b.m1393a(z, interfaceC12696f);
        if (interfaceC12696f instanceof C12721q) {
            C12721q c12721q = (C12721q) interfaceC12696f;
            this.f25845c = (C12722r) c12721q.m1324b();
            secureRandom = c12721q.m1325a();
        } else {
            this.f25845c = (C12722r) interfaceC12696f;
            secureRandom = new SecureRandom();
        }
        this.f25846d = secureRandom;
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public byte[] mo1397a(byte[] bArr, int i, int i2) {
        BigInteger m1390b;
        C12723s c12723s;
        BigInteger m1320d;
        if (this.f25845c != null) {
            BigInteger m1392a = this.f25844b.m1392a(bArr, i, i2);
            C12722r c12722r = this.f25845c;
            if (!(c12722r instanceof C12723s) || (m1320d = (c12723s = (C12723s) c12722r).m1320d()) == null) {
                m1390b = this.f25844b.m1390b(m1392a);
            } else {
                BigInteger b = c12723s.m1322b();
                BigInteger bigInteger = f25843a;
                BigInteger m407a = C12966b.m407a(bigInteger, b.subtract(bigInteger), this.f25846d);
                m1390b = this.f25844b.m1390b(m407a.modPow(m1320d, b).multiply(m1392a).mod(b)).multiply(m407a.modInverse(b)).mod(b);
                if (!m1392a.equals(m1390b.modPow(m1320d, b))) {
                    throw new IllegalStateException("RSA engine faulty decryption/signing detected");
                }
            }
            return this.f25844b.m1394a(m1390b);
        }
        throw new IllegalStateException("RSA engine not initialised");
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: b */
    public int mo1396b() {
        return this.f25844b.m1391b();
    }
}
