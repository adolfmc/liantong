package com.gmrz.appsdk.util;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class KeyStoreChecker {
    private static final String CLS_NAME_PROVIDER_HUAWEI_UNIVERSAL_KEYSTORE = "com.huawei.security.keystore.HwUniversalKeyStoreProvider";
    private static final String KEYSTORE_NAME_ANDROID = "AndroidKeyStore";
    private static final String KEYSTORE_NAME_HUAWEI_UNIVERSAL = "HwKeystore";
    private static final String PROVIDER_NAME_ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String PROVIDER_NAME_HUAWEI_UNIVERSAL_KEYSTORE = "HwUniversalKeyStoreProvider";
    private static final String TAG = "FingerprintUtils";
    private static KeyStoreChecker instance;
    private final FingerprintManager fingerprintManager;
    private Boolean mGoogleKeyStoreAvailable = null;
    private Boolean mHuaweiKeyStoreAvailable = null;

    private KeyStoreChecker(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission("android.permission.USE_FINGERPRINT") == 0) {
                this.fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint");
                return;
            }
            throw new Exception("fingerprint permission not granted");
        }
        throw new Exception("device android version below Android 6.0");
    }

    private byte[] genChallenge(int i) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public static KeyStoreChecker getInstance(Context context) {
        if (instance == null) {
            instance = new KeyStoreChecker(context);
        }
        return instance;
    }

    private boolean isGoogleAvailable() {
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        Logger.m15758d(TAG, "google keystore key pair generation test start");
        String uuid = UUID.randomUUID().toString();
        byte[] genChallenge = genChallenge(105);
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(uuid, 4).setDigests("SHA-256").setAlgorithmParameterSpec(new ECGenParameterSpec("prime256v1")).setUserAuthenticationRequired(false).setAttestationChallenge(genChallenge).build());
            keyPairGenerator.generateKeyPair();
            Logger.m15758d(TAG, "google keystore ECC key pair generate testing passed");
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            Logger.m15758d(TAG, "google keystore load testing passed");
            keyStore.getCertificateChain(uuid);
            Logger.m15758d(TAG, "google keystore export certificate chain testing passed");
            if (keyStore.getKey(uuid, null) != null) {
                keyStore.load(null);
                keyStore.deleteEntry(uuid);
                Logger.m15758d(TAG, "google keystore testing key pair deleted");
                return true;
            }
            throw new IOException("google keystore retrieve key failed: pri key instance is null");
        } catch (Exception e) {
            Logger.m15757e(TAG, "google keystore sign testing failure:" + e.getMessage());
            return false;
        }
    }

    private boolean isHuaweiAvailable() {
        Logger.m15758d(TAG, "huawei keystore key pair generation test start");
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        if (!HwChecker.isSupportHwKeystore()) {
            Logger.m15757e(TAG, "device not support huawei universal keystore");
            return false;
        }
        String uuid = UUID.randomUUID().toString();
        byte[] genChallenge = genChallenge(105);
        try {
            prepareHuaweiKeystore();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", PROVIDER_NAME_HUAWEI_UNIVERSAL_KEYSTORE);
            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(uuid, 4).setDigests("SHA-256").setAlgorithmParameterSpec(new ECGenParameterSpec("prime256v1")).setUserAuthenticationRequired(false).setAttestationChallenge(genChallenge).build());
            keyPairGenerator.generateKeyPair();
            Logger.m15758d(TAG, "huawei keystore ECC key pair generate testing passed");
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_NAME_HUAWEI_UNIVERSAL);
            keyStore.load(null);
            Logger.m15758d(TAG, "huawei keystore load testing passed");
            keyStore.getCertificateChain(uuid);
            Logger.m15758d(TAG, "huawei keystore export certificate chain testing passed");
            if (keyStore.getKey(uuid, null) != null) {
                keyStore.load(null);
                keyStore.deleteEntry(uuid);
                Logger.m15758d(TAG, "huawei keystore testing key pair deleted");
                return true;
            }
            throw new IOException("huawei keystore retrieve key failed: pri key instance is null");
        } catch (Exception e) {
            Logger.m15757e(TAG, "huawei keystore sign testing failure:" + e.getMessage());
            return false;
        }
    }

    public Boolean isGoogleKeyStoreAvailable() {
        if (this.mGoogleKeyStoreAvailable == null) {
            this.mGoogleKeyStoreAvailable = Boolean.valueOf(isGoogleAvailable());
        }
        return this.mGoogleKeyStoreAvailable;
    }

    public boolean isHardwareDetected() {
        FingerprintManager fingerprintManager;
        if (Build.VERSION.SDK_INT >= 23 && (fingerprintManager = this.fingerprintManager) != null) {
            return fingerprintManager.isHardwareDetected();
        }
        return false;
    }

    public Boolean isHuaweiKeyStoreAvailable() {
        if (this.mHuaweiKeyStoreAvailable == null) {
            this.mHuaweiKeyStoreAvailable = Boolean.valueOf(isHuaweiAvailable());
        }
        return this.mHuaweiKeyStoreAvailable;
    }

    public void prepareHuaweiKeystore() {
        try {
            Method method = Class.forName(CLS_NAME_PROVIDER_HUAWEI_UNIVERSAL_KEYSTORE).getMethod("install", new Class[0]);
            method.setAccessible(true);
            method.invoke(null, new Object[0]);
        } catch (Exception e) {
            Logger.m15757e(TAG, "HwUniversalKeystore init failed: " + e.getMessage());
        }
    }
}
