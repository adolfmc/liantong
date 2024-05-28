package com.gmrz.appsdk.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.UserNotAuthenticatedException;
import android.text.TextUtils;
import android.util.Log;
import com.gmrz.appsdk.attestation.AuthorizationList;
import com.gmrz.appsdk.attestation.KeyASecurityType;
import com.gmrz.appsdk.attestation.KeyDescription;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;
import javax.security.auth.x500.X500Principal;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FpUtil {
    public static final String KEY_DESCRIPTION_OID = "1.3.6.1.4.1.11129.2.1.17";
    private static final String TAG = "FpUtil";
    static final /* synthetic */ boolean $assertionsDisabled = !FpUtil.class.desiredAssertionStatus();
    public static byte TAG_ASN1_INT = 2;
    public static byte TAG_ASN1_SEQUENCE = 48;
    public static byte TAG_ASN1_OCTETSTRING = 4;
    public static byte TAG_ASN1_NULL = 5;
    public static byte TAG_ASN1_ENUM = 10;
    public static byte TAG_ASN1_SET = 49;
    public static int TAG_AUTHORIZATIONLIST_PURPOSE = 1;
    public static int TAG_AUTHORIZATIONLIST_ALGORITHM = 2;
    public static int TAG_AUTHORIZATIONLIST_KEYSIZE = 3;
    public static int TAG_AUTHORIZATIONLIST_DIGEST = 5;
    public static int TAG_AUTHORIZATIONLIST_ECCCURVE = 10;
    public static int TAG_AUTHORIZATIONLIST_NO_AUTH_REQUIRED = 503;
    public static int TAG_AUTHORIZATIONLIST_USER_AUTH_TYPE = 504;
    public static int TAG_AUTHORIZATIONLIST_AUTH_TIMEOUT = 505;
    public static int TAG_AUTHORIZATIONLIST_ALL_APPLICATIONS = 600;
    public static int TAG_AUTHORIZATIONLIST_ORIGIN = 702;

    public static String byte2hex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append("0123456789ABCDEF".charAt((b & 240) >> 4));
            sb.append("0123456789ABCDEF".charAt(b & 15));
        }
        return sb.toString();
    }

    private static int byteArrayToInt(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i |= (bArr[i2] & 255) << (((length - 1) - i2) * 8);
        }
        return i;
    }

    public static boolean checkSupport(Context context, String str) {
        try {
            int i = Build.VERSION.SDK_INT;
            if (i < 23) {
                Logger.m15756i("FpUtil", "can not perform below Android M");
                return false;
            } else if (context == null) {
                Logger.m15756i("FpUtil", "context is null");
                return false;
            } else {
                Logger.m15756i("FpUtil", "ECDSA Key generation Begin");
                Calendar calendar = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                calendar2.add(1, 20);
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
                KeyGenParameterSpec.Builder certificateNotAfter = new KeyGenParameterSpec.Builder(str, 4).setDigests("SHA-256").setAlgorithmParameterSpec(new ECGenParameterSpec("prime256v1")).setCertificateSubject(new X500Principal(String.format("CN=%s, OU=%s", str, context.getPackageName()))).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime());
                if (i > 23) {
                    certificateNotAfter.setAttestationChallenge(genChallenge());
                }
                if (TextUtils.equals("MI 5s", Build.MODEL)) {
                    return false;
                }
                keyPairGenerator.initialize(certificateNotAfter.build());
                keyPairGenerator.generateKeyPair();
                Logger.m15756i("FpUtil", "ECDSA Key generation complete");
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                Key key = keyStore.getKey(str, null);
                if (key == null) {
                    Logger.m15757e("FpUtil", "Failed to get key entry for uuid " + str);
                    return false;
                }
                Signature.getInstance("SHA256withECDSA").initSign((PrivateKey) key);
                return true;
            }
        } catch (Exception e) {
            Logger.m15757e("FpUtil", "ECDSA Key generation failed." + e.getMessage());
            return false;
        }
    }

    private static byte[] genChallenge() {
        byte[] bArr = new byte[32];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    private static int getASN1Length(ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        if ((b & 128) == 0) {
            return b;
        }
        int i = b & Byte.MAX_VALUE;
        if (i > 4) {
            return -1;
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return byteArrayToInt(bArr);
    }

    private static int getASN1Tag(ByteBuffer byteBuffer) {
        return byteBuffer.get();
    }

    public static KeyASecurityType getASecurityLevel(String str) {
        try {
            KeyDescription keyDescription = getKeyDescription(str);
            if (keyDescription == null) {
                Logger.m15757e("FpUtil", "keyDescription is null");
                return KeyASecurityType.NOATTESTATION;
            }
            Logger.m15757e("FpUtil", "KeyASecurityType: " + keyDescription.m15863a());
            return keyDescription.m15863a();
        } catch (Exception e) {
            Logger.m15757e("FpUtil", "getASecurityLevel: " + e.getMessage());
            return KeyASecurityType.NOATTESTATION;
        }
    }

    private static int getAbsoulteASN1Tag(ByteBuffer byteBuffer) {
        byte b;
        byte b2 = byteBuffer.get();
        Log.d("FpUtil", "getAbsoulteASN1Tag:" + byte2hex(b2));
        int i = b2 & 31;
        if ((i ^ 31) == 0) {
            Log.d("FpUtil", "getAbsoulteASN1Tag high");
            ArrayList arrayList = new ArrayList();
            do {
                b = byteBuffer.get();
                arrayList.add(Byte.valueOf(b));
            } while ((b & 128) != 0);
            int size = arrayList.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i2 |= (((Byte) arrayList.get(i3)).byteValue() & Byte.MAX_VALUE) << (((size - 1) - i3) * 7);
            }
            return i2;
        }
        Log.d("FpUtil", "getAbsoulteASN1Tag low");
        return i;
    }

    private static Certificate[] getCertificatesFromChain(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.getCertificateChain(str);
        } catch (Exception e) {
            Logger.m15757e("FpUtil", "getCertificatesFromChain: " + e.getMessage());
            return null;
        }
    }

    private static KeyDescription getKeyDescription(String str) {
        try {
            Certificate[] certificatesFromChain = getCertificatesFromChain(str);
            if (!$assertionsDisabled && certificatesFromChain == null) {
                throw new AssertionError();
            }
            byte[] extensionValue = ((X509Certificate) certificatesFromChain[0]).getExtensionValue("1.3.6.1.4.1.11129.2.1.17");
            Log.d("FpUtil", "extensionValue:" + byte2hex(extensionValue));
            return verifyAttestionExtension(extensionValue);
        } catch (Exception e) {
            Logger.m15757e("FpUtil", "getKeyDescription: " + e.getMessage());
            return null;
        }
    }

    private static AuthorizationList parseAuthorizationList(byte[] bArr) {
        Log.d("FpUtil", "parseAuthorizationList:" + byte2hex(bArr));
        AuthorizationList authorizationList = new AuthorizationList();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        while (wrap.hasRemaining()) {
            int absoulteASN1Tag = getAbsoulteASN1Tag(wrap);
            int aSN1Length = getASN1Length(wrap);
            if (aSN1Length != 0) {
                byte[] bArr2 = new byte[aSN1Length];
                wrap.get(bArr2);
                ByteBuffer wrap2 = ByteBuffer.wrap(bArr2);
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_PURPOSE) {
                    Log.d("FpUtil", "purpose:" + byte2hex(bArr2));
                    int aSN1Tag = getASN1Tag(wrap2);
                    getASN1Length(wrap2);
                    if (aSN1Tag != TAG_ASN1_SET) {
                        Logger.m15757e("FpUtil", "is not attestion extension by purpose set");
                    } else {
                        ArrayList arrayList = new ArrayList();
                        while (wrap2.hasRemaining()) {
                            int aSN1Tag2 = getASN1Tag(wrap2);
                            byte[] bArr3 = new byte[getASN1Length(wrap2)];
                            wrap2.get(bArr3);
                            if (aSN1Tag2 != TAG_ASN1_INT) {
                                Logger.m15757e("FpUtil", "is not attestion extension by purpose int");
                            } else {
                                arrayList.add(Integer.valueOf(bArr3[0] & 255));
                            }
                        }
                        int[] iArr = new int[arrayList.size()];
                        for (int i = 0; i < arrayList.size(); i++) {
                            iArr[i] = ((Integer) arrayList.get(i)).intValue();
                        }
                        authorizationList.m15870b(iArr);
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_ALGORITHM) {
                    int aSN1Tag3 = getASN1Tag(wrap2);
                    byte[] bArr4 = new byte[getASN1Length(wrap2)];
                    wrap2.get(bArr4);
                    if (aSN1Tag3 != TAG_ASN1_INT) {
                        Logger.m15757e("FpUtil", "is not attestion extension by algorithm");
                    } else {
                        authorizationList.m15876a(bArr4[0] & 255);
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_KEYSIZE) {
                    int aSN1Tag4 = getASN1Tag(wrap2);
                    byte[] bArr5 = new byte[getASN1Length(wrap2)];
                    wrap2.get(bArr5);
                    if (aSN1Tag4 != TAG_ASN1_INT) {
                        Logger.m15757e("FpUtil", "is not attestion extension by keysize");
                    } else {
                        authorizationList.m15866d(byteArrayToInt(bArr5));
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_DIGEST) {
                    int aSN1Tag5 = getASN1Tag(wrap2);
                    getASN1Length(wrap2);
                    if (aSN1Tag5 != TAG_ASN1_SET) {
                        Logger.m15757e("FpUtil", "is not attestion extension by digest set");
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        while (wrap2.hasRemaining()) {
                            int aSN1Tag6 = getASN1Tag(wrap2);
                            byte[] bArr6 = new byte[getASN1Length(wrap2)];
                            wrap2.get(bArr6);
                            if (aSN1Tag6 != TAG_ASN1_INT) {
                                Logger.m15757e("FpUtil", "is not attestion extension by digest int");
                            } else {
                                arrayList2.add(Integer.valueOf(bArr6[0] & 255));
                            }
                        }
                        int[] iArr2 = new int[arrayList2.size()];
                        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                            iArr2[i2] = ((Integer) arrayList2.get(i2)).intValue();
                        }
                        authorizationList.m15874a(iArr2);
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_ECCCURVE) {
                    int aSN1Tag7 = getASN1Tag(wrap2);
                    byte[] bArr7 = new byte[getASN1Length(wrap2)];
                    wrap2.get(bArr7);
                    if (aSN1Tag7 != TAG_ASN1_INT) {
                        Logger.m15757e("FpUtil", "is not attestion extension by ecc");
                    } else {
                        authorizationList.m15868c(bArr7[0] & 255);
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_USER_AUTH_TYPE) {
                    int aSN1Tag8 = getASN1Tag(wrap2);
                    byte[] bArr8 = new byte[getASN1Length(wrap2)];
                    wrap2.get(bArr8);
                    if (aSN1Tag8 != TAG_ASN1_INT) {
                        Logger.m15757e("FpUtil", "is not attestion extension by auth type");
                    } else {
                        authorizationList.m15864f(bArr8[0] & 255);
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_AUTH_TIMEOUT) {
                    int aSN1Tag9 = getASN1Tag(wrap2);
                    byte[] bArr9 = new byte[getASN1Length(wrap2)];
                    wrap2.get(bArr9);
                    if (aSN1Tag9 != TAG_ASN1_INT) {
                        Logger.m15757e("FpUtil", "is not attestion extension by auth type");
                    } else {
                        authorizationList.m15872b(bArr9[0] & 255);
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_NO_AUTH_REQUIRED) {
                    if (getASN1Tag(wrap2) != TAG_ASN1_NULL) {
                        Logger.m15757e("FpUtil", "is not attestion extension by no auth required");
                    } else {
                        authorizationList.m15871b(true);
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_ALL_APPLICATIONS) {
                    if (getASN1Tag(wrap2) != TAG_ASN1_NULL) {
                        Logger.m15757e("FpUtil", "is not attestion extension by all application");
                    } else {
                        authorizationList.m15875a(true);
                    }
                }
                if (absoulteASN1Tag == TAG_AUTHORIZATIONLIST_ORIGIN) {
                    int aSN1Tag10 = getASN1Tag(wrap2);
                    byte[] bArr10 = new byte[getASN1Length(wrap2)];
                    wrap2.get(bArr10);
                    if (aSN1Tag10 != TAG_ASN1_INT) {
                        Logger.m15757e("FpUtil", "is not attestion extension by origin");
                    } else {
                        authorizationList.m15865e(bArr10[0] & 255);
                    }
                }
            }
        }
        return authorizationList;
    }

    private static KeyDescription verifyAttestionExtension(byte[] bArr) {
        KeyDescription keyDescription = new KeyDescription();
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    byte b = wrap.get();
                    int aSN1Length = getASN1Length(wrap);
                    if (b == TAG_ASN1_OCTETSTRING && wrap.hasRemaining() && wrap.remaining() == aSN1Length) {
                        byte b2 = wrap.get();
                        if (getASN1Length(wrap) != 0 && (b2 != TAG_ASN1_SEQUENCE || !wrap.hasRemaining())) {
                            Logger.m15757e("FpUtil", "is not attestation extension by root sequence");
                            return null;
                        }
                        byte b3 = wrap.get();
                        byte[] bArr2 = new byte[getASN1Length(wrap)];
                        wrap.get(bArr2);
                        if (b3 != TAG_ASN1_INT) {
                            Logger.m15757e("FpUtil", "is not attestion extension by attestation version");
                            return null;
                        }
                        keyDescription.m15858b(bArr2[0] & 255);
                        byte b4 = wrap.get();
                        byte[] bArr3 = new byte[getASN1Length(wrap)];
                        wrap.get(bArr3);
                        if (b4 != TAG_ASN1_ENUM) {
                            Logger.m15757e("FpUtil", "is not attestion extension by tmp1");
                            return null;
                        }
                        keyDescription.m15862a(bArr3[0] & 255);
                        byte b5 = wrap.get();
                        int aSN1Length2 = getASN1Length(wrap);
                        if (aSN1Length2 != 0) {
                            byte[] bArr4 = new byte[aSN1Length2];
                            wrap.get(bArr4);
                            if (b5 != TAG_ASN1_INT) {
                                Logger.m15757e("FpUtil", "is not attestion extension by tmp2");
                                return null;
                            }
                            keyDescription.m15853d(bArr4[0] & 255);
                        }
                        byte b6 = wrap.get();
                        byte[] bArr5 = new byte[getASN1Length(wrap)];
                        wrap.get(bArr5);
                        if (b6 != TAG_ASN1_ENUM) {
                            Logger.m15757e("FpUtil", "is not attestion extension by keymaster security");
                            return null;
                        }
                        keyDescription.m15855c(bArr5[0] & 255);
                        byte b7 = wrap.get();
                        int aSN1Length3 = getASN1Length(wrap);
                        if (aSN1Length3 != 0) {
                            byte[] bArr6 = new byte[aSN1Length3];
                            wrap.get(bArr6);
                            if (b7 != TAG_ASN1_OCTETSTRING) {
                                Logger.m15757e("FpUtil", "is not attestion extension by challenge");
                                return null;
                            }
                            keyDescription.m15860a(bArr6);
                        }
                        byte b8 = wrap.get();
                        int aSN1Length4 = getASN1Length(wrap);
                        if (aSN1Length4 != 0) {
                            wrap.get(new byte[aSN1Length4]);
                            if (b8 != TAG_ASN1_OCTETSTRING) {
                                Logger.m15757e("FpUtil", "is not attestion extension by tmp2");
                                return null;
                            }
                        }
                        byte b9 = wrap.get();
                        int aSN1Length5 = getASN1Length(wrap);
                        if (aSN1Length5 != 0) {
                            byte[] bArr7 = new byte[aSN1Length5];
                            wrap.get(bArr7);
                            if (b9 != TAG_ASN1_SEQUENCE) {
                                Logger.m15757e("FpUtil", "is not attestion extension by sw");
                                return null;
                            }
                            Log.d("FpUtil", "check softwareEnforced");
                            AuthorizationList parseAuthorizationList = parseAuthorizationList(bArr7);
                            keyDescription.m15861a(parseAuthorizationList);
                            Log.d("FpUtil", "swEnforcedList:" + parseAuthorizationList);
                        }
                        byte b10 = wrap.get();
                        int aSN1Length6 = getASN1Length(wrap);
                        if (aSN1Length6 != 0) {
                            byte[] bArr8 = new byte[aSN1Length6];
                            wrap.get(bArr8);
                            if (b10 != TAG_ASN1_SEQUENCE) {
                                Logger.m15757e("FpUtil", "is not attestion extension by tee");
                                return null;
                            }
                            Log.d("FpUtil", "check teeEnforced");
                            AuthorizationList parseAuthorizationList2 = parseAuthorizationList(bArr8);
                            keyDescription.m15857b(parseAuthorizationList2);
                            Log.d("FpUtil", "teeEnforcedList:" + parseAuthorizationList2);
                        }
                        return keyDescription;
                    }
                    Logger.m15757e("FpUtil", "is not attestation extension by root , maybe not der");
                    return null;
                }
            } catch (Exception e) {
                Logger.m15757e("FpUtil", "verifyAttestionExtension:" + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean verifySecure(String str, boolean z) {
        try {
            KeyDescription keyDescription = getKeyDescription(str);
            if (keyDescription == null) {
                Logger.m15757e("FpUtil", "KeyDescription is null");
                return false;
            } else if (z && keyDescription.m15863a() != KeyASecurityType.TEE) {
                Logger.m15757e("FpUtil", "AttestationSecurity is not tee");
                return false;
            } else if (keyDescription.m15859b() != KeyASecurityType.TEE) {
                Logger.m15757e("FpUtil", "KeymasterSecurityLevel is not tee");
                return false;
            } else {
                keyDescription.m15856c();
                AuthorizationList m15854d = keyDescription.m15854d();
                if (m15854d.m15869c() != null && m15854d.m15869c().length == 1) {
                    if (Arrays.binarySearch(m15854d.m15869c(), 2) != 0) {
                        Logger.m15757e("FpUtil", "purpose is not sign");
                        return false;
                    } else if (m15854d.m15877a() != 0) {
                        Logger.m15757e("FpUtil", "user auth timeout is not 0");
                        return false;
                    } else if (m15854d.m15867d()) {
                        Logger.m15757e("FpUtil", "all applications is not granted");
                        return false;
                    } else if (m15854d.m15873b() != 0) {
                        Logger.m15757e("FpUtil", "key is need generate");
                        return false;
                    } else {
                        return true;
                    }
                }
                Logger.m15757e("FpUtil", "purpose is not sign,else");
                return false;
            }
        } catch (Exception e) {
            Logger.m15757e("FpUtil", "verifySecure: " + e.getMessage());
            return false;
        }
    }

    public static String byte2hex(byte b) {
        return byte2hex(new byte[]{b});
    }

    @SuppressLint({"NewApi"})
    @TargetApi(23)
    public static boolean checkSupport(Context context) {
        UUID randomUUID = UUID.randomUUID();
        String uuid = randomUUID.toString();
        Logger.m15756i("FpUtil", "ECDSA Key generation Begin");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 20);
        try {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
                keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(uuid, 4).setDigests("SHA-256").setAlgorithmParameterSpec(new ECGenParameterSpec("prime256v1")).setCertificateSubject(new X500Principal(String.format("CN=%s, OU=%s", randomUUID, context.getPackageName()))).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build());
                keyPairGenerator.generateKeyPair();
                Logger.m15756i("FpUtil", "Algorithm used to generate: " + keyPairGenerator.getAlgorithm());
                Logger.m15756i("FpUtil", "ECDSA Key generation complete");
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                Key key = keyStore.getKey(uuid, null);
                if (key == null) {
                    Logger.m15757e("FpUtil", "Failed to get key entry for uuid " + uuid);
                    return false;
                }
                Signature.getInstance("SHA256withECDSA").initSign((PrivateKey) key);
                return true;
            } catch (UserNotAuthenticatedException unused) {
                Logger.m15757e("FpUtil", "ECDSA Key generation failed,UserNotAuthenticatedException ");
                return false;
            }
        } catch (Error | Exception unused2) {
            Logger.m15757e("FpUtil", "ECDSA Key generation failed. ");
            return false;
        }
    }
}
