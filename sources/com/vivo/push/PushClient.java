package com.vivo.push;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.listener.IPushQueryActionListener;
import com.vivo.push.p368b.DefaultCommand;
import com.vivo.push.p370d.ISyncProfileInfo;
import com.vivo.push.p370d.SyncProfileInfoImpl;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.request.IPushRequestCallback;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.VivoPushException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PushClient extends BasePushClient implements ISyncProfileInfo {
    public static final String DEFAULT_REQUEST_ID = "1";
    private static volatile PushClient sPushClient;
    private Context mContext;
    private boolean mIsInitSdk;
    private boolean mIsSdkInited = true;
    private ISyncProfileInfo mSyncProfileInfo;

    private PushClient(Context context) {
        this.mContext = ContextDelegate.getContext(context);
        PushClientController.m5593a().m5592a(context);
        PushClientManager.m5648a().m5646a(context);
        this.mSyncProfileInfo = new SyncProfileInfoImpl();
    }

    public static synchronized PushClient getInstance(Context context) {
        PushClient pushClient;
        synchronized (PushClient.class) {
            if (sPushClient == null) {
                sPushClient = new PushClient(context.getApplicationContext());
            }
            pushClient = sPushClient;
        }
        return pushClient;
    }

    public void initialize(PushConfig pushConfig) throws VivoPushException {
        if (pushConfig == null) {
            throw new VivoPushException("initialize error config is null");
        }
        PushClientController.m5593a().m5588e().mo5540a(pushConfig);
        initialize();
    }

    private void initialize() throws VivoPushException {
        if (checkAgreePrivacyStatementAndInitSdk()) {
            checkManifest();
            PushClientManager m5648a = PushClientManager.m5648a();
            DefaultCommand defaultCommand = new DefaultCommand();
            PushClientController.m5593a();
            defaultCommand.m5801d();
            m5648a.m5638a(defaultCommand);
            if (this.mIsSdkInited) {
                return;
            }
            this.mIsSdkInited = true;
        }
    }

    private boolean checkAgreePrivacyStatementAndInitSdk() {
        if (PushClientController.m5593a().m5588e().mo5524l().isAgreePrivacyStatement()) {
            inidSdk(this.mContext);
            return true;
        }
        return false;
    }

    private void inidSdk(Context context) {
        synchronized (this) {
            if (!this.mIsInitSdk) {
                PushClientManager.m5648a().m5646a(context);
                this.mIsInitSdk = true;
            }
        }
    }

    private boolean isSdkInited() {
        return this.mIsSdkInited;
    }

    public void checkManifest() throws VivoPushException {
        if (checkAgreePrivacyStatementAndInitSdk() && isSdkInited()) {
            PushClientManager.m5648a().m5629b();
        }
    }

    private void checkParam(String str) {
        if (str == null) {
            throw new IllegalArgumentException("PushManager String param should not be ".concat(String.valueOf(str)));
        }
    }

    public void bindAlias(String str, IPushActionListener iPushActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(104);
            }
        } else if (isSdkInited()) {
            checkParam(str);
            PushClientManager.m5648a().m5633a(str, getAppId(""), getAppKey(""), iPushActionListener);
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(8011);
        }
    }

    private String getAppId(String str) {
        return !TextUtils.isEmpty(str) ? str : PushClientController.m5593a().m5588e().mo5543a();
    }

    private String getAppKey(String str) {
        return !TextUtils.isEmpty(str) ? str : PushClientController.m5593a().m5588e().mo5536c();
    }

    public void unBindAlias(String str, IPushActionListener iPushActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(104);
            }
        } else if (isSdkInited()) {
            checkParam(str);
            PushClientManager.m5648a().m5625b(str, getAppId(""), getAppKey(""), iPushActionListener);
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(8011);
        }
    }

    public void turnOnPush(IPushActionListener iPushActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(104);
            }
        } else if (isSdkInited()) {
            PushClientManager.m5648a().m5644a(iPushActionListener, getAppId(""), getAppKey(""));
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(8011);
        }
    }

    public void turnOffPush(IPushActionListener iPushActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(104);
            }
        } else if (isSdkInited()) {
            PushClientManager.m5648a().m5621c(iPushActionListener, getAppId(""), getAppKey(""));
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(8011);
        }
    }

    public String getAlias() {
        if (checkAgreePrivacyStatementAndInitSdk() && isSdkInited()) {
            return PushClientManager.m5648a().m5613i();
        }
        return null;
    }

    @Override // com.vivo.push.BasePushClient
    public void getRegId(IPushQueryActionListener iPushQueryActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushQueryActionListener != null) {
                iPushQueryActionListener.onFail(104);
            }
        } else if (isSdkInited()) {
            super.getRegId(iPushQueryActionListener);
        } else if (iPushQueryActionListener != null) {
            iPushQueryActionListener.onFail(8011);
        }
    }

    public String getVersion() {
        if (checkAgreePrivacyStatementAndInitSdk() && isSdkInited()) {
            return "3.4.0.0";
        }
        return null;
    }

    public void setTopic(String str, IPushActionListener iPushActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(104);
            }
        } else if (isSdkInited()) {
            ArrayList<String> arrayList = new ArrayList<>(1);
            arrayList.add(str);
            PushClientManager.m5648a().m5632a(arrayList, getAppId(""), getAppKey(""), iPushActionListener);
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(8011);
        }
    }

    public void delTopic(String str, IPushActionListener iPushActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(104);
            }
        } else if (isSdkInited()) {
            ArrayList<String> arrayList = new ArrayList<>(1);
            arrayList.add(str);
            PushClientManager.m5648a().m5624b(arrayList, getAppId(""), getAppKey(""), iPushActionListener);
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(8011);
        }
    }

    public List<String> getTopics() {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            return new ArrayList();
        }
        if (!isSdkInited()) {
            return new ArrayList();
        }
        PushClientManager.m5648a();
        return PushClientManager.m5622c();
    }

    public void setSystemModel(boolean z) {
        if (checkAgreePrivacyStatementAndInitSdk() && isSdkInited()) {
            PushClientManager.m5648a().m5630a(z);
        }
    }

    public boolean isSupport() {
        if (checkAgreePrivacyStatementAndInitSdk() && isSdkInited()) {
            return PushClientManager.m5648a().m5618d();
        }
        return false;
    }

    public int isSupportNewControlStrategies() {
        if (checkAgreePrivacyStatementAndInitSdk() && isSdkInited()) {
            return PushClientController.m5593a().m5586g().m5759a();
        }
        return -1;
    }

    public int isSupportSyncProfileInfo() {
        if (checkAgreePrivacyStatementAndInitSdk() && isSdkInited()) {
            return PushClientController.m5593a().m5586g().m5756b();
        }
        return -1;
    }

    @Override // com.vivo.push.p370d.ISyncProfileInfo
    public void addProfileId(String str, IPushRequestCallback<Integer> iPushRequestCallback) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(104);
            }
        } else if (!isSdkInited()) {
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(8011);
            }
        } else {
            ISyncProfileInfo iSyncProfileInfo = this.mSyncProfileInfo;
            if (iSyncProfileInfo != null) {
                iSyncProfileInfo.addProfileId(str, iPushRequestCallback);
            }
        }
    }

    @Override // com.vivo.push.p370d.ISyncProfileInfo
    public void deleteProfileId(String str, IPushRequestCallback<Integer> iPushRequestCallback) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(104);
            }
        } else if (!isSdkInited()) {
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(8011);
            }
        } else {
            ISyncProfileInfo iSyncProfileInfo = this.mSyncProfileInfo;
            if (iSyncProfileInfo != null) {
                iSyncProfileInfo.deleteProfileId(str, iPushRequestCallback);
            }
        }
    }

    @Override // com.vivo.push.p370d.ISyncProfileInfo
    public void deleteAllProfileId(IPushRequestCallback<Integer> iPushRequestCallback) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(104);
            }
        } else if (!isSdkInited()) {
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(8011);
            }
        } else {
            ISyncProfileInfo iSyncProfileInfo = this.mSyncProfileInfo;
            if (iSyncProfileInfo != null) {
                iSyncProfileInfo.deleteAllProfileId(iPushRequestCallback);
            }
        }
    }

    @Override // com.vivo.push.p370d.ISyncProfileInfo
    public void queryProfileIds(IPushRequestCallback<List<String>> iPushRequestCallback) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(104);
            }
        } else if (!isSdkInited()) {
            if (iPushRequestCallback != null) {
                iPushRequestCallback.onError(8011);
            }
        } else {
            ISyncProfileInfo iSyncProfileInfo = this.mSyncProfileInfo;
            if (iSyncProfileInfo != null) {
                iSyncProfileInfo.queryProfileIds(iPushRequestCallback);
            }
        }
    }

    public void deleteRegid(IPushActionListener iPushActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk()) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(104);
            }
        } else if (isSdkInited()) {
            super.deleteRegid(iPushActionListener, getAppId(""), getAppKey(""));
        } else if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(8011);
        }
    }

    @Override // com.vivo.push.BasePushClient
    public void querySubscribeState(IPushActionListener iPushActionListener) {
        if (!checkAgreePrivacyStatementAndInitSdk() && iPushActionListener != null) {
            iPushActionListener.onStateChanged(104);
        }
        if (!isSdkInited() && iPushActionListener != null) {
            iPushActionListener.onStateChanged(8011);
        }
        super.querySubscribeState(iPushActionListener);
    }
}
