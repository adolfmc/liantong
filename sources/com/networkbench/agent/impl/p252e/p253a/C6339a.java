package com.networkbench.agent.impl.p252e.p253a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6339a extends AbstractC6348i {
    public C6339a(Activity activity) {
        super(activity);
    }

    @Override // com.networkbench.agent.impl.p252e.p253a.AbstractC6348i
    /* renamed from: a */
    public boolean mo10324a() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                return ((Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke(null, this.f15969a)).booleanValue();
            } catch (Exception e) {
                InterfaceC6393e interfaceC6393e = f15968b;
                interfaceC6393e.mo10122a("error:" + e.getMessage());
                return true;
            }
        }
        return true;
    }

    @Override // com.networkbench.agent.impl.p252e.p253a.AbstractC6348i
    /* renamed from: b */
    public Intent mo10322b() {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setFlags(268435456);
        intent.setData(Uri.parse("package:" + this.f15969a.getPackageName()));
        return intent;
    }
}
