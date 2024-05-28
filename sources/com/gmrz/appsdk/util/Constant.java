package com.gmrz.appsdk.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Constant {
    public static final String AAID_FACE_REMOTE = "004A#01B0";
    public static final String AAID_QRCODE = "004A#01B4";
    public static final String AAID_REAL_NAME = "004A#01B2";
    public static final String AAID_REAL_NAME_ADD_FACE = "004A#01B3";
    public static final String AAID_VOICE_REMOTE = "004A#01B1";
    public static final String ACTION_FIDO_CLIENT_VIA_ACTIVITY = "org.fidoalliance.intent.FIDO_OPERATION";
    public static final String ACTION_FIDO_CLIENT_VIA_SERVICE = "org.fidoalliance.aidl.FIDO_OPERATION";
    public static final String ASM_GETREG = "{\"asmVersion\":{\"major\":1,\"minor\":0}, \"authenticatorIndex\":%1$s, \"requestType\":\"GetRegistrations\"}";
    public static final String AUTH_TYPE_FACE_LOCAL = "02";
    public static final String AUTH_TYPE_FACE_REMOTE = "10";
    public static final String AUTH_TYPE_FINGER = "00";
    public static final String AUTH_TYPE_GESTURE = "03";
    public static final String AUTH_TYPE_IRIS = "01";
    public static final String AUTH_TYPE_REAL_NAME = "12";
    public static final String AUTH_TYPE_REAL_NAME_ADD_FACE = "13";
    public static final String AUTH_TYPE_SCAN_QRCODE = "14";
    public static final String AUTH_TYPE_VOICE_REMOTE = "11";
    public static final String AuthenticatorVersion = "1.3";
    public static boolean BIOMETRIC_UI = false;
    public static final String CHECK_CANAUTH = "[{\"header\":{\"upv\":{\"major\":1,\"minor\":0},\"op\":\"Auth\",\"appID\":\"%1$s\",\"serverData\":\"c6GW4L_0W2q_5bzt3wZNrtRZxPcpdFNp2m5lFnggId6uFImdqwh5g0B_Vp-EK0IEkLI5SzkMOAOup6CtLqThKcLHWaGfEHPd5svRGfXhLbqxliKSwMbOwBehf6oZyOGON2az2hWZLouS0zBGSnSYFmhJyfZRLDT3U-DKIgeUKn50Yz4rjUR3Cc5eb8QQ1GlSaWBwvffWvG_bd4VAyMKlhY5IsumqylXtjU47OYie9sheEYZY_th6JEbf1F-RMeq8IqXrEK74uOFP0ShV3ERQh7mcrNTu3N-9mUk\"},\"challenge\":\"SLqANoSWDlIumRWjvVg7VrNXOJ-02nBiAt5VNNXNOdQ\",\"policy\":{\"accepted\":[[{\"aaid\":[\"%2$s\"],\"keyIDs\":[\"%3$s\"]}]]}}]";
    public static final String CHECK_FIDO_TYPE_ANOTHER_HALF = ",\"keyProtection\": 6,\"authenticationAlgorithms\": [1,2,3,4,5,6],\"assertionSchemes\": [\"UAFV1TLV\"]}] ]}}]";
    public static final String CHECK_FIDO_TYPE_HALF = "[{\"header\":{\"upv\":{\"major\":1,\"minor\":0},\"op\":\"Reg\",\"appID\":\"\",\"serverData\":\"1\"},\"challenge\":\"1AM2yZY4-9SG4Ns7-hMdB8IV_FTDKFFiUqNJNVbsVoo\",\"username\":\"1\",\"policy\":{\"accepted\":[[{\"userVerification\": ";
    public static final String CHECK_ISREG = "[{\"header\":{\"upv\":{\"major\":1,\"minor\":0},\"op\":\"Auth\",\"appID\":\"%1$s\",\"serverData\":\"emKubKMS8RxYOth7J8enT_x7dQWBaO1CiC0fGmSEhX56kq2RYo1LRpwvfHlzYRI3p9Ay-l4zJcV3lX6rQ0CYNWi5nNDabClFm3k0pPj0kX5V-db9ejN_05y2J6wqztSD\"},\"challenge\":\"1AM2yZY4-9SG4Ns7-hMdB8IV_FTDKFFiUqNJNVbsVoo\",\"policy\":{\"accepted\":[%2$s]}}]";
    public static boolean CHECK_ROOT = false;
    public static final String CHECK_TEE = "[{\"header\":{\"upv\":{\"major\":1,\"minor\":0},\"op\":\"Reg\",\"appID\":\"\",\"serverData\":\"1\"},\"challenge\":\"1AM2yZY4-9SG4Ns7-hMdB8IV_FTDKFFiUqNJNVbsVoo\",\"username\":\"1\",\"policy\":{\"accepted\":[[{\"userVerification\":2,\"keyProtection\":6,\"authenticationAlgorithms\":[1,2,3,4,5,6],\"assertionSchemes\":[\"UAFV1TLV\"]}]]}}]";
    public static final String CLASS_NAME_FOR_CER_APPSDK = "com.gmrz.android.appsdk.CerAppSdk";
    public static final String CLASS_NAME_FOR_CER_DEVICE_INFO = "com.gmrz.android.appsdk.CerDeviceInfo";
    public static final String DEREGISTER_ALL_FROM = "00";
    public static final String HEADER = "header";
    public static final String KEYSTORE_FACE_AAID = "004A#01AB";
    public static final String KEYSTORE_FINGER_AAID_GM_ATTESETATION = "004A#01AE";
    public static final String KEYSTORE_FINGER_AAID_HARDWARE_ATTESETATION = "004A#01AD";
    public static final String KEYSTORE_FINGER_AAID_NEW = "004A#01AA";
    public static final String KEYSTORE_FINGER_AAID_OLD = "001A#3333";
    public static final String KEYSTORE_FINGER_AAID_SOFTWARE_ATTESETATION = "004A#01A0";
    public static final String KEYSTORE_GESTURE_AAID = "004A#01AC";
    public static final String KEYSTORE_GM_GESTURE_AAID = "004A#01AF";
    public static final String KEY_OFFLINE_AUTH_USER_INFO_CACHE = "cache";
    public static final String KEY_OFFLINE_REG_PROCESS_CHALLENGE = "challenge";
    public static final String MIME_TYPE_FIDO_ASM = "application/fido.uaf_asm+json";
    public static final String MIME_TYPE_FIDO_CLIENT = "application/fido.uaf_client+json";

    /* renamed from: OP */
    public static final String f10354OP = "op";
    public static final String OP_TYPE_AUTH = "Auth";
    public static final String OP_TYPE_DEFAULT = "00";
    public static final String OP_TYPE_DEREG = "Dereg";
    public static final String OP_TYPE_REG = "Reg";
    public static final String SDK_VERSION = "5.4.2.a";
    public static final String TRANS_TYPE_LOGIN = "00";
    public static final String TRANS_TYPE_TRADE = "01";
    public static final String USER_CHECK_STATUS_ACTIVE = "1";
    public static final String USER_CHECK_STATUS_NOT_ACTIVE = "0";
    public static final String USER_VERIFY_EYEPRINT = "64";
    public static final String USER_VERIFY_FACEPRINT = "16";
    public static final String USER_VERIFY_FINGERPRINT = "2";
    public static final String USER_VERIFY_GESTURE = "128";
    public static boolean USE_SERVICE;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum SecurityLevel {
        TEE,
        HardwareAttestation,
        SoftwareAttestation,
        NoAttestation
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum SecurityLevelInfo {
        Support,
        ShouldConfig,
        NotSupport
    }
}
