package com.p189cn.froad.clouddecodingsdk.utils.p191np;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.p189cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.utils.np.DeviceUtil */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DeviceUtil {
    public static final String Android_APP = "A0";
    public static final String Android_BLUE = "AB";
    public static final String Android_SYS = "0";
    public static final String Android_USB = "AU";
    public static final String E_CARD_NFC = "03";
    public static final String ID_CARD = "01";
    private static final String TAG = "DeviceUtil";
    public static final String TRAVEL_CARD = "02";
    private static String TimeDeviceType = "";
    public static String androidType = "A0";
    private static String mUUID = "";

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.BufferedWriter, java.io.Closeable] */
    public static String generateUUId(Context context) {
        BufferedReader bufferedReader;
        ?? r3;
        String str = "";
        File file = new File(context.getCacheDir(), ".fft");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, ".fft");
        BufferedReader bufferedReader2 = null;
        try {
            if (file2.exists()) {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                    try {
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                        }
                        str = sb.toString();
                        close(bufferedReader);
                        close(bufferedReader);
                    } catch (FileNotFoundException e) {
                        e = e;
                        bufferedReader2 = bufferedReader;
                        e.printStackTrace();
                        close(bufferedReader2);
                        return str;
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader2 = bufferedReader;
                        e.printStackTrace();
                        close(bufferedReader2);
                        return str;
                    } catch (Throwable th) {
                        th = th;
                        close(bufferedReader);
                        throw th;
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                } catch (IOException e4) {
                    e = e4;
                }
            } else {
                try {
                    try {
                        file2.createNewFile();
                        UUID randomUUID = UUID.randomUUID();
                        r3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
                        try {
                            str = randomUUID.toString();
                            r3.write(str);
                            r3.flush();
                            close(r3);
                            close(r3);
                        } catch (IOException e5) {
                            e = e5;
                            bufferedReader2 = r3;
                            e.printStackTrace();
                            close(bufferedReader2);
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            close(r3);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        r3 = bufferedReader2;
                    }
                } catch (IOException e6) {
                    e = e6;
                }
            }
            return str;
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    public static String getDeviceID(Context context) {
        if (TextUtils.isEmpty(mUUID)) {
            mUUID = FFTEIDReadCardJNI.getJNI().getSM3Hex(getStrNotNull(generateUUId(context)) + getStrNotNull(Settings.Secure.getString(context.getContentResolver(), "android_id")) + getStrNotNull(Build.SERIAL) + getStrNotNull(Build.MANUFACTURER) + getStrNotNull(Build.BRAND) + getStrNotNull(Build.MODEL));
            StringBuilder sb = new StringBuilder();
            sb.append("getDeviceID: hexStr ");
            sb.append(mUUID);
            TMKeyLog.m16310d("DeviceUtil", sb.toString());
            return mUUID;
        }
        return mUUID;
    }

    public static String getDeviceIndex(String str) {
        return str + getSDKVersion();
    }

    public static String getFFTReqID(String str, String str2, long j) {
        TimeDeviceType = getTimeDeviceType(j, "0");
        return FCharUtils.string2HexStr("FFT") + str + FCharUtils.hexStr2LV(str2) + FCharUtils.hexStr2LV(TimeDeviceType);
    }

    public static String getSDKVersion() {
        return "1.2.3";
    }

    public static String getStrNotNull(String str) {
        return (str == null || "null".equalsIgnoreCase(str)) ? "" : str.trim();
    }

    public static String getThreeID(String str, String str2, String str3) {
        return FCharUtils.hexStr2LV(FCharUtils.string2HexStr(str)) + FCharUtils.hexStr2LV(str2) + FCharUtils.hexStr2LV(str3);
    }

    public static String getTimeDeviceType(long j, String str) {
        String valueOf = String.valueOf(j);
        if (valueOf.length() % 2 != 0) {
            return valueOf + str;
        }
        return valueOf;
    }

    public static void setAndroidType(String str) {
        androidType = str;
    }

    public static void setmUUID(String str) {
        mUUID = str;
    }
}
