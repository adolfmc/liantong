package com.baidu.platform.comapi.map;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandlerC3071m extends Handler {

    /* renamed from: a */
    final /* synthetic */ MapController f7981a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC3071m(MapController mapController) {
        this.f7981a = mapController;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what != 1) {
            return;
        }
        this.f7981a.m18034a();
    }
}
