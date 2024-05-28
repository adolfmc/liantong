package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p036h0;

import java.io.FilterInputStream;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x;

/* renamed from: a.a.a.a.a.e.a.c.h0.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0540g extends FilterInputStream {

    /* renamed from: C */
    public InterfaceC0643x f1757C;

    public C0540g(InputStream inputStream, InterfaceC0643x interfaceC0643x) {
        super(inputStream);
        this.f1757C = interfaceC0643x;
    }

    /* renamed from: a */
    public InterfaceC0643x m22879a() {
        return this.f1757C;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = ((FilterInputStream) this).in.read();
        if (read >= 0) {
            this.f1757C.mo22638a((byte) read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int read = ((FilterInputStream) this).in.read(bArr, i, i2);
        if (read > 0) {
            this.f1757C.mo22636a(bArr, i, read);
        }
        return read;
    }
}
