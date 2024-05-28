package com.mob.tools;

import com.mob.MobSDK;
import com.mob.commons.C5869r;
import com.mob.tools.log.NLog;
import com.mob.tools.proguard.EverythingKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MobLog implements EverythingKeeper {
    public static synchronized NLog getInstance() {
        NLog nLog;
        synchronized (MobLog.class) {
            nLog = NLog.getInstance("MobSDK", MobSDK.SDK_VERSION_CODE, C5869r.m12200a("009CdfedfefdVi@eded?gDfh"));
        }
        return nLog;
    }
}
