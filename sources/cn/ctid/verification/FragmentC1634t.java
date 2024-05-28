package cn.ctid.verification;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import cn.ctid.verification.C1603B;
import com.bytedance.applog.tracker.Tracker;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.t */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FragmentC1634t extends Fragment {

    /* renamed from: a */
    private SparseArray<C1603B.InterfaceC1604a> f2698a = new SparseArray<>();

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(140);
    }

    /* renamed from: a */
    public native void m22053a(Intent intent, C1603B.InterfaceC1604a interfaceC1604a);

    @Override // android.app.Fragment
    public native void onActivityResult(int i, int i2, Intent intent);

    @Override // android.app.Fragment
    public native void onCreate(Bundle bundle);

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
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }
}
