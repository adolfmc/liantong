package com.vivo.push.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import com.vivo.push.PushClient;
import com.vivo.push.PushClientManager;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.NetUtils;
import com.vivo.push.util.VivoPushException;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PushServiceReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static HandlerThread f21152a;

    /* renamed from: b */
    private static Handler f21153b;

    /* renamed from: c */
    private static RunnableC10980a f21154c = new RunnableC10980a();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Context context2 = ContextDelegate.getContext(context);
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) || "android.intent.action.ACTION_POWER_CONNECTED".equals(action) || "android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
            if (f21152a == null) {
                HandlerThread handlerThread = new HandlerThread("PushServiceReceiver");
                f21152a = handlerThread;
                handlerThread.start();
                f21153b = new Handler(f21152a.getLooper());
            }
            LogUtil.m5341d("PushServiceReceiver", context2.getPackageName() + ": start PushSerevice for by " + action + "  ; handler : " + f21153b);
            RunnableC10980a.m5491a(f21154c, context2, action);
            f21153b.removeCallbacks(f21154c);
            f21153b.postDelayed(f21154c, 2000L);
        }
    }

    /* renamed from: com.vivo.push.sdk.PushServiceReceiver$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class RunnableC10980a implements Runnable {

        /* renamed from: a */
        private Context f21155a;

        /* renamed from: b */
        private String f21156b;

        RunnableC10980a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            NetworkInfo m5335a = NetUtils.m5335a(this.f21155a);
            if (!(m5335a != null ? m5335a.isConnectedOrConnecting() : false)) {
                LogUtil.m5341d("PushServiceReceiver", this.f21155a.getPackageName() + ": 无网络  by " + this.f21156b);
                Context context = this.f21155a;
                LogUtil.m5356a(context, "触发静态广播:无网络(" + this.f21156b + "," + this.f21155a.getPackageName() + ")");
                return;
            }
            LogUtil.m5341d("PushServiceReceiver", this.f21155a.getPackageName() + ": 执行开始出发动作: " + this.f21156b);
            Context context2 = this.f21155a;
            LogUtil.m5356a(context2, "触发静态广播(" + this.f21156b + "," + this.f21155a.getPackageName() + ")");
            PushClientManager.m5648a().m5646a(this.f21155a);
            if (ClientConfigManagerImpl.getInstance(this.f21155a).isCancleBroadcastReceiver()) {
                return;
            }
            try {
                PushClient.getInstance(this.f21155a).initialize(PushClientController.m5593a().m5588e().mo5524l());
            } catch (VivoPushException e) {
                e.printStackTrace();
                Context context3 = this.f21155a;
                LogUtil.m5356a(context3, " 初始化异常 error= " + e.getMessage());
            }
        }

        /* renamed from: a */
        static /* synthetic */ void m5491a(RunnableC10980a runnableC10980a, Context context, String str) {
            runnableC10980a.f21155a = ContextDelegate.getContext(context);
            runnableC10980a.f21156b = str;
        }
    }
}
