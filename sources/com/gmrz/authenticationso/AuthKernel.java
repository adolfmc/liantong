package com.gmrz.authenticationso;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.authenticationso.authenticator.KSAuthenticatorKernel;
import com.gmrz.authenticationso.utils.FileHelper;
import com.utils.AAID;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AuthKernel {
    private static final String TAG = "AuthKernel";
    public static boolean isLoadOk;
    static Map<Integer, KSAuthenticatorKernel> ksAuthenticatorKernels = new HashMap();

    public static native String initJniC(Context context, boolean z, String str, String str2, String str3, int i);

    public static native byte[] processJniC(byte[] bArr);

    static {
        try {
            System.loadLibrary("AkSdk");
            Logger.m15895d("AuthKernel", "Library Loaded is ok: " + isLoadOk);
            isLoadOk = true;
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            isLoadOk = false;
            Logger.m15883w("AuthKernel", "Unable load 'AkSdk' library");
        }
    }

    public static String initJni(Context context, boolean z, KSAuthenticatorKernel kSAuthenticatorKernel, AAID aaid) {
        if (isLoadOk) {
            ksAuthenticatorKernels.put(Integer.valueOf(aaid.index), kSAuthenticatorKernel);
            String replace = AuthKernel.class.getName().replace(".", "/");
            String createDirectoryForKsTA = FileHelper.createDirectoryForKsTA(context);
            Logger.m15895d("AuthKernel", "fido keystore white box wrap key file path=" + createDirectoryForKsTA);
            if (TextUtils.isEmpty(createDirectoryForKsTA)) {
                Log.wtf("AuthKernel", "create fido keystore wrap key files folder failed");
                return null;
            }
            return initJniC(context, z, replace, createDirectoryForKsTA, aaid.name, aaid.index);
        }
        return null;
    }

    public static byte[] processJni(byte[] bArr) {
        return processJniC(bArr);
    }

    public static byte[] generateKeyPair(int i) {
        return ksAuthenticatorKernels.get(Integer.valueOf(i)).generateKeyPair();
    }

    public static byte[] exportPublicKey(byte[] bArr, int i) {
        return ksAuthenticatorKernels.get(Integer.valueOf(i)).exportPublicKey(bArr);
    }

    public static byte[] signData(byte[] bArr, byte[] bArr2, int i) {
        return ksAuthenticatorKernels.get(Integer.valueOf(i)).signData(bArr, bArr2);
    }

    public static byte[] encryptDecryptData(byte[] bArr, byte[] bArr2, int i) {
        return ksAuthenticatorKernels.get(Integer.valueOf(i)).encryptDecryptData(bArr, bArr2);
    }

    public static int getStatus(int i) {
        return ksAuthenticatorKernels.get(Integer.valueOf(i)).getStatus();
    }

    public static byte[] wrapObject(byte[] bArr, int i) {
        return ksAuthenticatorKernels.get(Integer.valueOf(i)).wrapObject(bArr);
    }

    public static byte[] unwrapObject(byte[] bArr, int i) {
        return ksAuthenticatorKernels.get(Integer.valueOf(i)).unwrapObject(bArr);
    }

    public static void removeKey(byte[] bArr, int i) {
        ksAuthenticatorKernels.get(Integer.valueOf(i)).removeKey(bArr);
    }
}
