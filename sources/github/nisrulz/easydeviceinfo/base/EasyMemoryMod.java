package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyMemoryMod {
    private static final int BYTEFACTOR = 1024;
    private static final String IO_EXCEPTION = "IO Exception";
    private final Context context;

    public float convertToGb(long j) {
        return ((float) j) / 1.07374182E9f;
    }

    public float convertToKb(long j) {
        return ((float) j) / 1024.0f;
    }

    public float convertToMb(long j) {
        return ((float) j) / 1048576.0f;
    }

    public float convertToTb(long j) {
        return ((float) j) / 0.0f;
    }

    public EasyMemoryMod(Context context) {
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.RandomAccessFile] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long getTotalRAM() {
        /*
            r7 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r3 = 16
            if (r0 < r3) goto L1f
            android.app.ActivityManager$MemoryInfo r0 = new android.app.ActivityManager$MemoryInfo
            r0.<init>()
            android.content.Context r3 = r7.context
            java.lang.String r4 = "activity"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.app.ActivityManager r3 = (android.app.ActivityManager) r3
            if (r3 == 0) goto L6c
            r3.getMemoryInfo(r0)
            long r1 = r0.totalMem
            goto L6c
        L1f:
            r0 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L51
            java.lang.String r4 = "/proc/meminfo"
            java.lang.String r5 = "r"
            r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L51
            java.lang.String r0 = r3.readLine()     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L6d
            java.lang.String r4 = "\\D+"
            java.lang.String r5 = ""
            java.lang.String r0 = r0.replaceAll(r4, r5)     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L6d
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.io.IOException -> L4b java.lang.Throwable -> L6d
            long r1 = (long) r0
            r3.close()     // Catch: java.io.IOException -> L3e
            goto L6c
        L3e:
            r0 = move-exception
            boolean r3 = github.nisrulz.easydeviceinfo.common.EasyDeviceInfo.debuggable
            if (r3 == 0) goto L6c
        L43:
            java.lang.String r3 = "EasyDeviceInfo"
            java.lang.String r4 = "IO Exception"
            android.util.Log.e(r3, r4, r0)
            goto L6c
        L4b:
            r0 = move-exception
            goto L55
        L4d:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L6e
        L51:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
        L55:
            boolean r4 = github.nisrulz.easydeviceinfo.common.EasyDeviceInfo.debuggable     // Catch: java.lang.Throwable -> L6d
            if (r4 == 0) goto L60
            java.lang.String r4 = "EasyDeviceInfo"
            java.lang.String r5 = "IO Exception"
            android.util.Log.e(r4, r5, r0)     // Catch: java.lang.Throwable -> L6d
        L60:
            if (r3 == 0) goto L6c
            r3.close()     // Catch: java.io.IOException -> L66
            goto L6c
        L66:
            r0 = move-exception
            boolean r3 = github.nisrulz.easydeviceinfo.common.EasyDeviceInfo.debuggable
            if (r3 == 0) goto L6c
            goto L43
        L6c:
            return r1
        L6d:
            r0 = move-exception
        L6e:
            if (r3 == 0) goto L80
            r3.close()     // Catch: java.io.IOException -> L74
            goto L80
        L74:
            r1 = move-exception
            boolean r2 = github.nisrulz.easydeviceinfo.common.EasyDeviceInfo.debuggable
            if (r2 == 0) goto L80
            java.lang.String r2 = "EasyDeviceInfo"
            java.lang.String r3 = "IO Exception"
            android.util.Log.e(r2, r3, r1)
        L80:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: github.nisrulz.easydeviceinfo.base.EasyMemoryMod.getTotalRAM():long");
    }

    public final long getAvailableInternalMemorySize() {
        long blockSize;
        long availableBlocks;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            blockSize = statFs.getBlockSizeLong();
            availableBlocks = statFs.getAvailableBlocksLong();
        } else {
            blockSize = statFs.getBlockSize();
            availableBlocks = statFs.getAvailableBlocks();
        }
        return availableBlocks * blockSize;
    }

    public final long getTotalInternalMemorySize() {
        long blockSize;
        long blockCount;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            blockSize = statFs.getBlockSizeLong();
            blockCount = statFs.getBlockCountLong();
        } else {
            blockSize = statFs.getBlockSize();
            blockCount = statFs.getBlockCount();
        }
        return blockCount * blockSize;
    }

    public final long getAvailableExternalMemorySize() {
        long blockSize;
        long availableBlocks;
        if (externalMemoryAvailable()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                availableBlocks = statFs.getAvailableBlocksLong();
            } else {
                blockSize = statFs.getBlockSize();
                availableBlocks = statFs.getAvailableBlocks();
            }
            return availableBlocks * blockSize;
        }
        return 0L;
    }

    private boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public final long getTotalExternalMemorySize() {
        long blockSize;
        long blockCount;
        if (externalMemoryAvailable()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                blockCount = statFs.getBlockCountLong();
            } else {
                blockSize = statFs.getBlockSize();
                blockCount = statFs.getBlockCount();
            }
            return blockCount * blockSize;
        }
        return 0L;
    }
}
