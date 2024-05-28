package com.sinovatech.unicom.separatemodule.dialog.action;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface ClickAction extends View.OnClickListener {
    <V extends View> V findViewById(@IdRes int i);

    @Override // android.view.View.OnClickListener
    void onClick(View view);

    void setOnClickListener(@Nullable View.OnClickListener onClickListener, @IdRes int... iArr);

    void setOnClickListener(@Nullable View.OnClickListener onClickListener, View... viewArr);

    void setOnClickListener(@IdRes int... iArr);

    void setOnClickListener(View... viewArr);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.dialog.action.ClickAction$-CC  reason: invalid class name */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        public static void $default$setOnClickListener(@Nullable ClickAction clickAction, @IdRes View.OnClickListener onClickListener, int... iArr) {
            for (int i : iArr) {
                clickAction.findViewById(i).setOnClickListener(onClickListener);
            }
        }

        public static void $default$setOnClickListener(@Nullable ClickAction clickAction, View.OnClickListener onClickListener, View... viewArr) {
            for (View view : viewArr) {
                view.setOnClickListener(onClickListener);
            }
        }

        public static void $default$onClick(ClickAction clickAction, View view) {
            NBSActionInstrumentation.onClickEventEnter(view, clickAction);
            Tracker.onClick(view);
            NBSActionInstrumentation.onClickEventExit();
        }
    }
}
