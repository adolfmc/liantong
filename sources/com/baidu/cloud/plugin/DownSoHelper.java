package com.baidu.cloud.plugin;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.cloud.download.DownloadManager;
import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.cloud.download.exception.DownloadException;
import com.baidu.cloud.plugin.soloader.SysSoLoaderUtils;
import com.baidu.cloud.util.FileTool;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownSoHelper implements ISoHelper {
    private static final int STATUS_DOWNING = 1;
    private static final int STATUS_DOWN_FAIL = 2;
    private static final int STATUS_DOWN_SUCCESS = 3;
    private Context mContext;
    private List<ISoCallback> mSoCallbackList;
    private Map<String, Integer> statusMap;

    private DownSoHelper() {
        this.statusMap = new HashMap();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class InnerSo {
        private static DownSoHelper sInstance = new DownSoHelper();

        private InnerSo() {
        }
    }

    public static DownSoHelper getInstance(Context context) {
        InnerSo.sInstance.mContext = context.getApplicationContext();
        return InnerSo.sInstance;
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void setSoCpuType(String str) {
        DownSoConstant.setDownCPUType(str);
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void registerCallback(ISoCallback iSoCallback) {
        if (iSoCallback == null) {
            return;
        }
        List<ISoCallback> list = this.mSoCallbackList;
        if (list == null) {
            this.mSoCallbackList = new ArrayList();
            this.mSoCallbackList.add(iSoCallback);
        } else if (list.contains(iSoCallback)) {
        } else {
            this.mSoCallbackList.add(iSoCallback);
        }
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void removeCallback(ISoCallback iSoCallback) {
        List<ISoCallback> list = this.mSoCallbackList;
        if (list == null || list.size() < 1) {
            return;
        }
        this.mSoCallbackList.remove(iSoCallback);
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public boolean isDownloadComplete(@NonNull String str) {
        return DownSoConstant.isSoDownloaded(this.mContext, str);
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public boolean isDownloadComplete(@NonNull String str, String str2) {
        return DownSoConstant.isSoDownloaded(this.mContext, str, str2);
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void downloadSo(@NonNull String str) {
        downloadSo(str, null);
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void downloadSo(@NonNull String str, ISoCallback iSoCallback) {
        downloadSo(str, DownSoConstant.getDefaultDownloadSoFoler(this.mContext), iSoCallback);
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void downloadSo(@NonNull String str, @NonNull String str2, ISoCallback iSoCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!isDownloadComplete(str, str2)) {
            download(str, str2, Looper.getMainLooper(), iSoCallback);
        } else if (iSoCallback != null) {
            iSoCallback.onDownloadSuccess(str, DownSoConstant.getDownLocalPath(this.mContext, str, str2));
        }
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void downloadSo(@NonNull String str, boolean z, ISoCallback iSoCallback) {
        String defaultDownloadSoFoler = DownSoConstant.getDefaultDownloadSoFoler(this.mContext);
        if (TextUtils.isEmpty(str)) {
            iSoCallback.onDownloadFail(str, 108, "download url is empty.");
        } else if (isDownloadComplete(str, defaultDownloadSoFoler)) {
            if (iSoCallback != null) {
                iSoCallback.onDownloadSuccess(str, DownSoConstant.getDownLocalPath(this.mContext, str, defaultDownloadSoFoler));
            }
        } else if (z) {
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                Log.d("RtcDownSo", "start down so main thread");
                download(str, defaultDownloadSoFoler, Looper.getMainLooper(), iSoCallback);
                return;
            }
            Looper.prepare();
            Log.d("RtcDownSo", "start down so sub thread");
            download(str, defaultDownloadSoFoler, Looper.myLooper(), iSoCallback);
            Looper.loop();
        } else {
            download(str, defaultDownloadSoFoler, Looper.getMainLooper(), iSoCallback);
        }
    }

    private void download(final String str, String str2, Looper looper, final ISoCallback iSoCallback) {
        if (!isDownloading(str)) {
            FileTool.deleteDir(new File(str2));
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.statusMap.put(str, 1);
        final String downLocalPath = DownSoConstant.getDownLocalPath(this.mContext, str, str2);
        final File file2 = new File(downLocalPath + ".temp");
        final File file3 = new File(downLocalPath + JtClient.UXUE_TEMP_FILE_SUFFIX);
        Log.d("RtcDownSo", "start down folder=" + str2 + "name=" + file2.getName());
        DownloadManager.getInstance().download(str, str2, file2.getName(), looper, new DownloadCallback() { // from class: com.baidu.cloud.plugin.DownSoHelper.1
            @Override // com.baidu.cloud.download.base.DownloadCallback
            public void onStarted() {
                super.onStarted();
                DownSoHelper.this.callbackStart(iSoCallback, str);
            }

            @Override // com.baidu.cloud.download.base.DownloadCallback
            public void onProgress(long j, long j2, int i) {
                super.onProgress(j, j2, i);
                DownSoHelper.this.callbackProgress(iSoCallback, str, i);
            }

            @Override // com.baidu.cloud.download.base.DownloadCallback
            public void onCompleted(String str3) {
                super.onCompleted(str3);
                try {
                    file2.renameTo(file3);
                    Log.e("RtcDownSo", "unzip:" + file3 + "---" + downLocalPath);
                    FileTool.unzipFile(file3, downLocalPath);
                    FileTool.deleteFile(file3.getAbsolutePath());
                    DownSoHelper.this.callbackSuccess(iSoCallback, str, downLocalPath);
                } catch (Exception e) {
                    e.printStackTrace();
                    FileTool.deleteFile(file3.getAbsolutePath());
                    if (FileTool.isExists(downLocalPath)) {
                        FileTool.deleteDir(new File(downLocalPath));
                    }
                    DownSoHelper.this.callbackFail(iSoCallback, str, 108, "unzip exception");
                }
            }

            @Override // com.baidu.cloud.download.base.DownloadCallback
            public void onFailed(DownloadException downloadException) {
                super.onFailed(downloadException);
                downloadException.printStackTrace();
                FileTool.deleteFile(file2.getAbsolutePath());
                DownSoHelper.this.callbackFail(iSoCallback, str, downloadException.getErrorCode(), downloadException.getErrorMessage());
            }
        });
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void download(String str, String str2, String str3, ISoCallback iSoCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return;
        }
        if (FileTool.isExists(DownSoConstant.getDownLocalPath(this.mContext, str, str2) + JtClient.UXUE_TEMP_FILE_SUFFIX)) {
            File file = new File(str3);
            if (file.exists() && file.isDirectory() && file.list().length > 0) {
                if (iSoCallback != null) {
                    iSoCallback.onDownloadSuccess(str, str3);
                    return;
                }
                return;
            }
        }
        if (isDownloading(str)) {
            registerCallback(iSoCallback);
        } else {
            download(str, str2, str3, Looper.getMainLooper(), iSoCallback);
        }
    }

    private void download(final String str, String str2, final String str3, Looper looper, final ISoCallback iSoCallback) {
        this.statusMap.put(str, 1);
        String downLocalPath = DownSoConstant.getDownLocalPath(this.mContext, str, str2);
        final File file = new File(downLocalPath + ".temp");
        final File file2 = new File(downLocalPath + JtClient.UXUE_TEMP_FILE_SUFFIX);
        DownloadManager.getInstance().download(str, str2, file.getName(), looper, new DownloadCallback() { // from class: com.baidu.cloud.plugin.DownSoHelper.2
            @Override // com.baidu.cloud.download.base.DownloadCallback
            public void onStarted() {
                super.onStarted();
                DownSoHelper.this.callbackStart(iSoCallback, str);
            }

            @Override // com.baidu.cloud.download.base.DownloadCallback
            public void onProgress(long j, long j2, int i) {
                super.onProgress(j, j2, i);
                DownSoHelper.this.callbackProgress(iSoCallback, str, i);
            }

            @Override // com.baidu.cloud.download.base.DownloadCallback
            public void onCompleted(String str4) {
                super.onCompleted(str4);
                try {
                    file.renameTo(file2);
                    FileTool.unzipFile(file2, str3);
                    DownSoHelper.this.callbackSuccess(iSoCallback, str, str3);
                } catch (Exception e) {
                    e.printStackTrace();
                    FileTool.deleteFile(file2.getAbsolutePath());
                    if (FileTool.isExists(str3)) {
                        FileTool.deleteDir(new File(str3));
                    }
                    DownSoHelper.this.callbackFail(iSoCallback, str, 108, "unzip exception");
                }
            }

            @Override // com.baidu.cloud.download.base.DownloadCallback
            public void onFailed(DownloadException downloadException) {
                super.onFailed(downloadException);
                downloadException.printStackTrace();
                FileTool.deleteFile(file.getAbsolutePath());
                DownSoHelper.this.callbackFail(iSoCallback, str, downloadException.getErrorCode(), downloadException.getErrorMessage());
            }
        });
    }

    private boolean isDownloading(String str) {
        return DownloadManager.getInstance().isRunning(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackStart(ISoCallback iSoCallback, String str) {
        if (iSoCallback != null) {
            iSoCallback.onDownloadStart(str);
        }
        if (this.mSoCallbackList != null) {
            for (int i = 0; i < this.mSoCallbackList.size(); i++) {
                this.mSoCallbackList.get(i).onDownloadStart(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackProgress(ISoCallback iSoCallback, String str, float f) {
        if (iSoCallback != null) {
            iSoCallback.onDownloadProgress(f);
        }
        if (this.mSoCallbackList != null) {
            for (int i = 0; i < this.mSoCallbackList.size(); i++) {
                this.mSoCallbackList.get(i).onDownloadProgress(f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackSuccess(ISoCallback iSoCallback, String str, String str2) {
        this.statusMap.put(str, 3);
        if (iSoCallback != null) {
            iSoCallback.onDownloadSuccess(str, str2);
        }
        if (this.mSoCallbackList != null) {
            for (int i = 0; i < this.mSoCallbackList.size(); i++) {
                this.mSoCallbackList.get(i).onDownloadSuccess(str, str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackFail(ISoCallback iSoCallback, String str, int i, String str2) {
        this.statusMap.put(str, 2);
        if (iSoCallback != null) {
            iSoCallback.onDownloadFail(str, i, str2);
        }
        if (this.mSoCallbackList != null) {
            for (int i2 = 0; i2 < this.mSoCallbackList.size(); i2++) {
                this.mSoCallbackList.get(i2).onDownloadFail(str, i, str2);
            }
        }
    }

    private boolean isAllFileDowned() {
        return FileTool.isExists(DownSoConstant.getRtcSoLocalFullPath(this.mContext));
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public boolean loadSo(@NonNull String str) {
        File file = new File(str);
        if (file.exists()) {
            if (SysSoLoaderUtils.containsNativeDir(this.mContext, file)) {
                return true;
            }
            SysSoLoaderUtils.addNativeDir(this.mContext, file);
            return SysSoLoaderUtils.containsNativeDir(this.mContext, file);
        }
        return false;
    }

    @Override // com.baidu.cloud.plugin.ISoHelper
    public void onDestroy() {
        List<ISoCallback> list = this.mSoCallbackList;
        if (list != null) {
            list.clear();
            this.mSoCallbackList = null;
        }
    }
}
