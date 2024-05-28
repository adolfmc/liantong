package com.networkbench.agent.impl.p252e.p253a;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6343d extends AbstractC6348i {
    public C6343d(Activity activity) {
        super(activity);
    }

    @Override // com.networkbench.agent.impl.p252e.p253a.AbstractC6348i
    /* renamed from: a */
    public boolean mo10324a() {
        if (Build.VERSION.SDK_INT >= 19) {
            return m10323a(24);
        }
        return true;
    }

    @Override // com.networkbench.agent.impl.p252e.p253a.AbstractC6348i
    /* renamed from: b */
    public Intent mo10322b() {
        Intent intent = new Intent();
        try {
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
            if (C6347h.m10325f() != 3.1d) {
                intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
                return intent;
            }
            return intent;
        } catch (ActivityNotFoundException unused) {
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem"));
            return intent;
        } catch (SecurityException unused2) {
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            return intent;
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15968b;
            interfaceC6393e.mo10122a("error:" + e.getMessage());
            return null;
        }
    }
}
