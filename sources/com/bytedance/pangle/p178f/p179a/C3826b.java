package com.bytedance.pangle.p178f.p179a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.bytedance.pangle.f.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3826b {

    /* renamed from: a */
    InputStream f9161a;

    /* renamed from: b */
    private int f9162b;

    public C3826b(InputStream inputStream) {
        m16863a(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m16863a(InputStream inputStream) {
        this.f9161a = inputStream;
        this.f9162b = 0;
    }

    /* renamed from: a */
    public final int m16865a() {
        int i = 0;
        for (int i2 = 0; i2 != 32; i2 += 8) {
            int read = this.f9161a.read();
            if (read == -1) {
                throw new EOFException();
            }
            this.f9162b++;
            i |= read << i2;
        }
        return i;
    }

    /* renamed from: a */
    public final int[] m16864a(int i) {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i > 0) {
            iArr[i2] = m16865a();
            i--;
            i2++;
        }
        return iArr;
    }

    /* renamed from: b */
    public final void m16862b() {
        long skip = this.f9161a.skip(4L);
        this.f9162b = (int) (this.f9162b + skip);
        if (skip != 4) {
            throw new EOFException();
        }
    }

    /* renamed from: b */
    public final void m16861b(int i) {
        int m16865a = m16865a();
        if (m16865a != i) {
            throw new IOException(String.format("Expected: 0x%08x got: 0x%08x", Integer.valueOf(i), Integer.valueOf(m16865a)));
        }
    }
}
