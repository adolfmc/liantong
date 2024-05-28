package p090c;

import android.app.Activity;
import android.util.Log;
import java.util.Locale;
import java.util.Map;
import p386e.InterfaceC11846a;
import p386e.InterfaceC11847b;
import p390g.C11944a;

/* renamed from: c.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1498h implements InterfaceC1492b {
    @Override // p090c.InterfaceC1492b
    /* renamed from: a */
    public final void mo22185a(Object obj, Activity activity) {
        C1497g.m22187c().f2513b.getClass();
        if (obj instanceof InterfaceC11846a) {
            String format = String.format(Locale.ENGLISH, "%s canceled the adaptation!", obj.getClass().getName());
            if (C11944a.f24299a) {
                Log.w("AndroidAutoSize", format);
            }
            Map<String, C1499i> map = C1493c.f2506a;
            if (C1497g.m22187c().f2523l) {
                C1493c.m22194a(activity, C1497g.m22187c().m22188b(), true);
            } else {
                C1493c.m22194a(activity, C1497g.m22187c().m22191a(), false);
            }
        } else if (!(obj instanceof InterfaceC11847b)) {
            C11944a.m2026a(String.format(Locale.ENGLISH, "%s used the global configuration.", obj.getClass().getName()));
            Map<String, C1499i> map2 = C1493c.f2506a;
        } else {
            C11944a.m2026a(String.format(Locale.ENGLISH, "%s implemented by %s!", obj.getClass().getName(), InterfaceC11847b.class.getName()));
            InterfaceC11847b interfaceC11847b = (InterfaceC11847b) obj;
            Map<String, C1499i> map3 = C1493c.f2506a;
            float m2069a = interfaceC11847b.m2069a();
            if (m2069a <= 0.0f) {
                m2069a = interfaceC11847b.m2068b() ? C1497g.m22187c().m22188b() : C1497g.m22187c().m22191a();
            }
            C1493c.m22194a(activity, m2069a, interfaceC11847b.m2068b());
        }
    }
}
