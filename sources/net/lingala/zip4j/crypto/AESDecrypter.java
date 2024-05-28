package net.lingala.zip4j.crypto;

import java.util.Arrays;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Engine;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.enums.AesKeyStrength;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AESDecrypter implements Decrypter {
    public static final int PASSWORD_VERIFIER_LENGTH = 2;
    private AESEngine aesEngine;
    private AESExtraDataRecord aesExtraDataRecord;
    private MacBasedPRF mac;
    private char[] password;
    private int nonce = 1;

    /* renamed from: iv */
    private byte[] f25025iv = new byte[16];
    private byte[] counterBlock = new byte[16];

    public AESDecrypter(AESExtraDataRecord aESExtraDataRecord, char[] cArr, byte[] bArr, byte[] bArr2) throws ZipException {
        this.aesExtraDataRecord = aESExtraDataRecord;
        this.password = cArr;
        init(bArr, bArr2);
    }

    private void init(byte[] bArr, byte[] bArr2) throws ZipException {
        AesKeyStrength aesKeyStrength = this.aesExtraDataRecord.getAesKeyStrength();
        char[] cArr = this.password;
        if (cArr == null || cArr.length <= 0) {
            throw new ZipException("empty or null password provided for AES Decryptor");
        }
        byte[] deriveKey = deriveKey(bArr, cArr, aesKeyStrength.getKeyLength(), aesKeyStrength.getMacLength());
        if (deriveKey == null || deriveKey.length != aesKeyStrength.getKeyLength() + aesKeyStrength.getMacLength() + 2) {
            throw new ZipException("invalid derived key");
        }
        byte[] bArr3 = new byte[aesKeyStrength.getKeyLength()];
        byte[] bArr4 = new byte[aesKeyStrength.getMacLength()];
        byte[] bArr5 = new byte[2];
        System.arraycopy(deriveKey, 0, bArr3, 0, aesKeyStrength.getKeyLength());
        System.arraycopy(deriveKey, aesKeyStrength.getKeyLength(), bArr4, 0, aesKeyStrength.getMacLength());
        System.arraycopy(deriveKey, aesKeyStrength.getKeyLength() + aesKeyStrength.getMacLength(), bArr5, 0, 2);
        if (!Arrays.equals(bArr2, bArr5)) {
            throw new ZipException("Wrong Password", ZipException.Type.WRONG_PASSWORD);
        }
        this.aesEngine = new AESEngine(bArr3);
        this.mac = new MacBasedPRF("HmacSHA1");
        this.mac.init(bArr4);
    }

    @Override // net.lingala.zip4j.crypto.Decrypter
    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        int i3 = i;
        while (true) {
            int i4 = i + i2;
            if (i3 >= i4) {
                return i2;
            }
            int i5 = i3 + 16;
            int i6 = i5 <= i4 ? 16 : i4 - i3;
            this.mac.update(bArr, i3, i6);
            AesCipherUtil.prepareBuffAESIVBytes(this.f25025iv, this.nonce);
            this.aesEngine.processBlock(this.f25025iv, this.counterBlock);
            for (int i7 = 0; i7 < i6; i7++) {
                int i8 = i3 + i7;
                bArr[i8] = (byte) (bArr[i8] ^ this.counterBlock[i7]);
            }
            this.nonce++;
            i3 = i5;
        }
    }

    private byte[] deriveKey(byte[] bArr, char[] cArr, int i, int i2) {
        return new PBKDF2Engine(new PBKDF2Parameters("HmacSHA1", "ISO-8859-1", bArr, 1000)).deriveKey(cArr, i + i2 + 2);
    }

    public byte[] getCalculatedAuthenticationBytes() {
        return this.mac.doFinal();
    }
}
