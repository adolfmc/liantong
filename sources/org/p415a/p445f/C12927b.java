package org.p415a.p445f;

import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.p415a.p416a.p417a.C12401b;
import org.p415a.p424c.C12608e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12927b implements InterfaceC12955y {

    /* renamed from: a */
    private int f26176a;

    /* renamed from: b */
    private int f26177b;

    /* renamed from: c */
    private OutputStream f26178c;

    /* renamed from: d */
    private C12608e f26179d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.f.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12928a extends C12401b {
        public C12928a(OutputStream outputStream) {
            super(outputStream);
        }

        @Override // org.p415a.p416a.p417a.C12401b, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            m1771a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.f.b$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C12929b extends DeflaterOutputStream {
        public C12929b(OutputStream outputStream, int i, boolean z) {
            super(outputStream, new Deflater(i, z));
        }

        @Override // java.util.zip.DeflaterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            finish();
            this.def.end();
        }
    }

    public C12927b(int i) {
        this(i, -1);
    }

    public C12927b(int i, int i2) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
                if (i2 == -1 || (i2 >= 0 && i2 <= 9)) {
                    this.f26176a = i;
                    this.f26177b = i2;
                    return;
                }
                throw new IllegalArgumentException("unknown compression level: " + i2);
            default:
                throw new IllegalArgumentException("unknown compression algorithm");
        }
    }

    /* renamed from: b */
    private void m470b() {
        OutputStream outputStream;
        this.f26179d.write(this.f26176a);
        switch (this.f26176a) {
            case 0:
                outputStream = this.f26179d;
                break;
            case 1:
                outputStream = new C12929b(this.f26179d, this.f26177b, true);
                break;
            case 2:
                outputStream = new C12929b(this.f26179d, this.f26177b, false);
                break;
            case 3:
                outputStream = new C12928a(this.f26179d);
                break;
            default:
                throw new IllegalStateException();
        }
        this.f26178c = outputStream;
    }

    /* renamed from: a */
    public OutputStream m471a(OutputStream outputStream) {
        if (this.f26178c == null) {
            this.f26179d = new C12608e(outputStream, 8);
            m470b();
            return new C12956z(this.f26178c, this);
        }
        throw new IllegalStateException("generator already in open state");
    }

    @Override // org.p415a.p445f.InterfaceC12955y
    /* renamed from: a */
    public void mo443a() {
        OutputStream outputStream = this.f26178c;
        if (outputStream != null) {
            if (outputStream != this.f26179d) {
                outputStream.close();
            }
            this.f26178c = null;
            this.f26179d.m1569a();
            this.f26179d.flush();
            this.f26179d = null;
        }
    }
}
