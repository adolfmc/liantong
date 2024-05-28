package com.bytedance.sdk.openadsdk;

import android.content.res.Resources;
import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TTPluginListener {
    Bundle config();

    void onPluginListener(int i, ClassLoader classLoader, Resources resources, Bundle bundle);

    String packageName();
}
