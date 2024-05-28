package com.sinovatech.unicom.separatemodule.notice;

import android.content.Context;
import android.content.Intent;
import android.support.p083v4.content.WakefulBroadcastReceiver;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoticeWakefulReceiver extends WakefulBroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            startWakefulService(context, new Intent(context, NoticeWakefulService.class));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
