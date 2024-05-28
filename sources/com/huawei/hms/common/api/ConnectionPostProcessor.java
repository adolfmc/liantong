package com.huawei.hms.common.api;

import android.app.Activity;
import com.huawei.hms.api.HuaweiApiClient;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ConnectionPostProcessor {
    void run(HuaweiApiClient huaweiApiClient, WeakReference<Activity> weakReference);
}
