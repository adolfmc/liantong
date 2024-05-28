package org.p415a.p427d.p431d;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.p415a.p427d.C12704h;
import org.p415a.p427d.InterfaceC12630a;
import org.p415a.p427d.InterfaceC12696f;
import org.p415a.p427d.p435h.C12715k;
import org.p415a.p427d.p435h.C12717m;
import org.p415a.p427d.p435h.C12718n;
import org.p415a.p427d.p435h.C12721q;
import org.p415a.p448g.C12966b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.d.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12687g implements InterfaceC12630a {

    /* renamed from: e */
    private static final BigInteger f25830e = BigInteger.valueOf(0);

    /* renamed from: f */
    private static final BigInteger f25831f = BigInteger.valueOf(1);

    /* renamed from: g */
    private static final BigInteger f25832g = BigInteger.valueOf(2);

    /* renamed from: a */
    private C12715k f25833a;

    /* renamed from: b */
    private SecureRandom f25834b;

    /* renamed from: c */
    private boolean f25835c;

    /* renamed from: d */
    private int f25836d;

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public int mo1399a() {
        return this.f25835c ? (this.f25836d - 1) / 8 : ((this.f25836d + 7) / 8) * 2;
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public void mo1398a(boolean z, InterfaceC12696f interfaceC12696f) {
        SecureRandom secureRandom;
        if (interfaceC12696f instanceof C12721q) {
            C12721q c12721q = (C12721q) interfaceC12696f;
            this.f25833a = (C12715k) c12721q.m1324b();
            secureRandom = c12721q.m1325a();
        } else {
            this.f25833a = (C12715k) interfaceC12696f;
            secureRandom = new SecureRandom();
        }
        this.f25834b = secureRandom;
        this.f25835c = z;
        this.f25836d = this.f25833a.m1334b().m1333a().bitLength();
        if (z) {
            if (!(this.f25833a instanceof C12718n)) {
                throw new IllegalArgumentException("ElGamalPublicKeyParameters are required for encryption.");
            }
        } else if (!(this.f25833a instanceof C12717m)) {
            throw new IllegalArgumentException("ElGamalPrivateKeyParameters are required for decryption.");
        }
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public byte[] mo1397a(byte[] bArr, int i, int i2) {
        if (this.f25833a != null) {
            if (i2 <= (this.f25835c ? ((this.f25836d - 1) + 7) / 8 : mo1399a())) {
                BigInteger m1333a = this.f25833a.m1334b().m1333a();
                if (this.f25833a instanceof C12717m) {
                    int i3 = i2 / 2;
                    byte[] bArr2 = new byte[i3];
                    byte[] bArr3 = new byte[i3];
                    System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
                    System.arraycopy(bArr, i + bArr2.length, bArr3, 0, bArr3.length);
                    return C12966b.m408a(new BigInteger(1, bArr2).modPow(m1333a.subtract(f25831f).subtract(((C12717m) this.f25833a).m1330c()), m1333a).multiply(new BigInteger(1, bArr3)).mod(m1333a));
                }
                if (i != 0 || i2 != bArr.length) {
                    byte[] bArr4 = new byte[i2];
                    System.arraycopy(bArr, i, bArr4, 0, i2);
                    bArr = bArr4;
                }
                BigInteger bigInteger = new BigInteger(1, bArr);
                if (bigInteger.compareTo(m1333a) < 0) {
                    C12718n c12718n = (C12718n) this.f25833a;
                    int bitLength = m1333a.bitLength();
                    BigInteger bigInteger2 = new BigInteger(bitLength, this.f25834b);
                    while (true) {
                        if (!bigInteger2.equals(f25830e) && bigInteger2.compareTo(m1333a.subtract(f25832g)) <= 0) {
                            break;
                        }
                        bigInteger2 = new BigInteger(bitLength, this.f25834b);
                    }
                    BigInteger modPow = this.f25833a.m1334b().m1332b().modPow(bigInteger2, m1333a);
                    BigInteger mod = bigInteger.multiply(c12718n.m1329c().modPow(bigInteger2, m1333a)).mod(m1333a);
                    byte[] byteArray = modPow.toByteArray();
                    byte[] byteArray2 = mod.toByteArray();
                    byte[] bArr5 = new byte[mo1396b()];
                    if (byteArray.length > bArr5.length / 2) {
                        System.arraycopy(byteArray, 1, bArr5, (bArr5.length / 2) - (byteArray.length - 1), byteArray.length - 1);
                    } else {
                        System.arraycopy(byteArray, 0, bArr5, (bArr5.length / 2) - byteArray.length, byteArray.length);
                    }
                    if (byteArray2.length > bArr5.length / 2) {
                        System.arraycopy(byteArray2, 1, bArr5, bArr5.length - (byteArray2.length - 1), byteArray2.length - 1);
                    } else {
                        System.arraycopy(byteArray2, 0, bArr5, bArr5.length - byteArray2.length, byteArray2.length);
                    }
                    return bArr5;
                }
                throw new C12704h("input too large for ElGamal cipher.\n");
            }
            throw new C12704h("input too large for ElGamal cipher.\n");
        }
        throw new IllegalStateException("ElGamal engine not initialised");
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: b */
    public int mo1396b() {
        return this.f25835c ? ((this.f25836d + 7) / 8) * 2 : (this.f25836d - 1) / 8;
    }
}
