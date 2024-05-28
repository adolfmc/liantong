package com.google.zxing.qrcode.encoder;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class BlockPair {
    private final byte[] dataBytes;
    private final byte[] errorCorrectionBytes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BlockPair(byte[] bArr, byte[] bArr2) {
        this.dataBytes = bArr;
        this.errorCorrectionBytes = bArr2;
    }

    public byte[] getDataBytes() {
        return this.dataBytes;
    }

    public byte[] getErrorCorrectionBytes() {
        return this.errorCorrectionBytes;
    }
}
