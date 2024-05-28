package com.huawei.hms.support.api.client;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ApiClient {
    String getAppID();

    Context getContext();

    String getCpID();

    String getPackageName();

    String getSessionId();

    SubAppInfo getSubAppInfo();

    String getTransportName();

    boolean isConnected();
}
