package com.networkbench.agent.impl.p252e.p253a;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6344e extends AbstractC6348i {
    public C6344e(Activity activity) {
        super(activity);
    }

    @Override // com.networkbench.agent.impl.p252e.p253a.AbstractC6348i
    /* renamed from: a */
    public boolean mo10324a() {
        if (Build.VERSION.SDK_INT >= 19) {
            return m10323a(24);
        }
        return false;
    }

    @Override // com.networkbench.agent.impl.p252e.p253a.AbstractC6348i
    /* renamed from: b */
    public Intent mo10322b() {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", this.f15969a.getPackageName());
        intent.setFlags(268435456);
        return intent;
    }
}
