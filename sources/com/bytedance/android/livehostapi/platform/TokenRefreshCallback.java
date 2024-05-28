package com.bytedance.android.livehostapi.platform;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TokenRefreshCallback {
    void onFailed(Throwable th);

    void onSuccess(TokenInfo tokenInfo);
}
