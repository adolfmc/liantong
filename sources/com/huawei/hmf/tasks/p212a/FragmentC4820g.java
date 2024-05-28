package com.huawei.hmf.tasks.p212a;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.hmf.tasks.ExecuteResult;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

@NBSInstrumented
/* renamed from: com.huawei.hmf.tasks.a.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class FragmentC4820g extends Fragment {

    /* renamed from: b */
    private static final WeakHashMap<Activity, WeakReference<FragmentC4820g>> f10845b = new WeakHashMap<>();

    /* renamed from: a */
    private final List<WeakReference<ExecuteResult<?>>> f10846a = new ArrayList();

    /* renamed from: a */
    private static FragmentC4820g m15365a(Activity activity) {
        FragmentC4820g fragmentC4820g;
        WeakReference<FragmentC4820g> weakReference = f10845b.get(activity);
        if (weakReference == null || weakReference.get() == null) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            try {
                FragmentC4820g fragmentC4820g2 = (FragmentC4820g) fragmentManager.findFragmentByTag("com.huawei.hmf.tasks.lifecycle_fragment_tag");
                if (fragmentC4820g2 == null) {
                    try {
                        fragmentC4820g = m15363a(fragmentManager);
                    } catch (ClassCastException e) {
                        e = e;
                        fragmentC4820g = fragmentC4820g2;
                        Log.e("LifecycleCallbackFrg", "found LifecycleCallbackFragment but the type do not match. " + e.getMessage());
                        return fragmentC4820g;
                    }
                } else {
                    fragmentC4820g = fragmentC4820g2;
                }
            } catch (ClassCastException e2) {
                e = e2;
                fragmentC4820g = null;
            }
            try {
                f10845b.put(activity, new WeakReference<>(fragmentC4820g));
                return fragmentC4820g;
            } catch (ClassCastException e3) {
                e = e3;
                Log.e("LifecycleCallbackFrg", "found LifecycleCallbackFragment but the type do not match. " + e.getMessage());
                return fragmentC4820g;
            }
        }
        return weakReference.get();
    }

    /* renamed from: a */
    private static FragmentC4820g m15363a(FragmentManager fragmentManager) {
        FragmentC4820g fragmentC4820g;
        try {
            fragmentC4820g = new FragmentC4820g();
            try {
                fragmentManager.beginTransaction().add(fragmentC4820g, "com.huawei.hmf.tasks.lifecycle_fragment_tag").commitAllowingStateLoss();
            } catch (Exception e) {
                e = e;
                Log.e("LifecycleCallbackFrg", "create fragment failed." + e.getMessage());
                return fragmentC4820g;
            }
        } catch (Exception e2) {
            e = e2;
            fragmentC4820g = null;
        }
        return fragmentC4820g;
    }

    /* renamed from: a */
    public static void m15364a(Activity activity, ExecuteResult executeResult) {
        FragmentC4820g m15365a = m15365a(activity);
        if (m15365a != null) {
            synchronized (m15365a.f10846a) {
                m15365a.f10846a.add(new WeakReference<>(executeResult));
            }
        }
    }

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
    public void onStop() {
        super.onStop();
        synchronized (this.f10846a) {
            for (WeakReference<ExecuteResult<?>> weakReference : this.f10846a) {
                ExecuteResult<?> executeResult = weakReference.get();
                if (executeResult != null) {
                    executeResult.cancel();
                }
            }
            this.f10846a.clear();
        }
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }
}
