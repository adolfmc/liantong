package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class XMSSNode implements Serializable {
    private static final long serialVersionUID = 1;
    private final int height;
    private final byte[] value;

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSNode(int i, byte[] bArr) {
        this.height = i;
        this.value = bArr;
    }

    public int getHeight() {
        return this.height;
    }

    public byte[] getValue() {
        return XMSSUtil.cloneArray(this.value);
    }
}
