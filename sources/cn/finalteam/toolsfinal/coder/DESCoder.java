package cn.finalteam.toolsfinal.coder;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DESCoder {
    public static byte[] encrypt(byte[] bArr, String str) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, generateSecret, secureRandom);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] decrypt(byte[] bArr, String str) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, generateSecret, secureRandom);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
