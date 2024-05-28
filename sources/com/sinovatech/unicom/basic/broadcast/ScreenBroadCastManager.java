package com.sinovatech.unicom.basic.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScreenBroadCastManager {
    private ScreenBroadCastListener listener;
    private ScreenBroadCastReceiver sbcr;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ScreenBroadCastListener {
        void onScreenOFF();

        void onScreenOn();
    }

    public void setListener(ScreenBroadCastListener screenBroadCastListener) {
        this.listener = screenBroadCastListener;
    }

    public void registerScreenBroadCastReceiver(Context context) {
        this.sbcr = new ScreenBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        context.registerReceiver(this.sbcr, intentFilter);
    }

    public void unRegisterScreenBroadCastReceiver(Context context) {
        ScreenBroadCastReceiver screenBroadCastReceiver = this.sbcr;
        if (screenBroadCastReceiver != null) {
            try {
                context.unregisterReceiver(screenBroadCastReceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class ScreenBroadCastReceiver extends BroadcastReceiver {
        ScreenBroadCastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                if (ScreenBroadCastManager.this.listener != null) {
                    ScreenBroadCastManager.this.listener.onScreenOn();
                }
            } else if (!"android.intent.action.SCREEN_OFF".equals(intent.getAction()) || ScreenBroadCastManager.this.listener == null) {
            } else {
                ScreenBroadCastManager.this.listener.onScreenOFF();
            }
        }
    }
}
