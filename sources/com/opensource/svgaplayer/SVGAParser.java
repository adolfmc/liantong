package com.opensource.svgaplayer;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.http.HttpResponseCache;
import android.os.Handler;
import android.os.Looper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.proto.MovieEntity;
import com.opensource.svgaplayer.utils.log.LogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Annotations;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.p401io.Closeable;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/* compiled from: SVGAParser.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 52\u00020\u0001:\u0003567B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J \u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0018\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u001a\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J*\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u001c\u001a\u00020\u001dJ \u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0018\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020%H\u0002J\u000e\u0010'\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0003J\u001a\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u001a\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J,\u0010.\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0007J\u001a\u0010.\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007J\u001a\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007J\u0012\u00100\u001a\u0004\u0018\u00010%2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\rJ\u0018\u00104\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAParser;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fileDownloader", "Lcom/opensource/svgaplayer/SVGAParser$FileDownloader;", "getFileDownloader", "()Lcom/opensource/svgaplayer/SVGAParser$FileDownloader;", "setFileDownloader", "(Lcom/opensource/svgaplayer/SVGAParser$FileDownloader;)V", "mContext", "mFrameHeight", "", "mFrameWidth", "_decodeFromCacheKey", "", "cacheKey", "", "callback", "Lcom/opensource/svgaplayer/SVGAParser$ParseCompletion;", "_decodeFromInputStream", "inputStream", "Ljava/io/InputStream;", "decodeFromAssets", "name", "decodeFromCacheKey", "decodeFromInputStream", "closeInputStream", "", "decodeFromURL", "Lkotlin/Function0;", "url", "Ljava/net/URL;", "doError", "error", "inflate", "", "byteArray", "init", "invokeCompleteCallback", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "invokeErrorCallback", "e", "Ljava/lang/Exception;", "parse", "assetsName", "readAsBytes", "setFrameSize", "frameWidth", "frameHeight", "unzip", "Companion", "FileDownloader", "ParseCompletion", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SVGAParser {
    private static final String TAG = "SVGAParser";
    @NotNull
    private FileDownloader fileDownloader;
    private Context mContext;
    private volatile int mFrameHeight;
    private volatile int mFrameWidth;
    public static final Companion Companion = new Companion(null);
    private static final AtomicInteger threadNum = new AtomicInteger(0);
    private static SVGAParser mShareParser = new SVGAParser(null);
    private static ExecutorService threadPoolExecutor = Executors.newCachedThreadPool(new ThreadFactory() { // from class: com.opensource.svgaplayer.SVGAParser$Companion$threadPoolExecutor$1
        @Override // java.util.concurrent.ThreadFactory
        @NotNull
        public final Thread newThread(Runnable runnable) {
            AtomicInteger atomicInteger;
            StringBuilder sb = new StringBuilder();
            sb.append("SVGAParser-Thread-");
            atomicInteger = SVGAParser.threadNum;
            sb.append(atomicInteger.getAndIncrement());
            return new Thread(runnable, sb.toString());
        }
    });

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: SVGAParser.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAParser$ParseCompletion;", "", "onComplete", "", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "onError", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ParseCompletion {
        void onComplete(@NotNull SVGAVideoEntity sVGAVideoEntity);

        void onError();
    }

    public SVGAParser(@Nullable Context context) {
        this.mContext = context != null ? context.getApplicationContext() : null;
        SVGACache.INSTANCE.onCreate(context);
        this.fileDownloader = new FileDownloader();
    }

    /* compiled from: SVGAParser.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J`\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u000b0\u000f2%\u0010\u0014\u001a!\u0012\u0017\u0012\u00150\u0015j\u0002`\u0016¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u000b0\u000fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0018"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAParser$FileDownloader;", "", "()V", "noCache", "", "getNoCache", "()Z", "setNoCache", "(Z)V", "resume", "Lkotlin/Function0;", "", "url", "Ljava/net/URL;", PrefetchCumpLauncher.PrefetchStatus_Complete, "Lkotlin/Function1;", "Ljava/io/InputStream;", "Lkotlin/ParameterName;", "name", "inputStream", "failure", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class FileDownloader {
        private boolean noCache;

        public final boolean getNoCache() {
            return this.noCache;
        }

        public final void setNoCache(boolean z) {
            this.noCache = z;
        }

        @NotNull
        public Functions<Unit> resume(@NotNull final URL url, @NotNull final Function1<? super InputStream, Unit> complete, @NotNull final Function1<? super Exception, Unit> failure) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(complete, "complete");
            Intrinsics.checkParameterIsNotNull(failure, "failure");
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = false;
            Functions<Unit> functions = new Functions<Unit>() { // from class: com.opensource.svgaplayer.SVGAParser$FileDownloader$resume$cancelBlock$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Functions
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Ref.BooleanRef.this.element = true;
                }
            };
            SVGAParser.Companion.getThreadPoolExecutor$com_opensource_svgaplayer().execute(new Runnable() { // from class: com.opensource.svgaplayer.SVGAParser$FileDownloader$resume$1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        LogUtils.INSTANCE.info("SVGAParser", "================ svga file download start ================");
                        if (HttpResponseCache.getInstalled() == null && !SVGAParser.FileDownloader.this.getNoCache()) {
                            LogUtils.INSTANCE.error("SVGAParser", "SVGAParser can not handle cache before install HttpResponseCache. see https://github.com/yyued/SVGAPlayer-Android#cache");
                            LogUtils.INSTANCE.error("SVGAParser", "在配置 HttpResponseCache 前 SVGAParser 无法缓存. 查看 https://github.com/yyued/SVGAPlayer-Android#cache ");
                        }
                        URLConnection openConnection = NBSInstrumentation.openConnection(url.openConnection());
                        if (!(openConnection instanceof HttpURLConnection)) {
                            openConnection = null;
                        }
                        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                        if (httpURLConnection != null) {
                            httpURLConnection.setConnectTimeout(20000);
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.connect();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            Throwable th = null;
                            InputStream inputStream2 = inputStream;
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            Throwable th2 = null;
                            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                            byte[] bArr = new byte[4096];
                            while (true) {
                                if (booleanRef.element) {
                                    LogUtils.INSTANCE.warn("SVGAParser", "================ svga file download canceled ================");
                                    break;
                                }
                                int read = inputStream2.read(bArr, 0, 4096);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr, 0, read);
                            }
                            if (booleanRef.element) {
                                LogUtils.INSTANCE.warn("SVGAParser", "================ svga file download canceled ================");
                                Closeable.closeFinally(byteArrayOutputStream, th2);
                                Closeable.closeFinally(inputStream, th);
                                return;
                            }
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
                            Throwable th3 = null;
                            try {
                                LogUtils.INSTANCE.info("SVGAParser", "================ svga file download complete ================");
                                complete.invoke(byteArrayInputStream);
                                Unit unit = Unit.INSTANCE;
                                Closeable.closeFinally(byteArrayInputStream, th3);
                                Unit unit2 = Unit.INSTANCE;
                                Closeable.closeFinally(byteArrayOutputStream, th2);
                                Unit unit3 = Unit.INSTANCE;
                                Closeable.closeFinally(inputStream, th);
                            } catch (Throwable th4) {
                                try {
                                    throw th4;
                                } catch (Throwable th5) {
                                    Closeable.closeFinally(byteArrayInputStream, th4);
                                    throw th5;
                                }
                            }
                        }
                    } catch (Exception e) {
                        LogUtils.INSTANCE.error("SVGAParser", "================ svga file download fail ================");
                        LogUtils.INSTANCE.error("SVGAParser", "error: " + e.getMessage());
                        e.printStackTrace();
                        failure.invoke(e);
                    }
                }
            });
            return functions;
        }
    }

    @NotNull
    public final FileDownloader getFileDownloader() {
        return this.fileDownloader;
    }

    public final void setFileDownloader(@NotNull FileDownloader fileDownloader) {
        Intrinsics.checkParameterIsNotNull(fileDownloader, "<set-?>");
        this.fileDownloader = fileDownloader;
    }

    /* compiled from: SVGAParser.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAParser$Companion;", "", "()V", "TAG", "", "mShareParser", "Lcom/opensource/svgaplayer/SVGAParser;", "threadNum", "Ljava/util/concurrent/atomic/AtomicInteger;", "threadPoolExecutor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "getThreadPoolExecutor$com_opensource_svgaplayer", "()Ljava/util/concurrent/ExecutorService;", "setThreadPoolExecutor$com_opensource_svgaplayer", "(Ljava/util/concurrent/ExecutorService;)V", "setThreadPoolExecutor", "", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "shareParser", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ExecutorService getThreadPoolExecutor$com_opensource_svgaplayer() {
            return SVGAParser.threadPoolExecutor;
        }

        public final void setThreadPoolExecutor$com_opensource_svgaplayer(ExecutorService executorService) {
            SVGAParser.threadPoolExecutor = executorService;
        }

        public final void setThreadPoolExecutor(@NotNull ThreadPoolExecutor executor) {
            Intrinsics.checkParameterIsNotNull(executor, "executor");
            setThreadPoolExecutor$com_opensource_svgaplayer(executor);
        }

        @NotNull
        public final SVGAParser shareParser() {
            return SVGAParser.mShareParser;
        }
    }

    public final void init(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mContext = context.getApplicationContext();
        SVGACache.INSTANCE.onCreate(this.mContext);
    }

    public final void setFrameSize(int i, int i2) {
        this.mFrameWidth = i;
        this.mFrameHeight = i2;
    }

    public final void decodeFromAssets(@NotNull final String name, @Nullable final ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (this.mContext == null) {
            LogUtils.INSTANCE.error(TAG, "在配置 SVGAParser context 前, 无法解析 SVGA 文件。");
            return;
        }
        try {
            LogUtils.INSTANCE.info(TAG, "================ decode from assets ================");
            threadPoolExecutor.execute(new Runnable() { // from class: com.opensource.svgaplayer.SVGAParser$decodeFromAssets$1
                @Override // java.lang.Runnable
                public final void run() {
                    Context context;
                    AssetManager assets;
                    InputStream open;
                    context = SVGAParser.this.mContext;
                    if (context == null || (assets = context.getAssets()) == null || (open = assets.open(name)) == null) {
                        return;
                    }
                    SVGAParser sVGAParser = SVGAParser.this;
                    SVGACache sVGACache = SVGACache.INSTANCE;
                    sVGAParser.decodeFromInputStream(open, sVGACache.buildCacheKey("file:///assets/" + name), parseCompletion, true);
                }
            });
        } catch (Exception e) {
            invokeErrorCallback(e, parseCompletion);
        }
    }

    @Nullable
    public final Functions<Unit> decodeFromURL(@NotNull URL url, @Nullable final ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        if (this.mContext == null) {
            LogUtils.INSTANCE.error(TAG, "在配置 SVGAParser context 前, 无法解析 SVGA 文件。");
            return null;
        }
        LogUtils.INSTANCE.info(TAG, "================ decode from url ================");
        final String buildCacheKey = SVGACache.INSTANCE.buildCacheKey(url);
        if (SVGACache.INSTANCE.isCached(buildCacheKey)) {
            LogUtils.INSTANCE.info(TAG, "this url cached");
            threadPoolExecutor.execute(new Runnable() { // from class: com.opensource.svgaplayer.SVGAParser$decodeFromURL$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (SVGACache.INSTANCE.isDefaultCache()) {
                        SVGAParser.this.decodeFromCacheKey(buildCacheKey, parseCompletion);
                    } else {
                        SVGAParser.this._decodeFromCacheKey(buildCacheKey, parseCompletion);
                    }
                }
            });
            return null;
        }
        LogUtils.INSTANCE.info(TAG, "no cached, prepare to download");
        return this.fileDownloader.resume(url, new Function1<InputStream, Unit>() { // from class: com.opensource.svgaplayer.SVGAParser$decodeFromURL$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InputStream inputStream) {
                invoke2(inputStream);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull InputStream it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (SVGACache.INSTANCE.isDefaultCache()) {
                    SVGAParser.decodeFromInputStream$default(SVGAParser.this, it, buildCacheKey, parseCompletion, false, 8, null);
                } else {
                    SVGAParser.this._decodeFromInputStream(it, buildCacheKey, parseCompletion);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.opensource.svgaplayer.SVGAParser$decodeFromURL$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Exception it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                SVGAParser.this.invokeErrorCallback(it, parseCompletion);
            }
        });
    }

    public final void _decodeFromCacheKey(@NotNull final String cacheKey, @Nullable final ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        File buildSvgaFile = SVGACache.INSTANCE.buildSvgaFile(cacheKey);
        try {
            LogUtils.INSTANCE.info(TAG, "cache.binary change to entity");
            FileInputStream fileInputStream = new FileInputStream(buildSvgaFile);
            Throwable th = null;
            FileInputStream fileInputStream2 = fileInputStream;
            try {
                byte[] readAsBytes = readAsBytes(fileInputStream2);
                if (readAsBytes != null) {
                    LogUtils.INSTANCE.info(TAG, "cache.inflate start");
                    byte[] inflate = inflate(readAsBytes);
                    if (inflate != null) {
                        LogUtils.INSTANCE.info(TAG, "cache.inflate success");
                        MovieEntity decode = MovieEntity.ADAPTER.decode(inflate);
                        Intrinsics.checkExpressionValueIsNotNull(decode, "MovieEntity.ADAPTER.decode(inflateBytes)");
                        final SVGAVideoEntity sVGAVideoEntity = new SVGAVideoEntity(decode, new File(cacheKey), this.mFrameWidth, this.mFrameHeight);
                        sVGAVideoEntity.prepare$com_opensource_svgaplayer(new Functions<Unit>() { // from class: com.opensource.svgaplayer.SVGAParser$_decodeFromCacheKey$$inlined$use$lambda$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Functions
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                LogUtils.INSTANCE.info("SVGAParser", "cache.prepare success");
                                this.invokeCompleteCallback(SVGAVideoEntity.this, parseCompletion);
                            }
                        });
                    } else {
                        doError("cache.inflate(bytes) cause exception", parseCompletion);
                    }
                } else {
                    doError("cache.readAsBytes(inputStream) cause exception", parseCompletion);
                }
            } catch (Exception e) {
                invokeErrorCallback(e, parseCompletion);
            }
            fileInputStream2.close();
            Unit unit = Unit.INSTANCE;
            Closeable.closeFinally(fileInputStream, th);
        } catch (Exception e2) {
            LogUtils.INSTANCE.error(TAG, "cache.binary change to entity fail", e2);
            if (!buildSvgaFile.exists()) {
                buildSvgaFile = null;
            }
            if (buildSvgaFile != null) {
                buildSvgaFile.delete();
            }
            invokeErrorCallback(e2, parseCompletion);
        }
    }

    public final void doError(@NotNull String error, @Nullable ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        LogUtils.INSTANCE.info(TAG, error);
        invokeErrorCallback(new Exception(error), parseCompletion);
    }

    public final void _decodeFromInputStream(@NotNull InputStream inputStream, @NotNull String cacheKey, @Nullable ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        threadPoolExecutor.execute(new SVGAParser$_decodeFromInputStream$1(this, inputStream, cacheKey, parseCompletion));
    }

    public static /* synthetic */ void decodeFromInputStream$default(SVGAParser sVGAParser, InputStream inputStream, String str, ParseCompletion parseCompletion, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        sVGAParser.decodeFromInputStream(inputStream, str, parseCompletion, z);
    }

    public final void decodeFromInputStream(@NotNull InputStream inputStream, @NotNull String cacheKey, @Nullable ParseCompletion parseCompletion, boolean z) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        if (this.mContext == null) {
            LogUtils.INSTANCE.error(TAG, "在配置 SVGAParser context 前, 无法解析 SVGA 文件。");
            return;
        }
        LogUtils.INSTANCE.info(TAG, "================ decode from input stream ================");
        threadPoolExecutor.execute(new SVGAParser$decodeFromInputStream$1(this, inputStream, cacheKey, parseCompletion, z));
    }

    @Annotations(message = "This method has been deprecated from 2.4.0.", replaceWith = @ReplaceWith(expression = "this.decodeFromAssets(assetsName, callback)", imports = {}))
    public final void parse(@NotNull String assetsName, @Nullable ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(assetsName, "assetsName");
        decodeFromAssets(assetsName, parseCompletion);
    }

    @Annotations(message = "This method has been deprecated from 2.4.0.", replaceWith = @ReplaceWith(expression = "this.decodeFromURL(url, callback)", imports = {}))
    public final void parse(@NotNull URL url, @Nullable ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        decodeFromURL(url, parseCompletion);
    }

    public static /* synthetic */ void parse$default(SVGAParser sVGAParser, InputStream inputStream, String str, ParseCompletion parseCompletion, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        sVGAParser.parse(inputStream, str, parseCompletion, z);
    }

    @Annotations(message = "This method has been deprecated from 2.4.0.", replaceWith = @ReplaceWith(expression = "this.decodeFromInputStream(inputStream, cacheKey, callback, closeInputStream)", imports = {}))
    public final void parse(@NotNull InputStream inputStream, @NotNull String cacheKey, @Nullable ParseCompletion parseCompletion, boolean z) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        decodeFromInputStream(inputStream, cacheKey, parseCompletion, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invokeCompleteCallback(final SVGAVideoEntity sVGAVideoEntity, final ParseCompletion parseCompletion) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.opensource.svgaplayer.SVGAParser$invokeCompleteCallback$1
            @Override // java.lang.Runnable
            public final void run() {
                LogUtils.INSTANCE.info("SVGAParser", "================ parser complete ================");
                SVGAParser.ParseCompletion parseCompletion2 = SVGAParser.ParseCompletion.this;
                if (parseCompletion2 != null) {
                    parseCompletion2.onComplete(sVGAVideoEntity);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invokeErrorCallback(Exception exc, final ParseCompletion parseCompletion) {
        exc.printStackTrace();
        LogUtils.INSTANCE.error(TAG, "================ parser error ================");
        LogUtils.INSTANCE.error(TAG, "error", exc);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.opensource.svgaplayer.SVGAParser$invokeErrorCallback$1
            @Override // java.lang.Runnable
            public final void run() {
                SVGAParser.ParseCompletion parseCompletion2 = SVGAParser.ParseCompletion.this;
                if (parseCompletion2 != null) {
                    parseCompletion2.onError();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void decodeFromCacheKey(String str, ParseCompletion parseCompletion) {
        LogUtils.INSTANCE.info(TAG, "================ decode from cache ================");
        LogUtils.INSTANCE.debug(TAG, "decodeFromCacheKey called with cacheKey : " + str);
        if (this.mContext == null) {
            LogUtils.INSTANCE.error(TAG, "在配置 SVGAParser context 前, 无法解析 SVGA 文件。");
            return;
        }
        try {
            File buildCacheDir = SVGACache.INSTANCE.buildCacheDir(str);
            File file = new File(buildCacheDir, "movie.binary");
            if (!file.isFile()) {
                file = null;
            }
            if (file != null) {
                try {
                    LogUtils.INSTANCE.info(TAG, "binary change to entity");
                    FileInputStream fileInputStream = new FileInputStream(file);
                    Throwable th = null;
                    LogUtils.INSTANCE.info(TAG, "binary change to entity success");
                    MovieEntity decode = MovieEntity.ADAPTER.decode(fileInputStream);
                    Intrinsics.checkExpressionValueIsNotNull(decode, "MovieEntity.ADAPTER.decode(it)");
                    invokeCompleteCallback(new SVGAVideoEntity(decode, buildCacheDir, this.mFrameWidth, this.mFrameHeight), parseCompletion);
                    Unit unit = Unit.INSTANCE;
                    Closeable.closeFinally(fileInputStream, th);
                } catch (Exception e) {
                    LogUtils.INSTANCE.error(TAG, "binary change to entity fail", e);
                    buildCacheDir.delete();
                    file.delete();
                    throw e;
                }
            }
            File file2 = new File(buildCacheDir, "movie.spec");
            if (!file2.isFile()) {
                file2 = null;
            }
            if (file2 == null) {
                return;
            }
            try {
                LogUtils.INSTANCE.info(TAG, "spec change to entity");
                FileInputStream fileInputStream2 = new FileInputStream(file2);
                Throwable th2 = null;
                FileInputStream fileInputStream3 = fileInputStream2;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Throwable th3 = null;
                ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = fileInputStream3.read(bArr, 0, bArr.length);
                    if (read != -1) {
                        byteArrayOutputStream2.write(bArr, 0, read);
                    } else {
                        JSONObject jSONObject = new JSONObject(byteArrayOutputStream2.toString());
                        LogUtils.INSTANCE.info(TAG, "spec change to entity success");
                        invokeCompleteCallback(new SVGAVideoEntity(jSONObject, buildCacheDir, this.mFrameWidth, this.mFrameHeight), parseCompletion);
                        Unit unit2 = Unit.INSTANCE;
                        Closeable.closeFinally(byteArrayOutputStream, th3);
                        Unit unit3 = Unit.INSTANCE;
                        Closeable.closeFinally(fileInputStream2, th2);
                        return;
                    }
                }
            } catch (Exception e2) {
                LogUtils.INSTANCE.error(TAG, "spec change to entity fail", e2);
                buildCacheDir.delete();
                file2.delete();
                throw e2;
            }
        } catch (Exception e3) {
            invokeErrorCallback(e3, parseCompletion);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] readAsBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Throwable th = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            byte[] bArr = new byte[2048];
            while (true) {
                int read = inputStream.read(bArr, 0, 2048);
                if (read > 0) {
                    byteArrayOutputStream2.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    Closeable.closeFinally(byteArrayOutputStream, th);
                    return byteArray;
                }
            }
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] inflate(byte[] bArr) {
        Inflater inflater = new Inflater();
        inflater.setInput(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[2048];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Throwable th = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            while (true) {
                int inflate = inflater.inflate(bArr2, 0, 2048);
                if (inflate > 0) {
                    byteArrayOutputStream2.write(bArr2, 0, inflate);
                } else {
                    inflater.end();
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    Closeable.closeFinally(byteArrayOutputStream, th);
                    return byteArray;
                }
            }
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unzip(InputStream inputStream, String str) {
        LogUtils.INSTANCE.info(TAG, "================ unzip prepare ================");
        File buildCacheDir = SVGACache.INSTANCE.buildCacheDir(str);
        buildCacheDir.mkdirs();
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            Throwable th = null;
            ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
            Throwable th2 = null;
            ZipInputStream zipInputStream2 = zipInputStream;
            while (true) {
                ZipEntry nextEntry = zipInputStream2.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "zipItem.name");
                    if (!StringsKt.contains$default((CharSequence) name, (CharSequence) "../", false, 2, (Object) null)) {
                        String name2 = nextEntry.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name2, "zipItem.name");
                        if (!StringsKt.contains$default((CharSequence) name2, (CharSequence) "/", false, 2, (Object) null)) {
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(buildCacheDir, nextEntry.getName()));
                            Throwable th3 = null;
                            try {
                                FileOutputStream fileOutputStream2 = fileOutputStream;
                                byte[] bArr = new byte[2048];
                                while (true) {
                                    int read = zipInputStream2.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    fileOutputStream2.write(bArr, 0, read);
                                }
                                Unit unit = Unit.INSTANCE;
                                Closeable.closeFinally(fileOutputStream, th3);
                                LogUtils.INSTANCE.error(TAG, "================ unzip complete ================");
                                zipInputStream2.closeEntry();
                            } finally {
                            }
                        }
                    }
                } else {
                    Unit unit2 = Unit.INSTANCE;
                    Closeable.closeFinally(zipInputStream, th2);
                    Unit unit3 = Unit.INSTANCE;
                    Closeable.closeFinally(bufferedInputStream, th);
                    return;
                }
            }
        } catch (Exception e) {
            LogUtils.INSTANCE.error(TAG, "================ unzip error ================");
            Exception exc = e;
            LogUtils.INSTANCE.error(TAG, "error", exc);
            buildCacheDir.delete();
            throw exc;
        }
    }
}
