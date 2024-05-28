package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p036h0;

import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v;

/* renamed from: a.a.a.a.a.e.a.c.h0.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0539f extends OutputStream {

    /* renamed from: C */
    public InterfaceC0641v f1756C;

    public C0539f(InterfaceC0641v interfaceC0641v) {
        this.f1756C = interfaceC0641v;
    }

    /* renamed from: a */
    public byte[] m22880a() {
        byte[] bArr = new byte[this.f1756C.mo22650c()];
        this.f1756C.mo22653a(bArr, 0);
        return bArr;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f1756C.mo22655a((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f1756C.mo22652a(bArr, i, i2);
    }
}
