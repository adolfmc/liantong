package com.xiaomi.push;

import android.os.Build;
import android.system.Os;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ca */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11226ca {
    /* renamed from: a */
    public static long m4609a(String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                if (new File(str).exists()) {
                    return Os.stat(str).st_size;
                }
                return 0L;
            } catch (Exception e) {
                AbstractC11049b.m5276a(e);
                return 0L;
            }
        }
        return 0L;
    }
}
