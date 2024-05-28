package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p036h0;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0501a0;

/* renamed from: a.a.a.a.a.e.a.c.h0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0535b extends FilterOutputStream {

    /* renamed from: C */
    public C0530g f1749C;

    /* renamed from: D */
    public InterfaceC0501a0 f1750D;

    /* renamed from: E */
    public byte[] f1751E;

    /* renamed from: F */
    public byte[] f1752F;

    public C0535b(OutputStream outputStream, C0530g c0530g) {
        super(outputStream);
        this.f1751E = new byte[1];
        this.f1749C = c0530g;
        this.f1752F = new byte[c0530g.m22891a()];
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        byte[] bArr;
        int mo22855a;
        try {
            if (this.f1749C != null && (mo22855a = this.f1749C.mo22855a((bArr = new byte[this.f1749C.mo22857a(0)]), 0)) != 0) {
                ((FilterOutputStream) this).out.write(bArr, 0, mo22855a);
            }
            flush();
            super.close();
        } catch (Exception e) {
            throw new IOException("Error closing stream: " + e.toString());
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() {
        super.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) {
        byte[] bArr = this.f1751E;
        byte b = (byte) i;
        bArr[0] = b;
        C0530g c0530g = this.f1749C;
        if (c0530g != null) {
            int mo22854a = c0530g.mo22854a(bArr, 0, 1, this.f1752F, 0);
            if (mo22854a != 0) {
                ((FilterOutputStream) this).out.write(this.f1752F, 0, mo22854a);
                return;
            }
            return;
        }
        ((FilterOutputStream) this).out.write(this.f1750D.mo22629a(b));
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public C0535b(OutputStream outputStream, InterfaceC0501a0 interfaceC0501a0) {
        super(outputStream);
        this.f1751E = new byte[1];
        this.f1750D = interfaceC0501a0;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        C0530g c0530g = this.f1749C;
        if (c0530g != null) {
            byte[] bArr2 = new byte[c0530g.mo22857a(i2)];
            int mo22854a = this.f1749C.mo22854a(bArr, i, i2, bArr2, 0);
            if (mo22854a != 0) {
                ((FilterOutputStream) this).out.write(bArr2, 0, mo22854a);
                return;
            }
            return;
        }
        byte[] bArr3 = new byte[i2];
        this.f1750D.mo22627a(bArr, i, i2, bArr3, 0);
        ((FilterOutputStream) this).out.write(bArr3, 0, i2);
    }
}
