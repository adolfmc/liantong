package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.push.C11272dc;
import com.xiaomi.push.C11273dd;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Logger {
    private static boolean sDisablePushLog;
    private static LoggerInterface sUserLogger;

    @Deprecated
    public static File getLogFile(String str) {
        return null;
    }

    @Deprecated
    public static void uploadLogFile(Context context, boolean z) {
    }

    public static void setLogger(Context context, LoggerInterface loggerInterface) {
        sUserLogger = loggerInterface;
        setPushLog(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static LoggerInterface getUserLogger() {
        return sUserLogger;
    }

    public static void disablePushFileLog(Context context) {
        sDisablePushLog = true;
        setPushLog(context);
    }

    public static void enablePushFileLog(Context context) {
        sDisablePushLog = false;
        setPushLog(context);
    }

    public static void setPushLog(Context context) {
        boolean z = sUserLogger != null;
        boolean z2 = sDisablePushLog;
        if (z2) {
            z = false;
        }
        AbstractC11049b.m5286a(new C11272dc(z ? sUserLogger : null, true ^ z2 ? C11273dd.m4369a(context) : null));
    }
}
