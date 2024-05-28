package com.xiaomi.mipush.sdk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11469j;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class BaseService extends Service {

    /* renamed from: a */
    private HandlerC11067a f21313a;

    /* renamed from: a */
    protected abstract boolean mo5178a();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (this.f21313a == null) {
            this.f21313a = new HandlerC11067a(new WeakReference(this));
        }
        this.f21313a.m5213a();
    }

    /* renamed from: com.xiaomi.mipush.sdk.BaseService$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class HandlerC11067a extends Handler {

        /* renamed from: a */
        private WeakReference<BaseService> f21314a;

        public HandlerC11067a(WeakReference<BaseService> weakReference) {
            this.f21314a = weakReference;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<BaseService> weakReference;
            BaseService baseService;
            if (message.what != 1001 || (weakReference = this.f21314a) == null || (baseService = weakReference.get()) == null) {
                return;
            }
            AbstractC11049b.m5270c("TimeoutHandler " + baseService.toString() + " kill self");
            if (!baseService.mo5178a()) {
                baseService.stopSelf();
                return;
            }
            AbstractC11049b.m5270c("TimeoutHandler has job");
            sendEmptyMessageDelayed(1001, 1000L);
        }

        /* renamed from: a */
        public void m5213a() {
            if (hasMessages(1001)) {
                removeMessages(1001);
            }
            sendEmptyMessageDelayed(1001, 1000L);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int onStartCommand = super.onStartCommand(intent, i, i2);
        if (C11469j.m2972a((Context) this)) {
            return onStartCommand;
        }
        return 2;
    }
}
