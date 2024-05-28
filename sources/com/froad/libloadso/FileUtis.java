package com.froad.libloadso;

import android.content.Context;
import com.froad.libloadso.utils.FCharUtils;
import com.froad.libloadso.utils.SM4Util;
import com.p189cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileUtis {
    private static final String TAG = "FileUtis";

    public static int parseEncFile(Context context, String str) {
        try {
            if (FCharUtils.isNotEmpty(str)) {
                InputStream open = context.getAssets().open(str);
                int available = open.available();
                byte[] bArr = new byte[available];
                open.read(bArr);
                int i = available - 64;
                byte[] bArr2 = new byte[i];
                byte[] bArr3 = new byte[32];
                byte[] bArr4 = new byte[32];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                System.arraycopy(bArr, i, bArr3, 0, 32);
                System.arraycopy(bArr, available - 32, bArr4, 0, 32);
                byte[] encKey = FFTeIDJni.getJNI().getEncKey(bArr4);
                if (encKey == null) {
                    return -1;
                }
                byte[] sms4_ecb_xor = SM4Util.getInstance().sms4_ecb_xor(bArr2, encKey, 0);
                if (sms4_ecb_xor == null) {
                    return -2;
                }
                if (!Arrays.equals(bArr3, FCharUtils.hexString2ByteArray(FFTeIDJni.getJNI().getSM3Hex(sms4_ecb_xor, true)))) {
                    return -3;
                }
            }
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static byte[] parseLicFile(Context context, String str) {
        byte[] bArr = null;
        try {
            if (FCharUtils.isNotEmpty(str)) {
                InputStream open = context.getAssets().open(str);
                bArr = new byte[open.available()];
                open.read(bArr);
                FFTEIDReadCardJNI.getJNI().setLicSm3Value(FFTeIDJni.getJNI().getSM3Hex(bArr, false));
                return bArr;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return bArr;
        }
    }

    public static int saveFile(String str, String str2, byte[] bArr) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        File file2 = new File(file, str2);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                try {
                    fileOutputStream2.write(bArr);
                    try {
                        fileOutputStream2.close();
                        return 1;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return 1;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return -4;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
