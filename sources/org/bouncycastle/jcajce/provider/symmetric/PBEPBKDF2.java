package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.p455gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.PasswordConverter;
import org.bouncycastle.jcajce.PBKDF2Key;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.util.Integers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PBEPBKDF2 {
    private static final Map prfCodes = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class AlgParams extends BaseAlgorithmParameters {
        PBKDF2Params params;

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() {
            try {
                return this.params.getEncoded("DER");
            } catch (IOException e) {
                throw new RuntimeException("Oooops! " + e.toString());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) {
            if (isASN1FormatString(str)) {
                return engineGetEncoded();
            }
            return null;
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (!(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PBKDF2 PBE parameters algorithm parameters object");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            this.params = new PBKDF2Params(pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) throws IOException {
            this.params = PBKDF2Params.getInstance(ASN1Primitive.fromByteArray(bArr));
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) throws IOException {
            if (!isASN1FormatString(str)) {
                throw new IOException("Unknown parameters format in PBKDF2 parameters object");
            }
            engineInit(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "PBKDF2 Parameters";
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == PBEParameterSpec.class || cls == AlgorithmParameterSpec.class) {
                return new PBEParameterSpec(this.params.getSalt(), this.params.getIterationCount().intValue());
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PBKDF2 PBE parameters object.");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class BasePBKDF2 extends BaseSecretKeyFactory {
        private int defaultDigest;
        private int scheme;

        public BasePBKDF2(String str, int i) {
            this(str, i, 1);
        }

        public BasePBKDF2(String str, int i, int i2) {
            super(str, PKCSObjectIdentifiers.id_PBKDF2);
            this.scheme = i;
            this.defaultDigest = i2;
        }

        private int getDigestCode(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws InvalidKeySpecException {
            Integer num = (Integer) PBEPBKDF2.prfCodes.get(aSN1ObjectIdentifier);
            if (num != null) {
                return num.intValue();
            }
            throw new InvalidKeySpecException("Invalid KeySpec: unknown PRF algorithm " + aSN1ObjectIdentifier);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        public SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof PBEKeySpec) {
                PBEKeySpec pBEKeySpec = (PBEKeySpec) keySpec;
                if (pBEKeySpec.getSalt() == null) {
                    return new PBKDF2Key(pBEKeySpec.getPassword(), this.scheme == 1 ? PasswordConverter.ASCII : PasswordConverter.UTF8);
                } else if (pBEKeySpec.getIterationCount() <= 0) {
                    throw new InvalidKeySpecException("positive iteration count required: " + pBEKeySpec.getIterationCount());
                } else if (pBEKeySpec.getKeyLength() <= 0) {
                    throw new InvalidKeySpecException("positive key length required: " + pBEKeySpec.getKeyLength());
                } else if (pBEKeySpec.getPassword().length != 0) {
                    if (pBEKeySpec instanceof PBKDF2KeySpec) {
                        int digestCode = getDigestCode(((PBKDF2KeySpec) pBEKeySpec).getPrf().getAlgorithm());
                        int keyLength = pBEKeySpec.getKeyLength();
                        return new BCPBEKey(this.algName, this.algOid, this.scheme, digestCode, keyLength, -1, pBEKeySpec, PBE.Util.makePBEMacParameters(pBEKeySpec, this.scheme, digestCode, keyLength));
                    }
                    int i = this.defaultDigest;
                    int keyLength2 = pBEKeySpec.getKeyLength();
                    return new BCPBEKey(this.algName, this.algOid, this.scheme, i, keyLength2, -1, pBEKeySpec, PBE.Util.makePBEMacParameters(pBEKeySpec, this.scheme, i, keyLength2));
                } else {
                    throw new IllegalArgumentException("password empty");
                }
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = PBEPBKDF2.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.PBKDF2", PREFIX + "$AlgParams");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.id_PBKDF2, "PBKDF2");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2", PREFIX + "$PBKDF2withUTF8");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WITHHMACSHA1", "PBKDF2");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WITHHMACSHA1ANDUTF8", "PBKDF2");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.id_PBKDF2, "PBKDF2");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHASCII", PREFIX + "$PBKDF2with8BIT");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WITH8BIT", "PBKDF2WITHASCII");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WITHHMACSHA1AND8BIT", "PBKDF2WITHASCII");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA224", PREFIX + "$PBKDF2withSHA224");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA256", PREFIX + "$PBKDF2withSHA256");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA384", PREFIX + "$PBKDF2withSHA384");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA512", PREFIX + "$PBKDF2withSHA512");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA3-224", PREFIX + "$PBKDF2withSHA3_224");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA3-256", PREFIX + "$PBKDF2withSHA3_256");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA3-384", PREFIX + "$PBKDF2withSHA3_384");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA3-512", PREFIX + "$PBKDF2withSHA3_512");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACGOST3411", PREFIX + "$PBKDF2withGOST3411");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSM3", PREFIX + "$PBKDF2withSM3");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2with8BIT extends BasePBKDF2 {
        public PBKDF2with8BIT() {
            super("PBKDF2", 1);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withGOST3411 extends BasePBKDF2 {
        public PBKDF2withGOST3411() {
            super("PBKDF2", 5, 6);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSHA224 extends BasePBKDF2 {
        public PBKDF2withSHA224() {
            super("PBKDF2", 5, 7);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSHA256 extends BasePBKDF2 {
        public PBKDF2withSHA256() {
            super("PBKDF2", 5, 4);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSHA384 extends BasePBKDF2 {
        public PBKDF2withSHA384() {
            super("PBKDF2", 5, 8);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSHA3_224 extends BasePBKDF2 {
        public PBKDF2withSHA3_224() {
            super("PBKDF2", 5, 10);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSHA3_256 extends BasePBKDF2 {
        public PBKDF2withSHA3_256() {
            super("PBKDF2", 5, 11);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSHA3_384 extends BasePBKDF2 {
        public PBKDF2withSHA3_384() {
            super("PBKDF2", 5, 12);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSHA3_512 extends BasePBKDF2 {
        public PBKDF2withSHA3_512() {
            super("PBKDF2", 5, 13);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSHA512 extends BasePBKDF2 {
        public PBKDF2withSHA512() {
            super("PBKDF2", 5, 9);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withSM3 extends BasePBKDF2 {
        public PBKDF2withSM3() {
            super("PBKDF2", 5, 14);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PBKDF2withUTF8 extends BasePBKDF2 {
        public PBKDF2withUTF8() {
            super("PBKDF2", 5);
        }
    }

    static {
        prfCodes.put(CryptoProObjectIdentifiers.gostR3411Hmac, Integers.valueOf(6));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA1, Integers.valueOf(1));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA256, Integers.valueOf(4));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA224, Integers.valueOf(7));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA384, Integers.valueOf(8));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA512, Integers.valueOf(9));
        prfCodes.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, Integers.valueOf(11));
        prfCodes.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, Integers.valueOf(10));
        prfCodes.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, Integers.valueOf(12));
        prfCodes.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, Integers.valueOf(13));
        prfCodes.put(GMObjectIdentifiers.hmac_sm3, Integers.valueOf(14));
    }

    private PBEPBKDF2() {
    }
}
