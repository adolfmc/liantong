package com.android.client.asm.core.uaf;

import android.annotation.SuppressLint;
import com.utils.sm4.SM4;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EncodeUtil {
    @SuppressLint({"GetInstance"})
    public static byte[] aesEncrypt(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, C0108a.f85c);
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(1, secretKeySpec);
            return cipher.doFinal(bArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getSHA256(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sm4Encrypt(byte[] bArr, byte[] bArr2) {
        return new SM4(bArr).encrypt(bArr2);
    }
}
