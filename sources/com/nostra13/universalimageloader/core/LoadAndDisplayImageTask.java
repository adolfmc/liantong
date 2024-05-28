package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.C6846L;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LoadAndDisplayImageTask implements IoUtils.CopyListener, Runnable {
    private static final String ERROR_NO_IMAGE_STREAM = "No stream for image [%s]";
    private static final String ERROR_POST_PROCESSOR_NULL = "Post-processor returned null [%s]";
    private static final String ERROR_PRE_PROCESSOR_NULL = "Pre-processor returned null [%s]";
    private static final String ERROR_PROCESSOR_FOR_DISK_CACHE_NULL = "Bitmap processor for disk cache returned null [%s]";
    private static final String LOG_CACHE_IMAGE_IN_MEMORY = "Cache image in memory [%s]";
    private static final String LOG_CACHE_IMAGE_ON_DISK = "Cache image on disk [%s]";
    private static final String LOG_DELAY_BEFORE_LOADING = "Delay %d ms before loading...  [%s]";
    private static final String LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING = "...Get cached bitmap from memory after waiting. [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_DISK_CACHE = "Load image from disk cache [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_NETWORK = "Load image from network [%s]";
    private static final String LOG_POSTPROCESS_IMAGE = "PostProcess image before displaying [%s]";
    private static final String LOG_PREPROCESS_IMAGE = "PreProcess image before caching in memory [%s]";
    private static final String LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISK = "Process image before cache on disk [%s]";
    private static final String LOG_RESIZE_CACHED_IMAGE_FILE = "Resize image in disk cache [%s]";
    private static final String LOG_RESUME_AFTER_PAUSE = ".. Resume loading [%s]";
    private static final String LOG_START_DISPLAY_IMAGE_TASK = "Start display image task [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_REUSED = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String LOG_TASK_INTERRUPTED = "Task was interrupted [%s]";
    private static final String LOG_WAITING_FOR_IMAGE_LOADED = "Image already is loading. Waiting... [%s]";
    private static final String LOG_WAITING_FOR_RESUME = "ImageLoader is paused. Waiting...  [%s]";
    private final ImageLoaderConfiguration configuration;
    private final ImageDecoder decoder;
    private final ImageDownloader downloader;
    private final ImageLoaderEngine engine;
    private final Handler handler;
    final ImageAware imageAware;
    private final ImageLoadingInfo imageLoadingInfo;
    final ImageLoadingListener listener;
    private LoadedFrom loadedFrom = LoadedFrom.NETWORK;
    private final String memoryCacheKey;
    private final ImageDownloader networkDeniedDownloader;
    final DisplayImageOptions options;
    final ImageLoadingProgressListener progressListener;
    private final ImageDownloader slowNetworkDownloader;
    private final boolean syncLoading;
    private final ImageSize targetSize;
    final String uri;

    public LoadAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.engine = imageLoaderEngine;
        this.imageLoadingInfo = imageLoadingInfo;
        this.handler = handler;
        this.configuration = imageLoaderEngine.configuration;
        this.downloader = this.configuration.downloader;
        this.networkDeniedDownloader = this.configuration.networkDeniedDownloader;
        this.slowNetworkDownloader = this.configuration.slowNetworkDownloader;
        this.decoder = this.configuration.decoder;
        this.uri = imageLoadingInfo.uri;
        this.memoryCacheKey = imageLoadingInfo.memoryCacheKey;
        this.imageAware = imageLoadingInfo.imageAware;
        this.targetSize = imageLoadingInfo.targetSize;
        this.options = imageLoadingInfo.options;
        this.listener = imageLoadingInfo.listener;
        this.progressListener = imageLoadingInfo.progressListener;
        this.syncLoading = this.options.isSyncLoading();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00d2 A[Catch: all -> 0x00fb, TaskCancelledException -> 0x00fd, Merged into TryCatch #1 {all -> 0x00fb, TaskCancelledException -> 0x00fd, blocks: (B:12:0x0033, B:14:0x0042, B:17:0x0049, B:32:0x00b3, B:34:0x00bb, B:36:0x00d2, B:37:0x00dd, B:18:0x0059, B:22:0x0063, B:24:0x0071, B:26:0x0088, B:28:0x0095, B:30:0x009d, B:42:0x00fd), top: B:47:0x0033 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.run():void");
    }

    private boolean waitIfPaused() {
        AtomicBoolean pause = this.engine.getPause();
        if (pause.get()) {
            synchronized (this.engine.getPauseLock()) {
                if (pause.get()) {
                    C6846L.m8370d("ImageLoader is paused. Waiting...  [%s]", this.memoryCacheKey);
                    try {
                        this.engine.getPauseLock().wait();
                        C6846L.m8370d(".. Resume loading [%s]", this.memoryCacheKey);
                    } catch (InterruptedException unused) {
                        C6846L.m8369e("Task was interrupted [%s]", this.memoryCacheKey);
                        return true;
                    }
                }
            }
        }
        return isTaskNotActual();
    }

    private boolean delayIfNeed() {
        if (this.options.shouldDelayBeforeLoading()) {
            C6846L.m8370d("Delay %d ms before loading...  [%s]", Integer.valueOf(this.options.getDelayBeforeLoading()), this.memoryCacheKey);
            try {
                Thread.sleep(this.options.getDelayBeforeLoading());
                return isTaskNotActual();
            } catch (InterruptedException unused) {
                C6846L.m8369e("Task was interrupted [%s]", this.memoryCacheKey);
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x004d, code lost:
        if (r1.getHeight() > 0) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Bitmap tryLoadBitmap() throws com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException {
        /*
            r9 = this;
            r0 = 0
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r1 = r9.configuration     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            com.nostra13.universalimageloader.cache.disc.DiskCache r1 = r1.diskCache     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            java.lang.String r2 = r9.uri     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            java.io.File r1 = r1.get(r2)     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L40
            boolean r4 = r1.exists()     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            if (r4 == 0) goto L40
            long r4 = r1.length()     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto L40
            java.lang.String r4 = "Load image from disk cache [%s]"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            java.lang.String r6 = r9.memoryCacheKey     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            r5[r2] = r6     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            com.nostra13.universalimageloader.utils.C6846L.m8370d(r4, r5)     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            com.nostra13.universalimageloader.core.assist.LoadedFrom r4 = com.nostra13.universalimageloader.core.assist.LoadedFrom.DISC_CACHE     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            r9.loadedFrom = r4     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            r9.checkTaskNotActual()     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            com.nostra13.universalimageloader.core.download.ImageDownloader$Scheme r4 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            java.lang.String r1 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            java.lang.String r1 = r4.wrap(r1)     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            android.graphics.Bitmap r1 = r9.decodeImage(r1)     // Catch: java.lang.Throwable -> La5 java.lang.OutOfMemoryError -> Lb2 java.io.IOException -> Lbf com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lce
            goto L41
        L40:
            r1 = r0
        L41:
            if (r1 == 0) goto L4f
            int r4 = r1.getWidth()     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            if (r4 <= 0) goto L4f
            int r4 = r1.getHeight()     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            if (r4 > 0) goto Ld4
        L4f:
            java.lang.String r4 = "Load image from network [%s]"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            java.lang.String r5 = r9.memoryCacheKey     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            r3[r2] = r5     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            com.nostra13.universalimageloader.utils.C6846L.m8370d(r4, r3)     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            com.nostra13.universalimageloader.core.assist.LoadedFrom r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.NETWORK     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            r9.loadedFrom = r2     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            java.lang.String r2 = r9.uri     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            com.nostra13.universalimageloader.core.DisplayImageOptions r3 = r9.options     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            boolean r3 = r3.isCacheOnDisk()     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            if (r3 == 0) goto L84
            boolean r3 = r9.tryCacheImageOnDisk()     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            if (r3 == 0) goto L84
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r3 = r9.configuration     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            com.nostra13.universalimageloader.cache.disc.DiskCache r3 = r3.diskCache     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            java.lang.String r4 = r9.uri     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            java.io.File r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            if (r3 == 0) goto L84
            com.nostra13.universalimageloader.core.download.ImageDownloader$Scheme r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            java.lang.String r2 = r2.wrap(r3)     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
        L84:
            r9.checkTaskNotActual()     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            android.graphics.Bitmap r1 = r9.decodeImage(r2)     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            if (r1 == 0) goto L99
            int r2 = r1.getWidth()     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            if (r2 <= 0) goto L99
            int r2 = r1.getHeight()     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            if (r2 > 0) goto Ld4
        L99:
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.DECODING_ERROR     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            r9.fireFailEvent(r2, r0)     // Catch: java.lang.Throwable -> L9f java.lang.OutOfMemoryError -> La1 java.io.IOException -> La3 com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lcc java.lang.IllegalStateException -> Lcf
            goto Ld4
        L9f:
            r0 = move-exception
            goto La9
        La1:
            r0 = move-exception
            goto Lb6
        La3:
            r0 = move-exception
            goto Lc3
        La5:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        La9:
            com.nostra13.universalimageloader.utils.C6846L.m8368e(r0)
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.UNKNOWN
            r9.fireFailEvent(r2, r0)
            goto Ld4
        Lb2:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        Lb6:
            com.nostra13.universalimageloader.utils.C6846L.m8368e(r0)
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.OUT_OF_MEMORY
            r9.fireFailEvent(r2, r0)
            goto Ld4
        Lbf:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        Lc3:
            com.nostra13.universalimageloader.utils.C6846L.m8368e(r0)
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.IO_ERROR
            r9.fireFailEvent(r2, r0)
            goto Ld4
        Lcc:
            r0 = move-exception
            throw r0
        Lce:
            r1 = r0
        Lcf:
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.NETWORK_DENIED
            r9.fireFailEvent(r2, r0)
        Ld4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.tryLoadBitmap():android.graphics.Bitmap");
    }

    private Bitmap decodeImage(String str) throws IOException {
        return this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, str, this.uri, this.targetSize, this.imageAware.getScaleType(), getDownloader(), this.options));
    }

    private boolean tryCacheImageOnDisk() throws TaskCancelledException {
        C6846L.m8370d("Cache image on disk [%s]", this.memoryCacheKey);
        try {
            boolean downloadImage = downloadImage();
            if (downloadImage) {
                int i = this.configuration.maxImageWidthForDiskCache;
                int i2 = this.configuration.maxImageHeightForDiskCache;
                if (i > 0 || i2 > 0) {
                    C6846L.m8370d("Resize image in disk cache [%s]", this.memoryCacheKey);
                    resizeAndSaveImage(i, i2);
                    return downloadImage;
                }
                return downloadImage;
            }
            return downloadImage;
        } catch (IOException e) {
            C6846L.m8368e(e);
            return false;
        }
    }

    private boolean downloadImage() throws IOException {
        InputStream stream = getDownloader().getStream(this.uri, this.options.getExtraForDownloader());
        if (stream == null) {
            C6846L.m8369e("No stream for image [%s]", this.memoryCacheKey);
            return false;
        }
        try {
            return this.configuration.diskCache.save(this.uri, stream, this);
        } finally {
            IoUtils.closeSilently(stream);
        }
    }

    private boolean resizeAndSaveImage(int i, int i2) throws IOException {
        File file = this.configuration.diskCache.get(this.uri);
        if (file == null || !file.exists()) {
            return false;
        }
        Bitmap decode = this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, ImageDownloader.Scheme.FILE.wrap(file.getAbsolutePath()), this.uri, new ImageSize(i, i2), ViewScaleType.FIT_INSIDE, getDownloader(), new DisplayImageOptions.Builder().cloneFrom(this.options).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build()));
        if (decode != null && this.configuration.processorForDiskCache != null) {
            C6846L.m8370d("Process image before cache on disk [%s]", this.memoryCacheKey);
            decode = this.configuration.processorForDiskCache.process(decode);
            if (decode == null) {
                C6846L.m8369e("Bitmap processor for disk cache returned null [%s]", this.memoryCacheKey);
            }
        }
        if (decode != null) {
            boolean save = this.configuration.diskCache.save(this.uri, decode);
            decode.recycle();
            return save;
        }
        return false;
    }

    @Override // com.nostra13.universalimageloader.utils.IoUtils.CopyListener
    public boolean onBytesCopied(int i, int i2) {
        return this.syncLoading || fireProgressEvent(i, i2);
    }

    private boolean fireProgressEvent(final int i, final int i2) {
        if (isTaskInterrupted() || isTaskNotActual()) {
            return false;
        }
        if (this.progressListener != null) {
            runTask(new Runnable() { // from class: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.1
                @Override // java.lang.Runnable
                public void run() {
                    LoadAndDisplayImageTask.this.progressListener.onProgressUpdate(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), i, i2);
                }
            }, false, this.handler, this.engine);
            return true;
        }
        return true;
    }

    private void fireFailEvent(final FailReason.FailType failType, final Throwable th) {
        if (this.syncLoading || isTaskInterrupted() || isTaskNotActual()) {
            return;
        }
        runTask(new Runnable() { // from class: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.2
            @Override // java.lang.Runnable
            public void run() {
                if (LoadAndDisplayImageTask.this.options.shouldShowImageOnFail()) {
                    LoadAndDisplayImageTask.this.imageAware.setImageDrawable(LoadAndDisplayImageTask.this.options.getImageOnFail(LoadAndDisplayImageTask.this.configuration.resources));
                }
                LoadAndDisplayImageTask.this.listener.onLoadingFailed(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), new FailReason(failType, th));
            }
        }, false, this.handler, this.engine);
    }

    private void fireCancelEvent() {
        if (this.syncLoading || isTaskInterrupted()) {
            return;
        }
        runTask(new Runnable() { // from class: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.3
            @Override // java.lang.Runnable
            public void run() {
                LoadAndDisplayImageTask.this.listener.onLoadingCancelled(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView());
            }
        }, false, this.handler, this.engine);
    }

    private ImageDownloader getDownloader() {
        if (this.engine.isNetworkDenied()) {
            return this.networkDeniedDownloader;
        }
        if (this.engine.isSlowNetwork()) {
            return this.slowNetworkDownloader;
        }
        return this.downloader;
    }

    private void checkTaskNotActual() throws TaskCancelledException {
        checkViewCollected();
        checkViewReused();
    }

    private boolean isTaskNotActual() {
        return isViewCollected() || isViewReused();
    }

    private void checkViewCollected() throws TaskCancelledException {
        if (isViewCollected()) {
            throw new TaskCancelledException();
        }
    }

    private boolean isViewCollected() {
        if (this.imageAware.isCollected()) {
            C6846L.m8370d("ImageAware was collected by GC. Task is cancelled. [%s]", this.memoryCacheKey);
            return true;
        }
        return false;
    }

    private void checkViewReused() throws TaskCancelledException {
        if (isViewReused()) {
            throw new TaskCancelledException();
        }
    }

    private boolean isViewReused() {
        if (!this.memoryCacheKey.equals(this.engine.getLoadingUriForView(this.imageAware))) {
            C6846L.m8370d("ImageAware is reused for another image. Task is cancelled. [%s]", this.memoryCacheKey);
            return true;
        }
        return false;
    }

    private void checkTaskInterrupted() throws TaskCancelledException {
        if (isTaskInterrupted()) {
            throw new TaskCancelledException();
        }
    }

    private boolean isTaskInterrupted() {
        if (Thread.interrupted()) {
            C6846L.m8370d("Task was interrupted [%s]", this.memoryCacheKey);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getLoadingUri() {
        return this.uri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void runTask(Runnable runnable, boolean z, Handler handler, ImageLoaderEngine imageLoaderEngine) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            imageLoaderEngine.fireCallback(runnable);
        } else {
            handler.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class TaskCancelledException extends Exception {
        TaskCancelledException() {
        }
    }
}
