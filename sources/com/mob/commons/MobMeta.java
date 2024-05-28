package com.mob.commons;

import android.os.Looper;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.proguard.PublicMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MobMeta implements PublicMemberKeeper {
    public static <T> T get(MobProduct mobProduct, String str, Class<T> cls, T t) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11326w("WARNING: gt mta in main: key = " + str);
        }
        T t2 = (T) C5868q.m12204a(str, cls, mobProduct);
        if (t2 == null) {
            t2 = (T) C5868q.m12205a(str);
        }
        return t2 == null ? t : t2;
    }
}
