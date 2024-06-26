package net.lingala.zip4j.crypto;

import java.util.Random;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Engine;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AESEncrpyter implements Encrypter {
    private static final int PASSWORD_VERIFIER_LENGTH = 2;
    private AESEngine aesEngine;
    private AesKeyStrength aesKeyStrength;
    private byte[] counterBlock;
    private byte[] derivedPasswordVerifier;
    private boolean finished;

    /* renamed from: iv */
    private byte[] f25026iv;
    private MacBasedPRF mac;
    private char[] password;
    private byte[] saltBytes;
    private int nonce = 1;
    private int loopCount = 0;

    public AESEncrpyter(char[] cArr, AesKeyStrength aesKeyStrength) throws ZipException {
        if (cArr == null || cArr.length == 0) {
            throw new ZipException("input password is empty or null");
        }
        if (aesKeyStrength != AesKeyStrength.KEY_STRENGTH_128 && aesKeyStrength != AesKeyStrength.KEY_STRENGTH_256) {
            throw new ZipException("Invalid AES key strength");
        }
        this.password = cArr;
        this.aesKeyStrength = aesKeyStrength;
        this.finished = false;
        this.counterBlock = new byte[16];
        this.f25026iv = new byte[16];
        init();
    }

    private void init() throws ZipException {
        int keyLength = this.aesKeyStrength.getKeyLength();
        int macLength = this.aesKeyStrength.getMacLength();
        this.saltBytes = generateSalt(this.aesKeyStrength.getSaltLength());
        byte[] deriveKey = deriveKey(this.saltBytes, this.password, keyLength, macLength);
        if (deriveKey != null) {
            int i = keyLength + macLength;
            if (deriveKey.length == i + 2) {
                byte[] bArr = new byte[keyLength];
                byte[] bArr2 = new byte[macLength];
                this.derivedPasswordVerifier = new byte[2];
                System.arraycopy(deriveKey, 0, bArr, 0, keyLength);
                System.arraycopy(deriveKey, keyLength, bArr2, 0, macLength);
                System.arraycopy(deriveKey, i, this.derivedPasswordVerifier, 0, 2);
                this.aesEngine = new AESEngine(bArr);
                this.mac = new MacBasedPRF("HmacSHA1");
                this.mac.init(bArr2);
                return;
            }
        }
        throw new ZipException("invalid key generated, cannot decrypt file");
    }

    private byte[] deriveKey(byte[] bArr, char[] cArr, int i, int i2) throws ZipException {
        try {
            return new PBKDF2Engine(new PBKDF2Parameters("HmacSHA1", "ISO-8859-1", bArr, 1000)).deriveKey(cArr, i + i2 + 2);
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    @Override // net.lingala.zip4j.crypto.Encrypter
    public int encryptData(byte[] bArr) throws ZipException {
        if (bArr == null) {
            throw new ZipException("input bytes are null, cannot perform AES encrpytion");
        }
        return encryptData(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.crypto.Encrypter
    public int encryptData(byte[] bArr, int i, int i2) throws ZipException {
        int i3;
        if (this.finished) {
            throw new ZipException("AES Encrypter is in finished state (A non 16 byte block has already been passed to encrypter)");
        }
        if (i2 % 16 != 0) {
            this.finished = true;
        }
        int i4 = i;
        while (true) {
            int i5 = i + i2;
            if (i4 >= i5) {
                return i2;
            }
            int i6 = i4 + 16;
            this.loopCount = i6 <= i5 ? 16 : i5 - i4;
            AesCipherUtil.prepareBuffAESIVBytes(this.f25026iv, this.nonce);
            this.aesEngine.processBlock(this.f25026iv, this.counterBlock);
            int i7 = 0;
            while (true) {
                i3 = this.loopCount;
                if (i7 < i3) {
                    int i8 = i4 + i7;
                    bArr[i8] = (byte) (bArr[i8] ^ this.counterBlock[i7]);
                    i7++;
                }
            }
            this.mac.update(bArr, i4, i3);
            this.nonce++;
            i4 = i6;
        }
    }

    private static byte[] generateSalt(int i) throws ZipException {
        if (i != 8 && i != 16) {
            throw new ZipException("invalid salt size, cannot generate salt");
        }
        int i2 = i == 8 ? 2 : 0;
        if (i == 16) {
            i2 = 4;
        }
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i2; i3++) {
            int nextInt = new Random().nextInt();
            int i4 = i3 * 4;
            bArr[i4 + 0] = (byte) (nextInt >> 24);
            bArr[i4 + 1] = (byte) (nextInt >> 16);
            bArr[i4 + 2] = (byte) (nextInt >> 8);
            bArr[i4 + 3] = (byte) nextInt;
        }
        return bArr;
    }

    public byte[] getFinalMac() {
        byte[] bArr = new byte[10];
        System.arraycopy(this.mac.doFinal(), 0, bArr, 0, 10);
        return bArr;
    }

    public byte[] getDerivedPasswordVerifier() {
        return this.derivedPasswordVerifier;
    }

    public byte[] getSaltBytes() {
        return this.saltBytes;
    }
}
