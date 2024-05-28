package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BIKEParameterSpec implements AlgorithmParameterSpec {
    public static final BIKEParameterSpec bike128 = new BIKEParameterSpec(BIKEParameters.bike128);
    public static final BIKEParameterSpec bike192 = new BIKEParameterSpec(BIKEParameters.bike192);
    public static final BIKEParameterSpec bike256 = new BIKEParameterSpec(BIKEParameters.bike256);
    private static Map parameters = new HashMap();
    private final String name;

    static {
        parameters.put("bike128", bike128);
        parameters.put("bike192", bike192);
        parameters.put("bike256", bike256);
    }

    private BIKEParameterSpec(BIKEParameters bIKEParameters) {
        this.name = bIKEParameters.getName();
    }

    public static BIKEParameterSpec fromName(String str) {
        return (BIKEParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
