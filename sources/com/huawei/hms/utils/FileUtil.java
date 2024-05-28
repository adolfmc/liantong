package com.huawei.hms.utils;

import android.content.Context;
import com.huawei.hms.support.log.HMSLog;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class FileUtil {
    public static final String LOCAL_REPORT_FILE = "hms/HwMobileServiceReport.txt";
    public static final String LOCAL_REPORT_FILE_CONFIG = "hms/config.txt";
    public static final long LOCAL_REPORT_FILE_MAX_SIZE = 10240;

    /* renamed from: a */
    private static boolean f11801a;

    /* renamed from: b */
    private static ScheduledExecutorService f11802b = Executors.newSingleThreadScheduledExecutor();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.utils.FileUtil$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5089a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ File f11803a;

        /* renamed from: b */
        final /* synthetic */ long f11804b;

        /* renamed from: c */
        final /* synthetic */ String f11805c;

        RunnableC5089a(File file, long j, String str) {
            this.f11803a = file;
            this.f11804b = j;
            this.f11805c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            RandomAccessFile randomAccessFile;
            Throwable th;
            IOException iOException;
            File file = this.f11803a;
            if (file == null) {
                HMSLog.m14112e("FileUtil", "In writeFile Failed to get local file.");
                return;
            }
            File parentFile = file.getParentFile();
            if (parentFile != null && (parentFile.mkdirs() || parentFile.isDirectory())) {
                RandomAccessFile randomAccessFile2 = null;
                try {
                    try {
                        long length = this.f11803a.length();
                        if (length > this.f11804b) {
                            String canonicalPath = this.f11803a.getCanonicalPath();
                            if (!this.f11803a.delete()) {
                                HMSLog.m14112e("FileUtil", "last file delete failed.");
                            }
                            randomAccessFile2 = new RandomAccessFile(new File(canonicalPath), "rw");
                        } else {
                            randomAccessFile = new RandomAccessFile(this.f11803a, "rw");
                            try {
                                randomAccessFile.seek(length);
                                randomAccessFile2 = randomAccessFile;
                            } catch (IOException e) {
                                iOException = e;
                                randomAccessFile2 = randomAccessFile;
                                HMSLog.m14111e("FileUtil", "writeFile exception:", iOException);
                                IOUtils.closeQuietly(randomAccessFile2);
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                                IOUtils.closeQuietly(randomAccessFile);
                                throw th;
                            }
                        }
                        randomAccessFile2.writeBytes(this.f11805c + System.getProperty("line.separator"));
                    } catch (Throwable th3) {
                        randomAccessFile = randomAccessFile2;
                        th = th3;
                    }
                } catch (IOException e2) {
                    iOException = e2;
                    randomAccessFile2 = randomAccessFile2;
                }
                IOUtils.closeQuietly(randomAccessFile2);
                return;
            }
            HMSLog.m14112e("FileUtil", "In writeFile, Failed to create directory.");
        }
    }

    public static boolean verifyHash(String str, File file) {
        byte[] digest = SHA256.digest(file);
        return digest != null && HEX.encodeHexString(digest, true).equalsIgnoreCase(str);
    }

    public static void writeFile(File file, String str, long j) {
        f11802b.execute(new RunnableC5089a(file, j, str));
    }

    public static void writeFileReport(Context context, File file, File file2, String str, long j, int i) {
        if (file != null && file.isFile() && file.exists()) {
            if (!f11801a) {
                if (file2 != null && file2.exists() && !file2.delete()) {
                    HMSLog.m14112e("FileUtil", "file delete failed.");
                }
                f11801a = true;
            }
            writeFile(file2, str + "|" + j + "|" + i, 10240L);
        }
    }
}
