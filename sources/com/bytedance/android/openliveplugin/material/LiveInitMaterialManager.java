package com.bytedance.android.openliveplugin.material;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.android.openliveplugin.net.NetApi;
import com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LiveInitMaterialManager {
    private static final String FETCH_PLUGIN_SETTINGS_URL = "/webcast/openapi/pangle/setting/";
    private static final String HOST = "https://webcast-open.douyin.com";
    private static final String MATERIAL_NAME = "material";
    private static final String TAG = "LiveInitMaterialManager";
    private volatile boolean isDownloading;
    private final AtomicInteger mRetryCount = new AtomicInteger(0);

    public void run(String str, Application application, ILiveMaterialGet iLiveMaterialGet) {
        ResPackage startCheckLiveInitMaterial = startCheckLiveInitMaterial(str, application);
        if (startCheckLiveInitMaterial == null || !startCheckLiveInitMaterial.isLegal()) {
            TTLogger.m17369e("live init : material task res package null error");
            ConfigParams localConfigParams = getLocalConfigParams(application);
            if (localConfigParams == null || iLiveMaterialGet == null) {
                return;
            }
            iLiveMaterialGet.onMaterialGet(localConfigParams);
            return;
        }
        ConfigParams localConfigParams2 = getLocalConfigParams(application);
        if (startCheckLiveInitMaterial.version <= (localConfigParams2 != null ? localConfigParams2.version : 0L)) {
            if (iLiveMaterialGet != null) {
                iLiveMaterialGet.onMaterialGet(localConfigParams2);
            }
        } else if (TextUtils.isEmpty(startCheckLiveInitMaterial.resUrl)) {
            TTLogger.m17369e("live init : material task res download url empty error");
        } else {
            ZipFileUtil.removeDir(new File(getMaterialDownloadPath(application)));
            File file = new File(getMaterialDownloadPath(application));
            if (!file.exists()) {
                file.mkdir();
            }
            TTLogger.m17373d("live init : start download material res");
            download(application, startCheckLiveInitMaterial.resUrl, getMaterialDownloadPath(application), MATERIAL_NAME, iLiveMaterialGet);
        }
    }

    private ResPackage startCheckLiveInitMaterial(String str, Application application) {
        if (TextUtils.isEmpty(str) || application == null) {
            return null;
        }
        String request = NetApi.getInstance().request(false, "https://webcast-open.douyin.com/webcast/openapi/pangle/setting/?app_id=" + str + "&package_name=" + application.getPackageName(), null);
        if (TextUtils.isEmpty(request)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(request);
            ResPackage resPackage = new ResPackage();
            resPackage.statusCode = jSONObject.optLong("status_code");
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            resPackage.resUrl = optJSONObject.optString("setting_url");
            resPackage.version = optJSONObject.optLong("version");
            return resPackage;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void download(final Context context, final String str, final String str2, final String str3, final ILiveMaterialGet iLiveMaterialGet) {
        if (this.isDownloading) {
            TTLogger.m17372d(TAG, "live init : Downloading...");
            return;
        }
        try {
            Downloader.with(context).url(str).savePath(str2).name(str3).retryCount(3).needRetryDelay(false).subThreadListener(new AbsDownloadListener() { // from class: com.bytedance.android.openliveplugin.material.LiveInitMaterialManager.1
                @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
                public void onSuccessed(DownloadInfo downloadInfo) {
                    try {
                        ZipFileUtil.unZipFolder(new File(downloadInfo.getSavePath(), downloadInfo.getName()).getAbsolutePath(), downloadInfo.getSavePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ConfigParams localConfigParams = LiveInitMaterialManager.this.getLocalConfigParams(context);
                    ILiveMaterialGet iLiveMaterialGet2 = iLiveMaterialGet;
                    if (iLiveMaterialGet2 != null) {
                        iLiveMaterialGet2.onMaterialGet(localConfigParams);
                    }
                    TTLogger.m17372d(LiveInitMaterialManager.TAG, "live init : Download end.");
                    LiveInitMaterialManager.this.isDownloading = false;
                }

                @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
                public void onFailed(DownloadInfo downloadInfo, BaseException baseException) {
                    TTLogger.m17368e(LiveInitMaterialManager.TAG, "live init : Download failed. " + baseException);
                    LiveInitMaterialManager.this.isDownloading = false;
                    if (LiveInitMaterialManager.this.mRetryCount.getAndIncrement() < 3) {
                        LiveInitMaterialManager.this.download(context, str, str2, str3, iLiveMaterialGet);
                    }
                }

                @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
                public void onCanceled(DownloadInfo downloadInfo) {
                    TTLogger.m17372d(LiveInitMaterialManager.TAG, "live init : Download task canceled.");
                    LiveInitMaterialManager.this.isDownloading = false;
                }
            }).download();
            TTLogger.m17372d(TAG, "live init : Download start.");
            this.isDownloading = true;
        } catch (Throwable th) {
            TTLogger.m17368e(TAG, "live init : Download file error: " + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConfigParams getLocalConfigParams(Context context) {
        File[] listFiles;
        JSONObject jSONObject;
        ConfigParams configParams;
        File file = new File(getMaterialDownloadPath(context));
        ConfigParams configParams2 = null;
        if (file.exists() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
            File file2 = null;
            File file3 = null;
            for (File file4 : listFiles) {
                if (file4.isFile() && file4.getName().contains("_config")) {
                    file2 = file4;
                }
                if (file4.isFile() && file4.getAbsolutePath().endsWith(".lic")) {
                    file3 = file4;
                }
                if (file2 != null && file3 != null) {
                    break;
                }
            }
            if (file2 != null) {
                try {
                    jSONObject = new JSONObject(ZipFileUtil.readTextFile(file2));
                    configParams = new ConfigParams();
                } catch (JSONException e) {
                    e = e;
                }
                try {
                    configParams.appID = jSONObject.optLong("appid");
                    configParams.webcastAppID = jSONObject.optLong("webcast_appid");
                    configParams.ttsdkAppID = jSONObject.optLong("ttsdk_appid");
                    configParams.clientKey = jSONObject.optString("client_key");
                    configParams.version = jSONObject.optLong("version");
                    if (file3 != null) {
                        configParams.ttSDKLicensePath = file3.getAbsolutePath();
                    }
                    return configParams;
                } catch (JSONException e2) {
                    e = e2;
                    configParams2 = configParams;
                    TTLogger.m17368e(TAG, "live init : parse local file error : " + e);
                    e.printStackTrace();
                    return configParams2;
                }
            }
            return null;
        }
        return null;
    }

    private String getMaterialDownloadPath(Context context) {
        return context.getFilesDir() + File.separator + "pangle_com.byted.live.lite" + File.separator + ".msdata_lc" + File.separator;
    }
}
