package com.sinovatech.unicom.separatemodule.dialog.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface ActivityAction {
    Activity getActivity();

    Context getContext();

    void startActivity(Intent intent);

    void startActivity(Class<? extends Activity> cls);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction$-CC  reason: invalid class name */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        /* JADX WARN: Removed duplicated region for block: B:7:0x000b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static android.app.Activity $default$getActivity(com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction r3) {
            /*
                android.content.Context r0 = r3.getContext()
            L4:
                boolean r1 = r0 instanceof android.app.Activity
                if (r1 == 0) goto Lb
                android.app.Activity r0 = (android.app.Activity) r0
                return r0
            Lb:
                boolean r1 = r0 instanceof android.content.ContextWrapper
                r2 = 0
                if (r1 == 0) goto L19
                android.content.ContextWrapper r0 = (android.content.ContextWrapper) r0
                android.content.Context r0 = r0.getBaseContext()
                if (r0 != 0) goto L4
                return r2
            L19:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction.CC.$default$getActivity(com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction):android.app.Activity");
        }

        public static void $default$startActivity(ActivityAction activityAction, Intent intent) {
            if (!(activityAction.getContext() instanceof Activity)) {
                intent.addFlags(268435456);
            }
            activityAction.getContext().startActivity(intent);
        }
    }
}
