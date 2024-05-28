package com.sinovatech.unicom.separatemodule.audience.fragment;

import android.support.p083v4.app.Fragment;
import com.bytedance.applog.tracker.Tracker;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class BaseFragment extends Fragment {
    private boolean isLoaded = false;
    private boolean isVisibleToUser = false;
    private boolean isCallResume = false;

    public abstract void clearData();

    public abstract long getFragmentId();

    abstract void lazyLoad();

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
        this.isCallResume = true;
        judgeLazyInit();
    }

    private void judgeLazyInit() {
        if (!this.isLoaded && this.isVisibleToUser && this.isCallResume) {
            this.isLoaded = true;
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
        this.isVisibleToUser = !z;
        judgeLazyInit();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.isLoaded = false;
        this.isVisibleToUser = false;
        this.isCallResume = false;
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
        this.isVisibleToUser = z;
        judgeLazyInit();
    }
}
