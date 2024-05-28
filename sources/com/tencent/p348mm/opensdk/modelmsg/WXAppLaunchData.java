package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXAppLaunchData */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class WXAppLaunchData {
    public static final String ACTION_HANDLE_WXAPPLAUNCH = ".ACTION_HANDLE_WXAPPLAUNCH";
    public static final String ACTION_HANDLE_WXAPP_RESULT = ".ACTION_HANDLE_WXAPP_RESULT";
    public static final String ACTION_HANDLE_WXAPP_SHOW = ".ACTION_HANDLE_WXAPP_SHOW";
    public int launchType;
    public String message;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelmsg.WXAppLaunchData$Builder */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Builder {
        public static WXAppLaunchData fromBundle(Bundle bundle) {
            WXAppLaunchData wXAppLaunchData = new WXAppLaunchData();
            wXAppLaunchData.launchType = bundle.getInt("_wxapplaunchdata_launchType");
            wXAppLaunchData.message = bundle.getString("_wxapplaunchdata_message");
            return wXAppLaunchData;
        }

        public static Bundle toBundle(WXAppLaunchData wXAppLaunchData) {
            Bundle bundle = new Bundle();
            bundle.putInt("_wxapplaunchdata_launchType", wXAppLaunchData.launchType);
            bundle.putString("_wxapplaunchdata_message", wXAppLaunchData.message);
            return bundle;
        }
    }
}
