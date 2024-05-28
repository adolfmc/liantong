package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NTRUParameterSpec implements AlgorithmParameterSpec {
    public static final NTRUParameterSpec ntruhps2048509 = new NTRUParameterSpec(NTRUParameters.ntruhps2048509);
    public static final NTRUParameterSpec ntruhps2048677 = new NTRUParameterSpec(NTRUParameters.ntruhps2048677);
    public static final NTRUParameterSpec ntruhps4096821 = new NTRUParameterSpec(NTRUParameters.ntruhps4096821);
    public static final NTRUParameterSpec ntruhrss701 = new NTRUParameterSpec(NTRUParameters.ntruhrss701);
    private static Map parameters = new HashMap();
    private final String name;

    static {
        parameters.put("ntruhps2048509", ntruhps2048509);
        parameters.put("ntruhps2048677", ntruhps2048677);
        parameters.put("ntruhps4096821", ntruhps4096821);
        parameters.put("ntruhrss701", ntruhrss701);
    }

    private NTRUParameterSpec(NTRUParameters nTRUParameters) {
        this.name = nTRUParameters.getName();
    }

    public static NTRUParameterSpec fromName(String str) {
        return (NTRUParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
