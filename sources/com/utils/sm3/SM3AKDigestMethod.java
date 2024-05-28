package com.utils.sm3;

import com.android.client.asm.sdk.IAKDigestMethod;
import com.gmrz.android.client.utils.Logger;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SM3AKDigestMethod implements IAKDigestMethod {
    public static final String TAG = "SM3AKDigestMethod";
    private final SM3Digest mMessageDigest = new SM3Digest();

    @Override // com.android.client.asm.sdk.IAKDigestMethod
    public String getAlgorithm() {
        return "SM3";
    }

    @Override // com.android.client.asm.sdk.IAKDigestMethod
    public void update(byte[] bArr) {
        this.mMessageDigest.update(bArr, 0, bArr.length);
    }

    @Override // com.android.client.asm.sdk.IAKDigestMethod
    public byte[] digest() {
        Logger.m15895d(TAG, "sm3 Digest()");
        byte[] bArr = new byte[32];
        this.mMessageDigest.doFinal(bArr, 0);
        return bArr;
    }

    @Override // com.android.client.asm.sdk.IAKDigestMethod
    public byte[] digest(byte[] bArr) {
        Logger.m15895d(TAG, "sm3 Digest(input)");
        return SM3Utils.doSM3Hash(bArr);
    }
}
