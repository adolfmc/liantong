package com.alibaba.android.arouter.facade.callback;

import com.alibaba.android.arouter.facade.Postcard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class NavCallback implements NavigationCallback {
    @Override // com.alibaba.android.arouter.facade.callback.NavigationCallback
    public abstract void onArrival(Postcard postcard);

    @Override // com.alibaba.android.arouter.facade.callback.NavigationCallback
    public void onFound(Postcard postcard) {
    }

    @Override // com.alibaba.android.arouter.facade.callback.NavigationCallback
    public void onInterrupt(Postcard postcard) {
    }

    @Override // com.alibaba.android.arouter.facade.callback.NavigationCallback
    public void onLost(Postcard postcard) {
    }
}
