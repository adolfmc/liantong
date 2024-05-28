package com.p319ss.android.downloadad.api.download;

import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.downloadlib.addownload.C9940x;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadad.api.download.AdDownloadController */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AdDownloadController implements DownloadController {
    protected int mDownloadMode;
    protected Object mExtraClickOperation;
    protected JSONObject mExtraJson;
    protected Object mExtraObject;
    protected int mInterceptFlag;
    protected boolean mIsAddToDownloadManage;
    protected boolean mIsAutoDownloadOnCardShow;
    protected boolean mIsEnableBackDialog;
    protected int mLinkMode;
    protected boolean mShouldUseNewWebView;
    protected boolean mEnableShowComplianceDialog = true;
    protected boolean mEnableNewActivity = true;
    protected boolean mEnableAH = true;
    protected boolean mEnableAM = true;

    @Override // com.p319ss.android.download.api.download.DownloadController
    public int getDowloadChunkCount() {
        return 1;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean isEnableMultipleDownload() {
        return false;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public int getLinkMode() {
        return this.mLinkMode;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public int getDownloadMode() {
        return this.mDownloadMode;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean isEnableBackDialog() {
        return this.mIsEnableBackDialog;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean isAddToDownloadManage() {
        return this.mIsAddToDownloadManage;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public Object getExtraClickOperation() {
        return this.mExtraClickOperation;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean shouldUseNewWebView() {
        return this.mShouldUseNewWebView;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public int getInterceptFlag() {
        return this.mInterceptFlag;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public JSONObject getExtraJson() {
        return this.mExtraJson;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public Object getExtraObject() {
        return this.mExtraObject;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public void setDownloadMode(int i) {
        this.mDownloadMode = i;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public void setLinkMode(int i) {
        this.mLinkMode = i;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean enableShowComplianceDialog() {
        return this.mEnableShowComplianceDialog;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public void setEnableShowComplianceDialog(boolean z) {
        this.mEnableShowComplianceDialog = z;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean isAutoDownloadOnCardShow() {
        return this.mIsAutoDownloadOnCardShow;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean enableNewActivity() {
        return this.mEnableNewActivity;
    }

    public void setIsAutoDownloadOnCardShow(boolean z) {
        this.mIsAutoDownloadOnCardShow = z;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public void setEnableNewActivity(boolean z) {
        this.mEnableNewActivity = z;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean enableAH() {
        return this.mEnableAH;
    }

    @Override // com.p319ss.android.download.api.download.DownloadController
    public boolean enableAM() {
        return this.mEnableAM;
    }

    public void setExtraObject(Object obj) {
        this.mExtraObject = obj;
    }

    public void setExtraJson(JSONObject jSONObject) {
        this.mExtraJson = jSONObject;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("link_mode", Integer.valueOf(this.mLinkMode));
            jSONObject.putOpt("download_mode", Integer.valueOf(this.mDownloadMode));
            int i = 1;
            jSONObject.putOpt("enable_back_dialog", Integer.valueOf(this.mIsEnableBackDialog ? 1 : 0));
            jSONObject.putOpt("add_to_manage", Integer.valueOf(this.mIsAddToDownloadManage ? 1 : 0));
            jSONObject.putOpt("use_new_webview", Integer.valueOf(this.mShouldUseNewWebView ? 1 : 0));
            jSONObject.putOpt("intercept_flag", Integer.valueOf(this.mInterceptFlag));
            jSONObject.putOpt("enable_show_compliance_dialog", Integer.valueOf(this.mEnableShowComplianceDialog ? 1 : 0));
            jSONObject.putOpt("is_auto_download_on_card_show", Integer.valueOf(this.mIsAutoDownloadOnCardShow ? 1 : 0));
            jSONObject.putOpt("extra", this.mExtraJson);
            jSONObject.putOpt("enable_new_activity", Integer.valueOf(this.mEnableNewActivity ? 1 : 0));
            jSONObject.putOpt("enable_ah", Integer.valueOf(this.mEnableAH ? 1 : 0));
            if (!this.mEnableAM) {
                i = 0;
            }
            jSONObject.putOpt("enable_am", Integer.valueOf(i));
        } catch (Exception e) {
            C9940x.m7363m().mo7282mb(e, "AdDownloadController toJson");
        }
        return jSONObject;
    }

    public static AdDownloadController fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Builder builder = new Builder();
        try {
            builder.setLinkMode(jSONObject.optInt("link_mode"));
            builder.setDownloadMode(jSONObject.optInt("download_mode"));
            builder.setIsEnableBackDialog(jSONObject.optInt("enable_back_dialog") == 1);
            builder.setIsAddToDownloadManage(jSONObject.optInt("add_to_manage") == 1);
            builder.setShouldUseNewWebView(jSONObject.optInt("use_new_webview") == 1);
            builder.setInterceptFlag(jSONObject.optInt("intercept_flag"));
            builder.setEnableShowComplianceDialog(jSONObject.optInt("enable_show_compliance_dialog", 1) == 1);
            builder.setIsAutoDownloadOnCardShow(jSONObject.optInt("is_auto_download_on_card_show") == 1);
            builder.setEnableNewActivity(jSONObject.optInt("enable_new_activity", 1) == 1);
            builder.setEnableAH(jSONObject.optInt("enable_ah", 1) == 1);
            builder.setEnableAM(jSONObject.optInt("enable_am", 1) == 1);
            builder.setExtraJson(jSONObject.optJSONObject("extra"));
        } catch (Exception e) {
            C9940x.m7363m().mo7282mb(e, "AdDownloadController fromJson");
        }
        return builder.build();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadad.api.download.AdDownloadController$Builder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class Builder {
        AdDownloadController controller = new AdDownloadController();

        @Deprecated
        public Builder setDowloadChunkCount(int i) {
            return this;
        }

        @Deprecated
        public Builder setIsEnableMultipleDownload(boolean z) {
            return this;
        }

        public Builder setLinkMode(int i) {
            this.controller.mLinkMode = i;
            return this;
        }

        public Builder setDownloadMode(int i) {
            this.controller.mDownloadMode = i;
            return this;
        }

        public Builder setIsEnableBackDialog(boolean z) {
            this.controller.mIsEnableBackDialog = z;
            return this;
        }

        public Builder setIsAddToDownloadManage(boolean z) {
            this.controller.mIsAddToDownloadManage = z;
            return this;
        }

        @Deprecated
        public Builder setExtraOperation(Object obj) {
            this.controller.mExtraClickOperation = obj;
            return this;
        }

        public Builder setShouldUseNewWebView(boolean z) {
            this.controller.mShouldUseNewWebView = z;
            return this;
        }

        public Builder setInterceptFlag(int i) {
            this.controller.mInterceptFlag = i;
            return this;
        }

        public Builder setExtraJson(JSONObject jSONObject) {
            this.controller.mExtraJson = jSONObject;
            return this;
        }

        public Builder setExtraObject(Object obj) {
            this.controller.mExtraObject = obj;
            return this;
        }

        public Builder setEnableShowComplianceDialog(boolean z) {
            this.controller.mEnableShowComplianceDialog = z;
            return this;
        }

        public Builder setIsAutoDownloadOnCardShow(boolean z) {
            this.controller.mIsAutoDownloadOnCardShow = z;
            return this;
        }

        public Builder setEnableNewActivity(boolean z) {
            this.controller.mEnableNewActivity = z;
            return this;
        }

        public Builder setEnableAH(boolean z) {
            this.controller.mEnableAH = z;
            return this;
        }

        public Builder setEnableAM(boolean z) {
            this.controller.mEnableAM = z;
            return this;
        }

        public AdDownloadController build() {
            return this.controller;
        }
    }
}
