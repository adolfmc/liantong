package com.networkbench.agent.impl.p252e.p253a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6345f extends AbstractC6348i {
    public C6345f(Activity activity) {
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
        int m10326e = C6347h.m10326e();
        InterfaceC6393e interfaceC6393e = f15968b;
        interfaceC6393e.mo10122a("miui versionCode:" + m10326e);
        if (m10326e == 5) {
            String packageName = this.f15969a.getPackageName();
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", packageName, null));
            intent.setFlags(268435456);
            return intent;
        } else if (m10326e == 6 || m10326e == 7) {
            Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent2.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent2.putExtra("extra_pkgname", this.f15969a.getPackageName());
            intent2.setFlags(268435456);
            return intent2;
        } else if (m10326e == 8) {
            Intent intent3 = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent3.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            intent3.putExtra("extra_pkgname", this.f15969a.getPackageName());
            intent3.setFlags(268435456);
            if (m10333a(intent3, this.f15969a)) {
                this.f15969a.startActivity(intent3);
                return intent3;
            }
            Intent intent4 = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent4.setPackage("com.miui.securitycenter");
            intent4.putExtra("extra_pkgname", this.f15969a.getPackageName());
            intent4.setFlags(268435456);
            return intent4;
        } else {
            InterfaceC6393e interfaceC6393e2 = f15968b;
            interfaceC6393e2.mo10122a("this is a special MIUI rom version, its version code " + m10326e);
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m10333a(Intent intent, Context context) {
        return intent != null && context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
