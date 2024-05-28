package com.eidlink.idocr.sdk.listener;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class EidLinkReadCardCallBack {
    public abstract byte[] transceiveTypeA(byte[] bArr);

    public abstract byte[] transceiveTypeB(byte[] bArr);

    public byte[] transmit(byte[] bArr) {
        return transceiveTypeA(bArr);
    }
}
