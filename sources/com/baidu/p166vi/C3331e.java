package com.baidu.p166vi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class C3331e extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        VDeviceAPI.onNetworkStateChanged();
    }
}
