package com.xiaomi.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.m */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11472m {

    /* renamed from: a */
    private static volatile Handler f23348a;

    /* renamed from: a */
    private static final Object f23349a = new Object();

    /* renamed from: b */
    private static volatile Handler f23350b;

    /* renamed from: b */
    private static Handler m2945b() {
        if (f23348a == null) {
            synchronized (C11472m.class) {
                if (f23348a == null) {
                    HandlerThread handlerThread = new HandlerThread("handle_receiver");
                    handlerThread.start();
                    f23348a = new Handler(handlerThread.getLooper());
                }
            }
        }
        return f23348a;
    }

    /* renamed from: a */
    public static Handler m2950a() {
        if (f23350b == null) {
            synchronized (f23349a) {
                if (f23350b == null) {
                    HandlerThread handlerThread = new HandlerThread("receiver_task");
                    handlerThread.start();
                    f23350b = new Handler(handlerThread.getLooper());
                }
            }
        }
        return f23350b;
    }

    /* renamed from: a */
    public static Intent m2949a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i) {
        return m2948a(context, broadcastReceiver, intentFilter, (String) null, i);
    }

    /* renamed from: a */
    public static Intent m2948a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, int i) {
        return m2946a(context, broadcastReceiver, intentFilter, str, m2945b(), i);
    }

    /* renamed from: a */
    public static Intent m2947a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
        return m2946a(context, broadcastReceiver, intentFilter, str, handler, 2);
    }

    /* renamed from: a */
    public static Intent m2946a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i) {
        if (context == null || broadcastReceiver == null || intentFilter == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i);
        }
        return context.registerReceiver(broadcastReceiver, intentFilter, str, handler);
    }
}
