package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.legacy.crypto.qtesla.QTESLASecurityCategory;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class QTESLAParameterSpec implements AlgorithmParameterSpec {
    public static final String PROVABLY_SECURE_I = QTESLASecurityCategory.getName(5);
    public static final String PROVABLY_SECURE_III = QTESLASecurityCategory.getName(6);
    private String securityCategory;

    public QTESLAParameterSpec(String str) {
        this.securityCategory = str;
    }

    public String getSecurityCategory() {
        return this.securityCategory;
    }
}
