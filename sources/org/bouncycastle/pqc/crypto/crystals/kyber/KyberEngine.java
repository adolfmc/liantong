package org.bouncycastle.pqc.crypto.crystals.kyber;

import java.security.SecureRandom;
import org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric;
import org.bouncycastle.util.Arrays;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KyberEngine {
    private static final int KyberEta2 = 2;
    private static final int KyberIndCpaMsgBytes = 32;
    public static final int KyberN = 256;
    public static final int KyberPolyBytes = 384;
    public static final int KyberQ = 3329;
    public static final int KyberQinv = 62209;
    private static final int KyberSharedSecretBytes = 32;
    public static final int KyberSymBytes = 32;
    private final int CryptoBytes;
    private final int CryptoCipherTextBytes;
    private final int CryptoPublicKeyBytes;
    private final int CryptoSecretKeyBytes;
    private final int KyberCipherTextBytes;
    private final int KyberEta1;
    private final int KyberIndCpaBytes;
    private final int KyberIndCpaPublicKeyBytes;
    private final int KyberIndCpaSecretKeyBytes;
    private final int KyberK;
    private final int KyberPolyCompressedBytes;
    private final int KyberPolyVecBytes;
    private final int KyberPolyVecCompressedBytes;
    private final int KyberPublicKeyBytes;
    private final int KyberSecretKeyBytes;
    private KyberIndCpa indCpa;
    private SecureRandom random;
    private final int sessionKeyLength;
    private final Symmetric symmetric;

    public KyberEngine(int i, boolean z) {
        int i2;
        this.KyberK = i;
        switch (i) {
            case 2:
                this.KyberEta1 = 3;
                this.KyberPolyCompressedBytes = 128;
                this.KyberPolyVecCompressedBytes = i * 320;
                i2 = 16;
                this.sessionKeyLength = i2;
                break;
            case 3:
                this.KyberEta1 = 2;
                this.KyberPolyCompressedBytes = 128;
                this.KyberPolyVecCompressedBytes = i * 320;
                i2 = 24;
                this.sessionKeyLength = i2;
                break;
            case 4:
                this.KyberEta1 = 2;
                this.KyberPolyCompressedBytes = C0567f.f1819h;
                this.KyberPolyVecCompressedBytes = i * 352;
                this.sessionKeyLength = 32;
                break;
            default:
                throw new IllegalArgumentException("K: " + i + " is not supported for Crystals Kyber");
        }
        this.KyberPolyVecBytes = i * 384;
        int i3 = this.KyberPolyVecBytes;
        this.KyberIndCpaPublicKeyBytes = i3 + 32;
        this.KyberIndCpaSecretKeyBytes = i3;
        this.KyberIndCpaBytes = this.KyberPolyVecCompressedBytes + this.KyberPolyCompressedBytes;
        int i4 = this.KyberIndCpaPublicKeyBytes;
        this.KyberPublicKeyBytes = i4;
        this.KyberSecretKeyBytes = this.KyberIndCpaSecretKeyBytes + i4 + 64;
        this.KyberCipherTextBytes = this.KyberIndCpaBytes;
        this.CryptoBytes = 32;
        this.CryptoSecretKeyBytes = this.KyberSecretKeyBytes;
        this.CryptoPublicKeyBytes = this.KyberPublicKeyBytes;
        this.CryptoCipherTextBytes = this.KyberCipherTextBytes;
        this.symmetric = z ? new Symmetric.AesSymmetric() : new Symmetric.ShakeSymmetric();
        this.indCpa = new KyberIndCpa(this);
    }

    private void cmov(byte[] bArr, byte[] bArr2, int i, boolean z) {
        if (z) {
            System.arraycopy(bArr2, 0, bArr, 0, i);
        } else {
            System.arraycopy(bArr, 0, bArr, 0, i);
        }
    }

    public static int getKyberEta2() {
        return 2;
    }

    public static int getKyberIndCpaMsgBytes() {
        return 32;
    }

    public byte[][] generateKemKeyPair() {
        byte[][] generateKeyPair = this.indCpa.generateKeyPair();
        int i = this.KyberIndCpaSecretKeyBytes;
        byte[] bArr = new byte[i];
        System.arraycopy(generateKeyPair[1], 0, bArr, 0, i);
        byte[] bArr2 = new byte[32];
        this.symmetric.hash_h(bArr2, generateKeyPair[0], 0);
        byte[] bArr3 = new byte[32];
        this.random.nextBytes(bArr3);
        int i2 = this.KyberIndCpaPublicKeyBytes;
        byte[] bArr4 = new byte[i2];
        System.arraycopy(generateKeyPair[0], 0, bArr4, 0, i2);
        return new byte[][]{Arrays.copyOfRange(bArr4, 0, bArr4.length - 32), Arrays.copyOfRange(bArr4, bArr4.length - 32, bArr4.length), bArr, bArr2, bArr3};
    }

    public int getCryptoBytes() {
        return this.CryptoBytes;
    }

    public int getCryptoCipherTextBytes() {
        return this.CryptoCipherTextBytes;
    }

    public int getCryptoPublicKeyBytes() {
        return this.CryptoPublicKeyBytes;
    }

    public int getCryptoSecretKeyBytes() {
        return this.CryptoSecretKeyBytes;
    }

    public int getKyberCipherTextBytes() {
        return this.KyberCipherTextBytes;
    }

    public int getKyberEta1() {
        return this.KyberEta1;
    }

    public int getKyberIndCpaBytes() {
        return this.KyberIndCpaBytes;
    }

    public int getKyberIndCpaPublicKeyBytes() {
        return this.KyberIndCpaPublicKeyBytes;
    }

    public int getKyberIndCpaSecretKeyBytes() {
        return this.KyberIndCpaSecretKeyBytes;
    }

    public int getKyberK() {
        return this.KyberK;
    }

    public int getKyberPolyCompressedBytes() {
        return this.KyberPolyCompressedBytes;
    }

    public int getKyberPolyVecBytes() {
        return this.KyberPolyVecBytes;
    }

    public int getKyberPolyVecCompressedBytes() {
        return this.KyberPolyVecCompressedBytes;
    }

    public int getKyberPublicKeyBytes() {
        return this.KyberPublicKeyBytes;
    }

    public int getKyberSecretKeyBytes() {
        return this.KyberSecretKeyBytes;
    }

    public void getRandomBytes(byte[] bArr) {
        this.random.nextBytes(bArr);
    }

    public Symmetric getSymmetric() {
        return this.symmetric;
    }

    public void init(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public byte[] kemDecrypt(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[64];
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, this.KyberIndCpaSecretKeyBytes, bArr2.length);
        System.arraycopy(this.indCpa.decrypt(bArr, bArr2), 0, bArr3, 0, 32);
        System.arraycopy(bArr2, this.KyberSecretKeyBytes - 64, bArr3, 32, 32);
        this.symmetric.hash_g(bArr4, bArr3);
        this.symmetric.hash_h(bArr4, bArr, 32);
        int i = this.KyberSecretKeyBytes;
        cmov(bArr4, Arrays.copyOfRange(bArr2, i - 32, i), 32, !Arrays.constantTimeAreEqual(bArr, this.indCpa.encrypt(Arrays.copyOfRange(bArr3, 0, 32), copyOfRange, Arrays.copyOfRange(bArr4, 32, bArr4.length))));
        byte[] bArr5 = new byte[this.sessionKeyLength];
        this.symmetric.kdf(bArr5, bArr4);
        return bArr5;
    }

    public byte[][] kemEncrypt(byte[] bArr) {
        byte[] bArr2 = new byte[64];
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[32];
        this.random.nextBytes(bArr4);
        this.symmetric.hash_h(bArr4, bArr4, 0);
        System.arraycopy(bArr4, 0, bArr2, 0, 32);
        this.symmetric.hash_h(bArr2, bArr, 32);
        this.symmetric.hash_g(bArr3, bArr2);
        byte[] encrypt = this.indCpa.encrypt(Arrays.copyOfRange(bArr2, 0, 32), bArr, Arrays.copyOfRange(bArr3, 32, bArr3.length));
        this.symmetric.hash_h(bArr3, encrypt, 32);
        byte[] bArr5 = new byte[this.sessionKeyLength];
        this.symmetric.kdf(bArr5, bArr3);
        return new byte[][]{bArr5, encrypt};
    }
}
