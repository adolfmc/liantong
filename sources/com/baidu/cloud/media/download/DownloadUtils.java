package com.baidu.cloud.media.download;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
final class DownloadUtils {
    public static final int CONNECT_TIME_OUT = 30000;
    public static final int HTTP_SAVE_RESULT_COMMON_FAILED = -1;
    public static final int HTTP_SAVE_RESULT_NETWORK_PROBLEM = -2;
    public static final int HTTP_SAVE_RESULT_SUCCESS = 1;
    public static final int MAX_STRING_LENGTH = 5242880;
    public static final int READ_TIME_OUT = 30000;
    private static final String TAG = "DownloadUtils";

    DownloadUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x016a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0153 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x016f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x015d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x017b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0158 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0176 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0165 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0143 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0180 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.baidu.cloud.media.download.HttpTSTask.Result httpSave(java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.media.download.DownloadUtils.httpSave(java.lang.String, java.lang.String):com.baidu.cloud.media.download.HttpTSTask$Result");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r5.disconnect();
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0091 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String httpGet(java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L78
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L78
            java.net.URLConnection r5 = r2.openConnection()     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L78
            java.net.URLConnection r5 = com.networkbench.agent.impl.instrumentation.NBSInstrumentation.openConnection(r5)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L78
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L78
            r2 = 30000(0x7530, float:4.2039E-41)
            r5.setConnectTimeout(r2)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r5.setReadTimeout(r2)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r5.connect()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            int r2 = r5.getResponseCode()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            int r2 = r2 / 100
            r3 = 2
            if (r2 != r3) goto L6a
            int r2 = r5.getContentLength()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r3 = 5242880(0x500000, float:7.34684E-39)
            if (r2 <= r3) goto L32
            goto L6a
        L32:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.io.InputStream r4 = r5.getInputStream()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
        L40:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L8d
            if (r3 == 0) goto L5b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L8d
            r4.<init>()     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L8d
            r4.append(r3)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L8d
            java.lang.String r3 = "\n"
            r4.append(r3)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L8d
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L8d
            r0.append(r3)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L8d
            goto L40
        L5b:
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L8d
            r2.close()     // Catch: java.lang.Exception -> L62
        L62:
            if (r5 == 0) goto L67
            r5.disconnect()     // Catch: java.lang.Exception -> L67
        L67:
            return r0
        L68:
            r0 = move-exception
            goto L7b
        L6a:
            if (r5 == 0) goto L6f
            r5.disconnect()     // Catch: java.lang.Exception -> L6f
        L6f:
            return r1
        L70:
            r0 = move-exception
            goto L8f
        L72:
            r0 = move-exception
            r2 = r1
            goto L7b
        L75:
            r0 = move-exception
            r5 = r1
            goto L8f
        L78:
            r0 = move-exception
            r5 = r1
            r2 = r5
        L7b:
            java.lang.String r3 = "DownloadUtils"
            java.lang.String r4 = ""
            android.util.Log.d(r3, r4, r0)     // Catch: java.lang.Throwable -> L8d
            if (r2 == 0) goto L87
            r2.close()     // Catch: java.lang.Exception -> L87
        L87:
            if (r5 == 0) goto L8c
            r5.disconnect()     // Catch: java.lang.Exception -> L8c
        L8c:
            return r1
        L8d:
            r0 = move-exception
            r1 = r2
        L8f:
            if (r1 == 0) goto L94
            r1.close()     // Catch: java.lang.Exception -> L94
        L94:
            if (r5 == 0) goto L99
            r5.disconnect()     // Catch: java.lang.Exception -> L99
        L99:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.media.download.DownloadUtils.httpGet(java.lang.String):java.lang.String");
    }

    public static boolean saveDataToFile(byte[] bArr, String str) {
        File file;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                file = new File(str);
                try {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e) {
                            Log.d("DownloadUtils", "" + e.getMessage());
                        }
                        return true;
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        Log.d("DownloadUtils", "" + Log.getStackTraceString(e));
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                Log.d("DownloadUtils", "" + e3.getMessage());
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                Log.d("DownloadUtils", "" + e4.getMessage());
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
        } catch (Exception e6) {
            e = e6;
            file = null;
        }
    }

    public static String getMD5(String str) {
        byte[] bytes = str.getBytes();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[32];
            int i = 0;
            for (int i2 = 0; i2 < 16; i2++) {
                byte b = digest[i2];
                int i3 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i3 + 1;
                cArr2[i3] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (NoSuchAlgorithmException e) {
            Log.e("DownloadUtils", "", e);
            return null;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null && allNetworkInfo.length > 0) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
