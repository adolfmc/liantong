package com.networkbench.agent.impl.p252e.p253a;

import android.app.Activity;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6340b {
    /* renamed from: a */
    public static AbstractC6348i m10339a(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            if (C6347h.m10329b()) {
                return new C6345f(activity);
            }
            if (C6347h.m10328c()) {
                return new C6344e(activity);
            }
            if (C6347h.m10331a()) {
                return new C6343d(activity);
            }
            if (C6347h.m10327d()) {
                return new C6346g(activity);
            }
        } else if (C6347h.m10328c()) {
            return new C6344e(activity);
        }
        return new C6339a(activity);
    }
}
