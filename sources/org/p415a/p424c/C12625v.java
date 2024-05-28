package org.p415a.p424c;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.p450b.C12967a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.v */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12625v extends AbstractC12611h {

    /* renamed from: a */
    private int f25619a;

    /* renamed from: b */
    private long f25620b;

    /* renamed from: c */
    private int f25621c;

    /* renamed from: d */
    private byte[][] f25622d;

    public C12625v(long j, int i, byte[][] bArr) {
        this.f25619a = 3;
        this.f25620b = j;
        this.f25621c = i;
        this.f25622d = new byte[bArr.length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            this.f25622d[i2] = C12957a.m430b(bArr[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12625v(C12592b c12592b) {
        this.f25619a = c12592b.read();
        this.f25620b |= c12592b.read() << 56;
        this.f25620b |= c12592b.read() << 48;
        this.f25620b |= c12592b.read() << 40;
        this.f25620b |= c12592b.read() << 32;
        this.f25620b |= c12592b.read() << 24;
        this.f25620b |= c12592b.read() << 16;
        this.f25620b |= c12592b.read() << 8;
        this.f25620b |= c12592b.read();
        this.f25621c = c12592b.read();
        int i = this.f25621c;
        if (i != 16) {
            if (i == 18) {
                this.f25622d = new byte[1];
                this.f25622d[0] = C12967a.m405a(c12592b);
                return;
            } else if (i != 20) {
                switch (i) {
                    case 1:
                    case 2:
                        this.f25622d = new byte[1];
                        this.f25622d[0] = new C12620q(c12592b).mo1536a();
                        return;
                    default:
                        throw new IOException("unknown PGP public key algorithm encountered");
                }
            }
        }
        this.f25622d = new byte[2];
        this.f25622d[0] = new C12620q(c12592b).mo1536a();
        this.f25622d[1] = new C12620q(c12592b).mo1536a();
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12608e c12608e2 = new C12608e(byteArrayOutputStream);
        c12608e2.write(this.f25619a);
        c12608e2.write((byte) (this.f25620b >> 56));
        c12608e2.write((byte) (this.f25620b >> 48));
        c12608e2.write((byte) (this.f25620b >> 40));
        c12608e2.write((byte) (this.f25620b >> 32));
        c12608e2.write((byte) (this.f25620b >> 24));
        c12608e2.write((byte) (this.f25620b >> 16));
        c12608e2.write((byte) (this.f25620b >> 8));
        c12608e2.write((byte) this.f25620b);
        c12608e2.write(this.f25621c);
        int i = 0;
        while (true) {
            byte[][] bArr = this.f25622d;
            if (i == bArr.length) {
                c12608e2.close();
                c12608e.m1566a(1, byteArrayOutputStream.toByteArray(), true);
                return;
            }
            c12608e2.write(bArr[i]);
            i++;
        }
    }
}
