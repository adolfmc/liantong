package com.p319ss.android.socialbase.appdownloader.p340u.p341mb;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.ss.android.socialbase.appdownloader.u.mb.hj */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10156hj {

    /* renamed from: b */
    private int f19631b;

    /* renamed from: mb */
    private InputStream f19632mb;

    /* renamed from: ox */
    private boolean f19633ox;

    public C10156hj() {
    }

    public C10156hj(InputStream inputStream, boolean z) {
        m6539mb(inputStream, z);
    }

    /* renamed from: mb */
    public final void m6539mb(InputStream inputStream, boolean z) {
        this.f19632mb = inputStream;
        this.f19633ox = z;
        this.f19631b = 0;
    }

    /* renamed from: mb */
    public final void m6541mb() {
        InputStream inputStream = this.f19632mb;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
            m6539mb(null, false);
        }
    }

    /* renamed from: ox */
    public final int m6537ox() throws IOException {
        return m6540mb(4);
    }

    /* renamed from: mb */
    public final int m6540mb(int i) throws IOException {
        if (i >= 0 && i <= 4) {
            int i2 = 0;
            if (this.f19633ox) {
                for (int i3 = (i - 1) * 8; i3 >= 0; i3 -= 8) {
                    int read = this.f19632mb.read();
                    if (read == -1) {
                        throw new EOFException();
                    }
                    this.f19631b++;
                    i2 |= read << i3;
                }
                return i2;
            }
            int i4 = i * 8;
            int i5 = 0;
            while (i2 != i4) {
                int read2 = this.f19632mb.read();
                if (read2 == -1) {
                    throw new EOFException();
                }
                this.f19631b++;
                i5 |= read2 << i2;
                i2 += 8;
            }
            return i5;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: ox */
    public final int[] m6536ox(int i) throws IOException {
        int[] iArr = new int[i];
        m6538mb(iArr, 0, i);
        return iArr;
    }

    /* renamed from: mb */
    public final void m6538mb(int[] iArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            iArr[i] = m6537ox();
            i2--;
            i++;
        }
    }

    /* renamed from: b */
    public final void m6542b(int i) throws IOException {
        if (i > 0) {
            long j = i;
            long skip = this.f19632mb.skip(j);
            this.f19631b = (int) (this.f19631b + skip);
            if (skip != j) {
                throw new EOFException();
            }
        }
    }

    /* renamed from: b */
    public final void m6543b() throws IOException {
        m6542b(4);
    }
}
