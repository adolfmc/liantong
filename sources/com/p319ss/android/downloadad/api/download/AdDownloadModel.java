package com.p319ss.android.downloadad.api.download;

import android.text.TextUtils;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.model.C9828b;
import com.p319ss.android.download.api.model.DeepLink;
import com.p319ss.android.download.api.p320b.C9779ox;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.socialbase.downloader.constants.ExecutorGroup;
import com.p319ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadad.api.download.AdDownloadModel */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AdDownloadModel implements DownloadModel {
    protected String mAppIcon;
    protected String mAppName;
    protected boolean mAutoInstallWithoutNotification;
    protected List<String> mBackupUrls;
    protected List<String> mClickTrackUrl;
    protected DeepLink mDeepLink;
    protected boolean mDistinctDir;
    protected JSONObject mDownloadSettings;
    protected String mDownloadUrl;
    protected long mExpectFileLength;
    protected JSONObject mExtra;
    protected long mExtraValue;
    protected String mFileName;
    protected String mFilePath;
    protected IDownloadFileUriProvider mFileUriProvider;
    protected Map<String, String> mHeaders;
    protected long mId;
    protected boolean mIndependentProcess;
    @Deprecated
    protected boolean mIsInExternalPublicDir;
    protected String mLogExtra;
    protected String mMd5;
    protected String mMimeType;
    protected int mModelType;
    protected String mNotificationJumpUrl;
    protected String mPackageName;
    protected C9828b mQuickAppModel;
    protected String mSdkMonitorScene;
    protected String mStartToast;
    protected int mVersionCode;
    protected String mVersionName;
    protected boolean mIsAd = true;
    protected boolean mIsShowToast = true;
    protected boolean mIsShowNotification = true;
    protected boolean mAutoInstall = true;
    protected boolean mNeedWifi = false;
    @ExecutorGroup
    protected int mExecutorGroup = 2;
    protected boolean mEnablePause = true;
    protected int mFunnelType = 1;

    protected AdDownloadModel() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r1 = (99 * 99) - ((18 * 18) * 34);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0000, code lost:
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String AdDownloadModel1672829046101dc(java.lang.String r4) {
        /*
        L0:
            r0 = 74
            r1 = 55
        L4:
            r2 = 0
            switch(r0) {
                case 72: goto L0;
                case 73: goto L9;
                case 74: goto Lc;
                default: goto L8;
            }
        L8:
            goto L54
        L9:
            switch(r1) {
                case 94: goto L4f;
                case 95: goto L10;
                case 96: goto L39;
                default: goto Lc;
            }
        Lc:
            switch(r1) {
                case 55: goto L4f;
                case 56: goto L4f;
                case 57: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L4f
        L10:
            r0 = 18
            r1 = 1
            switch(r1) {
                case 60: goto L17;
                case 61: goto L23;
                case 62: goto L30;
                default: goto L16;
            }
        L16:
            goto L39
        L17:
            int r3 = 0 - r1
            int r3 = r3 * r2
            r2 = 0
            int r2 = r2 * 2
            int r2 = r2 - r1
            int r3 = r3 * r2
            int r3 = r3 % 6
            if (r3 == 0) goto L0
        L23:
            int r2 = 18 - r1
            int r2 = r2 * r0
            r3 = 18
            int r3 = r3 * 2
            int r3 = r3 - r1
            int r2 = r2 * r3
            int r2 = r2 % 6
            if (r2 == 0) goto L4f
        L30:
            r1 = 99
            int r1 = r1 * r1
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r1 = r1 - r0
            r0 = -1
            goto L0
        L39:
            char[] r4 = r4.toCharArray()
        L3d:
            int r0 = r4.length
            if (r2 >= r0) goto L49
            char r0 = r4[r2]
            r0 = r0 ^ r2
            char r0 = (char) r0
            r4[r2] = r0
            int r2 = r2 + 1
            goto L3d
        L49:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            return r0
        L4f:
            r0 = 73
            r1 = 96
            goto L4
        L54:
            r0 = 72
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.downloadad.api.download.AdDownloadModel.AdDownloadModel1672829046101dc(java.lang.String):java.lang.String");
    }

    private static void appendBackupUrlsFromJson(JSONObject jSONObject, Builder builder) {
        JSONArray optJSONArray = jSONObject.optJSONArray("backup_urls");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.optString(i));
        }
        builder.setBackupUrls(arrayList);
    }

    private static void appendDeepLinkFromJson(JSONObject jSONObject, Builder builder) {
        builder.setDeepLink(new DeepLink(jSONObject.optString("open_url"), jSONObject.optString("web_url"), null));
    }

    private static void appendHeaderMapFromJson(JSONObject jSONObject, Builder builder) {
        JSONArray optJSONArray = jSONObject.optJSONArray("header_keys");
        JSONArray optJSONArray2 = jSONObject.optJSONArray("header_values");
        if (optJSONArray == null || optJSONArray2 == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            hashMap.put(optJSONArray.optString(i), optJSONArray2.optString(i));
        }
        builder.setHeaders(hashMap);
    }

    private static void appendQuickAppUrlFromJson(JSONObject jSONObject, Builder builder) {
        String optString = jSONObject.optString("quick_app_url");
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        builder.setQuickAppModel(new C9828b.C9829mb().m7879mb(optString).m7881mb());
    }

    private static void appendTrackUrlFromJson(JSONObject jSONObject, Builder builder) {
        JSONArray optJSONArray = jSONObject.optJSONArray("click_track_urls");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.optString(i));
            }
            builder.setClickTrackUrl(arrayList);
        }
    }

    public static AdDownloadModel fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Builder builder = new Builder();
        try {
            boolean z = true;
            Builder distinctDir = builder.setAdId(C9779ox.m7968mb(jSONObject, "id")).setIsAd(jSONObject.optInt("is_ad", 1) == 1).setModelType(jSONObject.optInt("model_type")).setMimeType(jSONObject.optString("mime_type")).setExtraValue(C9779ox.m7968mb(jSONObject, "ext_value")).setLogExtra(jSONObject.optString("log_extra")).setPackageName(jSONObject.optString("package_name")).setDownloadUrl(jSONObject.optString("download_url")).setAppName(jSONObject.optString("app_name")).setAppIcon(jSONObject.optString("app_icon")).setIsShowToast(jSONObject.optInt("is_show_toast", 1) == 1).setIsShowNotification(jSONObject.optInt("show_notification", 1) == 1).setNeedWifi(jSONObject.optInt("need_wifi") == 1).setMd5(jSONObject.optString("md5")).setExpectFileLength(jSONObject.optLong("expect_file_length")).setNeedIndependentProcess(jSONObject.optInt("independent_process") == 1).setVersionCode(jSONObject.optInt("version_code")).setVersionName(jSONObject.optString("version_name")).setFilePath(jSONObject.optString("file_path")).setFileName(jSONObject.optString("file_name")).setNotificationJumpUrl(jSONObject.optString("notification_jump_url")).setAutoInstallWithoutNotification(jSONObject.optInt("auto_install_without_notify") == 1).setExecutorGroup(jSONObject.optInt("executor_group")).setDownloadSettings(jSONObject.optJSONObject("download_settings")).setExtra(jSONObject.optJSONObject("extra")).setStartToast(jSONObject.optString("start_toast")).setSdkMonitorScene(jSONObject.optString("sdk_monitor_scene")).setAutoInstall(jSONObject.optInt("auto_install", 1) == 1).setDistinctDir(jSONObject.optInt("distinct_dir") == 1);
            if (jSONObject.optInt("enable_pause", 1) != 1) {
                z = false;
            }
            distinctDir.setEnablePause(z);
            appendDeepLinkFromJson(jSONObject, builder);
            appendQuickAppUrlFromJson(jSONObject, builder);
            appendTrackUrlFromJson(jSONObject, builder);
            appendHeaderMapFromJson(jSONObject, builder);
            appendBackupUrlsFromJson(jSONObject, builder);
        } catch (Exception e) {
            C9940x.m7363m().mo7282mb(e, "AdDownloadModel fromJson");
        }
        return builder.build();
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean autoInstallWithoutNotification() {
        return this.mAutoInstallWithoutNotification;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean distinctDir() {
        return this.mDistinctDir;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean enablePause() {
        return this.mEnablePause;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public void forceHideNotification() {
        this.mIsShowNotification = false;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public void forceHideToast() {
        this.mIsShowToast = false;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public void forceWifi() {
        this.mNeedWifi = true;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getAppIcon() {
        return this.mAppIcon;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public List<String> getBackupUrls() {
        return this.mBackupUrls;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public List<String> getClickTrackUrl() {
        return this.mClickTrackUrl;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public DeepLink getDeepLink() {
        return this.mDeepLink;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public IDownloadFileUriProvider getDownloadFileUriProvider() {
        return this.mFileUriProvider;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public JSONObject getDownloadSettings() {
        return this.mDownloadSettings;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public int getExecutorGroup() {
        return this.mExecutorGroup;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public long getExpectFileLength() {
        return this.mExpectFileLength;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public JSONObject getExtra() {
        return this.mExtra;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public long getExtraValue() {
        return this.mExtraValue;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getFileName() {
        return this.mFileName;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getFilePath() {
        return this.mFilePath;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public int getFunnelType() {
        return this.mFunnelType;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public long getId() {
        return this.mId;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getLogExtra() {
        return this.mLogExtra;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getMd5() {
        return this.mMd5;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getMimeType() {
        return this.mMimeType;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public int getModelType() {
        return this.mModelType;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getName() {
        return this.mAppName;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getNotificationJumpUrl() {
        return this.mNotificationJumpUrl;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getPackageName() {
        return this.mPackageName;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public C9828b getQuickAppModel() {
        return this.mQuickAppModel;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getSdkMonitorScene() {
        return this.mSdkMonitorScene;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getStartToast() {
        return this.mStartToast;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public int getVersionCode() {
        return this.mVersionCode;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public String getVersionName() {
        return this.mVersionName;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean isAd() {
        return this.mIsAd;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean isAutoInstall() {
        return this.mAutoInstall;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean isInExternalPublicDir() {
        return this.mIsInExternalPublicDir;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean isNeedWifi() {
        return this.mNeedWifi;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean isShowNotification() {
        return this.mIsShowNotification;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean isShowToast() {
        return this.mIsShowToast;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean isVisibleInDownloadsUi() {
        return true;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean needIndependentProcess() {
        return this.mIndependentProcess;
    }

    @Deprecated
    public AdDownloadModel setAdId(long j) {
        return setId(j);
    }

    public AdDownloadModel setAppIcon(String str) {
        this.mAppIcon = str;
        return this;
    }

    public void setAppName(String str) {
        this.mAppName = str;
    }

    public AdDownloadModel setAutoInstallWithoutNotification(boolean z) {
        this.mAutoInstallWithoutNotification = z;
        return this;
    }

    public AdDownloadModel setBackupUrls(List<String> list) {
        this.mBackupUrls = list;
        return this;
    }

    public AdDownloadModel setClickTrackUrl(List<String> list) {
        this.mClickTrackUrl = list;
        return this;
    }

    public AdDownloadModel setDeepLink(DeepLink deepLink) {
        this.mDeepLink = deepLink;
        return this;
    }

    public AdDownloadModel setDownloadUrl(String str) {
        this.mDownloadUrl = str;
        return this;
    }

    public void setExpectFileLength(long j) {
        this.mExpectFileLength = j;
    }

    public void setExtra(JSONObject jSONObject) {
        this.mExtra = jSONObject;
    }

    public void setExtraValue(long j) {
        this.mExtraValue = j;
    }

    public AdDownloadModel setFileName(String str) {
        this.mFileName = str;
        return this;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public AdDownloadModel setFilePath(String str) {
        this.mFilePath = str;
        return this;
    }

    public AdDownloadModel setFileUriProvider(IDownloadFileUriProvider iDownloadFileUriProvider) {
        this.mFileUriProvider = iDownloadFileUriProvider;
        return this;
    }

    public AdDownloadModel setFunnelType(int i) {
        this.mFunnelType = i;
        return this;
    }

    public AdDownloadModel setHeaders(Map<String, String> map) {
        this.mHeaders = map;
        return this;
    }

    public AdDownloadModel setId(long j) {
        this.mId = j;
        return this;
    }

    public AdDownloadModel setIsAd(boolean z) {
        this.mIsAd = z;
        return this;
    }

    public AdDownloadModel setIsShowNotification(boolean z) {
        this.mIsShowNotification = z;
        return this;
    }

    public void setIsShowToast(boolean z) {
        this.mIsShowToast = z;
    }

    public AdDownloadModel setLogExtra(String str) {
        this.mLogExtra = str;
        return this;
    }

    public void setMd5(String str) {
        this.mMd5 = str;
    }

    public AdDownloadModel setMimeType(String str) {
        this.mMimeType = str;
        return this;
    }

    public AdDownloadModel setModelType(int i) {
        this.mModelType = i;
        return this;
    }

    public AdDownloadModel setNeedIndependentProcess(boolean z) {
        this.mIndependentProcess = z;
        return this;
    }

    public void setNeedWifi(boolean z) {
        this.mNeedWifi = z;
    }

    public AdDownloadModel setNotificationJumpUrl(String str) {
        this.mNotificationJumpUrl = str;
        return this;
    }

    public AdDownloadModel setPackageName(String str) {
        this.mPackageName = str;
        return this;
    }

    public AdDownloadModel setQuickAppModel(C9828b c9828b) {
        this.mQuickAppModel = c9828b;
        return this;
    }

    public void setSdkMonitorScene(String str) {
        this.mSdkMonitorScene = str;
    }

    public void setStartToast(String str) {
        this.mStartToast = str;
    }

    public AdDownloadModel setVersionCode(int i) {
        this.mVersionCode = i;
        return this;
    }

    public AdDownloadModel setVersionName(String str) {
        this.mVersionName = str;
        return this;
    }

    @Override // com.p319ss.android.download.api.download.DownloadModel
    public boolean shouldDownloadWithPatchApply() {
        return C9779ox.m7970mb(DownloadSetting.obtain(getDownloadSettings()), getMimeType());
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.mId);
            int i = 1;
            jSONObject.put("is_ad", this.mIsAd ? 1 : 0);
            jSONObject.putOpt("model_type", Integer.valueOf(this.mModelType));
            jSONObject.putOpt("mime_type", this.mMimeType);
            jSONObject.putOpt("ext_value", Long.valueOf(this.mExtraValue));
            jSONObject.putOpt("log_extra", this.mLogExtra);
            jSONObject.putOpt("package_name", this.mPackageName);
            jSONObject.putOpt("download_url", this.mDownloadUrl);
            jSONObject.putOpt("app_name", this.mAppName);
            jSONObject.putOpt("app_icon", this.mAppIcon);
            jSONObject.putOpt("is_show_toast", Integer.valueOf(this.mIsShowToast ? 1 : 0));
            jSONObject.putOpt("show_notification", Integer.valueOf(this.mIsShowNotification ? 1 : 0));
            jSONObject.put("need_wifi", this.mNeedWifi ? 1 : 0);
            jSONObject.put("md5", this.mMd5);
            jSONObject.put("expect_file_length", this.mExpectFileLength);
            jSONObject.put("independent_process", this.mIndependentProcess ? 1 : 0);
            jSONObject.put("version_code", this.mVersionCode);
            jSONObject.putOpt("version_name", this.mVersionName);
            jSONObject.putOpt("file_path", this.mFilePath);
            jSONObject.putOpt("file_name", this.mFileName);
            jSONObject.putOpt("notification_jump_url", this.mNotificationJumpUrl);
            jSONObject.putOpt("auto_install_without_notify", Integer.valueOf(this.mAutoInstallWithoutNotification ? 1 : 0));
            jSONObject.putOpt("executor_group", Integer.valueOf(this.mExecutorGroup));
            jSONObject.putOpt("start_toast", this.mStartToast);
            jSONObject.putOpt("sdk_monitor_scene", this.mSdkMonitorScene);
            jSONObject.putOpt("auto_install", Integer.valueOf(this.mAutoInstall ? 1 : 0));
            jSONObject.putOpt("distinct_dir", Integer.valueOf(this.mDistinctDir ? 1 : 0));
            if (!this.mEnablePause) {
                i = 0;
            }
            jSONObject.putOpt("enable_pause", Integer.valueOf(i));
            if (this.mDownloadSettings != null) {
                jSONObject.put("download_settings", this.mDownloadSettings);
            }
            if (this.mBackupUrls != null && !this.mBackupUrls.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (String str : this.mBackupUrls) {
                    if (!TextUtils.isEmpty(str)) {
                        jSONArray.put(str);
                    }
                }
                jSONObject.put("backup_urls", jSONArray);
            }
            if (this.mDeepLink != null) {
                if (!TextUtils.isEmpty(this.mDeepLink.getOpenUrl())) {
                    jSONObject.put("open_url", this.mDeepLink.getOpenUrl());
                }
                if (!TextUtils.isEmpty(this.mDeepLink.getWebUrl())) {
                    jSONObject.put("web_url", this.mDeepLink.getWebUrl());
                }
            }
            if (this.mQuickAppModel != null) {
                jSONObject.putOpt("quick_app_url", this.mQuickAppModel.m7882mb());
            }
            if (this.mClickTrackUrl != null && !this.mClickTrackUrl.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (String str2 : this.mClickTrackUrl) {
                    jSONArray2.put(str2);
                }
                jSONObject.put("click_track_urls", jSONArray2);
            }
            if (this.mExtra != null) {
                jSONObject.put("extra", this.mExtra);
            }
            if (this.mHeaders != null && !this.mHeaders.isEmpty()) {
                JSONArray jSONArray3 = new JSONArray();
                JSONArray jSONArray4 = new JSONArray();
                for (Map.Entry<String, String> entry : this.mHeaders.entrySet()) {
                    jSONArray3.put(entry.getKey());
                    jSONArray4.put(entry.getKey());
                }
                jSONObject.put("header_keys", jSONArray3);
                jSONObject.put("header_values", jSONArray4);
            }
        } catch (Exception e) {
            C9940x.m7363m().mo7282mb(e, "AdDownloadModel toJson");
        }
        return jSONObject;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadad.api.download.AdDownloadModel$Builder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class Builder {
        AdDownloadModel model = new AdDownloadModel();

        public Builder setExpectFileLength(long j) {
            this.model.mExpectFileLength = j;
            return this;
        }

        public Builder setMd5(String str) {
            this.model.mMd5 = str;
            return this;
        }

        @Deprecated
        public Builder setAdId(long j) {
            return setId(j);
        }

        public Builder setId(long j) {
            this.model.mId = j;
            return this;
        }

        public Builder setExtraValue(long j) {
            this.model.mExtraValue = j;
            return this;
        }

        public Builder setIsAd(boolean z) {
            this.model.mIsAd = z;
            return this;
        }

        public Builder setModelType(int i) {
            this.model.mModelType = i;
            return this;
        }

        public Builder setLogExtra(String str) {
            this.model.mLogExtra = str;
            return this;
        }

        public Builder setPackageName(String str) {
            this.model.mPackageName = str;
            return this;
        }

        public Builder setAppIcon(String str) {
            this.model.mAppIcon = str;
            return this;
        }

        public Builder setDeepLink(DeepLink deepLink) {
            this.model.mDeepLink = deepLink;
            return this;
        }

        public Builder setClickTrackUrl(List<String> list) {
            this.model.mClickTrackUrl = list;
            return this;
        }

        public Builder setExtra(JSONObject jSONObject) {
            this.model.mExtra = jSONObject;
            return this;
        }

        public Builder setDownloadUrl(String str) {
            this.model.mDownloadUrl = str;
            return this;
        }

        public Builder setBackupUrls(List<String> list) {
            this.model.mBackupUrls = list;
            return this;
        }

        public Builder setNotificationJumpUrl(String str) {
            this.model.mNotificationJumpUrl = str;
            return this;
        }

        public Builder setAppName(String str) {
            this.model.mAppName = str;
            return this;
        }

        public Builder setMimeType(String str) {
            this.model.mMimeType = str;
            return this;
        }

        public Builder setHeaders(Map<String, String> map) {
            this.model.mHeaders = map;
            return this;
        }

        public Builder setIsShowToast(boolean z) {
            this.model.mIsShowToast = z;
            return this;
        }

        public Builder setIsShowNotification(boolean z) {
            this.model.mIsShowNotification = z;
            return this;
        }

        public Builder setNeedWifi(boolean z) {
            this.model.mNeedWifi = z;
            return this;
        }

        public Builder setDownloadSettings(JSONObject jSONObject) {
            this.model.mDownloadSettings = jSONObject;
            return this;
        }

        @Deprecated
        public Builder setIsInExternalPublicDir(boolean z) {
            this.model.mIsInExternalPublicDir = z;
            return this;
        }

        public Builder setFilePath(String str) {
            this.model.mFilePath = str;
            return this;
        }

        public Builder setFileName(String str) {
            this.model.mFileName = str;
            return this;
        }

        public Builder setNeedIndependentProcess(boolean z) {
            this.model.mIndependentProcess = z;
            return this;
        }

        public Builder setVersionCode(int i) {
            this.model.mVersionCode = i;
            return this;
        }

        public Builder setVersionName(String str) {
            this.model.mVersionName = str;
            return this;
        }

        public Builder setQuickAppModel(C9828b c9828b) {
            this.model.mQuickAppModel = c9828b;
            return this;
        }

        public Builder setAutoInstallWithoutNotification(boolean z) {
            this.model.mAutoInstallWithoutNotification = z;
            return this;
        }

        public Builder setFileUriProvider(IDownloadFileUriProvider iDownloadFileUriProvider) {
            this.model.mFileUriProvider = iDownloadFileUriProvider;
            return this;
        }

        public Builder setExecutorGroup(@ExecutorGroup int i) {
            this.model.mExecutorGroup = i;
            return this;
        }

        public Builder setStartToast(String str) {
            this.model.mStartToast = str;
            return this;
        }

        public Builder setSdkMonitorScene(String str) {
            this.model.mSdkMonitorScene = str;
            return this;
        }

        public Builder setAutoInstall(boolean z) {
            this.model.mAutoInstall = z;
            return this;
        }

        public Builder setDistinctDir(boolean z) {
            this.model.mDistinctDir = z;
            return this;
        }

        public Builder setEnablePause(boolean z) {
            this.model.mEnablePause = z;
            return this;
        }

        public AdDownloadModel build() {
            return this.model;
        }
    }
}
