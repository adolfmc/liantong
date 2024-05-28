package com.froad.libloadso;

import android.os.Process;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class LoadSOUtils {
    private static final String TAG = "LoadSOUtils";
    private static String soFilePath = "";

    /* JADX WARN: Removed duplicated region for block: B:52:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean copyFromAssets(android.content.Context r3, java.lang.String r4, java.io.File r5) {
        /*
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            r2 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L4e
            java.io.InputStream r3 = r3.open(r4)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L4e
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L4f
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L4f
        L13:
            int r5 = r3.read(r0)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L50
            r2 = -1
            if (r5 == r2) goto L1e
            r4.write(r0, r1, r5)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L50
            goto L13
        L1e:
            r3.close()     // Catch: java.io.IOException -> L22
            goto L26
        L22:
            r3 = move-exception
            r3.printStackTrace()
        L26:
            r4.close()     // Catch: java.io.IOException -> L2a
            goto L2e
        L2a:
            r3 = move-exception
            r3.printStackTrace()
        L2e:
            r3 = 1
            return r3
        L30:
            r5 = move-exception
            goto L39
        L32:
            r4 = move-exception
            r5 = r4
            goto L38
        L35:
            r3 = move-exception
            r5 = r3
            r3 = r2
        L38:
            r4 = r2
        L39:
            if (r3 == 0) goto L43
            r3.close()     // Catch: java.io.IOException -> L3f
            goto L43
        L3f:
            r3 = move-exception
            r3.printStackTrace()
        L43:
            if (r4 == 0) goto L4d
            r4.close()     // Catch: java.io.IOException -> L49
            goto L4d
        L49:
            r3 = move-exception
            r3.printStackTrace()
        L4d:
            throw r5
        L4e:
            r3 = r2
        L4f:
            r4 = r2
        L50:
            if (r3 == 0) goto L5a
            r3.close()     // Catch: java.io.IOException -> L56
            goto L5a
        L56:
            r3 = move-exception
            r3.printStackTrace()
        L5a:
            if (r4 == 0) goto L64
            r4.close()     // Catch: java.io.IOException -> L60
            goto L64
        L60:
            r3 = move-exception
            r3.printStackTrace()
        L64:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.froad.libloadso.LoadSOUtils.copyFromAssets(android.content.Context, java.lang.String, java.io.File):boolean");
    }

    public static boolean customLoadLibrary(String str) {
        if (str != null && !"".equals(str)) {
            File file = new File(str);
            if (file.exists()) {
                TMKeyLog.m16310d("LoadSOUtils", "customLoadLibrary>>>soFile exist");
                System.load(file.getAbsolutePath());
                return true;
            }
            TMKeyLog.m16310d("LoadSOUtils", "customLoadLibrary>>>soFile not exist");
        }
        return false;
    }

    public static String getSoFilePath() {
        return soFilePath;
    }

    public static boolean isLoadArm64() {
        int myPid = Process.myPid();
        File file = new File("/proc/" + myPid + "/maps");
        if (file.exists() && file.isFile()) {
            return readFileByLines(file.getAbsolutePath());
        }
        TMKeyLog.m16310d("LoadSOUtils", "getAllSO>>>cannot read so libs " + file.exists());
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean readFileByLines(java.lang.String r3) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            r3 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L38
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L38
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L38
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L38
        L10:
            java.lang.String r3 = r1.readLine()     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            if (r3 == 0) goto L40
            java.lang.String r0 = "lib64"
            boolean r0 = r3.contains(r0)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            if (r0 != 0) goto L26
            java.lang.String r0 = "arm64"
            boolean r3 = r3.contains(r0)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            if (r3 == 0) goto L10
        L26:
            r3 = 1
            r1.close()     // Catch: java.io.IOException -> L2b
            goto L2f
        L2b:
            r0 = move-exception
            r0.printStackTrace()
        L2f:
            return r3
        L30:
            r3 = move-exception
            goto L4a
        L32:
            r3 = move-exception
            goto L3b
        L34:
            r0 = move-exception
            r1 = r3
            r3 = r0
            goto L4a
        L38:
            r0 = move-exception
            r1 = r3
            r3 = r0
        L3b:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L48
        L40:
            r1.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r3 = move-exception
            r3.printStackTrace()
        L48:
            r3 = 0
            return r3
        L4a:
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.io.IOException -> L50
            goto L54
        L50:
            r0 = move-exception
            r0.printStackTrace()
        L54:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.froad.libloadso.LoadSOUtils.readFileByLines(java.lang.String):boolean");
    }

    public static void setSoFilePath(String str) {
        soFilePath = str;
    }
}
