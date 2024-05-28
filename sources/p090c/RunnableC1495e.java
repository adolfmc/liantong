package p090c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/* renamed from: c.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class RunnableC1495e implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ Context f2509a;

    /* renamed from: b */
    public final /* synthetic */ C1497g f2510b;

    public RunnableC1495e(C1497g c1497g, Context context) {
        this.f2510b = c1497g;
        this.f2509a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = this.f2509a.getPackageManager().getApplicationInfo(this.f2509a.getPackageName(), 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                return;
            }
            if (bundle.containsKey("design_width_in_dp")) {
                this.f2510b.f2518g = ((Integer) applicationInfo.metaData.get("design_width_in_dp")).intValue();
            }
            if (applicationInfo.metaData.containsKey("design_height_in_dp")) {
                this.f2510b.f2519h = ((Integer) applicationInfo.metaData.get("design_height_in_dp")).intValue();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
