package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class XDHParameterSpec implements AlgorithmParameterSpec {
    public static final String X25519 = "X25519";
    public static final String X448 = "X448";
    private final String curveName;

    public XDHParameterSpec(String str) {
        String str2;
        if (!str.equalsIgnoreCase("X25519")) {
            if (!str.equalsIgnoreCase("X448")) {
                if (!str.equals(EdECObjectIdentifiers.id_X25519.getId())) {
                    if (!str.equals(EdECObjectIdentifiers.id_X448.getId())) {
                        throw new IllegalArgumentException("unrecognized curve name: " + str);
                    }
                }
            }
            str2 = "X448";
            this.curveName = str2;
        }
        str2 = "X25519";
        this.curveName = str2;
    }

    public String getCurveName() {
        return this.curveName;
    }
}
