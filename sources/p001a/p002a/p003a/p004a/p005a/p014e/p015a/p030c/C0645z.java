package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.DataLengthException;

/* renamed from: a.a.a.a.a.e.a.c.z */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0645z implements InterfaceC0501a0 {

    /* renamed from: a */
    public InterfaceC0515e f1974a;

    /* renamed from: b */
    public byte[] f1975b = new byte[1];

    public C0645z(InterfaceC0515e interfaceC0515e) {
        if (interfaceC0515e.mo22861c() == 1) {
            this.f1974a = interfaceC0515e;
            return;
        }
        throw new IllegalArgumentException("block cipher block size != 1.");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0501a0
    /* renamed from: a */
    public void mo22628a(boolean z, InterfaceC0542i interfaceC0542i) {
        this.f1974a.mo22865a(z, interfaceC0542i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0501a0
    /* renamed from: b */
    public void mo22626b() {
        this.f1974a.mo22863b();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0501a0
    /* renamed from: a */
    public String mo22630a() {
        return this.f1974a.mo22866a();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0501a0
    /* renamed from: a */
    public byte mo22629a(byte b) {
        byte[] bArr = this.f1975b;
        bArr[0] = b;
        this.f1974a.mo22864a(bArr, 0, bArr, 0);
        return this.f1975b[0];
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0501a0
    /* renamed from: a */
    public void mo22627a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i3 + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too small in processBytes()");
        }
        for (int i4 = 0; i4 != i2; i4++) {
            this.f1974a.mo22864a(bArr, i + i4, bArr2, i3 + i4);
        }
    }
}
