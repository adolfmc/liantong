package com.gmrz.appsdk.util;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FingerprintSetUtil {
    private static final String KEYSTORE_NAME_ANDROID = "AndroidKeyStore";
    private static final String SIGNATURE_ALG_SHA256_RSA = "SHA256withRSA";
    private static final String TAG = "FingerprintSet";
    private static FingerprintSetUtil ourInstance;

    private boolean checkFingerprintSetChanged(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_NAME_ANDROID);
            keyStore.load(null);
            Key key = keyStore.getKey(str, null);
            if (key == null) {
                generateKeyPair(str);
                key = keyStore.getKey(str, null);
            }
            if (key != null) {
                Signature.getInstance(SIGNATURE_ALG_SHA256_RSA).initSign((PrivateKey) key);
            }
        } catch (InvalidKeyException | UnrecoverableKeyException unused) {
            Logger.m15757e(TAG, "system enrolled fingerprints set are changed. pri key of signature init exception!!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void generateKeyPair(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", KEYSTORE_NAME_ANDROID);
        keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 4).setDigests("SHA-256").setSignaturePaddings("PKCS1").setUserAuthenticationRequired(true).setKeySize(512).build());
        keyPairGenerator.generateKeyPair();
    }

    public static FingerprintSetUtil getOurInstance() {
        if (ourInstance == null) {
            synchronized (FingerprintSetUtil.class) {
                if (ourInstance == null) {
                    ourInstance = new FingerprintSetUtil();
                }
            }
        }
        return ourInstance;
    }

    public void deleteCheckerKeyPair(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_NAME_ANDROID);
            keyStore.load(null);
            keyStore.deleteEntry(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isFingerprintSetChanged(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.USE_BIOMETRIC") == 0 && context.checkSelfPermission("android.permission.USE_FINGERPRINT") == 0) {
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint");
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                return true;
            }
            if (fingerprintManager.isHardwareDetected()) {
                return checkFingerprintSetChanged(str);
            }
        }
        return false;
    }
}
