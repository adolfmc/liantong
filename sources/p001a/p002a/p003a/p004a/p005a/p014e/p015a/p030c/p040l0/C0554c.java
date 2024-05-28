package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.DataLengthException;
import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.OutputLengthException;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;

/* renamed from: a.a.a.a.a.e.a.c.l0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0554c extends C0530g {

    /* renamed from: g */
    public InterfaceC0552a f1800g;

    public C0554c(InterfaceC0515e interfaceC0515e, InterfaceC0552a interfaceC0552a) {
        this.f1732d = interfaceC0515e;
        this.f1800g = interfaceC0552a;
        this.f1729a = new byte[interfaceC0515e.mo22861c()];
        this.f1730b = 0;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g
    /* renamed from: a */
    public void mo22856a(boolean z, InterfaceC0542i interfaceC0542i) {
        this.f1731c = z;
        m22889c();
        if (interfaceC0542i instanceof C0578k0) {
            C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
            this.f1800g.mo22851a(c0578k0.m22800b());
            this.f1732d.mo22865a(z, c0578k0.m22801a());
            return;
        }
        this.f1800g.mo22851a((SecureRandom) null);
        this.f1732d.mo22865a(z, interfaceC0542i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g
    /* renamed from: b */
    public int mo22853b(int i) {
        int i2 = i + this.f1730b;
        byte[] bArr = this.f1729a;
        int length = i2 % bArr.length;
        return length == 0 ? i2 - bArr.length : i2 - length;
    }

    public C0554c(InterfaceC0515e interfaceC0515e) {
        this(interfaceC0515e, new C0553b());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g
    /* renamed from: a */
    public int mo22857a(int i) {
        int i2 = i + this.f1730b;
        byte[] bArr = this.f1729a;
        int length = i2 % bArr.length;
        if (length == 0) {
            return this.f1731c ? i2 + bArr.length : i2;
        }
        return (i2 - length) + bArr.length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g
    /* renamed from: a */
    public int mo22858a(byte b, byte[] bArr, int i) {
        int i2;
        int i3 = this.f1730b;
        byte[] bArr2 = this.f1729a;
        if (i3 == bArr2.length) {
            i2 = this.f1732d.mo22864a(bArr2, 0, bArr, i);
            this.f1730b = 0;
        } else {
            i2 = 0;
        }
        byte[] bArr3 = this.f1729a;
        int i4 = this.f1730b;
        this.f1730b = i4 + 1;
        bArr3[i4] = b;
        return i2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g
    /* renamed from: a */
    public int mo22854a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 >= 0) {
            int m22891a = m22891a();
            int mo22853b = mo22853b(i2);
            if (mo22853b > 0 && mo22853b + i3 > bArr2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            byte[] bArr3 = this.f1729a;
            int length = bArr3.length;
            int i4 = this.f1730b;
            int i5 = length - i4;
            int i6 = 0;
            if (i2 > i5) {
                System.arraycopy(bArr, i, bArr3, i4, i5);
                this.f1730b = 0;
                i2 -= i5;
                i += i5;
                i6 = this.f1732d.mo22864a(this.f1729a, 0, bArr2, i3) + 0;
                while (i2 > this.f1729a.length) {
                    i6 += this.f1732d.mo22864a(bArr, i, bArr2, i3 + i6);
                    i2 -= m22891a;
                    i += m22891a;
                }
            }
            System.arraycopy(bArr, i, this.f1729a, this.f1730b, i2);
            this.f1730b += i2;
            return i6;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g
    /* renamed from: a */
    public int mo22855a(byte[] bArr, int i) {
        int i2;
        int mo22861c = this.f1732d.mo22861c();
        if (this.f1731c) {
            if (this.f1730b != mo22861c) {
                i2 = 0;
            } else if ((mo22861c * 2) + i <= bArr.length) {
                i2 = this.f1732d.mo22864a(this.f1729a, 0, bArr, i);
                this.f1730b = 0;
            } else {
                m22889c();
                throw new OutputLengthException("output buffer too short");
            }
            this.f1800g.mo22849a(this.f1729a, this.f1730b);
            return i2 + this.f1732d.mo22864a(this.f1729a, 0, bArr, i + i2);
        } else if (this.f1730b == mo22861c) {
            InterfaceC0515e interfaceC0515e = this.f1732d;
            byte[] bArr2 = this.f1729a;
            int mo22864a = interfaceC0515e.mo22864a(bArr2, 0, bArr2, 0);
            this.f1730b = 0;
            try {
                int mo22850a = mo22864a - this.f1800g.mo22850a(this.f1729a);
                System.arraycopy(this.f1729a, 0, bArr, i, mo22850a);
                return mo22850a;
            } finally {
                m22889c();
            }
        } else {
            m22889c();
            throw new DataLengthException("last block incomplete in decryption");
        }
    }
}
