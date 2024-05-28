package p090c;

import android.app.Activity;
import android.util.Log;
import p388f.C11925b;
import p388f.EnumC11924a;
import p390g.C11945b;
import p390g.C11947d;
import p470p0.C13652o;

/* renamed from: c.k */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1502k implements InterfaceC1492b {

    /* renamed from: a */
    public final InterfaceC1492b f2535a;

    public C1502k(InterfaceC1492b interfaceC1492b) {
        this.f2535a = interfaceC1492b;
    }

    @Override // p090c.InterfaceC1492b
    /* renamed from: a */
    public final void mo22185a(Object obj, Activity activity) {
        C11925b m2046a;
        if (C1497g.m22187c().f2529r != null && activity != null) {
            int[] iArr = {activity.getResources().getDisplayMetrics().widthPixels, activity.getResources().getDisplayMetrics().heightPixels};
            C1497g m22187c = C1497g.m22187c();
            m22187c.f2525n = true;
            C11925b c11925b = m22187c.f2514c;
            c11925b.f24296d = false;
            c11925b.f24295c = false;
            c11925b.f24297e = (EnumC11924a) C11945b.m2025a(EnumC11924a.MM, "The supportSubunits can not be null, use Subunits.NONE instead");
            if (!C11947d.m2022a(activity)) {
                m2046a = C1497g.m22187c().f2514c.m2045b(iArr[0]).m2046a(iArr[1]);
            } else if (C11947d.m2021b(activity)) {
                if (C13652o.f27494a) {
                    Log.i("isLargeScreen", "isLargeWindow");
                }
                if (activity.getResources().getConfiguration().orientation == 2) {
                    m2046a = C1497g.m22187c().f2514c.m2045b(iArr[0]).m2046a(iArr[1]);
                } else {
                    m2046a = C1497g.m22187c().f2514c.m2045b(iArr[0]).m2046a(iArr[1]);
                }
            } else if (activity.getResources().getConfiguration().orientation == 2) {
                m2046a = C1497g.m22187c().f2514c.m2045b(iArr[0]).m2046a(iArr[1]);
            } else {
                m2046a = C1497g.m22187c().f2514c.m2045b(iArr[0]).m2046a(iArr[1]);
            }
            m2046a.m2045b(750.0f);
            m2046a.m2046a(1624.0f);
        }
        InterfaceC1492b interfaceC1492b = this.f2535a;
        if (interfaceC1492b != null) {
            interfaceC1492b.mo22185a(obj, activity);
        }
    }
}
