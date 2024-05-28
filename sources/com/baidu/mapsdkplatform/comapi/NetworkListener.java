package com.baidu.mapsdkplatform.comapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapsdkplatform.comapi.util.SysOSAPI;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NetworkListener extends BroadcastReceiver {

    /* renamed from: a */
    public static final String f7181a = "f";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        m18451a(context);
        NetworkUtil.updateNetworkProxy(context);
    }

    /* renamed from: a */
    public void m18451a(Context context) {
        String currentNetMode = NetworkUtil.getCurrentNetMode(context);
        String m18130g = SysOSAPI.m18130g();
        if (m18130g == null || m18130g.equals(currentNetMode)) {
            return;
        }
        SysOSAPI.m18141a(currentNetMode);
    }
}
