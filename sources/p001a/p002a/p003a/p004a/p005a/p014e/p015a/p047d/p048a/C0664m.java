package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;

/* renamed from: a.a.a.a.a.e.a.d.a.m */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0664m implements InterfaceC0654e {
    /* renamed from: a */
    public byte[] m22513a(byte b, BigInteger bigInteger) {
        byte[] bArr = new byte[bigInteger.bitLength() + 1];
        short s = (short) (1 << b);
        BigInteger valueOf = BigInteger.valueOf(s);
        int i = 0;
        int i2 = 0;
        while (bigInteger.signum() > 0) {
            if (bigInteger.testBit(0)) {
                BigInteger mod = bigInteger.mod(valueOf);
                if (mod.testBit(b - 1)) {
                    bArr[i2] = (byte) (mod.intValue() - s);
                } else {
                    bArr[i2] = (byte) mod.intValue();
                }
                bigInteger = bigInteger.subtract(BigInteger.valueOf(bArr[i2]));
                i = i2;
            } else {
                bArr[i2] = 0;
            }
            bigInteger = bigInteger.shiftRight(1);
            i2++;
        }
        int i3 = i + 1;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, 0, bArr2, 0, i3);
        return bArr2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0654e
    /* renamed from: a */
    public AbstractC0655f mo22506a(AbstractC0655f abstractC0655f, BigInteger bigInteger, InterfaceC0660i interfaceC0660i) {
        C0665n c0665n;
        int i;
        int length;
        if (interfaceC0660i != null && (interfaceC0660i instanceof C0665n)) {
            c0665n = (C0665n) interfaceC0660i;
        } else {
            c0665n = new C0665n();
        }
        int bitLength = bigInteger.bitLength();
        byte b = 8;
        if (bitLength < 13) {
            b = 2;
            i = 1;
        } else if (bitLength < 41) {
            b = 3;
            i = 2;
        } else if (bitLength < 121) {
            i = 4;
            b = 4;
        } else if (bitLength < 337) {
            b = 5;
            i = 8;
        } else if (bitLength < 897) {
            b = 6;
            i = 16;
        } else if (bitLength < 2305) {
            b = 7;
            i = 32;
        } else {
            i = 127;
        }
        AbstractC0655f[] m22512a = c0665n.m22512a();
        AbstractC0655f m22509b = c0665n.m22509b();
        if (m22512a == null) {
            m22512a = new AbstractC0655f[]{abstractC0655f};
            length = 1;
        } else {
            length = m22512a.length;
        }
        if (m22509b == null) {
            m22509b = abstractC0655f.mo22565i();
        }
        if (length < i) {
            AbstractC0655f[] abstractC0655fArr = new AbstractC0655f[i];
            System.arraycopy(m22512a, 0, abstractC0655fArr, 0, length);
            while (length < i) {
                abstractC0655fArr[length] = m22509b.mo22569a(abstractC0655fArr[length - 1]);
                length++;
            }
            m22512a = abstractC0655fArr;
        }
        byte[] m22513a = m22513a(b, bigInteger);
        int length2 = m22513a.length;
        AbstractC0655f mo22603d = abstractC0655f.m22580b().mo22603d();
        for (int i2 = length2 - 1; i2 >= 0; i2--) {
            mo22603d = mo22603d.mo22565i();
            if (m22513a[i2] != 0) {
                if (m22513a[i2] > 0) {
                    mo22603d = mo22603d.mo22569a(m22512a[(m22513a[i2] - 1) / 2]);
                } else {
                    mo22603d = mo22603d.mo22567b(m22512a[((-m22513a[i2]) - 1) / 2]);
                }
            }
        }
        c0665n.m22510a(m22512a);
        c0665n.m22511a(m22509b);
        abstractC0655f.m22582a(c0665n);
        return mo22603d;
    }
}
