package com.huawei.hms.common.internal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.SparseArray;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.hms.api.HuaweiApiClient;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AutoLifecycleFragment extends Fragment {

    /* renamed from: a */
    private final SparseArray<C4890a> f11112a = new SparseArray<>();

    /* renamed from: b */
    private boolean f11113b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.common.internal.AutoLifecycleFragment$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4890a {

        /* renamed from: a */
        public final HuaweiApiClient f11114a;

        /* renamed from: b */
        protected final int f11115b;

        public C4890a(int i, HuaweiApiClient huaweiApiClient) {
            this.f11114a = huaweiApiClient;
            this.f11115b = i;
        }

        /* renamed from: a */
        public void m15129a() {
            this.f11114a.disconnect();
        }
    }

    public static AutoLifecycleFragment getInstance(Activity activity) {
        Preconditions.checkMainThread("Must be called on the main thread");
        try {
            AutoLifecycleFragment autoLifecycleFragment = (AutoLifecycleFragment) activity.getFragmentManager().findFragmentByTag("HmsAutoLifecycleFrag");
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (autoLifecycleFragment == null) {
                AutoLifecycleFragment autoLifecycleFragment2 = new AutoLifecycleFragment();
                fragmentManager.beginTransaction().add(autoLifecycleFragment2, "HmsAutoLifecycleFrag").commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
                return autoLifecycleFragment2;
            }
            return autoLifecycleFragment;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag HmsAutoLifecycleFrag is not a AutoLifecycleFragment", e);
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
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
    public void onStart() {
        super.onStart();
        this.f11113b = true;
        for (int i = 0; i < this.f11112a.size(); i++) {
            this.f11112a.valueAt(i).f11114a.connect((Activity) null);
        }
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.f11113b = false;
        for (int i = 0; i < this.f11112a.size(); i++) {
            this.f11112a.valueAt(i).f11114a.disconnect();
        }
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    public void startAutoMange(int i, HuaweiApiClient huaweiApiClient) {
        Preconditions.checkNotNull(huaweiApiClient, "HuaweiApiClient instance cannot be null");
        boolean z = this.f11112a.indexOfKey(i) < 0;
        Preconditions.checkState(z, "Already managing a HuaweiApiClient with this clientId: " + i);
        this.f11112a.put(i, new C4890a(i, huaweiApiClient));
        if (this.f11113b) {
            huaweiApiClient.connect((Activity) null);
        }
    }

    public void stopAutoManage(int i) {
        C4890a c4890a = this.f11112a.get(i);
        this.f11112a.remove(i);
        if (c4890a != null) {
            c4890a.m15129a();
        }
    }
}
