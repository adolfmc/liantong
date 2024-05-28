package com.sinovatech.unicom.separatemodule.dialog.action;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface HandlerAction {
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());

    Handler getHandler();

    boolean post(Runnable runnable);

    boolean postAtTime(Runnable runnable, long j);

    boolean postDelayed(Runnable runnable, long j);

    void removeCallbacks();

    void removeCallbacks(Runnable runnable);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction$-CC  reason: invalid class name */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        public static boolean $default$postDelayed(HandlerAction handlerAction, Runnable runnable, long j) {
            if (j < 0) {
                j = 0;
            }
            return handlerAction.postAtTime(runnable, SystemClock.uptimeMillis() + j);
        }
    }
}
