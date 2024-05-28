package org.p415a.p424c;

import java.io.EOFException;
import java.io.InputStream;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.ae */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12583ae extends InputStream {

    /* renamed from: a */
    InputStream f25566a;

    public C12583ae(InputStream inputStream) {
        this.f25566a = inputStream;
    }

    /* renamed from: a */
    private byte[] m1582a(byte[] bArr, int i, int i2, String str) {
        if (i2 == i) {
            return C12957a.m439a(bArr, 0, i);
        }
        throw new EOFException("truncated " + str + " subpacket data.");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ef  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.p415a.p424c.C12582ad m1583a() {
        /*
            Method dump skipped, instructions count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p424c.C12583ae.m1583a():org.a.c.ad");
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f25566a.available();
    }

    @Override // java.io.InputStream
    public int read() {
        return this.f25566a.read();
    }
}
