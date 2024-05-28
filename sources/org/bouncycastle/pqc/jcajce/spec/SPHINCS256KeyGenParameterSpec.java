package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SPHINCS256KeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final String SHA3_256 = "SHA3-256";
    public static final String SHA512_256 = "SHA512-256";
    private final String treeHash;

    public SPHINCS256KeyGenParameterSpec() {
        this("SHA512-256");
    }

    public SPHINCS256KeyGenParameterSpec(String str) {
        this.treeHash = str;
    }

    public String getTreeDigest() {
        return this.treeHash;
    }
}
