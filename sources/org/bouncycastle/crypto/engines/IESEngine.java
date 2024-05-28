package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.IESParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class IESEngine {

    /* renamed from: IV */
    private byte[] f26621IV;

    /* renamed from: V */
    byte[] f26622V;
    BasicAgreement agree;
    BufferedBlockCipher cipher;
    boolean forEncryption;
    DerivationFunction kdf;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    Mac mac;
    byte[] macBuf;
    IESParameters param;
    CipherParameters privParam;
    CipherParameters pubParam;

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = null;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = bufferedBlockCipher;
    }

    private byte[] decryptBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        int processBytes;
        if (i2 >= this.f26622V.length + this.mac.getMacSize()) {
            if (this.cipher == null) {
                byte[] bArr4 = new byte[(i2 - this.f26622V.length) - this.mac.getMacSize()];
                bArr2 = new byte[this.param.getMacKeySize() / 8];
                byte[] bArr5 = new byte[bArr4.length + bArr2.length];
                this.kdf.generateBytes(bArr5, 0, bArr5.length);
                if (this.f26622V.length != 0) {
                    System.arraycopy(bArr5, 0, bArr2, 0, bArr2.length);
                    System.arraycopy(bArr5, bArr2.length, bArr4, 0, bArr4.length);
                } else {
                    System.arraycopy(bArr5, 0, bArr4, 0, bArr4.length);
                    System.arraycopy(bArr5, bArr4.length, bArr2, 0, bArr2.length);
                }
                byte[] bArr6 = new byte[bArr4.length];
                for (int i3 = 0; i3 != bArr4.length; i3++) {
                    bArr6[i3] = (byte) (bArr[(this.f26622V.length + i) + i3] ^ bArr4[i3]);
                }
                bArr3 = bArr6;
                processBytes = 0;
            } else {
                byte[] bArr7 = new byte[((IESWithCipherParameters) this.param).getCipherKeySize() / 8];
                bArr2 = new byte[this.param.getMacKeySize() / 8];
                byte[] bArr8 = new byte[bArr7.length + bArr2.length];
                this.kdf.generateBytes(bArr8, 0, bArr8.length);
                System.arraycopy(bArr8, 0, bArr7, 0, bArr7.length);
                System.arraycopy(bArr8, bArr7.length, bArr2, 0, bArr2.length);
                CipherParameters keyParameter = new KeyParameter(bArr7);
                byte[] bArr9 = this.f26621IV;
                if (bArr9 != null) {
                    keyParameter = new ParametersWithIV(keyParameter, bArr9);
                }
                this.cipher.init(false, keyParameter);
                bArr3 = new byte[this.cipher.getOutputSize((i2 - this.f26622V.length) - this.mac.getMacSize())];
                BufferedBlockCipher bufferedBlockCipher = this.cipher;
                byte[] bArr10 = this.f26622V;
                processBytes = bufferedBlockCipher.processBytes(bArr, bArr10.length + i, (i2 - bArr10.length) - this.mac.getMacSize(), bArr3, 0);
            }
            byte[] encodingV = this.param.getEncodingV();
            byte[] lengthTag = this.f26622V.length != 0 ? getLengthTag(encodingV) : null;
            int i4 = i + i2;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i4 - this.mac.getMacSize(), i4);
            byte[] bArr11 = new byte[copyOfRange.length];
            this.mac.init(new KeyParameter(bArr2));
            Mac mac = this.mac;
            byte[] bArr12 = this.f26622V;
            mac.update(bArr, i + bArr12.length, (i2 - bArr12.length) - bArr11.length);
            if (encodingV != null) {
                this.mac.update(encodingV, 0, encodingV.length);
            }
            if (this.f26622V.length != 0) {
                this.mac.update(lengthTag, 0, lengthTag.length);
            }
            this.mac.doFinal(bArr11, 0);
            if (Arrays.constantTimeAreEqual(copyOfRange, bArr11)) {
                BufferedBlockCipher bufferedBlockCipher2 = this.cipher;
                return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, processBytes + bufferedBlockCipher2.doFinal(bArr3, processBytes));
            }
            throw new InvalidCipherTextException("invalid MAC");
        }
        throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
    }

    private byte[] encryptBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        BufferedBlockCipher bufferedBlockCipher;
        CipherParameters keyParameter;
        byte[] bArr3;
        if (this.cipher == null) {
            byte[] bArr4 = new byte[i2];
            bArr2 = new byte[this.param.getMacKeySize() / 8];
            byte[] bArr5 = new byte[bArr4.length + bArr2.length];
            this.kdf.generateBytes(bArr5, 0, bArr5.length);
            if (this.f26622V.length != 0) {
                System.arraycopy(bArr5, 0, bArr2, 0, bArr2.length);
                System.arraycopy(bArr5, bArr2.length, bArr4, 0, bArr4.length);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, bArr4.length);
                System.arraycopy(bArr5, i2, bArr2, 0, bArr2.length);
            }
            byte[] bArr6 = new byte[i2];
            for (int i3 = 0; i3 != i2; i3++) {
                bArr6[i3] = (byte) (bArr[i + i3] ^ bArr4[i3]);
            }
            bArr3 = bArr6;
        } else {
            byte[] bArr7 = new byte[((IESWithCipherParameters) this.param).getCipherKeySize() / 8];
            bArr2 = new byte[this.param.getMacKeySize() / 8];
            byte[] bArr8 = new byte[bArr7.length + bArr2.length];
            this.kdf.generateBytes(bArr8, 0, bArr8.length);
            System.arraycopy(bArr8, 0, bArr7, 0, bArr7.length);
            System.arraycopy(bArr8, bArr7.length, bArr2, 0, bArr2.length);
            if (this.f26621IV != null) {
                bufferedBlockCipher = this.cipher;
                keyParameter = new ParametersWithIV(new KeyParameter(bArr7), this.f26621IV);
            } else {
                bufferedBlockCipher = this.cipher;
                keyParameter = new KeyParameter(bArr7);
            }
            bufferedBlockCipher.init(true, keyParameter);
            bArr3 = new byte[this.cipher.getOutputSize(i2)];
            int processBytes = this.cipher.processBytes(bArr, i, i2, bArr3, 0);
            i2 = processBytes + this.cipher.doFinal(bArr3, processBytes);
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] lengthTag = this.f26622V.length != 0 ? getLengthTag(encodingV) : null;
        byte[] bArr9 = new byte[this.mac.getMacSize()];
        this.mac.init(new KeyParameter(bArr2));
        this.mac.update(bArr3, 0, bArr3.length);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.f26622V.length != 0) {
            this.mac.update(lengthTag, 0, lengthTag.length);
        }
        this.mac.doFinal(bArr9, 0);
        byte[] bArr10 = this.f26622V;
        byte[] bArr11 = new byte[bArr10.length + i2 + bArr9.length];
        System.arraycopy(bArr10, 0, bArr11, 0, bArr10.length);
        System.arraycopy(bArr3, 0, bArr11, this.f26622V.length, i2);
        System.arraycopy(bArr9, 0, bArr11, this.f26622V.length + i2, bArr9.length);
        return bArr11;
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f26621IV = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            this.f26621IV = null;
        }
        this.param = (IESParameters) cipherParameters;
    }

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    protected byte[] getLengthTag(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(bArr.length * 8, bArr2, 0);
        }
        return bArr2;
    }

    public Mac getMac() {
        return this.mac;
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.forEncryption = false;
        this.privParam = asymmetricKeyParameter;
        this.keyParser = keyParser;
        extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.forEncryption = z;
        this.privParam = cipherParameters;
        this.pubParam = cipherParameters2;
        this.f26622V = new byte[0];
        extractParams(cipherParameters3);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ad A[Catch: all -> 0x00ba, TryCatch #2 {all -> 0x00ba, blocks: (B:22:0x0099, B:24:0x00ad, B:25:0x00b2), top: B:31:0x0099 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b2 A[Catch: all -> 0x00ba, TRY_LEAVE, TryCatch #2 {all -> 0x00ba, blocks: (B:22:0x0099, B:24:0x00ad, B:25:0x00b2), top: B:31:0x0099 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] processBlock(byte[] r5, int r6, int r7) throws org.bouncycastle.crypto.InvalidCipherTextException {
        /*
            r4 = this;
            boolean r0 = r4.forEncryption
            if (r0 == 0) goto L1d
            org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator r0 = r4.keyPairGenerator
            if (r0 == 0) goto L72
            org.bouncycastle.crypto.EphemeralKeyPair r0 = r0.generate()
            org.bouncycastle.crypto.AsymmetricCipherKeyPair r1 = r0.getKeyPair()
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r1 = r1.getPrivate()
            r4.privParam = r1
            byte[] r0 = r0.getEncodedPublicKey()
        L1a:
            r4.f26622V = r0
            goto L72
        L1d:
            org.bouncycastle.crypto.KeyParser r0 = r4.keyParser
            if (r0 == 0) goto L72
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r5, r6, r7)
            org.bouncycastle.crypto.KeyParser r1 = r4.keyParser     // Catch: java.lang.IllegalArgumentException -> L3a java.io.IOException -> L56
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r1 = r1.readKey(r0)     // Catch: java.lang.IllegalArgumentException -> L3a java.io.IOException -> L56
            r4.pubParam = r1     // Catch: java.lang.IllegalArgumentException -> L3a java.io.IOException -> L56
            int r0 = r0.available()
            int r0 = r7 - r0
            int r0 = r0 + r6
            byte[] r0 = org.bouncycastle.util.Arrays.copyOfRange(r5, r6, r0)
            goto L1a
        L3a:
            r5 = move-exception
            org.bouncycastle.crypto.InvalidCipherTextException r6 = new org.bouncycastle.crypto.InvalidCipherTextException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "unable to recover ephemeral public key: "
            r7.append(r0)
            java.lang.String r0 = r5.getMessage()
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7, r5)
            throw r6
        L56:
            r5 = move-exception
            org.bouncycastle.crypto.InvalidCipherTextException r6 = new org.bouncycastle.crypto.InvalidCipherTextException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "unable to recover ephemeral public key: "
            r7.append(r0)
            java.lang.String r0 = r5.getMessage()
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7, r5)
            throw r6
        L72:
            org.bouncycastle.crypto.BasicAgreement r0 = r4.agree
            org.bouncycastle.crypto.CipherParameters r1 = r4.privParam
            r0.init(r1)
            org.bouncycastle.crypto.BasicAgreement r0 = r4.agree
            org.bouncycastle.crypto.CipherParameters r1 = r4.pubParam
            java.math.BigInteger r0 = r0.calculateAgreement(r1)
            org.bouncycastle.crypto.BasicAgreement r1 = r4.agree
            int r1 = r1.getFieldSize()
            byte[] r0 = org.bouncycastle.util.BigIntegers.asUnsignedByteArray(r1, r0)
            byte[] r1 = r4.f26622V
            int r2 = r1.length
            r3 = 0
            if (r2 == 0) goto L99
            byte[] r1 = org.bouncycastle.util.Arrays.concatenate(r1, r0)
            org.bouncycastle.util.Arrays.fill(r0, r3)
            r0 = r1
        L99:
            org.bouncycastle.crypto.params.KDFParameters r1 = new org.bouncycastle.crypto.params.KDFParameters     // Catch: java.lang.Throwable -> Lba
            org.bouncycastle.crypto.params.IESParameters r2 = r4.param     // Catch: java.lang.Throwable -> Lba
            byte[] r2 = r2.getDerivationV()     // Catch: java.lang.Throwable -> Lba
            r1.<init>(r0, r2)     // Catch: java.lang.Throwable -> Lba
            org.bouncycastle.crypto.DerivationFunction r2 = r4.kdf     // Catch: java.lang.Throwable -> Lba
            r2.init(r1)     // Catch: java.lang.Throwable -> Lba
            boolean r1 = r4.forEncryption     // Catch: java.lang.Throwable -> Lba
            if (r1 == 0) goto Lb2
            byte[] r5 = r4.encryptBlock(r5, r6, r7)     // Catch: java.lang.Throwable -> Lba
            goto Lb6
        Lb2:
            byte[] r5 = r4.decryptBlock(r5, r6, r7)     // Catch: java.lang.Throwable -> Lba
        Lb6:
            org.bouncycastle.util.Arrays.fill(r0, r3)
            return r5
        Lba:
            r5 = move-exception
            org.bouncycastle.util.Arrays.fill(r0, r3)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.IESEngine.processBlock(byte[], int, int):byte[]");
    }
}
