package cn.finalteam.toolsfinal.coder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DES3Coder {
    private static final String Algorithm = "DESede";

    public static byte[] encryptMode(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(build3DesKey(str), "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(1, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static byte[] decryptMode(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(build3DesKey(str), "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static byte[] build3DesKey(String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[24];
        byte[] bytes = str.getBytes("UTF-8");
        if (bArr.length > bytes.length) {
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        } else {
            System.arraycopy(bytes, 0, bArr, 0, bArr.length);
        }
        return bArr;
    }
}
