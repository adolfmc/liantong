package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DilithiumParameterSpec implements AlgorithmParameterSpec {
    private final String name;
    public static final DilithiumParameterSpec dilithium2 = new DilithiumParameterSpec(DilithiumParameters.dilithium2);
    public static final DilithiumParameterSpec dilithium3 = new DilithiumParameterSpec(DilithiumParameters.dilithium3);
    public static final DilithiumParameterSpec dilithium5 = new DilithiumParameterSpec(DilithiumParameters.dilithium5);
    public static final DilithiumParameterSpec dilithium2_aes = new DilithiumParameterSpec(DilithiumParameters.dilithium2_aes);
    public static final DilithiumParameterSpec dilithium3_aes = new DilithiumParameterSpec(DilithiumParameters.dilithium3_aes);
    public static final DilithiumParameterSpec dilithium5_aes = new DilithiumParameterSpec(DilithiumParameters.dilithium5_aes);
    private static Map parameters = new HashMap();

    static {
        parameters.put("dilithium2", dilithium2);
        parameters.put("dilithium3", dilithium3);
        parameters.put("dilithium5", dilithium5);
        parameters.put("dilithium2-aes", dilithium2_aes);
        parameters.put("dilithium3-aes", dilithium3_aes);
        parameters.put("dilithium5-aes", dilithium5_aes);
    }

    private DilithiumParameterSpec(DilithiumParameters dilithiumParameters) {
        this.name = dilithiumParameters.getName();
    }

    public static DilithiumParameterSpec fromName(String str) {
        return (DilithiumParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
