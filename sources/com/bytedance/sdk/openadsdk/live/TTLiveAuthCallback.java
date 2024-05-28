package com.bytedance.sdk.openadsdk.live;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TTLiveAuthCallback extends Serializable {
    void onAuth(TTLiveToken tTLiveToken);

    void onFailed(Throwable th);
}
