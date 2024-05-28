package org.p415a.p418b;

import java.io.EOFException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bu */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12461bu extends AbstractC12464bx {

    /* renamed from: b */
    private int f25233b;

    /* renamed from: c */
    private int f25234c;

    /* renamed from: d */
    private boolean f25235d;

    /* renamed from: e */
    private boolean f25236e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12461bu(InputStream inputStream, int i) {
        super(inputStream, i);
        this.f25235d = false;
        this.f25236e = true;
        this.f25233b = inputStream.read();
        this.f25234c = inputStream.read();
        if (this.f25234c < 0) {
            throw new EOFException();
        }
        m1701b();
    }

    /* renamed from: b */
    private boolean m1701b() {
        if (!this.f25235d && this.f25236e && this.f25233b == 0 && this.f25234c == 0) {
            this.f25235d = true;
            m1697b(true);
        }
        return this.f25235d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1702a(boolean z) {
        this.f25236e = z;
        m1701b();
    }

    @Override // java.io.InputStream
    public int read() {
        if (m1701b()) {
            return -1;
        }
        int read = this.f25240a.read();
        if (read >= 0) {
            int i = this.f25233b;
            this.f25233b = this.f25234c;
            this.f25234c = read;
            return i;
        }
        throw new EOFException();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (this.f25236e || i2 < 3) {
            return super.read(bArr, i, i2);
        }
        if (this.f25235d) {
            return -1;
        }
        int read = this.f25240a.read(bArr, i + 2, i2 - 2);
        if (read >= 0) {
            bArr[i] = (byte) this.f25233b;
            bArr[i + 1] = (byte) this.f25234c;
            this.f25233b = this.f25240a.read();
            this.f25234c = this.f25240a.read();
            if (this.f25234c >= 0) {
                return read + 2;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }
}
