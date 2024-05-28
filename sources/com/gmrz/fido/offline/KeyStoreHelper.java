package com.gmrz.fido.offline;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Log;
import com.gmrz.appsdk.util.Logger;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* renamed from: com.gmrz.fido.offline.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class KeyStoreHelper {

    /* renamed from: b */
    private static String f10365b;

    /* renamed from: c */
    private static KeyStoreHelper f10366c;

    /* renamed from: a */
    private final KeyStore f10367a = KeyStore.getInstance("AndroidKeyStore");

    private KeyStoreHelper() {
    }

    /* renamed from: a */
    public static KeyStoreHelper m15716a(String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            f10365b = str;
            if (f10366c == null) {
                f10366c = new KeyStoreHelper();
            }
            return f10366c;
        }
        throw new Exception("symmetric encryption need use Android KeyStore can not run below version M");
    }

    /* renamed from: a */
    public boolean m15718a() {
        if (Build.VERSION.SDK_INT < 23) {
            Logger.wtf("KeyStoreHelper", "generate aes secret key use Android KeyStore can not run below version M");
            return false;
        } else if (this.f10367a == null) {
            Logger.wtf("KeyStoreHelper", "keystore instance is null and invoke get instance first");
            return false;
        } else if (TextUtils.isEmpty(f10365b)) {
            Logger.wtf("KeyStoreHelper", "sCacheFileName as Android KeyStore key alias name can not be null");
            return false;
        } else {
            try {
                this.f10367a.load(null);
                KeyGenerator keyGenerator = KeyGenerator.getInstance(C0108a.f85c, "AndroidKeyStore");
                KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(f10365b, 3);
                builder.setBlockModes("CBC");
                builder.setEncryptionPaddings("PKCS7Padding");
                keyGenerator.init(builder.build());
                keyGenerator.generateKey();
                Log.i("KeyStoreHelper", "aes secret key generate complete");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /* renamed from: a */
    public Cipher m15717a(int i, byte[] bArr) {
        if (Build.VERSION.SDK_INT < 23) {
            Logger.wtf("KeyStoreHelper", "generate aes secret key use Android KeyStore can not run below version M");
            return null;
        } else if (this.f10367a == null) {
            Logger.wtf("KeyStoreHelper", "keystore instance is null and invoke get instance first");
            return null;
        } else if (TextUtils.isEmpty(f10365b)) {
            Logger.wtf("KeyStoreHelper", "sCacheFileName as Android KeyStore key alias name can not be null");
            return null;
        } else if (i == 2 && (bArr == null || bArr.length == 0)) {
            Logger.wtf("KeyStoreHelper", "decrypt mode need IV and IV can not be null");
            return null;
        } else {
            try {
                this.f10367a.load(null);
                SecretKey secretKey = (SecretKey) this.f10367a.getKey(f10365b, null);
                if (secretKey == null) {
                    Logger.wtf("KeyStoreHelper", "keystore correspond key alias name to get aes secret key failed");
                    return null;
                }
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                if (i == 1) {
                    cipher.init(1, secretKey);
                } else if (i == 2) {
                    cipher.init(2, secretKey, new IvParameterSpec(bArr));
                }
                return cipher;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
