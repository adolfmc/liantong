package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KyberParameterSpec implements AlgorithmParameterSpec {
    private final String name;
    public static final KyberParameterSpec kyber512 = new KyberParameterSpec(KyberParameters.kyber512);
    public static final KyberParameterSpec kyber768 = new KyberParameterSpec(KyberParameters.kyber768);
    public static final KyberParameterSpec kyber1024 = new KyberParameterSpec(KyberParameters.kyber1024);
    public static final KyberParameterSpec kyber512_aes = new KyberParameterSpec(KyberParameters.kyber512_aes);
    public static final KyberParameterSpec kyber768_aes = new KyberParameterSpec(KyberParameters.kyber768_aes);
    public static final KyberParameterSpec kyber1024_aes = new KyberParameterSpec(KyberParameters.kyber1024_aes);
    private static Map parameters = new HashMap();

    static {
        parameters.put("kyber512", kyber512);
        parameters.put("kyber768", kyber768);
        parameters.put("kyber1024", kyber1024);
        parameters.put("kyber512-aes", kyber512_aes);
        parameters.put("kyber768-aes", kyber768_aes);
        parameters.put("kyber1024-aes", kyber1024_aes);
    }

    private KyberParameterSpec(KyberParameters kyberParameters) {
        this.name = kyberParameters.getName();
    }

    public static KyberParameterSpec fromName(String str) {
        return (KyberParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
