package com.huawei.secure.android.common.util;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ZipUtil {

    /* renamed from: a */
    private static final String f12158a = "ZipUtil";

    /* renamed from: b */
    private static final int f12159b = 104857600;

    /* renamed from: c */
    private static final int f12160c = 100;

    /* renamed from: d */
    private static final int f12161d = 4096;

    /* renamed from: e */
    private static final String f12162e = "..";

    /* renamed from: f */
    private static final String[] f12163f = {"..\\", "../", "./", ".\\.\\", "%00", "..%2F", "..%5C", ".%2F"};

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0198  */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.io.Closeable, java.util.zip.ZipFile] */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<java.io.File> m13795a(java.io.File r16, java.io.File r17, long r18, boolean r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 413
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.util.ZipUtil.m13795a(java.io.File, java.io.File, long, boolean, boolean):java.util.List");
    }

    /* renamed from: b */
    private static boolean m13789b(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (m13796a(file.getParentFile())) {
            try {
                return file.createNewFile();
            } catch (IOException unused) {
                Log.e("ZipUtil", "createOrExistsFile IOException ");
                return false;
            }
        }
        return false;
    }

    /* renamed from: c */
    private static File m13786c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return m13788b(str);
    }

    /* renamed from: d */
    private static String m13784d(String str) {
        int lastIndexOf;
        return (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(File.separator)) == -1) ? str : str.substring(lastIndexOf + 1);
    }

    /* renamed from: e */
    private static void m13783e(File file) {
        if (file == null) {
            return;
        }
        if (file.isFile()) {
            m13787c(file);
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    m13783e(file2);
                }
                m13787c(file);
                return;
            }
            m13787c(file);
        }
    }

    @Deprecated
    public static boolean unZip(String str, String str2, boolean z) throws SecurityCommonException {
        return unZip(str, str2, 104857600L, 100, z);
    }

    public static List<File> unZipNew(String str, String str2, boolean z) throws SecurityCommonException {
        return unZipNew(str, str2, 104857600L, 100, z);
    }

    /* renamed from: c */
    private static void m13787c(File file) {
        if (file == null || file.delete()) {
            return;
        }
        LogsUtil.m13819e("ZipUtil", "delete file error");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0153  */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v20, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r1v9 */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean unZip(java.lang.String r17, java.lang.String r18, long r19, int r21, boolean r22) throws com.huawei.secure.android.common.util.SecurityCommonException {
        /*
            Method dump skipped, instructions count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.util.ZipUtil.unZip(java.lang.String, java.lang.String, long, int, boolean):boolean");
    }

    public static List<File> unZipNew(String str, String str2, long j, int i, boolean z) throws SecurityCommonException {
        if (m13791a(str, str2, j, i)) {
            if (str2.endsWith(File.separator) && str2.length() > File.separator.length()) {
                str2 = str2.substring(0, str2.length() - File.separator.length());
            }
            return m13795a(m13786c(str), m13786c(str2), j, z, false);
        }
        return null;
    }

    /* renamed from: d */
    private static void m13785d(File file) {
        if (file == null || file.exists() || file.mkdirs()) {
            return;
        }
        LogsUtil.m13819e("ZipUtil", "mkdirs error , files exists or IOException.");
    }

    /* renamed from: b */
    private static File m13788b(String str) {
        m13793a(str);
        return new File(str);
    }

    /* renamed from: e */
    private static boolean m13782e(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("ZipUtil", "isContainInvalidStr: name is null");
            return false;
        } else if (str.equals("..")) {
            return true;
        } else {
            for (String str2 : f12163f) {
                if (str.contains(str2)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    private static void m13793a(String str) {
        if (TextUtils.isEmpty(str) || !m13782e(str)) {
            return;
        }
        Log.e("ZipUtil", "IllegalArgumentException--path is not a standard path");
        throw new IllegalArgumentException("path is not a standard path");
    }

    /* renamed from: a */
    private static boolean m13796a(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00cf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @androidx.annotation.RequiresApi(api = 24)
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m13792a(java.lang.String r11, long r12, int r14, boolean r15) {
        /*
            r0 = 0
            r1 = 0
            if (r15 != 0) goto Le
            java.util.zip.ZipFile r15 = new java.util.zip.ZipFile     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            r15.<init>(r11)     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            r1 = r15
            goto L30
        Lb:
            r11 = move-exception
            goto Lcd
        Le:
            java.lang.String r15 = "ZipUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            r2.<init>()     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            java.lang.String r3 = "not a utf8 zip file, use gbk open zip file : "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            r2.append(r11)     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            com.huawei.secure.android.common.util.LogsUtil.m13813i(r15, r2)     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            java.util.zip.ZipFile r15 = new java.util.zip.ZipFile     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            java.lang.String r2 = "GBK"
            java.nio.charset.Charset r2 = java.nio.charset.Charset.forName(r2)     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            r15.<init>(r11, r2)     // Catch: java.lang.Throwable -> Lb java.io.IOException -> La3
            r1 = r15
        L30:
            java.util.Enumeration r15 = r1.entries()     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            r2 = 1
            r3 = 0
            r5 = r0
            r6 = r2
        L39:
            boolean r7 = r15.hasMoreElements()     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            if (r7 == 0) goto L9e
            java.lang.Object r7 = r15.nextElement()     // Catch: java.lang.IllegalArgumentException -> L6e java.io.IOException -> La3 java.lang.Throwable -> Lcc
            java.util.zip.ZipEntry r7 = (java.util.zip.ZipEntry) r7     // Catch: java.lang.IllegalArgumentException -> L6e java.io.IOException -> La3 java.lang.Throwable -> Lcc
            long r8 = r7.getSize()     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            long r3 = r3 + r8
            int r5 = r5 + 1
            java.lang.String r8 = r7.getName()     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            boolean r8 = m13782e(r8)     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            if (r8 != 0) goto L66
            if (r5 >= r14) goto L66
            int r8 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r8 > 0) goto L66
            long r7 = r7.getSize()     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            r9 = -1
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 != 0) goto L39
        L66:
            java.lang.String r11 = "ZipUtil"
            java.lang.String r12 = "File name is invalid or too many files or too big"
            com.huawei.secure.android.common.util.LogsUtil.m13819e(r11, r12)     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            goto L9f
        L6e:
            r6 = move-exception
            java.lang.String r7 = "ZipUtil"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            r8.<init>()     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            java.lang.String r9 = "not a utf8 zip file, IllegalArgumentException : "
            r8.append(r9)     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            java.lang.String r6 = r6.getMessage()     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            r8.append(r6)     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            java.lang.String r6 = r8.toString()     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            com.huawei.secure.android.common.util.LogsUtil.m13813i(r7, r6)     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            r7 = 24
            if (r6 < r7) goto L95
            boolean r11 = m13792a(r11, r12, r14, r2)     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            r0 = r11
            goto L9f
        L95:
            java.lang.String r6 = "ZipUtil"
            java.lang.String r7 = "File is not a utf8 zip file and Build.VERSION_CODES < 24"
            com.huawei.secure.android.common.util.LogsUtil.m13819e(r6, r7)     // Catch: java.io.IOException -> La3 java.lang.Throwable -> Lcc
            r6 = r0
            goto L39
        L9e:
            r0 = r6
        L9f:
            r1.close()     // Catch: java.io.IOException -> Lc4
            goto Lcb
        La3:
            r11 = move-exception
            java.lang.String r12 = "ZipUtil"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcc
            r13.<init>()     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r14 = "not a valid zip file, IOException : "
            r13.append(r14)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r11 = r11.getMessage()     // Catch: java.lang.Throwable -> Lcc
            r13.append(r11)     // Catch: java.lang.Throwable -> Lcc
            java.lang.String r11 = r13.toString()     // Catch: java.lang.Throwable -> Lcc
            com.huawei.secure.android.common.util.LogsUtil.m13819e(r12, r11)     // Catch: java.lang.Throwable -> Lcc
            if (r1 == 0) goto Lcb
            r1.close()     // Catch: java.io.IOException -> Lc4
            goto Lcb
        Lc4:
            java.lang.String r11 = "ZipUtil"
            java.lang.String r12 = "close zipFile IOException "
            com.huawei.secure.android.common.util.LogsUtil.m13819e(r11, r12)
        Lcb:
            return r0
        Lcc:
            r11 = move-exception
        Lcd:
            if (r1 == 0) goto Lda
            r1.close()     // Catch: java.io.IOException -> Ld3
            goto Lda
        Ld3:
            java.lang.String r12 = "ZipUtil"
            java.lang.String r13 = "close zipFile IOException "
            com.huawei.secure.android.common.util.LogsUtil.m13819e(r12, r13)
        Lda:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.util.ZipUtil.m13792a(java.lang.String, long, int, boolean):boolean");
    }

    /* renamed from: a */
    private static boolean m13791a(String str, String str2, long j, int i) throws SecurityCommonException {
        if (!TextUtils.isEmpty(str) && !m13782e(str)) {
            if (!TextUtils.isEmpty(str2) && !m13782e(str2)) {
                if (m13792a(str, j, i, false)) {
                    return true;
                }
                LogsUtil.m13819e("ZipUtil", "zip file contains valid chars or too many files");
                throw new SecurityCommonException("unsecure zipfile!");
            }
            LogsUtil.m13819e("ZipUtil", "target directory is not valid");
            return false;
        }
        LogsUtil.m13819e("ZipUtil", "zip file is not valid");
        return false;
    }

    /* renamed from: a */
    private static boolean m13790a(List<File> list) {
        try {
            for (File file : list) {
                m13783e(file);
            }
            return true;
        } catch (Exception e) {
            LogsUtil.m13819e("ZipUtil", "unzip fail delete file failed" + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private static void m13794a(FileInputStream fileInputStream, BufferedOutputStream bufferedOutputStream, ZipInputStream zipInputStream, FileOutputStream fileOutputStream) {
        IOUtil.closeSecure((InputStream) fileInputStream);
        IOUtil.closeSecure((OutputStream) bufferedOutputStream);
        IOUtil.closeSecure((InputStream) zipInputStream);
        IOUtil.closeSecure((OutputStream) fileOutputStream);
    }
}
