package com.p319ss.android.download.api.download;

import com.p319ss.android.download.api.model.C9828b;
import com.p319ss.android.download.api.model.DeepLink;
import com.p319ss.android.socialbase.downloader.constants.ExecutorGroup;
import com.p319ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.download.DownloadModel */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface DownloadModel {
    boolean autoInstallWithoutNotification();

    boolean distinctDir();

    boolean enablePause();

    void forceHideNotification();

    void forceHideToast();

    void forceWifi();

    String getAppIcon();

    List<String> getBackupUrls();

    List<String> getClickTrackUrl();

    DeepLink getDeepLink();

    IDownloadFileUriProvider getDownloadFileUriProvider();

    JSONObject getDownloadSettings();

    String getDownloadUrl();

    @ExecutorGroup
    int getExecutorGroup();

    long getExpectFileLength();

    JSONObject getExtra();

    long getExtraValue();

    String getFileName();

    String getFilePath();

    int getFunnelType();

    Map<String, String> getHeaders();

    long getId();

    String getLogExtra();

    String getMd5();

    String getMimeType();

    int getModelType();

    String getName();

    String getNotificationJumpUrl();

    String getPackageName();

    C9828b getQuickAppModel();

    String getSdkMonitorScene();

    String getStartToast();

    int getVersionCode();

    String getVersionName();

    boolean isAd();

    boolean isAutoInstall();

    boolean isInExternalPublicDir();

    boolean isNeedWifi();

    boolean isShowNotification();

    boolean isShowToast();

    boolean isVisibleInDownloadsUi();

    boolean needIndependentProcess();

    DownloadModel setFilePath(String str);

    boolean shouldDownloadWithPatchApply();
}
