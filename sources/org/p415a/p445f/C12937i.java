package org.p415a.p445f;

import java.io.OutputStream;
import java.util.Date;
import org.p415a.p424c.C12608e;
import org.p415a.p448g.C12975h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12937i implements InterfaceC12955y {

    /* renamed from: a */
    public static final Date f26197a = C12936h.f26195a;

    /* renamed from: b */
    private C12608e f26198b;

    /* renamed from: c */
    private boolean f26199c = false;

    /* renamed from: a */
    private void m457a(OutputStream outputStream, char c, byte[] bArr, long j) {
        outputStream.write(c);
        outputStream.write((byte) bArr.length);
        for (int i = 0; i != bArr.length; i++) {
            outputStream.write(bArr[i]);
        }
        long j2 = j / 1000;
        outputStream.write((byte) (j2 >> 24));
        outputStream.write((byte) (j2 >> 16));
        outputStream.write((byte) (j2 >> 8));
        outputStream.write((byte) j2);
    }

    /* renamed from: a */
    public OutputStream m458a(OutputStream outputStream, char c, String str, long j, Date date) {
        if (this.f26198b == null) {
            byte[] m391a = C12975h.m391a(str);
            this.f26198b = new C12608e(outputStream, 11, j + 2 + m391a.length + 4, this.f26199c);
            m457a(this.f26198b, c, m391a, date.getTime());
            return new C12956z(this.f26198b, this);
        }
        throw new IllegalStateException("generator already in open state");
    }

    @Override // org.p415a.p445f.InterfaceC12955y
    /* renamed from: a */
    public void mo443a() {
        C12608e c12608e = this.f26198b;
        if (c12608e != null) {
            c12608e.m1569a();
            this.f26198b.flush();
            this.f26198b = null;
        }
    }
}
