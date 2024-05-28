package org.bouncycastle.pqc.crypto.falcon;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FalconParameters implements CipherParameters {
    private final int logn;
    private final String name;
    private final int nonce_length;
    public static final FalconParameters falcon_512 = new FalconParameters("falcon-512", 9, 40);
    public static final FalconParameters falcon_1024 = new FalconParameters("falcon-1024", 10, 40);

    private FalconParameters(String str, int i, int i2) {
        if (i < 1 || i > 10) {
            throw new IllegalArgumentException("Log N degree should be between 1 and 10");
        }
        this.name = str;
        this.logn = i;
        this.nonce_length = i2;
    }

    public int getLogN() {
        return this.logn;
    }

    public String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNonceLength() {
        return this.nonce_length;
    }
}
