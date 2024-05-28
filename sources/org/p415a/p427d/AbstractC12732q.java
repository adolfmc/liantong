package org.p415a.p427d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.q */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12732q implements InterfaceC12680d, InterfaceC12733r {

    /* renamed from: a */
    private final InterfaceC12680d f25933a;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC12732q(InterfaceC12680d interfaceC12680d) {
        this.f25933a = interfaceC12680d;
    }

    @Override // org.p415a.p427d.InterfaceC12733r
    /* renamed from: a */
    public final byte mo1306a(byte b) {
        return mo1307b(b);
    }

    @Override // org.p415a.p427d.InterfaceC12733r
    /* renamed from: a */
    public int mo1305a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i + i2;
        if (i4 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                while (i < i4) {
                    bArr2[i3] = mo1307b(bArr[i]);
                    i3++;
                    i++;
                }
                return i2;
            }
            throw new C12730o("output buffer too short");
        }
        throw new C12704h("input buffer too small");
    }

    /* renamed from: b */
    protected abstract byte mo1307b(byte b);
}
