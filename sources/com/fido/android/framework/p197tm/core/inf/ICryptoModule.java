package com.fido.android.framework.p197tm.core.inf;

import com.gmrz.android.client.asm.api.AsmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.fido.android.framework.tm.core.inf.ICryptoModule */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ICryptoModule {
    String bytesToHexString(byte[] bArr);

    byte[] decryptData(byte[] bArr, byte[] bArr2) throws AsmException;

    byte[] encryptData(byte[] bArr, byte[] bArr2) throws AsmException;
}
