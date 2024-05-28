package com.huawei.hms.utils;

import android.content.Intent;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class IntentUtil {
    public static Intent modifyIntentBehaviorsSafe(Intent intent) {
        if (intent == null) {
            return null;
        }
        String action = intent.getAction();
        if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action) || "android.media.action.IMAGE_CAPTURE".equals(action) || "android.media.action.IMAGE_CAPTURE_SECURE".equals(action) || "android.media.action.VIDEO_CAPTURE".equals(action)) {
            intent.setAction("android.intent.action.VIEW");
        }
        int flags = intent.getFlags();
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            flags &= -129;
        }
        if (i >= 19) {
            flags &= -65;
        }
        intent.setFlags(flags & (-2) & (-3));
        return intent;
    }
}
