package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.DataLengthException;

/* renamed from: a.a.a.a.a.e.a.c.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0525f {

    /* renamed from: a */
    public byte[] f1704a;

    /* renamed from: b */
    public int f1705b;

    /* renamed from: c */
    public final InterfaceC0500a f1706c;

    public C0525f(InterfaceC0500a interfaceC0500a) {
        this.f1706c = interfaceC0500a;
    }

    /* renamed from: a */
    public void m22917a(boolean z, InterfaceC0542i interfaceC0542i) {
        m22911f();
        this.f1706c.mo22898a(z, interfaceC0542i);
        this.f1704a = new byte[this.f1706c.mo22899a() + (z ? 1 : 0)];
        this.f1705b = 0;
    }

    /* renamed from: b */
    public int m22915b() {
        return this.f1705b;
    }

    /* renamed from: c */
    public int m22914c() {
        return this.f1706c.mo22899a();
    }

    /* renamed from: d */
    public int m22913d() {
        return this.f1706c.mo22896b();
    }

    /* renamed from: e */
    public InterfaceC0500a m22912e() {
        return this.f1706c;
    }

    /* renamed from: f */
    public void m22911f() {
        if (this.f1704a != null) {
            int i = 0;
            while (true) {
                byte[] bArr = this.f1704a;
                if (i >= bArr.length) {
                    break;
                }
                bArr[i] = 0;
                i++;
            }
        }
        this.f1705b = 0;
    }

    /* renamed from: a */
    public void m22918a(byte b) {
        int i = this.f1705b;
        byte[] bArr = this.f1704a;
        if (i < bArr.length) {
            this.f1705b = i + 1;
            bArr[i] = b;
            return;
        }
        throw new DataLengthException("attempt to process message too long for cipher");
    }

    /* renamed from: a */
    public void m22916a(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return;
        }
        if (i2 >= 0) {
            int i3 = this.f1705b;
            int i4 = i3 + i2;
            byte[] bArr2 = this.f1704a;
            if (i4 <= bArr2.length) {
                System.arraycopy(bArr, i, bArr2, i3, i2);
                this.f1705b += i2;
                return;
            }
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    /* renamed from: a */
    public byte[] m22919a() {
        byte[] mo22897a = this.f1706c.mo22897a(this.f1704a, 0, this.f1705b);
        m22911f();
        return mo22897a;
    }
}
