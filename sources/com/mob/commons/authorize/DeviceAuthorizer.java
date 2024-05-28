package com.mob.commons.authorize;

import android.content.Context;
import com.mob.commons.C5831e;
import com.mob.commons.C5849j;
import com.mob.commons.MobProduct;
import com.mob.tools.proguard.PublicMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class DeviceAuthorizer implements PublicMemberKeeper {
    public static boolean isFor() {
        return C5831e.m12321a();
    }

    public static String getMString(Context context) {
        return C5831e.m12320a(context);
    }

    public static String authorizeForOnce() {
        return C5831e.m12317b();
    }

    public static boolean isClear() {
        return C5849j.m12264a().m12253b();
    }

    public static synchronized String authorize(MobProduct mobProduct) {
        String m12319a;
        synchronized (DeviceAuthorizer.class) {
            m12319a = C5831e.m12319a(mobProduct);
        }
        return m12319a;
    }
}
