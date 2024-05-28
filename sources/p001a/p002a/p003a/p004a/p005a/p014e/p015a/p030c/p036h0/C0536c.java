package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p036h0;

import java.io.FilterInputStream;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;

/* renamed from: a.a.a.a.a.e.a.c.h0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0536c extends FilterInputStream {

    /* renamed from: C */
    public InterfaceC0605o f1753C;

    public C0536c(InputStream inputStream, InterfaceC0605o interfaceC0605o) {
        super(inputStream);
        this.f1753C = interfaceC0605o;
    }

    /* renamed from: a */
    public InterfaceC0605o m22883a() {
        return this.f1753C;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = ((FilterInputStream) this).in.read();
        if (read >= 0) {
            this.f1753C.mo22747a((byte) read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int read = ((FilterInputStream) this).in.read(bArr, i, i2);
        if (read > 0) {
            this.f1753C.mo22745a(bArr, i, read);
        }
        return read;
    }
}
