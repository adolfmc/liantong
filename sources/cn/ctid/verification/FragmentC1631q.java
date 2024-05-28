package cn.ctid.verification;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import cn.ctid.verification.AbstractC1607E;
import com.bytedance.applog.tracker.Tracker;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.q */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FragmentC1631q extends Fragment {

    /* renamed from: a */
    public AbstractC1607E.InterfaceC1608a f2697a;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(137);
    }

    /* renamed from: a */
    public native void m22055a();

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
    }

    @Override // android.app.Fragment
    public native void onViewCreated(View view, Bundle bundle);

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }
}
