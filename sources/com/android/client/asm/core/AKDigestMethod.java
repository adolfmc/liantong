package com.android.client.asm.core;

import com.android.client.asm.sdk.IAKDigestMethod;
import com.gmrz.android.client.utils.Logger;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AKDigestMethod implements IAKDigestMethod {

    /* renamed from: a */
    private static final String f4045a = "AKDigestMethod";

    /* renamed from: b */
    private MessageDigest f4046b;

    @Override // com.android.client.asm.sdk.IAKDigestMethod
    public String getAlgorithm() {
        return "SHA-256";
    }

    public AKDigestMethod() {
        try {
            this.f4046b = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            Logger.m15891e(f4045a, "Failed to getting digest algorithm for AK", e);
        }
    }

    @Override // com.android.client.asm.sdk.IAKDigestMethod
    public void update(byte[] bArr) {
        this.f4046b.update(bArr);
    }

    @Override // com.android.client.asm.sdk.IAKDigestMethod
    public byte[] digest() {
        return this.f4046b.digest();
    }

    @Override // com.android.client.asm.sdk.IAKDigestMethod
    public byte[] digest(byte[] bArr) {
        return this.f4046b.digest(bArr);
    }
}
