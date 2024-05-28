package org.p415a.p445f;

import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.p415a.p424c.C12608e;
import org.p415a.p445f.p446a.AbstractC12922f;
import org.p415a.p445f.p446a.InterfaceC12920d;
import org.p415a.p445f.p446a.InterfaceC12921e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12931d implements InterfaceC12955y {

    /* renamed from: a */
    private C12608e f26182a;

    /* renamed from: b */
    private OutputStream f26183b;

    /* renamed from: c */
    private boolean f26184c;

    /* renamed from: d */
    private InterfaceC12921e f26185d;

    /* renamed from: e */
    private OutputStream f26186e;

    /* renamed from: f */
    private InterfaceC12920d f26187f;

    /* renamed from: g */
    private List f26188g;

    /* renamed from: h */
    private int f26189h;

    /* renamed from: i */
    private SecureRandom f26190i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.f.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C12932a extends C12608e {
        public C12932a(OutputStream outputStream, int i, long j) {
            super(outputStream, i, j);
        }

        public C12932a(OutputStream outputStream, int i, long j, boolean z) {
            super(outputStream, i, j, z);
        }

        public C12932a(OutputStream outputStream, int i, byte[] bArr) {
            super(outputStream, i, bArr);
        }

        @Override // org.p415a.p424c.C12608e, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            m1569a();
        }
    }

    public C12931d(InterfaceC12920d interfaceC12920d) {
        this(interfaceC12920d, false);
    }

    public C12931d(InterfaceC12920d interfaceC12920d, boolean z) {
        this.f26184c = false;
        this.f26188g = new ArrayList();
        this.f26187f = interfaceC12920d;
        this.f26184c = z;
        this.f26189h = this.f26187f.mo484a();
        this.f26190i = this.f26187f.mo482b();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x011e A[Catch: Exception -> 0x015a, TryCatch #0 {Exception -> 0x015a, blocks: (B:16:0x00ac, B:18:0x00ba, B:20:0x00c0, B:21:0x00dc, B:27:0x0110, B:29:0x011e, B:30:0x012d, B:22:0x00e0, B:23:0x00f7, B:25:0x00fb, B:26:0x0107), top: B:39:0x00ac }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.io.OutputStream m467a(java.io.OutputStream r12, long r13, byte[] r15) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p445f.C12931d.m467a(java.io.OutputStream, long, byte[]):java.io.OutputStream");
    }

    /* renamed from: a */
    private void m465a(byte[] bArr) {
        int i = 0;
        for (int i2 = 1; i2 != bArr.length - 2; i2++) {
            i += bArr[i2] & 255;
        }
        bArr[bArr.length - 2] = (byte) (i >> 8);
        bArr[bArr.length - 1] = (byte) i;
    }

    /* renamed from: a */
    private byte[] m469a(int i, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 3];
        bArr2[0] = (byte) i;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        m465a(bArr2);
        return bArr2;
    }

    /* renamed from: a */
    public OutputStream m468a(OutputStream outputStream, long j) {
        return m467a(outputStream, j, null);
    }

    @Override // org.p415a.p445f.InterfaceC12955y
    /* renamed from: a */
    public void mo443a() {
        if (this.f26183b != null) {
            if (this.f26185d != null) {
                new C12608e(this.f26186e, 19, 20L).flush();
                this.f26183b.write(this.f26185d.mo479c());
            }
            this.f26183b.close();
            this.f26183b = null;
            this.f26182a = null;
        }
    }

    /* renamed from: a */
    public void m466a(AbstractC12922f abstractC12922f) {
        this.f26188g.add(abstractC12922f);
    }
}
