package com.bytedance.android.live.base.api.push;

import com.bytedance.android.live.base.api.push.model.PushData;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface PushCallback {
    void onFailed(Throwable th);

    void onSuccess(PushData pushData);
}
