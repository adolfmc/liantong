package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimeParameters;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NTRULPRimeParameterSpec implements AlgorithmParameterSpec {
    private final String name;
    public static final NTRULPRimeParameterSpec ntrulpr653 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr653);
    public static final NTRULPRimeParameterSpec ntrulpr761 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr761);
    public static final NTRULPRimeParameterSpec ntrulpr857 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr857);
    public static final NTRULPRimeParameterSpec ntrulpr953 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr953);
    public static final NTRULPRimeParameterSpec ntrulpr1013 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr1013);
    public static final NTRULPRimeParameterSpec ntrulpr1277 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr1277);
    private static Map parameters = new HashMap();

    static {
        parameters.put("ntrulpr653", ntrulpr653);
        parameters.put("ntrulpr761", ntrulpr761);
        parameters.put("ntrulpr857", ntrulpr857);
        parameters.put("ntrulpr953", ntrulpr953);
        parameters.put("ntrulpr1013", ntrulpr1013);
        parameters.put("ntrulpr1277", ntrulpr1277);
    }

    private NTRULPRimeParameterSpec(NTRULPRimeParameters nTRULPRimeParameters) {
        this.name = nTRULPRimeParameters.getName();
    }

    public static NTRULPRimeParameterSpec fromName(String str) {
        return (NTRULPRimeParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
