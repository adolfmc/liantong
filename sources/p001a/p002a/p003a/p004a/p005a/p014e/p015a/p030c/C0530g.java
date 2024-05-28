package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.DataLengthException;
import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.OutputLengthException;

/* renamed from: a.a.a.a.a.e.a.c.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0530g {

    /* renamed from: a */
    public byte[] f1729a;

    /* renamed from: b */
    public int f1730b;

    /* renamed from: c */
    public boolean f1731c;

    /* renamed from: d */
    public InterfaceC0515e f1732d;

    /* renamed from: e */
    public boolean f1733e;

    /* renamed from: f */
    public boolean f1734f;

    public C0530g() {
    }

    /* renamed from: a */
    public void mo22856a(boolean z, InterfaceC0542i interfaceC0542i) {
        this.f1731c = z;
        m22889c();
        this.f1732d.mo22865a(z, interfaceC0542i);
    }

    /* renamed from: b */
    public InterfaceC0515e m22890b() {
        return this.f1732d;
    }

    /* renamed from: c */
    public void m22889c() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f1729a;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.f1730b = 0;
                this.f1732d.mo22863b();
                return;
            }
        }
    }

    public C0530g(InterfaceC0515e interfaceC0515e) {
        this.f1732d = interfaceC0515e;
        this.f1729a = new byte[interfaceC0515e.mo22861c()];
        boolean z = false;
        this.f1730b = 0;
        String mo22866a = interfaceC0515e.mo22866a();
        int indexOf = mo22866a.indexOf(47) + 1;
        boolean z2 = indexOf > 0 && mo22866a.startsWith("PGP", indexOf);
        this.f1734f = z2;
        if (z2) {
            this.f1733e = true;
            return;
        }
        if (indexOf > 0 && (mo22866a.startsWith("CFB", indexOf) || mo22866a.startsWith("OFB", indexOf) || mo22866a.startsWith("OpenPGP", indexOf) || mo22866a.startsWith("SIC", indexOf) || mo22866a.startsWith("GCTR", indexOf))) {
            z = true;
        }
        this.f1733e = z;
    }

    /* renamed from: b */
    public int mo22853b(int i) {
        int length;
        int i2 = i + this.f1730b;
        if (this.f1734f) {
            length = (i2 % this.f1729a.length) - (this.f1732d.mo22861c() + 2);
        } else {
            length = i2 % this.f1729a.length;
        }
        return i2 - length;
    }

    /* renamed from: a */
    public int m22891a() {
        return this.f1732d.mo22861c();
    }

    /* renamed from: a */
    public int mo22857a(int i) {
        return i + this.f1730b;
    }

    /* renamed from: a */
    public int mo22858a(byte b, byte[] bArr, int i) {
        byte[] bArr2 = this.f1729a;
        int i2 = this.f1730b;
        int i3 = i2 + 1;
        this.f1730b = i3;
        bArr2[i2] = b;
        if (i3 == bArr2.length) {
            int mo22864a = this.f1732d.mo22864a(bArr2, 0, bArr, i);
            this.f1730b = 0;
            return mo22864a;
        }
        return 0;
    }

    /* renamed from: a */
    public int mo22854a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4;
        if (i2 >= 0) {
            int m22891a = m22891a();
            int mo22853b = mo22853b(i2);
            if (mo22853b > 0 && mo22853b + i3 > bArr2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            byte[] bArr3 = this.f1729a;
            int length = bArr3.length;
            int i5 = this.f1730b;
            int i6 = length - i5;
            if (i2 > i6) {
                System.arraycopy(bArr, i, bArr3, i5, i6);
                i4 = this.f1732d.mo22864a(this.f1729a, 0, bArr2, i3) + 0;
                this.f1730b = 0;
                i2 -= i6;
                i += i6;
                while (i2 > this.f1729a.length) {
                    i4 += this.f1732d.mo22864a(bArr, i, bArr2, i3 + i4);
                    i2 -= m22891a;
                    i += m22891a;
                }
            } else {
                i4 = 0;
            }
            System.arraycopy(bArr, i, this.f1729a, this.f1730b, i2);
            int i7 = this.f1730b + i2;
            this.f1730b = i7;
            byte[] bArr4 = this.f1729a;
            if (i7 == bArr4.length) {
                int mo22864a = i4 + this.f1732d.mo22864a(bArr4, 0, bArr2, i3 + i4);
                this.f1730b = 0;
                return mo22864a;
            }
            return i4;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    /* renamed from: a */
    public int mo22855a(byte[] bArr, int i) {
        int i2;
        try {
            if (this.f1730b + i <= bArr.length) {
                if (this.f1730b == 0) {
                    i2 = 0;
                } else if (this.f1733e) {
                    this.f1732d.mo22864a(this.f1729a, 0, this.f1729a, 0);
                    i2 = this.f1730b;
                    this.f1730b = 0;
                    System.arraycopy(this.f1729a, 0, bArr, i, i2);
                } else {
                    throw new DataLengthException("data not block size aligned");
                }
                return i2;
            }
            throw new OutputLengthException("output buffer too short for doFinal()");
        } finally {
            m22889c();
        }
    }
}
