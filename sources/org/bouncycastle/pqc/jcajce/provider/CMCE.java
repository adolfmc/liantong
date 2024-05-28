package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.p454bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyFactorySpi;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CMCE {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.cmce.";

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.CMCE", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.CMCE", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.CMCE", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyGeneratorSpi");
            CMCEKeyFactorySpi cMCEKeyFactorySpi = new CMCEKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.CMCE", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCECipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_mceliece, "CMCE");
            registerOid(configurableProvider, BCObjectIdentifiers.pqc_kem_mceliece, "CMCE", cMCEKeyFactorySpi);
            registerOidAlgorithmParameters(configurableProvider, BCObjectIdentifiers.pqc_kem_mceliece, "CMCE");
        }
    }
}
