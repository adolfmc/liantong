package org.bouncycastle.crypto.util;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class PBKDFConfig {
    private final ASN1ObjectIdentifier algorithm;

    /* JADX INFO: Access modifiers changed from: protected */
    public PBKDFConfig(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.algorithm = aSN1ObjectIdentifier;
    }

    public ASN1ObjectIdentifier getAlgorithm() {
        return this.algorithm;
    }
}
