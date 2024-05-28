package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.p454bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.frodo.FrodoKeyFactorySpi;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Frodo {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.frodo.";

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.FRODO", "org.bouncycastle.pqc.jcajce.provider.frodo.FrodoKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.FRODO", "org.bouncycastle.pqc.jcajce.provider.frodo.FrodoKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.FRODO", "org.bouncycastle.pqc.jcajce.provider.frodo.FrodoKeyGeneratorSpi");
            FrodoKeyFactorySpi frodoKeyFactorySpi = new FrodoKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.FRODO", "org.bouncycastle.pqc.jcajce.provider.frodo.FrodoCipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_frodo, "FRODO");
            registerOid(configurableProvider, BCObjectIdentifiers.pqc_kem_frodo, "Frodo", frodoKeyFactorySpi);
        }
    }
}
