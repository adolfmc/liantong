package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.app.FragmentManager;
import com.bytedance.applog.tracker.Tracker;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LifecycleDispatcher {
    private static final String REPORT_FRAGMENT_TAG = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private static AtomicBoolean sInitialized = new AtomicBoolean(false);

    LifecycleDispatcher() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Context context) {
        if (sInitialized.getAndSet(true)) {
            return;
        }
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @VisibleForTesting
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {
        private final FragmentCallback mFragmentCallback = new FragmentCallback();

        DispatcherActivityCallback() {
        }

        @Override // android.arch.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.mFragmentCallback, true);
            }
            ReportFragment.injectIfNeededIn(activity);
        }

        @Override // android.arch.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.markState((FragmentActivity) activity, Lifecycle.State.CREATED);
            }
        }

        @Override // android.arch.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.markState((FragmentActivity) activity, Lifecycle.State.CREATED);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class DestructionReportFragment extends Fragment {
        @Override // android.support.p083v4.app.Fragment
        public void onHiddenChanged(boolean z) {
            Tracker.onHiddenChanged(this, z);
            super.onHiddenChanged(z);
        }

        @Override // android.support.p083v4.app.Fragment
        public void onResume() {
            Tracker.onResume(this);
            super.onResume();
        }

        @Override // android.support.p083v4.app.Fragment
        public void setUserVisibleHint(boolean z) {
            Tracker.setUserVisibleHint(this, z);
            super.setUserVisibleHint(z);
        }

        @Override // android.support.p083v4.app.Fragment
        public void onPause() {
            Tracker.onPause(this);
            super.onPause();
            dispatch(Lifecycle.Event.ON_PAUSE);
        }

        @Override // android.support.p083v4.app.Fragment
        public void onStop() {
            super.onStop();
            dispatch(Lifecycle.Event.ON_STOP);
        }

        @Override // android.support.p083v4.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            dispatch(Lifecycle.Event.ON_DESTROY);
        }

        protected void dispatch(Lifecycle.Event event) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(getParentFragment(), event);
        }
    }

    private static void markState(FragmentManager fragmentManager, Lifecycle.State state) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                markStateIn(fragment, state);
                if (fragment.isAdded()) {
                    markState(fragment.getChildFragmentManager(), state);
                }
            }
        }
    }

    private static void markStateIn(Object obj, Lifecycle.State state) {
        if (obj instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) obj).getLifecycle().markState(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void markState(FragmentActivity fragmentActivity, Lifecycle.State state) {
        markStateIn(fragmentActivity, state);
        markState(fragmentActivity.getSupportFragmentManager(), state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dispatchIfLifecycleOwner(Fragment fragment, Lifecycle.Event event) {
        if (fragment instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) fragment).getLifecycle().handleLifecycleEvent(event);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @VisibleForTesting
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class FragmentCallback extends FragmentManager.FragmentLifecycleCallbacks {
        FragmentCallback() {
        }

        @Override // android.support.p083v4.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(fragment, Lifecycle.Event.ON_CREATE);
            if ((fragment instanceof LifecycleRegistryOwner) && fragment.getChildFragmentManager().findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
                fragment.getChildFragmentManager().beginTransaction().add(new DestructionReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            }
        }

        @Override // android.support.p083v4.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(fragment, Lifecycle.Event.ON_START);
        }

        @Override // android.support.p083v4.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(fragment, Lifecycle.Event.ON_RESUME);
        }
    }
}
