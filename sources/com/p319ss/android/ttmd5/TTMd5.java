package com.p319ss.android.ttmd5;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.ttmd5.TTMd5 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TTMd5 {
    private static final int DEFAULT_SAMPLE_COUNT = 9;
    private static final int DEFAULT_SAMPLE_SIZE = 8192;
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String PROTOCOL = "ttmd5";
    private static final String TAG = "TTMd5";
    private static final int VERSION_MAIN = 1;
    private static final int VERSION_SUB = 1;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.ss.android.ttmd5.TTMd5$CHECK_MD5_STATUS */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public @interface CHECK_MD5_STATUS {
        public static final int FILE_NOT_EXIST = 5;
        public static final int GET_FILE_MD5_ERROR = 6;
        public static final int MD5_EMPTY = 2;
        public static final int MD5_MATCH = 0;
        public static final int MD5_NOT_MATCH = 1;
        public static final int TTMD5_TAG_PARSER_ERROR = 4;
        public static final int TTMD5_VERSION_NOT_SUPPORT = 3;
        public static final int UNKNOWN_ERROR = 99;
    }

    public static String ttmd5(File file) {
        return ttmd5(file, 9, 8192L);
    }

    public static String ttmd5(File file, int i) {
        return ttmd5(file, i, 8192L);
    }

    public static String ttmd5(File file, int i, long j) {
        if (file != null) {
            try {
                return !file.exists() ? "" : _ttmd5(file, i, j);
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public static String md5(File file) {
        return ttmd5(file, -1, -1L);
    }

    public static String ttmd5(IRandomAccess iRandomAccess) {
        return ttmd5(iRandomAccess, 9, 8192L);
    }

    public static String ttmd5(IRandomAccess iRandomAccess, int i) {
        return ttmd5(iRandomAccess, i, 8192L);
    }

    public static String ttmd5(IRandomAccess iRandomAccess, int i, long j) {
        if (iRandomAccess == null) {
            return "";
        }
        try {
            return _ttmd5(iRandomAccess, i, j);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int checkMd5(String str, File file) {
        return checkMd5(str, file, null);
    }

    public static int checkMd5(String str, File file, IRandomAccess iRandomAccess) {
        String _ttmd5;
        if (str == null || str.length() == 0) {
            return 2;
        }
        try {
            if (iRandomAccess != null) {
                if (iRandomAccess.length() <= 0) {
                    try {
                        iRandomAccess.close();
                    } catch (Throwable unused) {
                    }
                    return 5;
                }
            } else if (file == null || !file.exists()) {
                return 5;
            }
            int i = -1;
            long j = -1;
            try {
                TTMd5Args parserTTMd5Args = parserTTMd5Args(str);
                if (parserTTMd5Args != null) {
                    if (parserTTMd5Args.versionMain > 1) {
                        return 3;
                    }
                    i = parserTTMd5Args.sampleCount;
                    j = parserTTMd5Args.sampleSize;
                }
                TTMd5Args tTMd5Args = null;
                if (iRandomAccess != null) {
                    _ttmd5 = _ttmd5(iRandomAccess, i, j);
                } else {
                    _ttmd5 = _ttmd5(file, i, j);
                }
                if (_ttmd5 != null && _ttmd5.length() != 0) {
                    if (parserTTMd5Args != null && (parserTTMd5Args.versionMain != 1 || parserTTMd5Args.versionSub != 1)) {
                        if (parserTTMd5Args.realMd5 != null) {
                            try {
                                tTMd5Args = parserTTMd5Args(_ttmd5);
                            } catch (Throwable unused2) {
                            }
                            if (tTMd5Args != null && parserTTMd5Args.sampleCount == tTMd5Args.sampleCount && parserTTMd5Args.sampleSize == tTMd5Args.sampleSize && parserTTMd5Args.realMd5.equals(tTMd5Args.realMd5)) {
                                return 0;
                            }
                        }
                    }
                    return _ttmd5.equals(str) ? 0 : 1;
                }
                return 6;
            } catch (Throwable unused3) {
                return 4;
            }
        } catch (Throwable unused4) {
            return 99;
        }
    }

    public static int checkMd5(File file, File file2) {
        if (file == null || file2 == null) {
            return 5;
        }
        try {
            if (file.exists() && file2.exists()) {
                if (file == file2) {
                    return 0;
                }
                return _ttmd5(file, 9, 8192L).equals(_ttmd5(file2, 9, 8192L)) ? 0 : 1;
            }
            return 5;
        } catch (Throwable th) {
            th.printStackTrace();
            return 99;
        }
    }

    private static String _ttmd5(File file, int i, long j) throws Exception {
        return _ttmd5(new FileRandomAccess(file), i, j);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:5|6|(6:13|14|(3:16|(1:18)|19)|(1:21)|22|(4:31|32|33|34)(3:26|27|28))|38|14|(0)|(0)|22|(1:24)|31|32|33|34) */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003f A[Catch: all -> 0x009d, TryCatch #3 {all -> 0x009d, blocks: (B:6:0x000d, B:10:0x001a, B:15:0x002d, B:17:0x003f, B:19:0x004c, B:21:0x0065, B:22:0x006f, B:28:0x0081), top: B:43:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0065 A[Catch: all -> 0x009d, TryCatch #3 {all -> 0x009d, blocks: (B:6:0x000d, B:10:0x001a, B:15:0x002d, B:17:0x003f, B:19:0x004c, B:21:0x0065, B:22:0x006f, B:28:0x0081), top: B:43:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String _ttmd5(com.p319ss.android.ttmd5.IRandomAccess r21, int r22, long r23) throws java.lang.Exception {
        /*
            r0 = r22
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)
            if (r1 != 0) goto Ld
            java.lang.String r0 = ""
            return r0
        Ld:
            long r9 = r21.length()     // Catch: java.lang.Throwable -> L9d
            r11 = 1
            if (r0 <= 0) goto L2b
            r2 = 0
            int r2 = (r23 > r2 ? 1 : (r23 == r2 ? 0 : -1))
            if (r2 <= 0) goto L2b
            long r2 = (long) r0     // Catch: java.lang.Throwable -> L9d
            long r2 = r2 * r23
            r4 = 8
            long r4 = r4 * r9
            r6 = 10
            long r4 = r4 / r6
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L28
            goto L2b
        L28:
            r12 = r23
            goto L2d
        L2b:
            r12 = r9
            r0 = r11
        L2d:
            r2 = 8192(0x2000, float:1.14794E-41)
            byte[] r14 = new byte[r2]     // Catch: java.lang.Throwable -> L9d
            r15 = 0
            r2 = r21
            r3 = r1
            r4 = r14
            r5 = r15
            r7 = r12
            updateSample(r2, r3, r4, r5, r7)     // Catch: java.lang.Throwable -> L9d
            r2 = 2
            if (r0 <= r2) goto L63
            long r2 = (long) r0     // Catch: java.lang.Throwable -> L9d
            long r2 = r2 * r12
            long r2 = r9 - r2
            int r7 = r0 + (-1)
            long r4 = (long) r7     // Catch: java.lang.Throwable -> L9d
            long r17 = r2 / r4
            r2 = r15
            r15 = r11
        L4a:
            if (r15 >= r7) goto L63
            long r4 = r12 + r17
            long r19 = r2 + r4
            r2 = r21
            r3 = r1
            r4 = r14
            r5 = r19
            r16 = r7
            r7 = r12
            updateSample(r2, r3, r4, r5, r7)     // Catch: java.lang.Throwable -> L9d
            int r15 = r15 + 1
            r7 = r16
            r2 = r19
            goto L4a
        L63:
            if (r0 <= r11) goto L6f
            long r5 = r9 - r12
            r2 = r21
            r3 = r1
            r4 = r14
            r7 = r12
            updateSample(r2, r3, r4, r5, r7)     // Catch: java.lang.Throwable -> L9d
        L6f:
            byte[] r1 = r1.digest()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r1 = toHexString(r1)     // Catch: java.lang.Throwable -> L9d
            if (r0 != r11) goto L81
            int r2 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
            if (r2 != 0) goto L81
            r21.close()     // Catch: java.lang.Throwable -> L80
        L80:
            return r1
        L81:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9d
            r2.<init>()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r0 = generateTTMd5Tag(r0, r12)     // Catch: java.lang.Throwable -> L9d
            r2.append(r0)     // Catch: java.lang.Throwable -> L9d
            java.lang.String r0 = ";"
            r2.append(r0)     // Catch: java.lang.Throwable -> L9d
            r2.append(r1)     // Catch: java.lang.Throwable -> L9d
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L9d
            r21.close()     // Catch: java.lang.Throwable -> L9c
        L9c:
            return r0
        L9d:
            r0 = move-exception
            r21.close()     // Catch: java.lang.Throwable -> La1
        La1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.ttmd5.TTMd5._ttmd5(com.ss.android.ttmd5.IRandomAccess, int, long):java.lang.String");
    }

    private static void updateSample(IRandomAccess iRandomAccess, MessageDigest messageDigest, byte[] bArr, long j, long j2) throws IOException {
        iRandomAccess.seek(j, j2);
        long j3 = 0;
        while (j3 < j2) {
            int read = iRandomAccess.read(bArr, 0, (int) Math.min(j2 - j3, bArr.length));
            if (read <= 0) {
                throw new IOException("updateSample unexpected readCount <= 0, readCount = " + read + ", readTotalCount = " + j3 + ", sampleSize = " + j2);
            }
            messageDigest.update(bArr, 0, read);
            j3 += read;
        }
    }

    private static String toHexString(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        int length = bArr.length;
        int i = length * 2;
        char[] cArr = new char[i];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = bArr[i3 + 0] & 255;
            int i5 = i2 + 1;
            char[] cArr2 = HEX_CHARS;
            cArr[i2] = cArr2[i4 >> 4];
            i2 = i5 + 1;
            cArr[i5] = cArr2[i4 & 15];
        }
        return new String(cArr, 0, i);
    }

    private static String generateTTMd5Tag(int i, long j) {
        return "ttmd5:1:1:" + encryptionNum(i) + "g" + encryptionNum(j);
    }

    private static TTMd5Args parserTTMd5Args(String str) throws Exception {
        if (str.startsWith("ttmd5:")) {
            String[] split = str.split(";");
            String[] split2 = split[0].split(":");
            TTMd5Args tTMd5Args = new TTMd5Args();
            tTMd5Args.versionMain = Integer.parseInt(split2[1]);
            if (tTMd5Args.versionMain > 1) {
                return tTMd5Args;
            }
            tTMd5Args.versionSub = Integer.parseInt(split2[2]);
            String[] split3 = split2[3].split("g");
            tTMd5Args.sampleCount = (int) decryptNum(split3[0]);
            tTMd5Args.sampleSize = decryptNum(split3[1]);
            tTMd5Args.realMd5 = split[1];
            return tTMd5Args;
        }
        return null;
    }

    private static String encryptionNum(long j) {
        return Long.toHexString((j << 4) + 31);
    }

    private static long decryptNum(String str) throws RuntimeException {
        return (Long.parseLong(str, 16) - 31) >> 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.ttmd5.TTMd5$TTMd5Args */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TTMd5Args {
        private String realMd5;
        private int sampleCount;
        private long sampleSize;
        private int versionMain;
        private int versionSub;

        private TTMd5Args() {
        }
    }
}
