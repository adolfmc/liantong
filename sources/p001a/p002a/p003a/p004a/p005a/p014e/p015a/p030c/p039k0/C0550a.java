package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p039k0;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.DataLengthException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0576j0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.c.k0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0550a implements InterfaceC0515e {

    /* renamed from: a */
    public byte[] f1794a;

    /* renamed from: b */
    public byte[] f1795b;

    /* renamed from: c */
    public byte[] f1796c;

    /* renamed from: d */
    public int f1797d;

    /* renamed from: e */
    public InterfaceC0515e f1798e;

    /* renamed from: f */
    public boolean f1799f;

    public C0550a(InterfaceC0515e interfaceC0515e) {
        this.f1798e = null;
        this.f1798e = interfaceC0515e;
        int mo22861c = interfaceC0515e.mo22861c();
        this.f1797d = mo22861c;
        this.f1794a = new byte[mo22861c];
        this.f1795b = new byte[mo22861c];
        this.f1796c = new byte[mo22861c];
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e
    /* renamed from: a */
    public void mo22865a(boolean z, InterfaceC0542i interfaceC0542i) {
        boolean z2 = this.f1799f;
        this.f1799f = z;
        if (interfaceC0542i instanceof C0576j0) {
            C0576j0 c0576j0 = (C0576j0) interfaceC0542i;
            byte[] m22804a = c0576j0.m22804a();
            if (m22804a.length == this.f1797d) {
                System.arraycopy(m22804a, 0, this.f1794a, 0, m22804a.length);
                mo22863b();
                if (c0576j0.m22803b() != null) {
                    this.f1798e.mo22865a(z, c0576j0.m22803b());
                    return;
                } else if (z2 != z) {
                    throw new IllegalArgumentException("cannot change encrypting state without providing key.");
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("initialisation vector must be the same length as block size");
        }
        mo22863b();
        if (interfaceC0542i != null) {
            this.f1798e.mo22865a(z, interfaceC0542i);
        } else if (z2 != z) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e
    /* renamed from: b */
    public void mo22863b() {
        byte[] bArr = this.f1794a;
        System.arraycopy(bArr, 0, this.f1795b, 0, bArr.length);
        C0669a.m22502a(this.f1796c, (byte) 0);
        this.f1798e.mo22863b();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e
    /* renamed from: c */
    public int mo22861c() {
        return this.f1798e.mo22861c();
    }

    /* renamed from: d */
    public InterfaceC0515e m22859d() {
        return this.f1798e;
    }

    /* renamed from: c */
    private int m22860c(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.f1797d + i <= bArr.length) {
            for (int i3 = 0; i3 < this.f1797d; i3++) {
                byte[] bArr3 = this.f1795b;
                bArr3[i3] = (byte) (bArr3[i3] ^ bArr[i + i3]);
            }
            int mo22864a = this.f1798e.mo22864a(this.f1795b, 0, bArr2, i2);
            byte[] bArr4 = this.f1795b;
            System.arraycopy(bArr2, i2, bArr4, 0, bArr4.length);
            return mo22864a;
        }
        throw new DataLengthException("input buffer too short");
    }

    /* renamed from: b */
    private int m22862b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = this.f1797d;
        if (i + i3 <= bArr.length) {
            System.arraycopy(bArr, i, this.f1796c, 0, i3);
            int mo22864a = this.f1798e.mo22864a(bArr, i, bArr2, i2);
            for (int i4 = 0; i4 < this.f1797d; i4++) {
                int i5 = i2 + i4;
                bArr2[i5] = (byte) (bArr2[i5] ^ this.f1795b[i4]);
            }
            byte[] bArr3 = this.f1795b;
            this.f1795b = this.f1796c;
            this.f1796c = bArr3;
            return mo22864a;
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e
    /* renamed from: a */
    public String mo22866a() {
        return this.f1798e.mo22866a() + "/CBC";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e
    /* renamed from: a */
    public int mo22864a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.f1799f ? m22860c(bArr, i, bArr2, i2) : m22862b(bArr, i, bArr2, i2);
    }
}
