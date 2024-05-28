package com.bytedance.pangle.fragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Keep;
import com.bytedance.applog.tracker.Tracker;
import com.bytedance.pangle.Zeus;

@Keep
@SuppressLint({"NewApi"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ReportBaseFragment extends Fragment {
    Application.ActivityLifecycleCallbacks callbacks = new C3836c(this);

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

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Zeus.getAppApplication().registerActivityLifecycleCallbacks(this.callbacks);
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        Zeus.getAppApplication().unregisterActivityLifecycleCallbacks(this.callbacks);
    }
}
