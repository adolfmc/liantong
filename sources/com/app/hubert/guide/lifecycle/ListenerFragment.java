package com.app.hubert.guide.lifecycle;

import android.app.Fragment;
import com.app.hubert.guide.util.LogUtil;
import com.bytedance.applog.tracker.Tracker;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class ListenerFragment extends Fragment {
    FragmentLifecycle mFragmentLifecycle;

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

    public void setFragmentLifecycle(FragmentLifecycle fragmentLifecycle) {
        this.mFragmentLifecycle = fragmentLifecycle;
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.m20455d("onStart: ");
        FragmentLifecycle fragmentLifecycle = this.mFragmentLifecycle;
        if (fragmentLifecycle != null) {
            fragmentLifecycle.onStart();
        }
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        FragmentLifecycle fragmentLifecycle = this.mFragmentLifecycle;
        if (fragmentLifecycle != null) {
            fragmentLifecycle.onStop();
        }
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        FragmentLifecycle fragmentLifecycle = this.mFragmentLifecycle;
        if (fragmentLifecycle != null) {
            fragmentLifecycle.onDestroyView();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.m20455d("onDestroy: ");
        FragmentLifecycle fragmentLifecycle = this.mFragmentLifecycle;
        if (fragmentLifecycle != null) {
            fragmentLifecycle.onDestroy();
        }
    }
}
