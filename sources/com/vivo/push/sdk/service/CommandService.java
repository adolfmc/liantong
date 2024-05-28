package com.vivo.push.sdk.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.sdk.CommandWorker;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.LogUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CommandService extends Service {
    @Override // android.app.Service
    public void onCreate() {
        LogUtil.m5342c("CommandService", getClass().getSimpleName() + " -- oncreate " + getPackageName());
        super.onCreate();
        Context context = ContextDelegate.getContext(getApplicationContext());
        PushClientController.m5593a().m5592a(context);
        CommandWorker.m5490a().m5813a(context);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.m5342c("CommandService", getClass().getSimpleName() + " -- onStartCommand " + getPackageName());
        if (intent == null) {
            stopSelf();
            return 2;
        } else if (!mo5484a(intent.getAction())) {
            LogUtil.m5354a("CommandService", getPackageName() + " receive invalid action " + intent.getAction());
            stopSelf();
            return 2;
        } else {
            try {
                CommandWorker.m5490a().m5487a(getClass().getName());
                CommandWorker.m5490a().m5489a(intent);
            } catch (Exception e) {
                LogUtil.m5353a("CommandService", "onStartCommand -- error", e);
            }
            stopSelf();
            return 2;
        }
    }

    /* renamed from: a */
    protected boolean mo5484a(String str) {
        return "com.vivo.pushservice.action.RECEIVE".equals(str);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.m5342c("CommandService", "onBind initSuc: ");
        return null;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
