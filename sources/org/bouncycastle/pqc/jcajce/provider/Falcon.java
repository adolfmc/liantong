package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.p454bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.falcon.FalconKeyFactorySpi;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Falcon {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.falcon.";

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.FALCON", "org.bouncycastle.pqc.jcajce.provider.falcon.FalconKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.FALCON", "org.bouncycastle.pqc.jcajce.provider.falcon.FalconKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.FALCON", "org.bouncycastle.pqc.jcajce.provider.falcon.FalconKeyGeneratorSpi");
            addSignatureAlgorithm(configurableProvider, "FALCON", "org.bouncycastle.pqc.jcajce.provider.falcon.SignatureSpi$Base", BCObjectIdentifiers.falcon);
            addSignatureAlias(configurableProvider, "FALCON", BCObjectIdentifiers.falcon_512);
            addSignatureAlias(configurableProvider, "FALCON", BCObjectIdentifiers.falcon_1024);
            FalconKeyFactorySpi falconKeyFactorySpi = new FalconKeyFactorySpi();
            registerOid(configurableProvider, BCObjectIdentifiers.falcon_512, "FALCON", falconKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.falcon_1024, "FALCON", falconKeyFactorySpi);
        }
    }
}
