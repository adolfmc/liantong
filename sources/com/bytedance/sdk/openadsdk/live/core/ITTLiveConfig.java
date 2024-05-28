package com.bytedance.sdk.openadsdk.live.core;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ITTLiveConfig {
    String getAppName();

    String getChannel();

    String getECHostAppId();

    String getGeneralAppId();

    TTHostPermissionInner getHostPermission();

    ITTLiveHostAction getLiveHostAction();

    String getPartner();

    String getPartnerSecret();

    String getVersion();

    int getVersionCode();

    boolean isDebug();

    boolean isValid();
}
