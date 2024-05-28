package com.huawei.hms.hatool;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.UserManager;

/* renamed from: com.huawei.hms.hatool.q1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5021q1 {

    /* renamed from: c */
    private static C5021q1 f11486c = new C5021q1();

    /* renamed from: a */
    private boolean f11487a = false;

    /* renamed from: b */
    private Context f11488b = AbstractC5020q0.m14526i();

    private C5021q1() {
    }

    /* renamed from: b */
    public static C5021q1 m14518b() {
        return f11486c;
    }

    @TargetApi(24)
    /* renamed from: a */
    public boolean m14519a() {
        boolean z;
        if (!this.f11487a) {
            Context context = this.f11488b;
            if (context == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                UserManager userManager = (UserManager) context.getSystemService("user");
                if (userManager != null) {
                    z = userManager.isUserUnlocked();
                } else {
                    this.f11487a = false;
                }
            } else {
                z = true;
            }
            this.f11487a = z;
        }
        return this.f11487a;
    }
}
