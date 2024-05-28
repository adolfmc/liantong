package org.p415a.p418b;

import java.io.EOFException;
import java.io.InputStream;
import org.p415a.p448g.p450b.C12967a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bs */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12459bs extends AbstractC12464bx {

    /* renamed from: b */
    private static final byte[] f25230b = new byte[0];

    /* renamed from: c */
    private final int f25231c;

    /* renamed from: d */
    private int f25232d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12459bs(InputStream inputStream, int i) {
        super(inputStream, i);
        if (i < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this.f25231c = i;
        this.f25232d = i;
        if (i == 0) {
            m1697b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12464bx
    /* renamed from: a */
    public int mo1698a() {
        return this.f25232d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public byte[] m1703b() {
        int i = this.f25232d;
        if (i == 0) {
            return f25230b;
        }
        byte[] bArr = new byte[i];
        int m403a = i - C12967a.m403a(this.f25240a, bArr);
        this.f25232d = m403a;
        if (m403a == 0) {
            m1697b(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this.f25231c + " object truncated by " + this.f25232d);
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.f25232d == 0) {
            return -1;
        }
        int read = this.f25240a.read();
        if (read >= 0) {
            int i = this.f25232d - 1;
            this.f25232d = i;
            if (i == 0) {
                m1697b(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this.f25231c + " object truncated by " + this.f25232d);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int i3 = this.f25232d;
        if (i3 == 0) {
            return -1;
        }
        int read = this.f25240a.read(bArr, i, Math.min(i2, i3));
        if (read >= 0) {
            int i4 = this.f25232d - read;
            this.f25232d = i4;
            if (i4 == 0) {
                m1697b(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this.f25231c + " object truncated by " + this.f25232d);
    }
}
