package org.p415a.p418b;

import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.r */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12567r {

    /* renamed from: a */
    private OutputStream f25511a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.b.r$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C12568a extends C12567r {

        /* renamed from: b */
        private boolean f25513b;

        public C12568a(OutputStream outputStream) {
            super(outputStream);
            this.f25513b = true;
        }

        @Override // org.p415a.p418b.C12567r
        /* renamed from: b */
        public void mo1620b(int i) {
            if (this.f25513b) {
                this.f25513b = false;
            } else {
                super.mo1620b(i);
            }
        }
    }

    public C12567r(OutputStream outputStream) {
        this.f25511a = outputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C12567r mo1630a() {
        return new C12442bb(this.f25511a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1629a(int i) {
        if (i <= 127) {
            mo1620b((byte) i);
            return;
        }
        int i2 = i;
        int i3 = 1;
        while (true) {
            i2 >>>= 8;
            if (i2 == 0) {
                break;
            }
            i3++;
        }
        mo1620b((byte) (i3 | 128));
        for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
            mo1620b((byte) (i >> i4));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1628a(int i, int i2) {
        if (i2 < 31) {
            mo1620b(i | i2);
            return;
        }
        mo1620b(i | 31);
        if (i2 < 128) {
            mo1620b(i2);
            return;
        }
        byte[] bArr = new byte[5];
        int length = bArr.length - 1;
        bArr[length] = (byte) (i2 & 127);
        do {
            i2 >>= 7;
            length--;
            bArr[length] = (byte) ((i2 & 127) | 128);
        } while (i2 > 127);
        m1622a(bArr, length, bArr.length - length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1627a(int i, int i2, byte[] bArr) {
        m1628a(i, i2);
        m1629a(bArr.length);
        m1623a(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1626a(int i, byte[] bArr) {
        mo1620b(i);
        m1629a(bArr.length);
        m1623a(bArr);
    }

    /* renamed from: a */
    public void mo1625a(InterfaceC12554f interfaceC12554f) {
        if (interfaceC12554f == null) {
            throw new IOException("null object detected");
        }
        interfaceC12554f.mo1617h().mo1597a(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1624a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t == null) {
            throw new IOException("null object detected");
        }
        abstractC12570t.mo1597a(new C12568a(this.f25511a));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1623a(byte[] bArr) {
        this.f25511a.write(bArr);
    }

    /* renamed from: a */
    void m1622a(byte[] bArr, int i, int i2) {
        this.f25511a.write(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public C12567r mo1621b() {
        return new C12455bo(this.f25511a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1620b(int i) {
        this.f25511a.write(i);
    }
}
