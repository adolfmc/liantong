package p090c;

import android.os.Bundle;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: c.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1501j extends FragmentManager.FragmentLifecycleCallbacks {

    /* renamed from: a */
    public InterfaceC1492b f2534a;

    public C1501j(InterfaceC1492b interfaceC1492b) {
        this.f2534a = interfaceC1492b;
    }

    @Override // android.support.p083v4.app.FragmentManager.FragmentLifecycleCallbacks
    public final void onFragmentCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        InterfaceC1492b interfaceC1492b = this.f2534a;
        if (interfaceC1492b != null) {
            interfaceC1492b.mo22185a(fragment, fragment.getActivity());
        }
    }
}
