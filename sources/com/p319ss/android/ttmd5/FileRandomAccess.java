package com.p319ss.android.ttmd5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.ttmd5.FileRandomAccess */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class FileRandomAccess implements IRandomAccess {
    private final RandomAccessFile accessFile;

    public FileRandomAccess(File file) throws FileNotFoundException {
        this.accessFile = new RandomAccessFile(file, "r");
    }

    @Override // com.p319ss.android.ttmd5.IRandomAccess
    public long length() throws IOException {
        return this.accessFile.length();
    }

    @Override // com.p319ss.android.ttmd5.IRandomAccess
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.accessFile.read(bArr, i, i2);
    }

    @Override // com.p319ss.android.ttmd5.IRandomAccess
    public void seek(long j, long j2) throws IOException {
        this.accessFile.seek(j);
    }

    @Override // com.p319ss.android.ttmd5.IRandomAccess
    public void close() throws IOException {
        this.accessFile.close();
    }
}
