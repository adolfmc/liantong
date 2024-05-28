package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.mipush.sdk.C11087b;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11420gv;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11431hf;
import com.xiaomi.push.C11432hg;
import com.xiaomi.push.C11437hl;
import com.xiaomi.push.C11438hm;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11455i;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.EnumC11317ed;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.EnumC11418gt;
import com.xiaomi.push.service.C11541aj;
import com.xiaomi.push.service.C11568au;
import com.xiaomi.push.service.C11635x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MiPushClient4Hybrid {
    private static MiPushCallback sCallback;
    private static Map<String, C11087b.C11088a> dataMap = new HashMap();
    private static Map<String, Long> sRegisterTimeMap = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class MiPushCallback {
        public void onCommandResult(String str, MiPushCommandMessage miPushCommandMessage) {
        }

        public void onReceiveRegisterResult(String str, MiPushCommandMessage miPushCommandMessage) {
        }

        public void onReceiveUnregisterResult(String str, MiPushCommandMessage miPushCommandMessage) {
        }
    }

    public static void setCallback(MiPushCallback miPushCallback) {
        sCallback = miPushCallback;
    }

    public static void registerPush(Context context, String str, String str2, String str3) {
        if (C11087b.m5151a(context).m5145a(str2, str3, str)) {
            ArrayList arrayList = new ArrayList();
            C11087b.C11088a m5150a = C11087b.m5151a(context).m5150a(str);
            if (m5150a != null) {
                arrayList.add(m5150a.f21362c);
                MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage(EnumC11317ed.COMMAND_REGISTER.f22058a, arrayList, 0L, null, null, null);
                MiPushCallback miPushCallback = sCallback;
                if (miPushCallback != null) {
                    miPushCallback.onReceiveRegisterResult(str, generateCommandMessage);
                }
            }
            if (shouldPullNotification(context, str)) {
                C11430he c11430he = new C11430he();
                c11430he.m3335b(str2);
                c11430he.m3331c(EnumC11414gp.PullOfflineMessage.f22745a);
                c11430he.m3344a(C11541aj.m2703a());
                c11430he.m3340a(false);
                C11118u.m5003a(context).m4983a(c11430he, EnumC11404gf.Notification, false, true, null, false, str, str2);
                AbstractC11049b.m5274b("MiPushClient4Hybrid pull offline pass through message");
                addPullNotificationTime(context, str);
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - (sRegisterTimeMap.get(str) != null ? sRegisterTimeMap.get(str).longValue() : 0L)) < 5000) {
            AbstractC11049b.m5282a("MiPushClient4Hybrid  Could not send register message within 5s repeatedly.");
            return;
        }
        sRegisterTimeMap.put(str, Long.valueOf(currentTimeMillis));
        String m4758a = C11184bb.m4758a(6);
        C11087b.C11088a c11088a = new C11087b.C11088a(context);
        c11088a.m5117c(str2, str3, m4758a);
        dataMap.put(str, c11088a);
        C11431hf c11431hf = new C11431hf();
        c11431hf.m3305a(C11541aj.m2703a());
        c11431hf.m3300b(str2);
        c11431hf.m3289e(str3);
        c11431hf.m3292d(str);
        c11431hf.m3286f(m4758a);
        c11431hf.m3295c(C11395g.m3717a(context, context.getPackageName()));
        c11431hf.m3301b(C11395g.m3720a(context, context.getPackageName()));
        c11431hf.m3280h("5_9_9-C");
        c11431hf.m3309a(50909);
        c11431hf.m3308a(EnumC11418gt.Init);
        if (!C11469j.m2958d()) {
            String m3034d = C11455i.m3034d(context);
            if (!TextUtils.isEmpty(m3034d)) {
                c11431hf.m3277i(C11184bb.m4757a(m3034d));
            }
        }
        int m3056a = C11455i.m3056a();
        if (m3056a >= 0) {
            c11431hf.m3296c(m3056a);
        }
        C11430he c11430he2 = new C11430he();
        c11430he2.m3331c(EnumC11414gp.HybridRegister.f22745a);
        c11430he2.m3335b(C11087b.m5151a(context).m5156a());
        c11430he2.m3327d(context.getPackageName());
        c11430he2.m3338a(C11441hp.m3085a(c11431hf));
        c11430he2.m3344a(C11541aj.m2703a());
        C11118u.m5003a(context).m4988a((C11118u) c11430he2, EnumC11404gf.Notification, (C11417gs) null);
    }

    public static void unregisterPush(Context context, String str) {
        sRegisterTimeMap.remove(str);
        C11087b.C11088a m5150a = C11087b.m5151a(context).m5150a(str);
        if (m5150a == null) {
            return;
        }
        C11437hl c11437hl = new C11437hl();
        c11437hl.m3155a(C11541aj.m2703a());
        c11437hl.m3147d(str);
        c11437hl.m3152b(m5150a.f21358a);
        c11437hl.m3149c(m5150a.f21362c);
        c11437hl.m3145e(m5150a.f21360b);
        C11430he c11430he = new C11430he();
        c11430he.m3331c(EnumC11414gp.HybridUnregister.f22745a);
        c11430he.m3335b(C11087b.m5151a(context).m5156a());
        c11430he.m3327d(context.getPackageName());
        c11430he.m3338a(C11441hp.m3085a(c11437hl));
        c11430he.m3344a(C11541aj.m2703a());
        C11118u.m5003a(context).m4988a((C11118u) c11430he, EnumC11404gf.Notification, (C11417gs) null);
        C11087b.m5151a(context).m5140b(str);
    }

    public static boolean isRegistered(Context context, String str) {
        return C11087b.m5151a(context).m5150a(str) != null;
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        MiPushClient.reportMessageClicked(context, miPushMessage);
    }

    public static void reportMessageArrived(Context context, MiPushMessage miPushMessage, boolean z) {
        if (miPushMessage == null || miPushMessage.getExtra() == null) {
            AbstractC11049b.m5282a("do not ack message, message is null");
            return;
        }
        try {
            C11420gv c11420gv = new C11420gv();
            c11420gv.m3497b(C11087b.m5151a(context).m5156a());
            c11420gv.m3501a(miPushMessage.getMessageId());
            c11420gv.m3504a(Long.valueOf(miPushMessage.getExtra().get("__hybrid_message_ts")).longValue());
            c11420gv.m3500a(getDeviceStatus(miPushMessage, z));
            if (!TextUtils.isEmpty(miPushMessage.getTopic())) {
                c11420gv.m3494c(miPushMessage.getTopic());
            }
            C11118u.m5003a(context).m4986a((C11118u) c11420gv, EnumC11404gf.AckMessage, false, C11568au.m2630a(PushMessageHelper.generateMessage(miPushMessage)));
            AbstractC11049b.m5274b("MiPushClient4Hybrid ack mina message, messageId is " + miPushMessage.getMessageId());
        } finally {
            try {
            } finally {
            }
        }
    }

    public static void removeDuplicateCache(Context context, MiPushMessage miPushMessage) {
        String str = miPushMessage.getExtra() != null ? miPushMessage.getExtra().get("jobkey") : null;
        if (TextUtils.isEmpty(str)) {
            str = miPushMessage.getMessageId();
        }
        C11116t.m5029a(context, str);
    }

    public static void uploadClearMessageData(Context context, LinkedList<? extends Object> linkedList) {
        C11635x.m2317a(context, linkedList);
    }

    public static void onReceiveRegisterResult(Context context, C11432hg c11432hg) {
        ArrayList arrayList;
        C11087b.C11088a c11088a;
        String m3248c = c11432hg.m3248c();
        if (c11432hg.m3259a() == 0 && (c11088a = dataMap.get(m3248c)) != null) {
            c11088a.m5123a(c11432hg.f23115e, c11432hg.f23116f);
            C11087b.m5151a(context).m5148a(m3248c, c11088a);
        }
        if (TextUtils.isEmpty(c11432hg.f23115e)) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(c11432hg.f23115e);
            arrayList = arrayList2;
        }
        MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage(EnumC11317ed.COMMAND_REGISTER.f22058a, arrayList, c11432hg.f23103a, c11432hg.f23114d, null, null);
        MiPushCallback miPushCallback = sCallback;
        if (miPushCallback != null) {
            miPushCallback.onReceiveRegisterResult(m3248c, generateCommandMessage);
        }
    }

    public static void onReceiveUnregisterResult(Context context, C11438hm c11438hm) {
        MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage(EnumC11317ed.COMMAND_UNREGISTER.f22058a, null, c11438hm.f23238a, c11438hm.f23246d, null, null);
        String m3137a = c11438hm.m3137a();
        MiPushCallback miPushCallback = sCallback;
        if (miPushCallback != null) {
            miPushCallback.onReceiveUnregisterResult(m3137a, generateCommandMessage);
        }
    }

    private static void addPullNotificationTime(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        sharedPreferences.edit().putLong("last_pull_notification_" + str, System.currentTimeMillis()).commit();
    }

    private static boolean shouldPullNotification(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        StringBuilder sb = new StringBuilder();
        sb.append("last_pull_notification_");
        sb.append(str);
        return Math.abs(System.currentTimeMillis() - sharedPreferences.getLong(sb.toString(), -1L)) > 300000;
    }

    private static short getDeviceStatus(MiPushMessage miPushMessage, boolean z) {
        String str = miPushMessage.getExtra() == null ? "" : miPushMessage.getExtra().get("__hybrid_device_status");
        int intValue = TextUtils.isEmpty(str) ? 0 : Integer.valueOf(str).intValue();
        if (!z) {
            intValue = (intValue & (-4)) + C11395g.EnumC11397b.NOT_ALLOWED.m3705a();
        }
        return (short) intValue;
    }
}
