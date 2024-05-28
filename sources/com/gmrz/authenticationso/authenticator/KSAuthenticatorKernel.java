package com.gmrz.authenticationso.authenticator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import com.android.AKException;
import com.android.client.asm.core.shared.DescriptorLoader;
import com.android.client.asm.sdk.AuthenticatorException;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.android.client.asm.sdk.IMatcher;
import com.android.client.asm.sdk.ProtocolType;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.authenticationso.utils.Configurator;
import com.gmrz.authenticationso.utils.UtilByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.security.auth.x500.X500Principal;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class KSAuthenticatorKernel extends AuthenticatorKernel implements IAuthenticatorKernel {
    public static final int SUCCESS = 0;
    private static final String TAG = "KSAuthenticatorKernel";
    protected static String mAppID;
    protected static Context mContext;
    protected static IAuthenticatorDescriptor mDescriptor;
    protected static boolean mIsAKManagedMatcher;
    protected static IMatcher mMatcher;
    protected static IMatcher.MatcherInParams mMatcherInParams;
    protected boolean isNewAAID;
    protected KeyStore mKeyStore;
    protected String mLabel;
    public int statusCode;

    public abstract byte[] encryptDecryptData(byte[] bArr, byte[] bArr2);

    public abstract byte[] exportPublicKey(byte[] bArr);

    public abstract byte[] generateKeyPair();

    protected abstract String performInitJni(boolean z);

    protected abstract byte[] performProcessJni(byte[] bArr);

    @Override // com.android.client.asm.sdk.IAuthenticatorKernel
    public void setCallerActivity(Activity activity) {
    }

    public abstract byte[] signData(byte[] bArr, byte[] bArr2);

    public KSAuthenticatorKernel() throws AKException {
        this.mKeyStore = null;
        this.statusCode = 0;
        try {
            this.mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            this.mKeyStore.load(null);
            this.isNewAAID = Configurator.isEnableNewAAID(mContext);
            this.mLabel = performInitJni(this.isNewAAID);
            Logger.m15889i(TAG, "Selected HardwareKeyStorePresent: true ,label: " + this.mLabel);
            String str = this.mLabel;
            if (str == null || str.isEmpty()) {
                Logger.m15892e(TAG, "initJni returned null or empty AAID label ");
                throw new AKException("initJni is failed");
            }
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            Logger.m15891e(TAG, "Failed to get KeyStore instance", e);
            throw new AKException("Failed to get KeyStore instance");
        }
    }

    public KSAuthenticatorKernel(Context context, IMatcher iMatcher) throws AKException {
        this.mKeyStore = null;
        this.statusCode = 0;
        if (context == null) {
            Logger.m15892e(TAG, "ctx passing is null");
            return;
        }
        mContext = context;
        this.isNewAAID = Configurator.isEnableNewAAID(context);
        this.mLabel = performInitJni(this.isNewAAID);
        Logger.m15889i(TAG, "Selected HardwareKeyStorePresent: true ,label: " + this.mLabel);
        String str = this.mLabel;
        if (str == null || str.isEmpty()) {
            Logger.m15892e(TAG, "initJni returned null or empty AAID label");
            throw new AKException("initJni is failed");
        } else {
            mMatcher = iMatcher;
        }
    }

    public int getStatus() {
        return this.statusCode;
    }

    @Override // com.gmrz.authenticationso.authenticator.AuthenticatorKernel
    public String getLabel() {
        return this.mLabel;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorKernel
    public boolean postProcess() {
        mAppID = null;
        mDescriptor = null;
        mMatcherInParams = null;
        mMatcher = null;
        mIsAKManagedMatcher = false;
        this.statusCode = 0;
        return true;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @Override // com.gmrz.authenticationso.authenticator.AuthenticatorKernel
    public byte[] processRequestJni(byte[] bArr, Map<IAuthenticatorKernel.AKDataKeys, Object> map) {
        try {
            initAKManagedMatcher(bArr, map);
        } catch (AuthenticatorException e) {
            e.printStackTrace();
        }
        return performProcessJni(bArr);
    }

    protected void initAKManagedMatcher(byte[] bArr, Map<IAuthenticatorKernel.AKDataKeys, Object> map) throws AuthenticatorException {
        if (map != null) {
            Logger.m15895d(TAG, "Begin Initializing AK for AK Managed Matcher");
            try {
                if (map.get(IAuthenticatorKernel.AKDataKeys.APPID) == null) {
                    Logger.m15895d(TAG, "AppID not found");
                    mAppID = "appid0";
                } else {
                    mAppID = new String((byte[]) map.get(IAuthenticatorKernel.AKDataKeys.APPID), Charsets.utf8Charset);
                }
                IAuthenticatorDescriptor iAuthenticatorDescriptor = (IAuthenticatorDescriptor) map.get(IAuthenticatorKernel.AKDataKeys.DESCRIPTOR);
                mDescriptor = iAuthenticatorDescriptor;
                if (iAuthenticatorDescriptor != null) {
                    Logger.m15895d(TAG, "Check AK Managed matcher :" + mDescriptor.isAKManagedMatcher() + " : ");
                }
                if (mDescriptor != null && mDescriptor.isAKManagedMatcher()) {
                    mIsAKManagedMatcher = true;
                    Logger.m15895d(TAG, "Is AKManaged Matcher");
                    IMatcher loadAuthenticatorUIFromClassName = DescriptorLoader.loadAuthenticatorUIFromClassName(mDescriptor.getMatcherClass(), mContext, ProtocolType.UAF);
                    mMatcher = loadAuthenticatorUIFromClassName;
                    if (loadAuthenticatorUIFromClassName == null) {
                        throw new AuthenticatorException("Failed to load Matcher " + mDescriptor.getMatcherClass().toString());
                    }
                    IMatcher.MatcherInParams matcherInParams = (IMatcher.MatcherInParams) map.get(IAuthenticatorKernel.AKDataKeys.MATCHER_IN_PARAMS);
                    mMatcherInParams = matcherInParams;
                    if (matcherInParams == null) {
                        throw new AuthenticatorException("MatcherInParams is null.");
                    }
                    return;
                }
                mMatcher = null;
                mMatcherInParams = null;
                mDescriptor = null;
                Logger.m15895d(TAG, "Is NOT A AKManaged Matcher");
            } catch (AsmException e) {
                throw new AuthenticatorException("loadAuthenticatorUI failed.", e);
            } catch (ClassCastException e2) {
                throw new AuthenticatorException("APPID or Descriptor  or MatcherInParams class is incorrect.", e2);
            }
        }
    }

    public void removeKey(byte[] bArr) {
        Logger.m15889i(TAG, "Begin remove key.");
        keyStoreCryptoUtils.removeKey(new String(bArr, Charsets.utf8Charset));
    }

    public int wrapGetLength(int i) {
        Logger.m15889i(TAG, "wrapGetLength");
        return Build.VERSION.SDK_INT < 23 ? i + 1 : i + 16 + 13;
    }

    public int unwrapGetLength(int i, byte b) {
        Logger.m15889i(TAG, "unwrapGetLength");
        return b == -127 ? i - 1 : b == Byte.MIN_VALUE ? (i - 1) - 12 : i;
    }

    public byte[] wrapObject(byte[] bArr) {
        Logger.m15889i(TAG, "wrapObject");
        if (Build.VERSION.SDK_INT < 23) {
            byte[] bArr2 = new byte[bArr.length + 1];
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            bArr2[0] = -127;
            return bArr2;
        }
        return keyStoreCryptoUtils.wrapObject(bArr);
    }

    public byte[] unwrapObject(byte[] bArr) {
        Logger.m15889i(TAG, "unwrapObject");
        if (bArr[0] == -127) {
            return Arrays.copyOfRange(bArr, 1, bArr.length);
        }
        return bArr[0] == Byte.MIN_VALUE ? keyStoreCryptoUtils.unwrapObject(bArr) : bArr;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class keyStoreCryptoUtils {
        public static final short EC_KEY_SIZE = 32;
        private static SecretKey secretKey;

        private static byte[] copy(byte[] bArr) {
            byte[] bArr2 = new byte[32];
            Arrays.fill(bArr2, (byte) 0);
            if (bArr.length > 32) {
                System.arraycopy(bArr, 1, bArr2, 0, 32);
            } else {
                System.arraycopy(bArr, 0, bArr2, 32 - bArr.length, bArr.length);
            }
            return bArr2;
        }

        private static byte[] signDataWithECDSA(String str, byte[] bArr) {
            Logger.m15889i(KSAuthenticatorKernel.TAG, "signDataWithECDSA");
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                Signature signature = Signature.getInstance("SHA256withECDSA");
                Key key = keyStore.getKey(str, null);
                if (key == null) {
                    Logger.m15892e(KSAuthenticatorKernel.TAG, "Unable to get the signing key by name " + str);
                    return null;
                }
                signature.initSign((PrivateKey) key);
                signature.update(bArr);
                byte[] sign = signature.sign();
                byte b = sign[3];
                if (b > 33) {
                    Logger.m15892e(KSAuthenticatorKernel.TAG, "Invalid ECDSA signature: incorrect length of r");
                    return null;
                }
                int i = b + 4;
                byte[] copy = copy(Arrays.copyOfRange(sign, 4, i));
                byte b2 = sign[i + 1];
                if (b2 > 33) {
                    Logger.m15892e(KSAuthenticatorKernel.TAG, "Invalid ECDSA signature: incorrect length of s");
                    return null;
                }
                int i2 = i + 2;
                byte[] copy2 = copy(Arrays.copyOfRange(sign, i2, b2 + i2));
                byte[] bArr2 = new byte[64];
                System.arraycopy(copy, 0, bArr2, 0, 32);
                System.arraycopy(copy2, 0, bArr2, 32, 32);
                String byte2hex = UtilByte.byte2hex(bArr2);
                Logger.m15895d(KSAuthenticatorKernel.TAG, "Data Signing complete, Signature: " + byte2hex);
                return bArr2;
            } catch (IOException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | SignatureException | UnrecoverableEntryException | CertificateException e) {
                Logger.m15891e(KSAuthenticatorKernel.TAG, "Failed to sign Data with ECDSA", e);
                return null;
            }
        }

        @SuppressLint({"NewApi"})
        private static byte[] generateKeyStoreECDSAKeyPair() {
            Logger.m15889i(KSAuthenticatorKernel.TAG, "generateKeyStoreECDSAKeyPair");
            String uuid = UUID.randomUUID().toString();
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 20);
            try {
                KeyPairGeneratorSpec.Builder builder = new KeyPairGeneratorSpec.Builder(KSAuthenticatorKernel.mContext);
                builder.setAlias(uuid).setSubject(new X500Principal(String.format("CN=%s, OU=%s", uuid, KSAuthenticatorKernel.mContext.getPackageName()))).setSerialNumber(BigInteger.ONE).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime());
                if (Build.VERSION.SDK_INT >= 19) {
                    builder.setKeyType("EC").setKeySize(256);
                    KeyPairGeneratorSpec build = builder.build();
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(Build.VERSION.SDK_INT < 23 ? "RSA" : "EC", "AndroidKeyStore");
                    keyPairGenerator.initialize(build);
                    keyPairGenerator.generateKeyPair();
                    return uuid.getBytes(Charsets.utf8Charset);
                }
                Logger.m15892e(KSAuthenticatorKernel.TAG, "EC algorithm is not supported");
                throw new NoSuchAlgorithmException();
            } catch (IllegalStateException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
                Logger.m15891e(KSAuthenticatorKernel.TAG, "Failed to generate KeyPair, ", e);
                return null;
            }
        }

        @SuppressLint({"NewApi"})
        private static SecretKey getCryptoKey() {
            Logger.m15889i(KSAuthenticatorKernel.TAG, "getCryptoKey");
            SecretKey secretKey2 = secretKey;
            if (secretKey2 != null) {
                return secretKey2;
            }
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                SecretKey secretKey3 = (SecretKey) keyStore.getKey("CalKsCryptoKey", null);
                if (secretKey3 != null) {
                    secretKey = secretKey3;
                } else {
                    KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder("CalKsCryptoKey", 3);
                    builder.setBlockModes("GCM");
                    builder.setEncryptionPaddings("NoPadding");
                    builder.setKeySize(256);
                    KeyGenerator keyGenerator = KeyGenerator.getInstance(C0108a.f85c, "AndroidKeyStore");
                    keyGenerator.init(builder.build());
                    secretKey = keyGenerator.generateKey();
                }
                return secretKey;
            } catch (IOException | InvalidAlgorithmParameterException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableEntryException | CertificateException e) {
                Logger.m15891e(KSAuthenticatorKernel.TAG, "Failed to get crypto key.", e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] wrapObject(byte[] bArr) {
            Logger.m15889i(KSAuthenticatorKernel.TAG, "wrapObject");
            try {
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(1, getCryptoKey());
                byte[] iv = cipher.getIV();
                if (iv == null) {
                    Logger.m15892e(KSAuthenticatorKernel.TAG, "Failed to get IV for encrypt operation");
                    return null;
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(-128);
                byteArrayOutputStream.write(iv);
                byteArrayOutputStream.write(cipher.doFinal(bArr));
                return byteArrayOutputStream.toByteArray();
            } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
                Logger.m15891e(KSAuthenticatorKernel.TAG, "wrapObject failed", e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @SuppressLint({"NewApi"})
        public static byte[] unwrapObject(byte[] bArr) {
            Logger.m15889i(KSAuthenticatorKernel.TAG, "unwrapObject");
            try {
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                SecretKey cryptoKey = getCryptoKey();
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 1, 13);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 13, bArr.length);
                cipher.init(2, cryptoKey, new GCMParameterSpec(128, copyOfRange));
                return cipher.doFinal(copyOfRange2);
            } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
                Logger.m15891e(KSAuthenticatorKernel.TAG, "unwrapObject failed", e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void removeKey(String str) {
            Logger.m15889i(KSAuthenticatorKernel.TAG, "removeKey");
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                if (keyStore.getKey("CalKsCryptoKey", null) != null) {
                    keyStore.deleteEntry("CalKsCryptoKey");
                }
            } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException | CertificateException e) {
                Logger.m15891e(KSAuthenticatorKernel.TAG, "Failed to get crypto key.", e);
            }
        }
    }
}
