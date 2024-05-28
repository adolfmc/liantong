package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface SysUpdateObserver {
    void init(String str);

    void updateNetworkInfo(Context context);

    void updateNetworkProxy(Context context);

    void updatePhoneInfo(String str);
}
