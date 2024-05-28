package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.hqc.HQCParameters;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HQCParameterSpec implements AlgorithmParameterSpec {
    public static final HQCParameterSpec hqc128 = new HQCParameterSpec(HQCParameters.hqc128);
    public static final HQCParameterSpec hqc192 = new HQCParameterSpec(HQCParameters.hqc192);
    public static final HQCParameterSpec hqc256 = new HQCParameterSpec(HQCParameters.hqc256);
    private static Map parameters = new HashMap();
    private final String name;

    static {
        parameters.put("hqc128", hqc128);
        parameters.put("hqc192", hqc192);
        parameters.put("hqc256", hqc256);
    }

    private HQCParameterSpec(HQCParameters hQCParameters) {
        this.name = hQCParameters.getName();
    }

    public static HQCParameterSpec fromName(String str) {
        return (HQCParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
