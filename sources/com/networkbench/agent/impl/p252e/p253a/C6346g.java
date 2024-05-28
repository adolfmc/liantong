package com.networkbench.agent.impl.p252e.p253a;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6346g extends AbstractC6348i {
    public C6346g(Activity activity) {
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
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        intent.setFlags(268435456);
        if (m10332a(intent)) {
            return intent;
        }
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
        if (m10332a(intent)) {
            return intent;
        }
        return null;
    }

    /* renamed from: a */
    private boolean m10332a(Intent intent) {
        return intent != null && this.f15969a.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
