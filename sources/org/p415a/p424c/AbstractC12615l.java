package org.p415a.p424c;

import java.io.IOException;
import java.math.BigInteger;
import org.p415a.p418b.AbstractC12570t;
import org.p415a.p418b.C12563o;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.l */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12615l extends AbstractC12607d implements InterfaceC12606c {

    /* renamed from: a */
    C12563o f25599a;

    /* renamed from: b */
    BigInteger f25600b;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC12615l(C12592b c12592b) {
        this.f25599a = C12563o.m1639a((Object) AbstractC12570t.m1619b(m1549a(c12592b)));
        this.f25600b = new C12620q(c12592b).m1543b();
    }

    /* renamed from: a */
    protected static byte[] m1549a(C12592b c12592b) {
        int read = c12592b.read();
        if (read == 0 || read == 255) {
            throw new IOException("future extensions not yet implemented.");
        }
        byte[] bArr = new byte[read + 2];
        c12592b.m1575a(bArr, 2, bArr.length - 2);
        bArr[0] = 6;
        bArr[1] = (byte) read;
        return bArr;
    }

    @Override // org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public void mo1533a(C12608e c12608e) {
        byte[] i = this.f25599a.m1643i();
        c12608e.write(i, 1, i.length - 1);
        c12608e.m1564a(new C12620q(this.f25600b));
    }

    @Override // org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public byte[] mo1536a() {
        try {
            return super.mo1536a();
        } catch (IOException unused) {
            return null;
        }
    }

    /* renamed from: d */
    public BigInteger m1548d() {
        return this.f25600b;
    }

    /* renamed from: e */
    public C12563o m1547e() {
        return this.f25599a;
    }
}
