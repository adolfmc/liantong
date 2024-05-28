package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.p454bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.bike.BIKEKeyFactorySpi;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BIKE {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.bike.";

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.BIKE", "org.bouncycastle.pqc.jcajce.provider.bike.BIKEKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.BIKE", "org.bouncycastle.pqc.jcajce.provider.bike.BIKEKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.BIKE", "org.bouncycastle.pqc.jcajce.provider.bike.BIKEKeyGeneratorSpi");
            BIKEKeyFactorySpi bIKEKeyFactorySpi = new BIKEKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.BIKE", "org.bouncycastle.pqc.jcajce.provider.bike.BIKECipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_bike, "BIKE");
            registerOid(configurableProvider, BCObjectIdentifiers.pqc_kem_bike, "BIKE", bIKEKeyFactorySpi);
        }
    }
}
