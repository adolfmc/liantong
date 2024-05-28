package cn.sharesdk.framework.authorize;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface SSOListener {
    void onCancel();

    void onComplete(Bundle bundle);

    void onFailed(Throwable th);
}
