package org.apache.commons.codec.binary;

import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Base32InputStream extends BaseNCodecInputStream {
    public Base32InputStream(InputStream inputStream) {
        this(inputStream, false);
    }

    public Base32InputStream(InputStream inputStream, boolean z) {
        super(inputStream, new Base32(false), z);
    }

    public Base32InputStream(InputStream inputStream, boolean z, int i, byte[] bArr) {
        super(inputStream, new Base32(i, bArr), z);
    }
}
