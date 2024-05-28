package com.tencent.p348mm.sdk.openapi;

import android.content.Intent;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.modelbase.BaseResp;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.openapi.IWXAPI */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IWXAPI {
    void detach();

    int getWXAppSupportAPI();

    boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler);

    boolean isWXAppInstalled();

    boolean isWXAppSupportAPI();

    boolean openWXApp();

    boolean registerApp(String str);

    boolean sendReq(BaseReq baseReq);

    boolean sendResp(BaseResp baseResp);

    void unregisterApp();
}
