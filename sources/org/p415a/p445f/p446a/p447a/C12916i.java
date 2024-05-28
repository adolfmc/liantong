package org.p415a.p445f.p446a.p447a;

import java.io.OutputStream;
import org.p415a.p427d.InterfaceC12724i;
import org.p415a.p427d.p428a.C12636f;
import org.p415a.p445f.p446a.InterfaceC12921e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12916i implements InterfaceC12921e {

    /* renamed from: a */
    private InterfaceC12724i f26166a = new C12636f();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.f.a.a.i$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C12917a extends OutputStream {

        /* renamed from: b */
        private InterfaceC12724i f26168b;

        C12917a(InterfaceC12724i interfaceC12724i) {
            this.f26168b = interfaceC12724i;
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            this.f26168b.mo1313a((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            this.f26168b.mo1311a(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            this.f26168b.mo1311a(bArr, i, i2);
        }
    }

    @Override // org.p415a.p445f.p446a.InterfaceC12921e
    /* renamed from: a */
    public int mo481a() {
        return 2;
    }

    @Override // org.p415a.p445f.p446a.InterfaceC12921e
    /* renamed from: b */
    public OutputStream mo480b() {
        return new C12917a(this.f26166a);
    }

    @Override // org.p415a.p445f.p446a.InterfaceC12921e
    /* renamed from: c */
    public byte[] mo479c() {
        byte[] bArr = new byte[this.f26166a.mo1314a()];
        this.f26166a.mo1312a(bArr, 0);
        return bArr;
    }
}
