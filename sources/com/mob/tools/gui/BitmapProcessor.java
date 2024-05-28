package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.mob.commons.C5873u;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.ResHelper;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BitmapProcessor {
    private static final int CAPACITY = 3;
    private static final int MAX_CACHE_SIZE = 50;
    private static final int MAX_CACHE_TIME = 60000;
    private static final int MAX_REQ_TIME = 20000;
    private static final int MAX_SIZE = 100;
    private static final int OVERFLOW_SIZE = 120;
    private static final int SCAN_INTERVAL = 20000;
    private static File cacheDir;
    private static CachePoolInner<String, SoftReference<Bitmap>> cachePool;
    private static ManagerThread manager;
    private static ArrayList<ImageReq> netReqTPS;
    private static ArrayList<ImageReq> reqList;
    private static NetworkHelper.NetworkTimeOut timeout = new NetworkHelper.NetworkTimeOut();
    private static boolean work;
    private static WorkerThread[] workerList;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface BitmapCallback {
        void onImageGot(String str, Bitmap bitmap);
    }

    static {
        NetworkHelper.NetworkTimeOut networkTimeOut = timeout;
        networkTimeOut.connectionTimeout = 5000;
        networkTimeOut.readTimout = 20000 - networkTimeOut.connectionTimeout;
        reqList = new ArrayList<>();
        netReqTPS = new ArrayList<>();
        workerList = new WorkerThread[3];
        cachePool = new CachePoolInner<>(50);
    }

    public static synchronized void prepare(Context context) {
        synchronized (BitmapProcessor.class) {
            cacheDir = new File(ResHelper.getImageCachePath(context));
        }
    }

    public static synchronized void start() {
        synchronized (BitmapProcessor.class) {
            if (!work) {
                work = true;
                manager = new ManagerThread();
            }
        }
    }

    public static synchronized void stop() {
        synchronized (BitmapProcessor.class) {
            if (work) {
                work = false;
                synchronized (reqList) {
                    reqList.clear();
                    cachePool.clear();
                }
                manager.quit();
            }
        }
    }

    public static synchronized void process(String str, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, null, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, true, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, boolean z, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, z, true, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, boolean z, boolean z2, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, z, z2, 0L, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, boolean z, boolean z2, long j, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            if (str == null) {
                return;
            }
            synchronized (reqList) {
                int size = reqList.size();
                for (int i = 0; i < size; i++) {
                    ImageReq imageReq = reqList.get(i);
                    boolean equals = imageReq.url.equals(str);
                    boolean z3 = (imageReq.bitmapDesiredOptions == null && bitmapDesiredOptions == null) || (imageReq.bitmapDesiredOptions != null && imageReq.bitmapDesiredOptions.equals(bitmapDesiredOptions));
                    if (equals && z3) {
                        if (bitmapCallback != null && imageReq.callbacks.indexOf(bitmapCallback) == -1) {
                            imageReq.callbacks.add(bitmapCallback);
                        }
                        start();
                        return;
                    }
                }
                ImageReq imageReq2 = new ImageReq();
                imageReq2.url = str;
                imageReq2.bitmapDesiredOptions = bitmapDesiredOptions;
                imageReq2.useRamCache = z;
                imageReq2.diskCacheTime = j;
                imageReq2.useDiskCache = z2;
                if (bitmapCallback != null) {
                    imageReq2.callbacks.add(bitmapCallback);
                }
                synchronized (reqList) {
                    reqList.add(imageReq2);
                    if (reqList.size() > 120) {
                        while (reqList.size() > 100) {
                            reqList.remove(0);
                        }
                    }
                }
                start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getCacheKey(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        if (bitmapDesiredOptions == null) {
            return str;
        }
        return str + bitmapDesiredOptions.toString();
    }

    public static Bitmap getBitmapFromCache(String str) {
        return getBitmapFromCache(str, null);
    }

    public static Bitmap getBitmapFromCache(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        CachePoolInner<String, SoftReference<Bitmap>> cachePoolInner = cachePool;
        if (cachePoolInner == null || str == null || cachePoolInner.get(getCacheKey(str, bitmapDesiredOptions)) == null) {
            return null;
        }
        return cachePool.get(getCacheKey(str, bitmapDesiredOptions)).get();
    }

    public static void removeBitmapFromRamCache(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        CachePoolInner<String, SoftReference<Bitmap>> cachePoolInner = cachePool;
        if (cachePoolInner != null) {
            cachePoolInner.put(getCacheKey(str, bitmapDesiredOptions), null);
        }
    }

    public static void deleteCachedFile(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        removeBitmapFromRamCache(str, bitmapDesiredOptions);
        new File(cacheDir, Data.MD5(str)).delete();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.mob.tools.gui.BitmapProcessor$1] */
    public static void deleteCacheDir(boolean z) {
        if (z) {
            deleteCacheDir();
        } else {
            new Thread() { // from class: com.mob.tools.gui.BitmapProcessor.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    BitmapProcessor.deleteCacheDir();
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void deleteCacheDir() {
        synchronized (BitmapProcessor.class) {
            File file = new File(cacheDir.getPath());
            if (file.isDirectory()) {
                for (String str : file.list()) {
                    new File(file, str).delete();
                }
            }
        }
    }

    public static long getCacheSizeInByte() {
        long j = 0;
        for (File file : new File(cacheDir.getPath()).listFiles()) {
            j += file.length();
        }
        return j;
    }

    public static String getCacheSizeText() {
        float cacheSizeInByte = (float) getCacheSizeInByte();
        if (cacheSizeInByte < 1024.0f) {
            return String.format(Locale.CHINA, "%.02f", Float.valueOf(cacheSizeInByte)) + " B";
        }
        float f = cacheSizeInByte / 1024.0f;
        if (f < 1000.0f) {
            return String.format(Locale.CHINA, "%.02f", Float.valueOf(f)) + " KB";
        }
        return String.format(Locale.CHINA, "%.02f", Float.valueOf(f / 1204.0f)) + " MB";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ManagerThread implements Handler.Callback {
        private Handler handler = MobHandlerThread.newHandler(new Runnable() { // from class: com.mob.tools.gui.BitmapProcessor.ManagerThread.1
            @Override // java.lang.Runnable
            public void run() {
                int i = 0;
                while (i < BitmapProcessor.workerList.length) {
                    if (BitmapProcessor.workerList[i] == null) {
                        BitmapProcessor.workerList[i] = new WorkerThread();
                        BitmapProcessor.workerList[i].setName("worker " + i);
                        BitmapProcessor.workerList[i].localType = i == 0;
                        BitmapProcessor.workerList[i].start();
                    }
                    i++;
                }
            }
        }, this);

        public ManagerThread() {
            this.handler.sendEmptyMessageDelayed(1, 20000L);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (BitmapProcessor.cachePool != null) {
                BitmapProcessor.cachePool.trimBeforeTime(System.currentTimeMillis() - 60000);
            }
            int size = BitmapProcessor.cachePool == null ? 0 : BitmapProcessor.cachePool.size();
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d(">>>> BitmapProcessor.cachePool: " + size, new Object[0]);
            int size2 = BitmapProcessor.reqList == null ? 0 : BitmapProcessor.reqList.size();
            NLog mobLog2 = MobLog.getInstance();
            mobLog2.m11342d(">>>> BitmapProcessor.reqList: " + size2, new Object[0]);
            if (BitmapProcessor.work) {
                this.handler.sendEmptyMessageDelayed(1, 20000L);
            }
            return false;
        }

        public void quit() {
            this.handler.removeMessages(1);
            this.handler.getLooper().quit();
            for (int i = 0; i < BitmapProcessor.workerList.length; i++) {
                if (BitmapProcessor.workerList[i] != null) {
                    BitmapProcessor.workerList[i].interrupt();
                    BitmapProcessor.workerList[i] = null;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class WorkerThread extends Thread {
        private ImageReq curReq;
        private boolean localType;

        private WorkerThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (BitmapProcessor.work) {
                try {
                    if (this.localType) {
                        doLocalTask();
                    } else {
                        doNetworkTask();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().m11325w(th);
                }
            }
        }

        private void doLocalTask() throws Throwable {
            Bitmap bitmap;
            ImageReq imageReq;
            SoftReference softReference;
            synchronized (BitmapProcessor.reqList) {
                bitmap = null;
                imageReq = BitmapProcessor.reqList.size() > 0 ? (ImageReq) BitmapProcessor.reqList.remove(0) : null;
            }
            if (imageReq == null) {
                try {
                    Thread.sleep(30L);
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            if (imageReq.useRamCache && (softReference = (SoftReference) BitmapProcessor.cachePool.get(BitmapProcessor.getCacheKey(imageReq.url, imageReq.bitmapDesiredOptions))) != null) {
                bitmap = (Bitmap) softReference.get();
            }
            if (bitmap != null) {
                this.curReq = imageReq;
                this.curReq.worker = this;
                imageReq.throwComplete(bitmap);
            } else if (!imageReq.useDiskCache || BitmapProcessor.cacheDir == null || !new File(BitmapProcessor.cacheDir, Data.MD5(imageReq.url)).exists()) {
                synchronized (BitmapProcessor.reqList) {
                    if (BitmapProcessor.netReqTPS.size() > 100) {
                        synchronized (BitmapProcessor.reqList) {
                            while (BitmapProcessor.reqList.size() > 0) {
                                BitmapProcessor.reqList.remove(0);
                            }
                        }
                        BitmapProcessor.netReqTPS.remove(0);
                    }
                }
                BitmapProcessor.netReqTPS.add(imageReq);
            } else {
                doTask(imageReq);
            }
        }

        private void doNetworkTask() throws Throwable {
            Bitmap bitmap;
            ImageReq imageReq;
            SoftReference softReference;
            synchronized (BitmapProcessor.netReqTPS) {
                bitmap = null;
                imageReq = BitmapProcessor.netReqTPS.size() > 0 ? (ImageReq) BitmapProcessor.netReqTPS.remove(0) : null;
            }
            if (imageReq == null) {
                synchronized (BitmapProcessor.reqList) {
                    if (BitmapProcessor.reqList.size() > 0) {
                        imageReq = (ImageReq) BitmapProcessor.reqList.remove(0);
                    }
                }
            }
            if (imageReq == null) {
                try {
                    Thread.sleep(30L);
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            if (imageReq.useRamCache && (softReference = (SoftReference) BitmapProcessor.cachePool.get(BitmapProcessor.getCacheKey(imageReq.url, imageReq.bitmapDesiredOptions))) != null) {
                bitmap = (Bitmap) softReference.get();
            }
            if (bitmap != null) {
                this.curReq = imageReq;
                this.curReq.worker = this;
                imageReq.throwComplete(bitmap);
                return;
            }
            doTask(imageReq);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x009c A[Catch: Throwable -> 0x00da, TryCatch #0 {Throwable -> 0x00da, blocks: (B:3:0x0001, B:5:0x001f, B:7:0x0029, B:9:0x002f, B:11:0x0040, B:12:0x0043, B:14:0x0049, B:16:0x004f, B:18:0x0055, B:20:0x005b, B:23:0x0068, B:26:0x009c, B:28:0x00a2, B:29:0x00ba, B:31:0x00c1, B:30:0x00be, B:24:0x0092, B:32:0x00c4), top: B:37:0x0001 }] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00be A[Catch: Throwable -> 0x00da, TryCatch #0 {Throwable -> 0x00da, blocks: (B:3:0x0001, B:5:0x001f, B:7:0x0029, B:9:0x002f, B:11:0x0040, B:12:0x0043, B:14:0x0049, B:16:0x004f, B:18:0x0055, B:20:0x005b, B:23:0x0068, B:26:0x009c, B:28:0x00a2, B:29:0x00ba, B:31:0x00c1, B:30:0x00be, B:24:0x0092, B:32:0x00c4), top: B:37:0x0001 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void doTask(final com.mob.tools.gui.BitmapProcessor.ImageReq r11) throws java.lang.Throwable {
            /*
                r10 = this;
                r0 = 0
                r10.curReq = r11     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.gui.BitmapProcessor$ImageReq r1 = r10.curReq     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.gui.BitmapProcessor.ImageReq.access$1402(r1, r10)     // Catch: java.lang.Throwable -> Lda
                java.lang.String r1 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$000(r11)     // Catch: java.lang.Throwable -> Lda
                java.lang.String r1 = com.mob.tools.utils.Data.MD5(r1)     // Catch: java.lang.Throwable -> Lda
                java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> Lda
                java.io.File r3 = com.mob.tools.gui.BitmapProcessor.access$1600()     // Catch: java.lang.Throwable -> Lda
                r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> Lda
                boolean r3 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$500(r11)     // Catch: java.lang.Throwable -> Lda
                if (r3 == 0) goto L43
                long r3 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$400(r11)     // Catch: java.lang.Throwable -> Lda
                r5 = 0
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 <= 0) goto L43
                boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> Lda
                if (r3 == 0) goto L43
                long r3 = r2.lastModified()     // Catch: java.lang.Throwable -> Lda
                long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lda
                long r7 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$400(r11)     // Catch: java.lang.Throwable -> Lda
                long r3 = r3 + r7
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 >= 0) goto L43
                r2.delete()     // Catch: java.lang.Throwable -> Lda
            L43:
                boolean r3 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$500(r11)     // Catch: java.lang.Throwable -> Lda
                if (r3 == 0) goto Lc4
                java.io.File r3 = com.mob.tools.gui.BitmapProcessor.access$1600()     // Catch: java.lang.Throwable -> Lda
                if (r3 == 0) goto Lc4
                boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> Lda
                if (r3 == 0) goto Lc4
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r3 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$100(r11)     // Catch: java.lang.Throwable -> Lda
                if (r3 == 0) goto L92
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r3 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$100(r11)     // Catch: java.lang.Throwable -> Lda
                java.lang.String r4 = ""
                boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> Lda
                if (r3 == 0) goto L68
                goto L92
            L68:
                java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> Lda
                java.io.File r3 = com.mob.tools.gui.BitmapProcessor.access$1600()     // Catch: java.lang.Throwable -> Lda
                r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> Lda
                java.lang.String r4 = r2.getAbsolutePath()     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$100(r11)     // Catch: java.lang.Throwable -> Lda
                int r5 = r1.desiredWidth     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$100(r11)     // Catch: java.lang.Throwable -> Lda
                int r6 = r1.desiredHeight     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$100(r11)     // Catch: java.lang.Throwable -> Lda
                int r7 = r1.quality     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$100(r11)     // Catch: java.lang.Throwable -> Lda
                long r8 = r1.maxBytes     // Catch: java.lang.Throwable -> Lda
                android.graphics.Bitmap r1 = com.mob.tools.utils.BitmapHelper.getBitmapByCompressQuality(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lda
                goto L9a
            L92:
                java.lang.String r1 = r2.getAbsolutePath()     // Catch: java.lang.Throwable -> Lda
                android.graphics.Bitmap r1 = com.mob.tools.utils.BitmapHelper.getBitmap(r1)     // Catch: java.lang.Throwable -> Lda
            L9a:
                if (r1 == 0) goto Lbe
                boolean r2 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$300(r11)     // Catch: java.lang.Throwable -> Lda
                if (r2 == 0) goto Lba
                com.mob.tools.gui.CachePoolInner r2 = com.mob.tools.gui.BitmapProcessor.access$1000()     // Catch: java.lang.Throwable -> Lda
                java.lang.String r3 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$000(r11)     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r4 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$100(r11)     // Catch: java.lang.Throwable -> Lda
                java.lang.String r3 = com.mob.tools.gui.BitmapProcessor.access$1300(r3, r4)     // Catch: java.lang.Throwable -> Lda
                java.lang.ref.SoftReference r4 = new java.lang.ref.SoftReference     // Catch: java.lang.Throwable -> Lda
                r4.<init>(r1)     // Catch: java.lang.Throwable -> Lda
                r2.put(r3, r4)     // Catch: java.lang.Throwable -> Lda
            Lba:
                com.mob.tools.gui.BitmapProcessor.ImageReq.access$1500(r11, r1)     // Catch: java.lang.Throwable -> Lda
                goto Lc1
            Lbe:
                com.mob.tools.gui.BitmapProcessor.ImageReq.access$1800(r11)     // Catch: java.lang.Throwable -> Lda
            Lc1:
                r10.curReq = r0     // Catch: java.lang.Throwable -> Lda
                goto Le7
            Lc4:
                com.mob.tools.network.NetworkHelper r2 = new com.mob.tools.network.NetworkHelper     // Catch: java.lang.Throwable -> Lda
                r2.<init>()     // Catch: java.lang.Throwable -> Lda
                java.lang.String r3 = com.mob.tools.gui.BitmapProcessor.ImageReq.access$000(r11)     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.gui.BitmapProcessor$WorkerThread$1 r4 = new com.mob.tools.gui.BitmapProcessor$WorkerThread$1     // Catch: java.lang.Throwable -> Lda
                r4.<init>()     // Catch: java.lang.Throwable -> Lda
                com.mob.tools.network.NetworkHelper$NetworkTimeOut r1 = com.mob.tools.gui.BitmapProcessor.access$2100()     // Catch: java.lang.Throwable -> Lda
                r2.rawGet(r3, r4, r1)     // Catch: java.lang.Throwable -> Lda
                goto Le7
            Lda:
                r1 = move-exception
                com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
                r2.m11325w(r1)
                com.mob.tools.gui.BitmapProcessor.ImageReq.access$1800(r11)
                r10.curReq = r0
            Le7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.BitmapProcessor.WorkerThread.doTask(com.mob.tools.gui.BitmapProcessor$ImageReq):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void saveFile(InputStream inputStream, File file) {
            Closeable[] closeableArr;
            FileOutputStream fileOutputStream;
            FileOutputStream fileOutputStream2 = null;
            try {
                if (file.exists()) {
                    file.delete();
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable unused) {
            }
            try {
                byte[] bArr = new byte[256];
                int read = inputStream.read(bArr);
                while (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                    read = inputStream.read(bArr);
                }
                fileOutputStream.flush();
                closeableArr = new Closeable[]{fileOutputStream};
            } catch (Throwable unused2) {
                fileOutputStream2 = fileOutputStream;
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    closeableArr = new Closeable[]{fileOutputStream2};
                    C5873u.m12179a(closeableArr);
                } catch (Throwable th) {
                    C5873u.m12179a(fileOutputStream2);
                    throw th;
                }
            }
            C5873u.m12179a(closeableArr);
        }

        @Override // java.lang.Thread
        public void interrupt() {
            try {
                super.interrupt();
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static class PatchInputStream extends FilterInputStream {

        /* renamed from: in */
        InputStream f14987in;

        protected PatchInputStream(InputStream inputStream) {
            super(inputStream);
            this.f14987in = inputStream;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.f14987in.skip(j - j2);
                if (skip == 0) {
                    break;
                }
                j2 += skip;
            }
            return j2;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ImageReq {
        private BitmapDesiredOptions bitmapDesiredOptions;
        private String url;
        private WorkerThread worker;
        private boolean useRamCache = true;
        private boolean useDiskCache = true;
        private long diskCacheTime = 0;
        private long reqTime = System.currentTimeMillis();
        private ArrayList<BitmapCallback> callbacks = new ArrayList<>();

        /* JADX INFO: Access modifiers changed from: private */
        public void throwComplete(Bitmap bitmap) {
            Iterator<BitmapCallback> it = this.callbacks.iterator();
            while (it.hasNext()) {
                it.next().onImageGot(this.url, bitmap);
            }
            this.callbacks.clear();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void throwError() {
            Iterator<BitmapCallback> it = this.callbacks.iterator();
            while (it.hasNext()) {
                it.next().onImageGot(this.url, null);
            }
            this.callbacks.clear();
        }

        public String toString() {
            return "url=" + this.url + "time=" + this.reqTime + "worker=" + this.worker.getName() + " (" + this.worker.getId() + "";
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class BitmapDesiredOptions {
        public int desiredWidth = 0;
        public int desiredHeight = 0;
        public long maxBytes = 0;
        public int quality = 0;

        public boolean equals(Object obj) {
            return super.equals(obj) || (obj != null && obj.toString().equals(toString()));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            int i = this.desiredWidth;
            if (i > 0) {
                sb.append(i);
            }
            int i2 = this.desiredHeight;
            if (i2 > 0) {
                sb.append(i2);
            }
            long j = this.maxBytes;
            if (j > 0) {
                sb.append(j);
            }
            int i3 = this.quality;
            if (i3 > 0) {
                sb.append(i3);
            }
            return sb.toString();
        }
    }
}
