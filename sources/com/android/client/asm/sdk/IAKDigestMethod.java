package com.android.client.asm.sdk;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IAKDigestMethod {
    byte[] digest();

    byte[] digest(byte[] bArr);

    String getAlgorithm();

    void update(byte[] bArr);
}
