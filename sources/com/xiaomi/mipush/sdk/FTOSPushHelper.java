package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class FTOSPushHelper {

    /* renamed from: a */
    private static long f21317a;

    /* renamed from: a */
    private static volatile boolean f21318a;

    public static boolean hasNetwork(Context context) {
        return C11094f.m5101a(context);
    }

    public static void uploadToken(Context context, String str) {
        C11094f.m5095a(context, EnumC11090d.ASSEMBLE_PUSH_FTOS, str);
    }

    public static void doInNetworkChange(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (getNeedRegister()) {
            long j = f21317a;
            if (j <= 0 || j + 300000 <= elapsedRealtime) {
                f21317a = elapsedRealtime;
                m5206a(context);
            }
        }
    }

    /* renamed from: a */
    private static void m5206a(Context context) {
        AbstractPushManager m5112a = C11091e.m5114a(context).m5112a(EnumC11090d.ASSEMBLE_PUSH_FTOS);
        if (m5112a != null) {
            AbstractC11049b.m5282a("ASSEMBLE_PUSH :  register fun touch os when network change!");
            m5112a.register();
        }
    }

    public static void setNeedRegister(boolean z) {
        f21318a = z;
    }

    public static boolean getNeedRegister() {
        return f21318a;
    }

    public static void notifyFTOSNotificationClicked(Context context, Map<String, String> map) {
        PushMessageReceiver m5103a;
        if (map == null || !map.containsKey("pushMsg")) {
            return;
        }
        String str = map.get("pushMsg");
        if (TextUtils.isEmpty(str) || (m5103a = C11094f.m5103a(context)) == null) {
            return;
        }
        MiPushMessage m5087a = C11094f.m5087a(str);
        if (m5087a.getExtra().containsKey("notify_effect")) {
            return;
        }
        m5103a.onNotificationMessageClicked(context, m5087a);
    }
}
