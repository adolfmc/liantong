package org.bouncycastle.util.encoders;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Translator {
    int decode(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    int encode(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    int getDecodedBlockSize();

    int getEncodedBlockSize();
}
