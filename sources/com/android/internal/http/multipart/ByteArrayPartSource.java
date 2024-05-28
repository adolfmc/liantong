package com.android.internal.http.multipart;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class ByteArrayPartSource implements PartSource {
    private byte[] bytes;
    private String fileName;

    public ByteArrayPartSource(String str, byte[] bArr) {
        this.fileName = str;
        this.bytes = bArr;
    }

    @Override // com.android.internal.http.multipart.PartSource
    public long getLength() {
        return this.bytes.length;
    }

    @Override // com.android.internal.http.multipart.PartSource
    public String getFileName() {
        return this.fileName;
    }

    @Override // com.android.internal.http.multipart.PartSource
    public InputStream createInputStream() {
        return new ByteArrayInputStream(this.bytes);
    }
}
