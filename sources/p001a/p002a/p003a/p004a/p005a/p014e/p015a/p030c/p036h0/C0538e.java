package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p036h0;

import java.io.FilterInputStream;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v;

/* renamed from: a.a.a.a.a.e.a.c.h0.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0538e extends FilterInputStream {

    /* renamed from: C */
    public InterfaceC0641v f1755C;

    public C0538e(InputStream inputStream, InterfaceC0641v interfaceC0641v) {
        super(inputStream);
        this.f1755C = interfaceC0641v;
    }

    /* renamed from: a */
    public InterfaceC0641v m22881a() {
        return this.f1755C;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = ((FilterInputStream) this).in.read();
        if (read >= 0) {
            this.f1755C.mo22655a((byte) read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int read = ((FilterInputStream) this).in.read(bArr, i, i2);
        if (read >= 0) {
            this.f1755C.mo22652a(bArr, i, read);
        }
        return read;
    }
}
