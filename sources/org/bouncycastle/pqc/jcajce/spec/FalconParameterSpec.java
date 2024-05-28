package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FalconParameterSpec implements AlgorithmParameterSpec {
    private final String name;
    public static final FalconParameterSpec falcon_512 = new FalconParameterSpec(FalconParameters.falcon_512);
    public static final FalconParameterSpec falcon_1024 = new FalconParameterSpec(FalconParameters.falcon_1024);
    private static Map parameters = new HashMap();

    static {
        parameters.put("falcon-512", falcon_512);
        parameters.put("falcon-1024", falcon_1024);
    }

    private FalconParameterSpec(FalconParameters falconParameters) {
        this.name = falconParameters.getName();
    }

    public static FalconParameterSpec fromName(String str) {
        return (FalconParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
