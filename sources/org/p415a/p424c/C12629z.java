package org.p415a.p424c;

import java.io.DataInputStream;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.z */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12629z extends AbstractC12607d {

    /* renamed from: a */
    int f25630a;

    /* renamed from: b */
    int f25631b;

    /* renamed from: c */
    byte[] f25632c;

    /* renamed from: d */
    int f25633d;

    /* renamed from: e */
    int f25634e;

    public C12629z(int i, byte[] bArr, int i2) {
        this.f25633d = -1;
        this.f25634e = -1;
        this.f25630a = 3;
        this.f25631b = i;
        this.f25632c = bArr;
        this.f25633d = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12629z(InputStream inputStream) {
        this.f25633d = -1;
        this.f25634e = -1;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f25630a = dataInputStream.read();
        this.f25631b = dataInputStream.read();
        int i = this.f25630a;
        if (i == 101) {
            dataInputStream.read();
            dataInputStream.read();
            dataInputStream.read();
            this.f25634e = dataInputStream.read();
        } else if (i != 0) {
            this.f25632c = new byte[8];
            byte[] bArr = this.f25632c;
            dataInputStream.readFully(bArr, 0, bArr.length);
            if (this.f25630a == 3) {
                this.f25633d = dataInputStream.read();
            }
        }
    }

    @Override // org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public void mo1533a(C12608e c12608e) {
        int i;
        c12608e.write(this.f25630a);
        c12608e.write(this.f25631b);
        int i2 = this.f25630a;
        if (i2 != 101) {
            if (i2 != 0) {
                c12608e.write(this.f25632c);
            }
            if (this.f25630a != 3) {
                return;
            }
            i = this.f25633d;
        } else {
            c12608e.write(71);
            c12608e.write(78);
            c12608e.write(85);
            i = this.f25634e;
        }
        c12608e.write(i);
    }

    /* renamed from: b */
    public int m1532b() {
        return this.f25630a;
    }

    /* renamed from: c */
    public int m1531c() {
        return this.f25631b;
    }

    /* renamed from: d */
    public byte[] m1530d() {
        return this.f25632c;
    }

    /* renamed from: e */
    public long m1529e() {
        int i = this.f25633d;
        return ((i & 15) + 16) << ((i >> 4) + 6);
    }

    /* renamed from: f */
    public int m1528f() {
        return this.f25634e;
    }
}
