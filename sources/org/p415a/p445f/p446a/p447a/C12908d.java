package org.p415a.p445f.p446a.p447a;

import java.io.OutputStream;
import org.p415a.p427d.InterfaceC12724i;
import org.p415a.p445f.p446a.InterfaceC12921e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12908d {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.f.a.a.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C12910a extends OutputStream {

        /* renamed from: b */
        private InterfaceC12724i f26159b;

        C12910a(InterfaceC12724i interfaceC12724i) {
            this.f26159b = interfaceC12724i;
        }

        /* renamed from: a */
        byte[] m498a() {
            byte[] bArr = new byte[this.f26159b.mo1314a()];
            this.f26159b.mo1312a(bArr, 0);
            return bArr;
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            this.f26159b.mo1313a((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            this.f26159b.mo1311a(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            this.f26159b.mo1311a(bArr, i, i2);
        }
    }

    /* renamed from: a */
    public InterfaceC12921e m499a(final int i) {
        final InterfaceC12724i m508a = C12904a.m508a(i);
        final C12910a c12910a = new C12910a(m508a);
        return new InterfaceC12921e() { // from class: org.a.f.a.a.d.1
            @Override // org.p415a.p445f.p446a.InterfaceC12921e
            /* renamed from: a */
            public int mo481a() {
                return i;
            }

            @Override // org.p415a.p445f.p446a.InterfaceC12921e
            /* renamed from: b */
            public OutputStream mo480b() {
                return c12910a;
            }

            @Override // org.p415a.p445f.p446a.InterfaceC12921e
            /* renamed from: c */
            public byte[] mo479c() {
                return c12910a.m498a();
            }
        };
    }
}
