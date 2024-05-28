package com.vivo.push;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.vivo.push.util.LogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.t */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class PushClientThread {

    /* renamed from: a */
    private static final Handler f21161a = new Handler(Looper.getMainLooper());

    /* renamed from: b */
    private static final HandlerThread f21162b;

    /* renamed from: c */
    private static final Handler f21163c;

    static {
        HandlerThread handlerThread = new HandlerThread("push_client_thread");
        f21162b = handlerThread;
        handlerThread.start();
        f21163c = new HandlerC10982u(f21162b.getLooper());
    }

    /* renamed from: a */
    public static void m5483a(PushClientTask pushClientTask) {
        if (pushClientTask == null) {
            LogUtil.m5354a("PushClientThread", "client thread error, task is null!");
            return;
        }
        int m5493a = pushClientTask.m5493a();
        Message message = new Message();
        message.what = m5493a;
        message.obj = pushClientTask;
        f21163c.sendMessageDelayed(message, 0L);
    }

    /* renamed from: a */
    public static void m5482a(Runnable runnable) {
        f21163c.removeCallbacks(runnable);
        f21163c.postDelayed(runnable, 15000L);
    }

    /* renamed from: b */
    public static void m5481b(Runnable runnable) {
        f21161a.post(runnable);
    }

    /* renamed from: c */
    public static void m5480c(Runnable runnable) {
        Handler handler = f21163c;
        if (handler != null) {
            handler.post(runnable);
        }
    }
}
