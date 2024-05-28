package com.megvii.lv5;

import java.io.ByteArrayOutputStream;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.c5 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5401c5 extends ByteArrayOutputStream {

    /* renamed from: a */
    public final C5623u4 f12428a;

    public C5401c5(C5623u4 c5623u4, int i) {
        this.f12428a = c5623u4;
        ((ByteArrayOutputStream) this).buf = c5623u4.m12975a(Math.max(i, 256));
    }

    /* renamed from: a */
    public final void m13603a(int i) {
        int i2 = ((ByteArrayOutputStream) this).count + i;
        if (i2 <= ((ByteArrayOutputStream) this).buf.length) {
            return;
        }
        byte[] m12975a = this.f12428a.m12975a(i2 * 2);
        System.arraycopy(((ByteArrayOutputStream) this).buf, 0, m12975a, 0, ((ByteArrayOutputStream) this).count);
        this.f12428a.m12974a(((ByteArrayOutputStream) this).buf);
        ((ByteArrayOutputStream) this).buf = m12975a;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f12428a.m12974a(((ByteArrayOutputStream) this).buf);
        ((ByteArrayOutputStream) this).buf = null;
        super.close();
    }

    public void finalize() {
        this.f12428a.m12974a(((ByteArrayOutputStream) this).buf);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(int i) {
        m13603a(1);
        super.write(i);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) {
        m13603a(i2);
        super.write(bArr, i, i2);
    }
}
