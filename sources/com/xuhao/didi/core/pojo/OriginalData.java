package com.xuhao.didi.core.pojo;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class OriginalData implements Serializable {
    private byte[] mBodyBytes;
    private byte[] mHeadBytes;

    public byte[] getHeadBytes() {
        return this.mHeadBytes;
    }

    public void setHeadBytes(byte[] bArr) {
        this.mHeadBytes = bArr;
    }

    public byte[] getBodyBytes() {
        return this.mBodyBytes;
    }

    public void setBodyBytes(byte[] bArr) {
        this.mBodyBytes = bArr;
    }
}
