package com.bytedance.sdk.openadsdk;

import com.bytedance.sdk.openadsdk.live.ITTLiveTokenInjectionAuth;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface AdConfig {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface SdkInfo {
        boolean isPlugin();

        String pluginName();

        int sdkVersionCode();

        String sdkVersionName();
    }

    String getAppId();

    String getAppName();

    TTCustomController getCustomController();

    String getData();

    int[] getDirectDownloadNetworkType();

    @Deprecated
    Object getExtra(String str);

    ITTLiveTokenInjectionAuth getInjectionAuth();

    String getKeywords();

    @Deprecated
    String[] getNeedClearTaskReset();

    SdkInfo getSdkInfo();

    int getTitleBarTheme();

    boolean isAllowShowNotify();

    boolean isAllowShowPageWhenScreenLock();

    boolean isAsyncInit();

    boolean isDebug();

    boolean isPaid();

    boolean isSupportMultiProcess();

    boolean isUseTextureView();

    @Deprecated
    Object removeExtra(String str);

    void setAgeGroup(int i);

    @Deprecated
    void setExtra(String str, Object obj);
}
