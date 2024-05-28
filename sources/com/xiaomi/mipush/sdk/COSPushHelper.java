package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class COSPushHelper {

    /* renamed from: a */
    private static long f21315a;

    /* renamed from: a */
    private static volatile boolean f21316a;

    public static void onNotificationMessageCome(Context context, String str) {
    }

    public static void onPassThoughMessageCome(Context context, String str) {
    }

    public static boolean hasNetwork(Context context) {
        return C11094f.m5101a(context);
    }

    public static void uploadToken(Context context, String str) {
        C11094f.m5095a(context, EnumC11090d.ASSEMBLE_PUSH_COS, str);
    }

    public static boolean getNeedRegister() {
        return f21316a;
    }

    public static synchronized void setNeedRegister(boolean z) {
        synchronized (COSPushHelper.class) {
            f21316a = z;
        }
    }

    public static void registerCOSAssemblePush(Context context) {
        AbstractPushManager m5112a = C11091e.m5114a(context).m5112a(EnumC11090d.ASSEMBLE_PUSH_COS);
        if (m5112a != null) {
            AbstractC11049b.m5282a("ASSEMBLE_PUSH :  register cos when network change!");
            m5112a.register();
        }
    }

    public static void doInNetworkChange(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (getNeedRegister()) {
            long j = f21315a;
            if (j <= 0 || j + 300000 <= elapsedRealtime) {
                f21315a = elapsedRealtime;
                registerCOSAssemblePush(context);
            }
        }
    }

    public static void convertMessage(Intent intent) {
        C11094f.m5091a(intent);
    }
}
