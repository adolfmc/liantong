package com.alibaba.android.arouter.facade.callback;

import com.alibaba.android.arouter.facade.Postcard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface NavigationCallback {
    void onArrival(Postcard postcard);

    void onFound(Postcard postcard);

    void onInterrupt(Postcard postcard);

    void onLost(Postcard postcard);
}
