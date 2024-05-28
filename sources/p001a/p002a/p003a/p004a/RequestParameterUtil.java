package p001a.p002a.p003a.p004a;

import android.text.TextUtils;
import android.util.Base64;
import com.baidu.license.SDKHttpConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import javax.crypto.Cipher;
import p001a.p002a.p003a.p057f.Base64Utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.trw */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RequestParameterUtil {
    /* renamed from: a */
    public static HashMap<String, Object> m22368a() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("appId", SDKHttpConfig.appId);
        hashMap.put("sdkVersion", SDKHttpConfig.sdkVersion);
        hashMap.put("packageName", SDKHttpConfig.packageName);
        hashMap.put("platform", "Android");
        return hashMap;
    }

    /* renamed from: b */
    public static boolean m22363b(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    /* renamed from: a */
    public static String m22365a(String str, String str2) {
        byte[] bArr;
        byte[] doFinal;
        byte[] m22354a = Base64Utils.m22354a(str);
        byte[] m22354a2 = Base64Utils.m22354a(str2);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, keyFactory.generatePrivate(new PKCS8EncodedKeySpec(m22354a2)));
        try {
            int length = m22354a.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 <= 0) {
                    break;
                }
                if (i3 > 128) {
                    doFinal = cipher.doFinal(m22354a, i, 128);
                } else {
                    doFinal = cipher.doFinal(m22354a, i, i3);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i2++;
                i = i2 * 128;
            }
            bArr = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception unused) {
            bArr = null;
        }
        return new String(bArr);
    }

    /* renamed from: a */
    public static String m22364a(String str, String str2, String str3) {
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str2, 0)));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(generatePrivate);
            signature.update(str.getBytes(str3));
            return Base64.encodeToString(signature.sign(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m22367a(File file) {
        if (file == null) {
            return true;
        }
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!m22367a(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* renamed from: a */
    public static boolean m22366a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.delete();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
