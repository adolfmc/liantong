package org.p415a.p427d.p431d;

import java.math.BigInteger;
import org.p415a.p427d.C12704h;
import org.p415a.p427d.InterfaceC12696f;
import org.p415a.p427d.p435h.C12721q;
import org.p415a.p427d.p435h.C12722r;
import org.p415a.p427d.p435h.C12723s;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.d.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12691k {

    /* renamed from: a */
    private C12722r f25847a;

    /* renamed from: b */
    private boolean f25848b;

    /* renamed from: a */
    public int m1395a() {
        int bitLength = this.f25847a.m1322b().bitLength();
        return this.f25848b ? ((bitLength + 7) / 8) - 1 : (bitLength + 7) / 8;
    }

    /* renamed from: a */
    public BigInteger m1392a(byte[] bArr, int i, int i2) {
        if (i2 <= m1395a() + 1) {
            if (i2 != m1395a() + 1 || this.f25848b) {
                if (i != 0 || i2 != bArr.length) {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, i, bArr2, 0, i2);
                    bArr = bArr2;
                }
                BigInteger bigInteger = new BigInteger(1, bArr);
                if (bigInteger.compareTo(this.f25847a.m1322b()) < 0) {
                    return bigInteger;
                }
                throw new C12704h("input too large for RSA cipher.");
            }
            throw new C12704h("input too large for RSA cipher.");
        }
        throw new C12704h("input too large for RSA cipher.");
    }

    /* renamed from: a */
    public void m1393a(boolean z, InterfaceC12696f interfaceC12696f) {
        if (interfaceC12696f instanceof C12721q) {
            interfaceC12696f = ((C12721q) interfaceC12696f).m1324b();
        }
        this.f25847a = (C12722r) interfaceC12696f;
        this.f25848b = z;
    }

    /* renamed from: a */
    public byte[] m1394a(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (this.f25848b) {
            if (byteArray[0] == 0 && byteArray.length > m1391b()) {
                byte[] bArr = new byte[byteArray.length - 1];
                System.arraycopy(byteArray, 1, bArr, 0, bArr.length);
                return bArr;
            } else if (byteArray.length < m1391b()) {
                byte[] bArr2 = new byte[m1391b()];
                System.arraycopy(byteArray, 0, bArr2, bArr2.length - byteArray.length, byteArray.length);
                return bArr2;
            }
        } else if (byteArray[0] == 0) {
            byte[] bArr3 = new byte[byteArray.length - 1];
            System.arraycopy(byteArray, 1, bArr3, 0, bArr3.length);
            return bArr3;
        }
        return byteArray;
    }

    /* renamed from: b */
    public int m1391b() {
        int bitLength = this.f25847a.m1322b().bitLength();
        return this.f25848b ? (bitLength + 7) / 8 : ((bitLength + 7) / 8) - 1;
    }

    /* renamed from: b */
    public BigInteger m1390b(BigInteger bigInteger) {
        C12722r c12722r = this.f25847a;
        if (c12722r instanceof C12723s) {
            C12723s c12723s = (C12723s) c12722r;
            BigInteger m1319e = c12723s.m1319e();
            BigInteger m1318f = c12723s.m1318f();
            BigInteger m1317g = c12723s.m1317g();
            BigInteger m1316h = c12723s.m1316h();
            BigInteger m1315i = c12723s.m1315i();
            BigInteger modPow = bigInteger.remainder(m1319e).modPow(m1317g, m1319e);
            BigInteger modPow2 = bigInteger.remainder(m1318f).modPow(m1316h, m1318f);
            return modPow.subtract(modPow2).multiply(m1315i).mod(m1319e).multiply(m1318f).add(modPow2);
        }
        return bigInteger.modPow(c12722r.m1321c(), this.f25847a.m1322b());
    }
}
