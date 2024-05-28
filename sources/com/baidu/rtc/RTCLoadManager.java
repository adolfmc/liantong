package com.baidu.rtc;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.cloud.plugin.DownSoConstant;
import com.baidu.cloud.plugin.DownSoHelper;
import com.baidu.cloud.plugin.ISoCallback;
import com.baidu.rtc.LibraryLoader;
import com.webrtc.Logging;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RTCLoadManager {
    public static final String JINGLE_LIB_NAME = "bdcloud_rtc";
    private static final String TAG = "BRTCLoadManager";
    private static RTCLoadManager sInstance;
    private Context mContext;
    private ExecutorService mLoadServer = Executors.newSingleThreadExecutor();
    private LoadStatus mLoadStatus = LoadStatus.IDLE;
    private List<LoadListener> mCallbackList = new ArrayList();
    private ISoCallback mSoCallback = new ISoCallback.AbsSoCallback() { // from class: com.baidu.rtc.RTCLoadManager.2
        @Override // com.baidu.cloud.plugin.ISoCallback
        public void onDownloadFail(String str, int i, String str2) {
            Logging.m5305d("BRTCLoadManager", "Failed to download so :" + str2 + " / " + str);
            RTCLoadManager.this.mLoadStatus = LoadStatus.LOAD_FAILED;
            RTCLoadManager rTCLoadManager = RTCLoadManager.this;
            rTCLoadManager.callbackFail(i, str2 + " / " + str);
        }

        @Override // com.baidu.cloud.plugin.ISoCallback.AbsSoCallback, com.baidu.cloud.plugin.ISoCallback
        public void onDownloadProgress(float f) {
            RTCLoadManager.this.callbackProgress(f);
        }

        @Override // com.baidu.cloud.plugin.ISoCallback
        public void onDownloadSuccess(String str, String str2) {
            String str3 = DownSoConstant.getRtcSoLocalFullPath(RTCLoadManager.this.mContext) + File.separator + "libjingle_peerconnection_so.so";
            Logging.m5305d("BRTCLoadManager", "RTC so path is: " + str3);
            RTCLoadManager.this.callbackSoDownloadSuccess();
            DownSoHelper.getInstance(RTCLoadManager.this.mContext).loadSo(DownSoConstant.getRtcSoLocalFullPath(RTCLoadManager.this.mContext));
            try {
                System.load(str3);
                RTCLoadManager.this.mLoadStatus = LoadStatus.LOAD_COMPLETED;
                Logging.m5305d("BRTCLoadManager", "So loaded completed.");
                RTCLoadManager.this.callbackSuccess();
            } catch (Throwable th) {
                Logging.m5305d("BRTCLoadManager", "Failed call System.load to load so! Error: " + th);
                RTCLoadManager.this.mLoadStatus = LoadStatus.LOAD_FAILED;
                RTCLoadManager.this.callbackFail(-1001, th.getMessage() + " / " + str);
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface LoadListener {
        void onLibsDownloadCompleted();

        void onLoadError(int i, String str);

        void onLoadProgress(float f);

        void onLoadSuccess();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum LoadStatus {
        IDLE(0),
        LIBRARY_LOADING(1),
        LOAD_FAILED(2),
        LOAD_COMPLETED(3);
        
        private final int mValue;

        LoadStatus(int i) {
            this.mValue = i;
        }
    }

    private RTCLoadManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static synchronized RTCLoadManager getInstance(Context context) {
        RTCLoadManager rTCLoadManager;
        synchronized (RTCLoadManager.class) {
            if (sInstance == null) {
                sInstance = new RTCLoadManager(context);
            }
            rTCLoadManager = sInstance;
        }
        return rTCLoadManager;
    }

    public static synchronized boolean loadLibrary(Context context, String str) {
        boolean isLoaded;
        synchronized (RTCLoadManager.class) {
            LibraryLoader.initialize(new LibraryLoader.LibNameLoader(), "bdcloud_rtc");
            if (!LibraryLoader.isLoaded() && isLibrariesDownloaded(context, str)) {
                LibraryLoader.initialize(new LibraryLoader.LibPathLoader(), DownSoConstant.getRtcSoLocalFullPath(context) + File.separator + "libjingle_peerconnection_so.so");
            }
            isLoaded = LibraryLoader.isLoaded();
        }
        return isLoaded;
    }

    public void loadLibraries(String str, LoadListener loadListener) {
        loadLibraries(null, str, loadListener);
    }

    public synchronized void loadLibraries(final String str, final String str2, LoadListener loadListener) {
        registerCallback(loadListener);
        if (this.mLoadStatus != LoadStatus.IDLE && this.mLoadStatus != LoadStatus.LOAD_FAILED) {
            Logging.m5305d("BRTCLoadManager", "Rtc library is loading or has loaded.");
            return;
        }
        this.mLoadStatus = LoadStatus.LIBRARY_LOADING;
        try {
            System.loadLibrary("bdcloud_rtc");
            this.mLoadStatus = LoadStatus.LOAD_COMPLETED;
            callbackSuccess();
            Logging.m5305d("BRTCLoadManager", "Loaded default so in aar.");
        } catch (UnsatisfiedLinkError e) {
            Logging.m5305d("BRTCLoadManager", "Load default so fail " + e.getMessage());
            if (this.mLoadServer == null || this.mLoadServer.isShutdown()) {
                this.mLoadServer = Executors.newSingleThreadExecutor();
            }
            this.mLoadServer.submit(new Runnable() { // from class: com.baidu.rtc.RTCLoadManager.1
                @Override // java.lang.Runnable
                public void run() {
                    RTCLoadManager.this.setupSoLaterLoad(str, str2);
                }
            });
        }
    }

    public void setupSoLaterLoad(String str, String str2) {
        Logging.m5305d("BRTCLoadManager", "setup so later loading feature cpu type: " + str2);
        DownSoHelper.getInstance(this.mContext).setSoCpuType(str2);
        DownSoHelper.getInstance(this.mContext).registerCallback(this.mSoCallback);
        if (TextUtils.isEmpty(str)) {
            DownSoHelper.getInstance(this.mContext).downloadSo(DownSoConstant.getRtcDownloadUrl(), true, (ISoCallback) null);
            return;
        }
        Logging.m5305d("BRTCLoadManager", "setup so later load url: " + str);
        DownSoHelper.getInstance(this.mContext).downloadSo(str, true, (ISoCallback) null);
    }

    public static synchronized boolean isLibrariesDownloaded(Context context, String str) {
        boolean z;
        synchronized (RTCLoadManager.class) {
            z = false;
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    DownSoHelper.getInstance(context).setSoCpuType(str);
                    z = DownSoHelper.getInstance(context).isDownloadComplete(DownSoConstant.getRtcDownloadUrl());
                }
            }
            Logging.m5305d("BRTCLoadManager", str + " libs downloaded: " + z);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackSoDownloadSuccess() {
        if (this.mCallbackList != null) {
            for (int i = 0; i < this.mCallbackList.size(); i++) {
                this.mCallbackList.get(i).onLibsDownloadCompleted();
            }
        }
    }

    public void registerCallback(LoadListener loadListener) {
        if (loadListener == null) {
            return;
        }
        List<LoadListener> list = this.mCallbackList;
        if (list == null) {
            this.mCallbackList = new ArrayList();
            this.mCallbackList.add(loadListener);
        } else if (list.contains(loadListener)) {
        } else {
            this.mCallbackList.add(loadListener);
        }
    }

    public void unregisterCallback(LoadListener loadListener) {
        List<LoadListener> list = this.mCallbackList;
        if (list == null || list.size() < 1) {
            return;
        }
        this.mCallbackList.remove(loadListener);
    }

    public void clearCallback() {
        List<LoadListener> list = this.mCallbackList;
        if (list == null || list.size() < 1) {
            return;
        }
        this.mCallbackList.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackProgress(float f) {
        if (this.mCallbackList != null) {
            for (int i = 0; i < this.mCallbackList.size(); i++) {
                this.mCallbackList.get(i).onLoadProgress(f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackSuccess() {
        if (this.mCallbackList != null) {
            for (int i = 0; i < this.mCallbackList.size(); i++) {
                this.mCallbackList.get(i).onLoadSuccess();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackFail(int i, String str) {
        if (this.mCallbackList != null) {
            for (int i2 = 0; i2 < this.mCallbackList.size(); i2++) {
                this.mCallbackList.get(i2).onLoadError(i, str);
            }
        }
    }

    public synchronized boolean isLoadCompleted() {
        return this.mLoadStatus == LoadStatus.LOAD_COMPLETED;
    }

    public LoadStatus getLoadStatus() {
        return this.mLoadStatus;
    }

    public void release() {
        clearCallback();
        DownSoHelper.getInstance(this.mContext).onDestroy();
        ExecutorService executorService = this.mLoadServer;
        if (executorService != null) {
            executorService.shutdown();
            this.mLoadServer = null;
        }
        this.mLoadStatus = LoadStatus.IDLE;
        sInstance = null;
    }
}
