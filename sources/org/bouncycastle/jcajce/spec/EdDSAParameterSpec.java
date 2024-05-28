package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EdDSAParameterSpec implements AlgorithmParameterSpec {
    public static final String Ed25519 = "Ed25519";
    public static final String Ed448 = "Ed448";
    private final String curveName;

    public EdDSAParameterSpec(String str) {
        String str2;
        if (!str.equalsIgnoreCase("Ed25519")) {
            if (!str.equalsIgnoreCase("Ed448")) {
                if (!str.equals(EdECObjectIdentifiers.id_Ed25519.getId())) {
                    if (!str.equals(EdECObjectIdentifiers.id_Ed448.getId())) {
                        throw new IllegalArgumentException("unrecognized curve name: " + str);
                    }
                }
            }
            str2 = "Ed448";
            this.curveName = str2;
        }
        str2 = "Ed25519";
        this.curveName = str2;
    }

    public String getCurveName() {
        return this.curveName;
    }
}
