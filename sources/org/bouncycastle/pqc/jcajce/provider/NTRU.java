package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.p454bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.ntru.NTRUKeyFactorySpi;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NTRU {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.ntru.";

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.NTRU", "org.bouncycastle.pqc.jcajce.provider.ntru.NTRUKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.NTRU", "org.bouncycastle.pqc.jcajce.provider.ntru.NTRUKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.NTRU", "org.bouncycastle.pqc.jcajce.provider.ntru.NTRUKeyGeneratorSpi");
            NTRUKeyFactorySpi nTRUKeyFactorySpi = new NTRUKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.NTRU", "org.bouncycastle.pqc.jcajce.provider.ntru.NTRUCipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_ntru, "NTRU");
            registerOid(configurableProvider, BCObjectIdentifiers.pqc_kem_ntru, "NTRU", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntruhps2048509, "NTRU", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntruhps2048677, "NTRU", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntruhps4096821, "NTRU", nTRUKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.ntruhrss701, "NTRU", nTRUKeyFactorySpi);
        }
    }
}
