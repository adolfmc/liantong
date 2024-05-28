package com.tencent.p348mm.sdk.openapi;

import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.modelbase.BaseResp;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.openapi.IWXAPIEventHandler */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IWXAPIEventHandler {
    void onReq(BaseReq baseReq);

    void onResp(BaseResp baseResp);
}
