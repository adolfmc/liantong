package com.unionpay;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface UPQuerySEPayInfoCallback {
    void onError(String str, String str2, String str3, String str4);

    void onResult(String str, String str2, int i, Bundle bundle);
}
