package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.p454bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.hqc.HQCKeyFactorySpi;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HQC {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.hqc.";

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.HQC", "org.bouncycastle.pqc.jcajce.provider.hqc.HQCKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.HQC", "org.bouncycastle.pqc.jcajce.provider.hqc.HQCKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.HQC", "org.bouncycastle.pqc.jcajce.provider.hqc.HQCKeyGeneratorSpi");
            HQCKeyFactorySpi hQCKeyFactorySpi = new HQCKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.HQC", "org.bouncycastle.pqc.jcajce.provider.hqc.HQCCipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_hqc, "HQC");
            registerOid(configurableProvider, BCObjectIdentifiers.pqc_kem_hqc, "HQC", hQCKeyFactorySpi);
        }
    }
}
