package com.sina.weibo.sdk.share;

import com.sina.weibo.sdk.common.UiError;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface WbShareCallback {
    void onCancel();

    void onComplete();

    void onError(UiError uiError);
}
