package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p036h0;

import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;

/* renamed from: a.a.a.a.a.e.a.c.h0.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0537d extends OutputStream {

    /* renamed from: C */
    public InterfaceC0605o f1754C;

    public C0537d(InterfaceC0605o interfaceC0605o) {
        this.f1754C = interfaceC0605o;
    }

    /* renamed from: a */
    public byte[] m22882a() {
        byte[] bArr = new byte[this.f1754C.mo22743c()];
        this.f1754C.mo22746a(bArr, 0);
        return bArr;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f1754C.mo22747a((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f1754C.mo22745a(bArr, i, i2);
    }
}
