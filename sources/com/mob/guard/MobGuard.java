package com.mob.guard;

import android.content.Context;
import com.mob.mcl.MobMCL;
import com.mob.mgs.MobMGS;
import com.mob.mgs.impl.C5994e;
import com.mob.tools.proguard.PublicMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MobGuard implements PublicMemberKeeper {
    public static String getGuardId() {
        try {
            return MobMCL.getSuid();
        } catch (Throwable th) {
            C5994e.m11860a().m11857a(th);
            return null;
        }
    }

    public static void getGuardId(final OnIdChangeListener onIdChangeListener) {
        if (onIdChangeListener != null) {
            MobMCL.getSuid(new com.mob.mgs.OnIdChangeListener() { // from class: com.mob.guard.MobGuard.1
                @Override // com.mob.mgs.OnIdChangeListener
                public void onChanged(String str, String str2) {
                    OnIdChangeListener onIdChangeListener2 = OnIdChangeListener.this;
                    if (onIdChangeListener2 != null) {
                        onIdChangeListener2.onChanged(str, str2);
                    }
                }
            });
        }
    }

    public static void setOnAppActiveListener(final OnAppActiveListener onAppActiveListener) {
        if (onAppActiveListener != null) {
            MobMGS.setOnAppActiveListener(new com.mob.mgs.OnAppActiveListener() { // from class: com.mob.guard.MobGuard.2
                @Override // com.mob.mgs.OnAppActiveListener
                public void onAppActive(Context context, int i) {
                    OnAppActiveListener onAppActiveListener2 = OnAppActiveListener.this;
                    if (onAppActiveListener2 != null) {
                        onAppActiveListener2.onAppActive(context, i);
                    }
                }
            });
        }
    }
}
