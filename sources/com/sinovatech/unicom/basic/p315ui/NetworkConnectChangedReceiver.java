package com.sinovatech.unicom.basic.p315ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sinovatech.unicom.basic.eventbus.NetEvent;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.common.EventBusUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.NetworkConnectChangedReceiver */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NetworkConnectChangedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "android.net.conn.CONNECTIVITY_CHANGE") {
            EventBusUtils.post(new NetEvent(EventBusUtils.EVENT_NET));
            EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_REFRESH_WEBSOCKET));
        }
    }
}
