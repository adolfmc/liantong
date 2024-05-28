package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FrodoParameterSpec implements AlgorithmParameterSpec {
    private final String name;
    public static final FrodoParameterSpec frodokem640aes = new FrodoParameterSpec(FrodoParameters.frodokem640aes);
    public static final FrodoParameterSpec frodokem640shake = new FrodoParameterSpec(FrodoParameters.frodokem640shake);
    public static final FrodoParameterSpec frodokem976aes = new FrodoParameterSpec(FrodoParameters.frodokem976aes);
    public static final FrodoParameterSpec frodokem976shake = new FrodoParameterSpec(FrodoParameters.frodokem976shake);
    public static final FrodoParameterSpec frodokem1344aes = new FrodoParameterSpec(FrodoParameters.frodokem1344aes);
    public static final FrodoParameterSpec frodokem1344shake = new FrodoParameterSpec(FrodoParameters.frodokem1344shake);
    private static Map parameters = new HashMap();

    static {
        parameters.put("frodokem19888r3", frodokem640aes);
        parameters.put("frodokem19888shaker3", frodokem640shake);
        parameters.put("frodokem31296r3", frodokem976aes);
        parameters.put("frodokem31296shaker3", frodokem976shake);
        parameters.put("frodokem43088r3", frodokem1344aes);
        parameters.put("frodokem43088shaker3", frodokem1344shake);
        parameters.put("frodokem640aes", frodokem640aes);
        parameters.put("frodokem640shake", frodokem640shake);
        parameters.put("frodokem976aes", frodokem976aes);
        parameters.put("frodokem976shake", frodokem976shake);
        parameters.put("frodokem1344aes", frodokem1344aes);
        parameters.put("frodokem1344shake", frodokem1344shake);
    }

    private FrodoParameterSpec(FrodoParameters frodoParameters) {
        this.name = frodoParameters.getName();
    }

    public static FrodoParameterSpec fromName(String str) {
        return (FrodoParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
