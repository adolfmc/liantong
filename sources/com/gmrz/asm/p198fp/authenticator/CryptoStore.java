package com.gmrz.asm.p198fp.authenticator;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.appsdk.util.FingerprintSetUtil;
import com.gmrz.appsdk.util.KeyStoreChecker;
import com.gmrz.authenticationso.utils.UtilByte;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.authenticator.CryptoStore */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CryptoStore {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BASE64_FLAG = 27;
    private static final String CIPHER_TRANSFORMATION_RSA = "RSA/ECB/PKCS1Padding";
    private static final String KEYSTORE_NAME_ANDROID = "AndroidKeyStore";
    private static final String KEYSTORE_NAME_HUAWEI_UNIVERSAL = "HwKeystore";
    @TargetApi(23)
    private static final int KEY_PURPOSE_RSA = 3;
    private static final String PROVIDER_NAME_ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String PROVIDER_NAME_HUAWEI_UNIVERSAL_KEYSTORE = "HwUniversalKeyStoreProvider";
    private static final String SIGNATURE_SHA256_ECDSA = "SHA256withECDSA";
    private static final String TAG = "CryptoStore";

    public static boolean generateKeyPairECDSA(Context context, String str) {
        Logger.m15895d("CryptoStore", "ECDSA keypair generation Begin");
        if (Build.VERSION.SDK_INT < 23) {
            Logger.m15892e("CryptoStore", "ECDSA keypair generation need above Android M. can not continue");
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 20);
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 4).setDigests("SHA-256").setAlgorithmParameterSpec(new ECGenParameterSpec("prime256v1")).setCertificateSubject(new X500Principal(String.format("CN=%s, OU=%s", str, context.getPackageName()))).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).setUserAuthenticationRequired(true).build());
            keyPairGenerator.generateKeyPair();
            Logger.m15895d("CryptoStore", "Algorithm used to generate: " + keyPairGenerator.getAlgorithm());
            Logger.m15895d("CryptoStore", "ECDSA Key generation complete");
            return true;
        } catch (Exception e) {
            Logger.m15892e("CryptoStore", "ECDSA Key generation failed , reason:" + e.getMessage());
            return false;
        }
    }

    public static boolean generateKeyPairECDSA(Context context, String str, byte[] bArr) {
        Logger.m15895d("CryptoStore", "ECDSA Key generation Begin");
        Logger.m15895d("CryptoStore", "ECDSA Key generation Begin - fc: " + Base64.encodeToString(bArr, 2));
        if (Build.VERSION.SDK_INT < 24) {
            Logger.m15892e("CryptoStore", "ECDSA key attestation keypair generation need above Android N. can not continue");
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 20);
        try {
            KeyPairGenerator loadKeyPairGenerator = loadKeyPairGenerator(context, "EC");
            loadKeyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 4).setDigests("SHA-256").setAlgorithmParameterSpec(new ECGenParameterSpec("prime256v1")).setCertificateSubject(new X500Principal(String.format("CN=%s, OU=%s", str, context.getPackageName()))).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).setUserAuthenticationRequired(true).setAttestationChallenge(bArr).build());
            loadKeyPairGenerator.generateKeyPair();
            Logger.m15895d("CryptoStore", "Algorithm used to generate: " + loadKeyPairGenerator.getAlgorithm());
            Logger.m15895d("CryptoStore", "ECDSA Key generation complete");
            return true;
        } catch (ProviderException e) {
            Logger.m15892e("CryptoStore", "ECDSA Key generation failed , reason:" + e.getMessage());
            Logger.m15892e("CryptoStore", "try build without fc");
            return false;
        } catch (Exception e2) {
            Logger.m15892e("CryptoStore", "ECDSA Key generation failed , reason:" + e2.getMessage());
            return false;
        }
    }

    public static byte[] getFpsPublicKeyECDSA(Context context, String str) throws Exception {
        return getFpsPublicKeyECDSA(context, str, false);
    }

    public static byte[] getFpsPublicKeyECDSA(Context context, String str, boolean z) throws Exception {
        KeyStore loadKeyStore;
        if (z) {
            Logger.m15895d("CryptoStore", "forced use Android Google KeyStore");
            loadKeyStore = KeyStore.getInstance("AndroidKeyStore");
            loadKeyStore.load(null);
        } else {
            loadKeyStore = loadKeyStore(context);
        }
        Certificate certificate = loadKeyStore.getCertificate(str);
        if (certificate == null || certificate.getPublicKey() == null) {
            throw new Exception("Unable to get the certificate by name " + str);
        }
        ECPublicKey eCPublicKey = (ECPublicKey) certificate.getPublicKey();
        byte[] byteArray = eCPublicKey.getW().getAffineX().toByteArray();
        if (byteArray.length > 33) {
            Logger.m15892e("CryptoStore", "Export EC public key failed: Incorrect length of x");
            throw new Exception("Export EC public key failed: Incorrect length of x");
        }
        byte[] byteArray2 = eCPublicKey.getW().getAffineY().toByteArray();
        if (byteArray2.length > 33) {
            Logger.m15892e("CryptoStore", "Export EC public key failed: Incorrect length of y");
            throw new Exception("Export EC public key failed: Incorrect length of y");
        }
        ByteBuffer allocate = ByteBuffer.allocate(68);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putShort((short) 32);
        allocate.put(getRawData(byteArray));
        allocate.putShort((short) 32);
        allocate.put(getRawData(byteArray2));
        return allocate.array();
    }

    public static Signature initSignatureECDSA(Context context, String str) throws Exception {
        return initSignatureECDSA(context, str, false);
    }

    public static Signature initSignatureECDSA(Context context, String str, boolean z) throws Exception {
        KeyStore loadKeyStore;
        Signature loadSignature;
        if (z) {
            Logger.m15895d("CryptoStore", "forced use Android Google KeyStore");
            loadKeyStore = KeyStore.getInstance("AndroidKeyStore");
            loadKeyStore.load(null);
        } else {
            loadKeyStore = loadKeyStore(context);
        }
        Key key = loadKeyStore.getKey(str, null);
        if (key == null) {
            Logger.m15892e("CryptoStore", "Failed to get key entry for uuid " + str);
            return null;
        }
        if (z) {
            loadSignature = Signature.getInstance("SHA256withECDSA");
        } else {
            loadSignature = loadSignature(context);
        }
        loadSignature.initSign((PrivateKey) key);
        Logger.m15895d("CryptoStore", "signature init sign successfully");
        return loadSignature;
    }

    public static byte[] packageSignedDataECDSA(byte[] bArr) throws Exception {
        Logger.m15889i("CryptoStore", "packageKsEcdsaSignedData");
        byte b = bArr[3];
        if (b > 33) {
            Logger.m15892e("CryptoStore", "Invalid ECDSA signature: incorrect length of r");
            throw new Exception("Invalid ECDSA signature: incorrect length of r");
        }
        int i = b + 4;
        byte[] rawData = getRawData(Arrays.copyOfRange(bArr, 4, i));
        byte b2 = bArr[i + 1];
        if (b2 > 33) {
            Logger.m15892e("CryptoStore", "Invalid ECDSA signature: incorrect length of s");
            throw new Exception("Invalid ECDSA signature: incorrect length of s");
        }
        int i2 = i + 2;
        byte[] rawData2 = getRawData(Arrays.copyOfRange(bArr, i2, b2 + i2));
        byte[] bArr2 = new byte[rawData.length + rawData2.length];
        System.arraycopy(rawData, 0, bArr2, 0, rawData.length);
        System.arraycopy(rawData2, 0, bArr2, rawData.length, rawData2.length);
        String byte2hex = UtilByte.byte2hex(bArr2);
        Logger.m15895d("CryptoStore", "Data Signing complete , len = " + bArr2.length + "   Signature: " + byte2hex);
        return bArr2;
    }

    public static boolean generateKeyPairRSA(Context context, String str, byte[] bArr) {
        Logger.m15895d("CryptoStore", "generate RSA keypair 2048 length - SM2 protection key");
        if (Build.VERSION.SDK_INT < 24) {
            Logger.m15892e("CryptoStore", "RSA key attestation keypair generation need above Android N. can not continue");
            return false;
        }
        try {
            KeyPairGenerator loadKeyPairGenerator = loadKeyPairGenerator(context, "RSA");
            loadKeyPairGenerator.initialize(new KeyGenParameterSpec.Builder(str, 3).setDigests("SHA-256").setBlockModes("ECB").setEncryptionPaddings("PKCS1Padding").setUserAuthenticationRequired(true).setAttestationChallenge(bArr).setKeySize(2048).build());
            loadKeyPairGenerator.generateKeyPair();
            Logger.m15895d("CryptoStore", "RSA Keypair generation successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Cipher initCipherRSA(Context context, String str, int i) throws Exception {
        Logger.m15895d("CryptoStore", "init RSA cipher. encrypt or decrypt data with it");
        KeyStore loadKeyStore = loadKeyStore(context);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        if (1 == i) {
            Logger.m15895d("CryptoStore", "cipher in RSA encrypt mode");
            PublicKey publicKey = loadKeyStore.getCertificate(str).getPublicKey();
            if (publicKey == null) {
                Logger.m15892e("CryptoStore", "Failed to get pub key for uuid " + str);
                return null;
            }
            cipher.init(1, publicKey);
        } else if (2 == i) {
            Logger.m15895d("CryptoStore", "cipher in RSA decrypt mode");
            KeyStore.Entry entry = loadKeyStore.getEntry(str, null);
            if (entry == null) {
                Logger.m15892e("CryptoStore", "Failed to get key entry for uuid " + str);
                return null;
            }
            cipher.init(2, ((KeyStore.PrivateKeyEntry) entry).getPrivateKey());
        }
        Logger.m15895d("CryptoStore", "cipher init successfully");
        return cipher;
    }

    public static String exportKeyAttestation(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT < 24) {
            sb.append("p");
            return sb.toString();
        }
        try {
            Certificate[] certificateChain = loadKeyStore(context).getCertificateChain(str);
            String[] strArr = new String[certificateChain.length];
            int i = 0;
            for (Certificate certificate : certificateChain) {
                strArr[i] = Base64.encodeToString(certificate.getEncoded(), 27);
                i++;
            }
            sb.append(new JSONArray(strArr));
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            sb.append("a");
            return sb.toString();
        }
    }

    public static void removeKey(Context context, String str) throws Exception {
        removeKey(context, str, false);
    }

    public static void removeKey(Context context, String str, boolean z) throws Exception {
        KeyStore loadKeyStore;
        Logger.m15889i("CryptoStore", "removeKey");
        if (z) {
            loadKeyStore = KeyStore.getInstance("AndroidKeyStore");
            loadKeyStore.load(null);
        } else {
            loadKeyStore = loadKeyStore(context);
        }
        if (loadKeyStore == null) {
            throw new IllegalAccessException("keystore instance is null");
        }
        loadKeyStore.deleteEntry(str);
        Logger.m15889i("CryptoStore", "Successfully removed the key from KeyStore");
        resetFpSetCheckerHuawei(context, str);
    }

    public static KeyStore loadKeyStore(Context context) throws Exception {
        KeyStore keyStore;
        if (KeyStoreChecker.getInstance(context).isGoogleKeyStoreAvailable().booleanValue()) {
            Logger.m15889i("CryptoStore", "-- load Android GoogleKeystore --");
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } else {
            Logger.m15889i("CryptoStore", "-- load Huawei HwUniversalKeystore --");
            KeyStoreChecker.getInstance(context).prepareHuaweiKeystore();
            keyStore = KeyStore.getInstance("HwKeystore");
        }
        if (keyStore != null) {
            keyStore.load(null);
        }
        return keyStore;
    }

    private static KeyPairGenerator loadKeyPairGenerator(Context context, String str) throws Exception {
        if (KeyStoreChecker.getInstance(context).isGoogleKeyStoreAvailable().booleanValue()) {
            Logger.m15895d("CryptoStore", "-- keypair generator with Android GoogleKeystore --");
            return KeyPairGenerator.getInstance(str, "AndroidKeyStore");
        }
        Logger.m15895d("CryptoStore", "-- keypair generator with Huawei HwUniversalKeystore --");
        return KeyPairGenerator.getInstance(str, "HwUniversalKeyStoreProvider");
    }

    private static Signature loadSignature(Context context) throws Exception {
        if (KeyStoreChecker.getInstance(context).isGoogleKeyStoreAvailable().booleanValue()) {
            return Signature.getInstance("SHA256withECDSA");
        }
        return Signature.getInstance("SHA256withECDSA", "HwUniversalKeyStoreProvider");
    }

    protected static byte[] getRawData(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        Arrays.fill(bArr2, (byte) 0);
        if (bArr.length > 32) {
            System.arraycopy(bArr, 1, bArr2, 0, bArr2.length);
        } else {
            System.arraycopy(bArr, 0, bArr2, bArr2.length - bArr.length, bArr.length);
        }
        return bArr2;
    }

    public static boolean isFpSetChangedHuawei(Context context, String str) throws Exception {
        if (KeyStoreChecker.getInstance(context).isGoogleKeyStoreAvailable().booleanValue()) {
            return false;
        }
        Logger.m15895d("CryptoStore", "fingerprint set checker working on Huawei Universal Keystore. fp set checking.");
        return FingerprintSetUtil.getOurInstance().isFingerprintSetChanged(context, str);
    }

    public static void resetFpSetCheckerHuawei(Context context, String str) throws Exception {
        if (KeyStoreChecker.getInstance(context).isHuaweiKeyStoreAvailable().booleanValue()) {
            FingerprintSetUtil.getOurInstance().deleteCheckerKeyPair(str);
            Logger.m15895d("CryptoStore", "fingerprint set checker working on Huawei Universal Keystore. cleaning data.");
        }
    }
}
