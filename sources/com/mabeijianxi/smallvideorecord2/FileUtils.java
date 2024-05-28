package com.mabeijianxi.smallvideorecord2;

import android.os.Environment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileUtils {
    public static String concatPath(String... strArr) {
        StringBuilder sb = new StringBuilder();
        if (strArr != null) {
            for (String str : strArr) {
                if (str != null && str.length() > 0) {
                    int length = sb.length();
                    boolean z = length > 0 && sb.charAt(length + (-1)) == File.separatorChar;
                    boolean z2 = str.charAt(0) == File.separatorChar;
                    if (z && z2) {
                        sb.append(str.substring(1));
                    } else if (!z && !z2) {
                        sb.append(File.separatorChar);
                        sb.append(str);
                    } else {
                        sb.append(str);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static String calculateMD5(File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            messageDigest.update(bArr, 0, read);
                        } catch (IOException e) {
                            throw new RuntimeException("Unable to process file for MD5", e);
                        }
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            C5256Log.m13728e("FileUtils", "Exception on closing MD5 input stream", e2);
                        }
                        throw th;
                    }
                }
                String replace = String.format("%32s", new BigInteger(1, messageDigest.digest()).toString(16)).replace(' ', '0');
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    C5256Log.m13728e("FileUtils", "Exception on closing MD5 input stream", e3);
                }
                return replace;
            } catch (FileNotFoundException e4) {
                C5256Log.m13728e("FileUtils", "Exception while getting FileInputStream", e4);
                return null;
            }
        } catch (NoSuchAlgorithmException e5) {
            C5256Log.m13728e("FileUtils", "Exception while getting digest", e5);
            return null;
        }
    }

    public static String calculateMD5(File file, int i, int i2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                if (i > 0) {
                    try {
                        try {
                            fileInputStream.skip(i);
                        } catch (Throwable th) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e) {
                                C5256Log.m13728e("FileUtils", "Exception on closing MD5 input stream", e);
                            }
                            throw th;
                        }
                    } catch (IOException e2) {
                        throw new RuntimeException("Unable to process file for MD5", e2);
                    }
                }
                int min = Math.min(8192, i2);
                int i3 = 0;
                while (true) {
                    int read = fileInputStream.read(bArr, 0, min);
                    if (read <= 0 || i3 >= i2) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                    i3 += read;
                    if (i3 + 8192 > i2) {
                        min = i2 - i3;
                    }
                }
                String replace = String.format("%32s", new BigInteger(1, messageDigest.digest()).toString(16)).replace(' ', '0');
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    C5256Log.m13728e("FileUtils", "Exception on closing MD5 input stream", e3);
                }
                return replace;
            } catch (FileNotFoundException e4) {
                C5256Log.m13728e("FileUtils", "Exception while getting FileInputStream", e4);
                return null;
            }
        } catch (NoSuchAlgorithmException e5) {
            C5256Log.m13728e("FileUtils", "Exception while getting digest", e5);
            return null;
        }
    }

    public static boolean checkFile(File file) {
        if (file != null && file.exists() && file.canRead()) {
            if (file.isDirectory()) {
                return true;
            }
            return file.isFile() && file.length() > 0;
        }
        return false;
    }

    public static boolean checkFile(String str) {
        if (StringUtils.isNotEmpty(str)) {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                if (file.isDirectory()) {
                    return true;
                }
                return file.isFile() && file.length() > 0;
            }
            return false;
        }
        return false;
    }

    public static String getExternalStorageDirectory() {
        String path = Environment.getExternalStorageDirectory().getPath();
        return DeviceUtils.isZte() ? path.replace("/sdcard", "/sdcard-ext") : path;
    }

    public static long getFileSize(String str) {
        long j;
        try {
            j = new File(str).length();
        } catch (Exception e) {
            e.printStackTrace();
            j = 0;
        }
        return (j < 0 ? null : Long.valueOf(j)).longValue();
    }

    public static long getFileSize(File file) {
        if (file == null) {
            return 0L;
        }
        return file.length();
    }

    public static String getFileType(String str, String str2) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        return contentTypeFor == null ? str2 : contentTypeFor;
    }

    public static String getFileType(String str) {
        return getFileType(str, "application/octet-stream");
    }

    public static String getFileExtension(String str) {
        int lastIndexOf;
        String str2 = "";
        if (str != null && (lastIndexOf = str.lastIndexOf(".")) >= 0 && lastIndexOf < str.length() - 1) {
            str2 = str.substring(lastIndexOf + 1);
        }
        return str2.toLowerCase();
    }

    public static boolean deleteFile(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return false;
        }
        return file.delete();
    }

    public static void deleteDir(File file) {
        File[] listFiles;
        if (file != null && file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    deleteDir(file2);
                }
                file2.delete();
            }
            file.delete();
        }
    }

    public static void deleteCacheFile(String str) {
        File[] listFiles;
        if (str == null || str.length() <= 0) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.isDirectory() && (file2.getName().contains(".ts") || file2.getName().contains("temp"))) {
                    file2.delete();
                }
            }
        }
    }

    public static void deleteCacheFile2TS(String str) {
        File[] listFiles;
        if (str == null || str.length() <= 0) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.isDirectory() && file2.getName().contains(".ts")) {
                    file2.delete();
                }
            }
        }
    }

    public static void deleteDir(String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        deleteDir(new File(str));
    }

    public static boolean deleteFile(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        return deleteFile(new File(str));
    }

    public static String readFile(File file, String str) {
        StringBuilder sb = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return sb.toString();
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            if (!sb.toString().equals("")) {
                                sb.append("\r\n");
                            }
                            sb.append(readLine);
                        } else {
                            bufferedReader2.close();
                            try {
                                bufferedReader2.close();
                                return sb.toString();
                            } catch (IOException e) {
                                throw new RuntimeException("IOException occurred. ", e);
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        throw new RuntimeException("IOException occurred. ", e);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                throw new RuntimeException("IOException occurred. ", e3);
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e = e4;
        }
    }

    public static String readFile(String str, String str2) {
        return readFile(new File(str), str2);
    }

    public static String readFile(File file) {
        return readFile(file, "utf-8");
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x004b, code lost:
        if (r4 == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0058, code lost:
        if (r4 == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0065, code lost:
        if (r4 == 0) goto L21;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.FileOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean fileCopy(java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L41 java.io.IOException -> L4e java.io.FileNotFoundException -> L5b
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L41 java.io.IOException -> L4e java.io.FileNotFoundException -> L5b
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31 java.io.IOException -> L35 java.io.FileNotFoundException -> L39
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31 java.io.IOException -> L35 java.io.FileNotFoundException -> L39
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28 java.io.IOException -> L2a java.io.FileNotFoundException -> L2c
        L10:
            int r0 = r2.read(r5)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28 java.io.IOException -> L2a java.io.FileNotFoundException -> L2c
            r3 = -1
            if (r0 == r3) goto L1b
            r4.write(r5, r1, r0)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28 java.io.IOException -> L2a java.io.FileNotFoundException -> L2c
            goto L10
        L1b:
            r4.flush()     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28 java.io.IOException -> L2a java.io.FileNotFoundException -> L2c
            r1 = 1
            r2.close()     // Catch: java.io.IOException -> L22
        L22:
            r4.close()     // Catch: java.io.IOException -> L68
            goto L68
        L26:
            r5 = move-exception
            goto L6b
        L28:
            r5 = move-exception
            goto L33
        L2a:
            r5 = move-exception
            goto L37
        L2c:
            r5 = move-exception
            goto L3b
        L2e:
            r5 = move-exception
            r4 = r0
            goto L6b
        L31:
            r5 = move-exception
            r4 = r0
        L33:
            r0 = r2
            goto L43
        L35:
            r5 = move-exception
            r4 = r0
        L37:
            r0 = r2
            goto L50
        L39:
            r5 = move-exception
            r4 = r0
        L3b:
            r0 = r2
            goto L5d
        L3d:
            r5 = move-exception
            r4 = r0
            r2 = r4
            goto L6b
        L41:
            r5 = move-exception
            r4 = r0
        L43:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L69
            if (r0 == 0) goto L4b
            r0.close()     // Catch: java.io.IOException -> L4b
        L4b:
            if (r4 == 0) goto L68
            goto L22
        L4e:
            r5 = move-exception
            r4 = r0
        L50:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L69
            if (r0 == 0) goto L58
            r0.close()     // Catch: java.io.IOException -> L58
        L58:
            if (r4 == 0) goto L68
            goto L22
        L5b:
            r5 = move-exception
            r4 = r0
        L5d:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L69
            if (r0 == 0) goto L65
            r0.close()     // Catch: java.io.IOException -> L65
        L65:
            if (r4 == 0) goto L68
            goto L22
        L68:
            return r1
        L69:
            r5 = move-exception
            r2 = r0
        L6b:
            if (r2 == 0) goto L70
            r2.close()     // Catch: java.io.IOException -> L70
        L70:
            if (r4 == 0) goto L75
            r4.close()     // Catch: java.io.IOException -> L75
        L75:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mabeijianxi.smallvideorecord2.FileUtils.fileCopy(java.lang.String, java.lang.String):boolean");
    }
}
