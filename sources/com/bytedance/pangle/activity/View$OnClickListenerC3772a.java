package com.bytedance.pangle.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@NBSInstrumented
/* renamed from: com.bytedance.pangle.activity.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC3772a implements View.OnClickListener {

    /* renamed from: a */
    private final Activity f9022a;

    /* renamed from: b */
    private final String f9023b;

    /* renamed from: c */
    private final int f9024c;

    /* renamed from: d */
    private Method f9025d;

    public View$OnClickListenerC3772a(@NonNull Activity activity, int i, @NonNull String str) {
        this.f9022a = activity;
        this.f9023b = str;
        this.f9024c = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(@NonNull View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (this.f9025d == null) {
            try {
                Method method = this.f9022a.getClass().getMethod(this.f9023b, View.class);
                if (method != null) {
                    this.f9025d = method;
                }
            } catch (NoSuchMethodException unused) {
            }
            IllegalStateException illegalStateException = new IllegalStateException("Could not find method " + this.f9023b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f9024c);
            NBSActionInstrumentation.onClickEventExit();
            throw illegalStateException;
        }
        try {
            this.f9025d.invoke(this.f9022a, view);
            NBSActionInstrumentation.onClickEventExit();
        } catch (IllegalAccessException e) {
            IllegalStateException illegalStateException2 = new IllegalStateException("Could not execute non-public method for android:onClick", e);
            NBSActionInstrumentation.onClickEventExit();
            throw illegalStateException2;
        } catch (InvocationTargetException e2) {
            IllegalStateException illegalStateException3 = new IllegalStateException("Could not execute method for android:onClick", e2);
            NBSActionInstrumentation.onClickEventExit();
            throw illegalStateException3;
        }
    }
}
