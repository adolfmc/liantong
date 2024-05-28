package com.p319ss.android.ttmd5;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.ttmd5.IRandomAccess */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IRandomAccess {
    void close() throws IOException;

    long length() throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    void seek(long j, long j2) throws IOException;
}
