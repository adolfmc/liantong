package org.bouncycastle.pqc.jcajce.provider.ntruprime;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.DestroyFailedException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jcajce.spec.KEMParameterSpec;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimeKEMExtractor;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimeKEMGenerator;
import org.bouncycastle.pqc.jcajce.provider.util.WrapUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class SNTRUPrimeCipherSpi extends CipherSpi {
    private final String algorithmName;
    private AlgorithmParameters engineParams;
    private SNTRUPrimeKEMGenerator kemGen;
    private KEMParameterSpec kemParameterSpec;
    private SecureRandom random;
    private BCSNTRUPrimePrivateKey unwrapKey;
    private BCSNTRUPrimePublicKey wrapKey;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Base extends SNTRUPrimeCipherSpi {
        public Base() throws NoSuchAlgorithmException {
            super("SNTRUPrime");
        }
    }

    SNTRUPrimeCipherSpi(String str) throws NoSuchAlgorithmException {
        this.algorithmName = str;
    }

    @Override // javax.crypto.CipherSpi
    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        throw new IllegalStateException("Not supported in a wrapping mode");
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        throw new IllegalStateException("Not supported in a wrapping mode");
    }

    @Override // javax.crypto.CipherSpi
    protected int engineGetBlockSize() {
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineGetIV() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    protected int engineGetKeySize(Key key) {
        return 2048;
    }

    @Override // javax.crypto.CipherSpi
    protected int engineGetOutputSize(int i) {
        return -1;
    }

    @Override // javax.crypto.CipherSpi
    protected AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null) {
            try {
                this.engineParams = AlgorithmParameters.getInstance(this.algorithmName, "BCPQC");
                this.engineParams.init(this.kemParameterSpec);
            } catch (Exception e) {
                throw Exceptions.illegalStateException(e.toString(), e);
            }
        }
        return this.engineParams;
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            try {
                parameterSpec = algorithmParameters.getParameterSpec(KEMParameterSpec.class);
            } catch (Exception unused) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        } else {
            parameterSpec = null;
        }
        engineInit(i, key, parameterSpec, this.random);
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw Exceptions.illegalArgumentException(e.getMessage(), e);
        }
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        KEMParameterSpec kEMParameterSpec;
        if (secureRandom == null) {
            this.random = CryptoServicesRegistrar.getSecureRandom();
        }
        if (algorithmParameterSpec == null) {
            kEMParameterSpec = new KEMParameterSpec("AES-KWP");
        } else if (!(algorithmParameterSpec instanceof KEMParameterSpec)) {
            throw new InvalidAlgorithmParameterException(this.algorithmName + " can only accept KTSParameterSpec");
        } else {
            kEMParameterSpec = (KEMParameterSpec) algorithmParameterSpec;
        }
        this.kemParameterSpec = kEMParameterSpec;
        if (i == 3) {
            if (!(key instanceof BCSNTRUPrimePublicKey)) {
                throw new InvalidKeyException("Only an RSA public key can be used for wrapping");
            }
            this.wrapKey = (BCSNTRUPrimePublicKey) key;
            this.kemGen = new SNTRUPrimeKEMGenerator(secureRandom);
        } else if (i != 4) {
            throw new InvalidParameterException("Cipher only valid for wrapping/unwrapping");
        } else {
            if (!(key instanceof BCSNTRUPrimePrivateKey)) {
                throw new InvalidKeyException("Only an RSA private key can be used for unwrapping");
            }
            this.unwrapKey = (BCSNTRUPrimePrivateKey) key;
        }
    }

    @Override // javax.crypto.CipherSpi
    protected void engineSetMode(String str) throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("Cannot support mode " + str);
    }

    @Override // javax.crypto.CipherSpi
    protected void engineSetPadding(String str) throws NoSuchPaddingException {
        throw new NoSuchPaddingException("Padding " + str + " unknown");
    }

    @Override // javax.crypto.CipherSpi
    protected Key engineUnwrap(byte[] bArr, String str, int i) throws InvalidKeyException, NoSuchAlgorithmException {
        if (i == 3) {
            try {
                SNTRUPrimeKEMExtractor sNTRUPrimeKEMExtractor = new SNTRUPrimeKEMExtractor(this.unwrapKey.getKeyParams());
                byte[] extractSecret = sNTRUPrimeKEMExtractor.extractSecret(Arrays.copyOfRange(bArr, 0, sNTRUPrimeKEMExtractor.getEncapsulationLength()));
                Wrapper wrapper = WrapUtil.getWrapper(this.kemParameterSpec.getKeyAlgorithmName());
                KeyParameter keyParameter = new KeyParameter(extractSecret);
                Arrays.clear(extractSecret);
                wrapper.init(false, keyParameter);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, sNTRUPrimeKEMExtractor.getEncapsulationLength(), bArr.length);
                SecretKeySpec secretKeySpec = new SecretKeySpec(wrapper.unwrap(copyOfRange, 0, copyOfRange.length), str);
                Arrays.clear(keyParameter.getKey());
                return secretKeySpec;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new NoSuchAlgorithmException("unable to extract KTS secret: " + e.getMessage());
            } catch (InvalidCipherTextException e2) {
                throw new InvalidKeyException("unable to extract KTS secret: " + e2.getMessage());
            }
        }
        throw new InvalidKeyException("only SECRET_KEY supported");
    }

    @Override // javax.crypto.CipherSpi
    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException {
        throw new IllegalStateException("Not supported in a wrapping mode");
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        throw new IllegalStateException("Not supported in a wrapping mode");
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        if (key.getEncoded() != null) {
            try {
                SecretWithEncapsulation generateEncapsulated = this.kemGen.generateEncapsulated(this.wrapKey.getKeyParams());
                Wrapper wrapper = WrapUtil.getWrapper(this.kemParameterSpec.getKeyAlgorithmName());
                wrapper.init(true, new KeyParameter(generateEncapsulated.getSecret()));
                byte[] encapsulation = generateEncapsulated.getEncapsulation();
                generateEncapsulated.destroy();
                byte[] encoded = key.getEncoded();
                byte[] concatenate = Arrays.concatenate(encapsulation, wrapper.wrap(encoded, 0, encoded.length));
                Arrays.clear(encoded);
                return concatenate;
            } catch (IllegalArgumentException e) {
                throw new IllegalBlockSizeException("unable to generate KTS secret: " + e.getMessage());
            } catch (DestroyFailedException e2) {
                throw new IllegalBlockSizeException("unable to destroy interim values: " + e2.getMessage());
            }
        }
        throw new InvalidKeyException("Cannot wrap key, null encoding.");
    }
}
