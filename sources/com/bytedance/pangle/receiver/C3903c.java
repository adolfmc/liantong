package com.bytedance.pangle.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.service.p182a.BinderC3929a;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* renamed from: com.bytedance.pangle.receiver.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3903c {

    /* renamed from: d */
    private static C3903c f9284d;

    /* renamed from: a */
    public final Map<String, C3904a> f9285a = new ConcurrentHashMap();

    /* renamed from: b */
    public final Map<PluginBroadcastReceiver, BroadcastReceiver> f9286b = new ConcurrentHashMap();

    /* renamed from: c */
    public final Set<Integer> f9287c = new CopyOnWriteArraySet();

    /* renamed from: com.bytedance.pangle.receiver.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3904a {

        /* renamed from: a */
        public String f9288a;

        /* renamed from: b */
        public final Set<PluginBroadcastReceiver> f9289b = new CopyOnWriteArraySet();

        /* renamed from: a */
        public final void m16731a(PluginBroadcastReceiver pluginBroadcastReceiver) {
            if (pluginBroadcastReceiver != null) {
                ZeusLogger.m16792i("Zeus/receiver_pangle", "plugin-receiver:" + pluginBroadcastReceiver.getClass().getSimpleName() + ",action=" + this.f9288a + "[注册完成]");
                this.f9289b.add(pluginBroadcastReceiver);
            }
        }

        /* renamed from: a */
        public final void m16732a(Context context, Intent intent) {
            Set<PluginBroadcastReceiver> set = this.f9289b;
            if (set == null || set.size() <= 0) {
                return;
            }
            for (PluginBroadcastReceiver pluginBroadcastReceiver : this.f9289b) {
                if (pluginBroadcastReceiver != null) {
                    try {
                        pluginBroadcastReceiver.onReceive(context, intent);
                    } catch (Throwable th) {
                        String action = intent != null ? intent.getAction() : "";
                        ZeusLogger.m16787w("Zeus/receiver_pangle", "plugin-receiver->action:" + action + "[exception]:", th);
                    }
                }
            }
        }
    }

    private C3903c() {
    }

    /* renamed from: a */
    public static C3903c m16735a() {
        if (f9284d == null) {
            synchronized (BinderC3929a.class) {
                if (f9284d == null) {
                    f9284d = new C3903c();
                }
            }
        }
        return f9284d;
    }

    /* renamed from: a */
    public final void m16733a(IntentFilter intentFilter, PluginBroadcastReceiver pluginBroadcastReceiver) {
        if (intentFilter == null || intentFilter.actionsIterator() == null) {
            return;
        }
        Iterator<String> actionsIterator = intentFilter.actionsIterator();
        while (actionsIterator.hasNext()) {
            String next = actionsIterator.next();
            if (next != null) {
                C3904a c3904a = this.f9285a.get(next);
                if (c3904a != null) {
                    c3904a.m16731a(pluginBroadcastReceiver);
                } else {
                    C3904a c3904a2 = new C3904a();
                    c3904a2.f9288a = next;
                    c3904a2.m16731a(pluginBroadcastReceiver);
                    this.f9285a.put(next, c3904a2);
                }
            }
        }
    }

    /* renamed from: a */
    public final void m16734a(Context context, Intent intent) {
        C3904a value;
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        Map<String, C3904a> map = this.f9285a;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (Map.Entry<String, C3904a> entry : this.f9285a.entrySet()) {
            if (action.equals(entry.getKey()) && (value = entry.getValue()) != null) {
                ZeusLogger.m16794d("Zeus/receiver_pangle", "action[" + action + "] match success ！ invoke onReceiver");
                value.m16732a(context, intent);
            }
        }
    }
}
