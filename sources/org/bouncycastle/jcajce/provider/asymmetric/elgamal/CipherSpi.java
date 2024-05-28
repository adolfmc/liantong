package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.encodings.ISO9796d1Encoding;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.ElGamalEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi;
import org.bouncycastle.jcajce.provider.util.BadBlockException;
import org.bouncycastle.jcajce.provider.util.DigestFactory;
import org.bouncycastle.jce.interfaces.ElGamalKey;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CipherSpi extends BaseCipherSpi {
    private BaseCipherSpi.ErasableOutputStream bOut = new BaseCipherSpi.ErasableOutputStream();
    private AsymmetricBlockCipher cipher;
    private AlgorithmParameters engineParams;
    private AlgorithmParameterSpec paramSpec;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class NoPadding extends CipherSpi {
        public NoPadding() {
            super(new ElGamalEngine());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PKCS1v1_5Padding extends CipherSpi {
        public PKCS1v1_5Padding() {
            super(new PKCS1Encoding(new ElGamalEngine()));
        }
    }

    public CipherSpi(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.cipher = asymmetricBlockCipher;
    }

    private byte[] getOutput() throws BadPaddingException {
        try {
            try {
                try {
                    return this.cipher.processBlock(this.bOut.getBuf(), 0, this.bOut.size());
                } catch (InvalidCipherTextException e) {
                    throw new BadBlockException("unable to decrypt block", e);
                }
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new BadBlockException("unable to decrypt block", e2);
            }
        } finally {
            this.bOut.erase();
        }
    }

    private void initFromSpec(OAEPParameterSpec oAEPParameterSpec) throws NoSuchPaddingException {
        MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters();
        Digest digest = DigestFactory.getDigest(mGF1ParameterSpec.getDigestAlgorithm());
        if (digest != null) {
            this.cipher = new OAEPEncoding(new ElGamalEngine(), digest, ((PSource.PSpecified) oAEPParameterSpec.getPSource()).getValue());
            this.paramSpec = oAEPParameterSpec;
            return;
        }
        throw new NoSuchPaddingException("no match on OAEP constructor for digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
    }

    @Override // javax.crypto.CipherSpi
    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        if (engineGetOutputSize(i2) + i3 <= bArr2.length) {
            if (bArr != null) {
                this.bOut.write(bArr, i, i2);
            }
            if (this.cipher instanceof ElGamalEngine) {
                if (this.bOut.size() > this.cipher.getInputBlockSize() + 1) {
                    throw new ArrayIndexOutOfBoundsException("too much data for ElGamal block");
                }
            } else if (this.bOut.size() > this.cipher.getInputBlockSize()) {
                throw new ArrayIndexOutOfBoundsException("too much data for ElGamal block");
            }
            byte[] output = getOutput();
            for (int i4 = 0; i4 != output.length; i4++) {
                bArr2[i3 + i4] = output[i4];
            }
            return output.length;
        }
        throw new ShortBufferException("output buffer too short for input.");
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        if (bArr != null) {
            this.bOut.write(bArr, i, i2);
        }
        if (this.cipher instanceof ElGamalEngine) {
            if (this.bOut.size() > this.cipher.getInputBlockSize() + 1) {
                throw new ArrayIndexOutOfBoundsException("too much data for ElGamal block");
            }
        } else if (this.bOut.size() > this.cipher.getInputBlockSize()) {
            throw new ArrayIndexOutOfBoundsException("too much data for ElGamal block");
        }
        return getOutput();
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi, javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        return this.cipher.getInputBlockSize();
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi, javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        BigInteger p;
        if (key instanceof ElGamalKey) {
            p = ((ElGamalKey) key).getParameters().getP();
        } else if (!(key instanceof DHKey)) {
            throw new IllegalArgumentException("not an ElGamal key!");
        } else {
            p = ((DHKey) key).getParams().getP();
        }
        return p.bitLength();
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi, javax.crypto.CipherSpi
    public int engineGetOutputSize(int i) {
        return this.cipher.getOutputBlockSize();
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi, javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null && this.paramSpec != null) {
            try {
                this.engineParams = createParametersInstance("OAEP");
                this.engineParams.init(this.paramSpec);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.engineParams;
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("can't handle parameters in ElGamal");
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException("Eeeek! " + e.toString(), e);
        }
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters generatePrivateKeyParameter;
        AsymmetricBlockCipher asymmetricBlockCipher;
        boolean z;
        if (key instanceof DHPublicKey) {
            generatePrivateKeyParameter = ElGamalUtil.generatePublicKeyParameter((PublicKey) key);
        } else if (!(key instanceof DHPrivateKey)) {
            throw new InvalidKeyException("unknown key type passed to ElGamal");
        } else {
            generatePrivateKeyParameter = ElGamalUtil.generatePrivateKeyParameter((PrivateKey) key);
        }
        if (algorithmParameterSpec instanceof OAEPParameterSpec) {
            OAEPParameterSpec oAEPParameterSpec = (OAEPParameterSpec) algorithmParameterSpec;
            this.paramSpec = algorithmParameterSpec;
            if (!oAEPParameterSpec.getMGFAlgorithm().equalsIgnoreCase("MGF1") && !oAEPParameterSpec.getMGFAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1.getId())) {
                throw new InvalidAlgorithmParameterException("unknown mask generation function specified");
            }
            if (!(oAEPParameterSpec.getMGFParameters() instanceof MGF1ParameterSpec)) {
                throw new InvalidAlgorithmParameterException("unkown MGF parameters");
            }
            Digest digest = DigestFactory.getDigest(oAEPParameterSpec.getDigestAlgorithm());
            if (digest == null) {
                throw new InvalidAlgorithmParameterException("no match on digest algorithm: " + oAEPParameterSpec.getDigestAlgorithm());
            }
            MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters();
            Digest digest2 = DigestFactory.getDigest(mGF1ParameterSpec.getDigestAlgorithm());
            if (digest2 == null) {
                throw new InvalidAlgorithmParameterException("no match on MGF digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
            }
            this.cipher = new OAEPEncoding(new ElGamalEngine(), digest, digest2, ((PSource.PSpecified) oAEPParameterSpec.getPSource()).getValue());
        } else if (algorithmParameterSpec != null) {
            throw new InvalidAlgorithmParameterException("unknown parameter type.");
        }
        if (secureRandom != null) {
            generatePrivateKeyParameter = new ParametersWithRandom(generatePrivateKeyParameter, secureRandom);
        }
        switch (i) {
            case 1:
            case 3:
                asymmetricBlockCipher = this.cipher;
                z = true;
                break;
            case 2:
            case 4:
                asymmetricBlockCipher = this.cipher;
                z = false;
                break;
            default:
                throw new InvalidParameterException("unknown opmode " + i + " passed to ElGamal");
        }
        asymmetricBlockCipher.init(z, generatePrivateKeyParameter);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi, javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        String upperCase = Strings.toUpperCase(str);
        if (upperCase.equals("NONE") || upperCase.equals("ECB")) {
            return;
        }
        throw new NoSuchAlgorithmException("can't support mode " + str);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseCipherSpi, javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        OAEPParameterSpec oAEPParameterSpec;
        AsymmetricBlockCipher iSO9796d1Encoding;
        String upperCase = Strings.toUpperCase(str);
        if (upperCase.equals("NOPADDING")) {
            iSO9796d1Encoding = new ElGamalEngine();
        } else if (upperCase.equals("PKCS1PADDING")) {
            iSO9796d1Encoding = new PKCS1Encoding(new ElGamalEngine());
        } else if (!upperCase.equals("ISO9796-1PADDING")) {
            if (!upperCase.equals("OAEPPADDING")) {
                if (upperCase.equals("OAEPWITHMD5ANDMGF1PADDING")) {
                    oAEPParameterSpec = new OAEPParameterSpec("MD5", "MGF1", new MGF1ParameterSpec("MD5"), PSource.PSpecified.DEFAULT);
                } else if (!upperCase.equals("OAEPWITHSHA1ANDMGF1PADDING")) {
                    if (upperCase.equals("OAEPWITHSHA224ANDMGF1PADDING")) {
                        oAEPParameterSpec = new OAEPParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), PSource.PSpecified.DEFAULT);
                    } else if (upperCase.equals("OAEPWITHSHA256ANDMGF1PADDING")) {
                        oAEPParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
                    } else if (upperCase.equals("OAEPWITHSHA384ANDMGF1PADDING")) {
                        oAEPParameterSpec = new OAEPParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, PSource.PSpecified.DEFAULT);
                    } else if (upperCase.equals("OAEPWITHSHA512ANDMGF1PADDING")) {
                        oAEPParameterSpec = new OAEPParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, PSource.PSpecified.DEFAULT);
                    } else if (upperCase.equals("OAEPWITHSHA3-224ANDMGF1PADDING")) {
                        oAEPParameterSpec = new OAEPParameterSpec("SHA3-224", "MGF1", new MGF1ParameterSpec("SHA3-224"), PSource.PSpecified.DEFAULT);
                    } else if (upperCase.equals("OAEPWITHSHA3-256ANDMGF1PADDING")) {
                        oAEPParameterSpec = new OAEPParameterSpec("SHA3-256", "MGF1", new MGF1ParameterSpec("SHA3-256"), PSource.PSpecified.DEFAULT);
                    } else if (upperCase.equals("OAEPWITHSHA3-384ANDMGF1PADDING")) {
                        oAEPParameterSpec = new OAEPParameterSpec("SHA3-384", "MGF1", new MGF1ParameterSpec("SHA3-384"), PSource.PSpecified.DEFAULT);
                    } else if (!upperCase.equals("OAEPWITHSHA3-512ANDMGF1PADDING")) {
                        throw new NoSuchPaddingException(str + " unavailable with ElGamal.");
                    } else {
                        oAEPParameterSpec = new OAEPParameterSpec("SHA3-512", "MGF1", new MGF1ParameterSpec("SHA3-512"), PSource.PSpecified.DEFAULT);
                    }
                }
                initFromSpec(oAEPParameterSpec);
                return;
            }
            oAEPParameterSpec = OAEPParameterSpec.DEFAULT;
            initFromSpec(oAEPParameterSpec);
            return;
        } else {
            iSO9796d1Encoding = new ISO9796d1Encoding(new ElGamalEngine());
        }
        this.cipher = iSO9796d1Encoding;
    }

    @Override // javax.crypto.CipherSpi
    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.bOut.write(bArr, i, i2);
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.bOut.write(bArr, i, i2);
        return null;
    }
}
