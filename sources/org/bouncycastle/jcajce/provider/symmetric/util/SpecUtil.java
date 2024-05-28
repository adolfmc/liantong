package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.spec.AlgorithmParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class SpecUtil {
    SpecUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameterSpec extractSpec(AlgorithmParameters algorithmParameters, Class[] clsArr) {
        try {
            return algorithmParameters.getParameterSpec(AlgorithmParameterSpec.class);
        } catch (Exception unused) {
            for (int i = 0; i != clsArr.length; i++) {
                if (clsArr[i] != null) {
                    try {
                        return algorithmParameters.getParameterSpec(clsArr[i]);
                    } catch (Exception unused2) {
                    }
                }
            }
            return null;
        }
    }
}
