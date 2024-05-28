package androidx.navigation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NavigationRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import com.bytedance.applog.tracker.Tracker;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class NavHostFragment extends Fragment implements NavHost {
    private static final String KEY_DEFAULT_NAV_HOST = "android-support-nav:fragment:defaultHost";
    private static final String KEY_GRAPH_ID = "android-support-nav:fragment:graphId";
    private static final String KEY_NAV_CONTROLLER_STATE = "android-support-nav:fragment:navControllerState";
    private static final String KEY_START_DESTINATION_ARGS = "android-support-nav:fragment:startDestinationArgs";
    private boolean mDefaultNavHost;
    private int mGraphId;
    private NavController mNavController;

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // android.support.p083v4.app.Fragment
    @android.support.annotation.CallSuper
    public void onInflate(@android.support.annotation.NonNull android.content.Context r-2, @android.support.annotation.NonNull android.util.AttributeSet r-1, @android.support.annotation.Nullable android.os.Bundle r0) {
        /*
            r-3 = this;
            com.bytedance.applog.tracker.Tracker.onPause(r0)
            super.onPause()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.fragment.NavHostFragment.onInflate(android.content.Context, android.util.AttributeSet, android.os.Bundle):void");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.p083v4.app.Fragment
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        Tracker.setUserVisibleHint((Fragment) this, (boolean) bundle);
        super.setUserVisibleHint(bundle);
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // android.support.p083v4.app.Fragment
    public void onViewCreated(@android.support.annotation.NonNull android.view.View r0, @android.support.annotation.Nullable android.os.Bundle r1) {
        /*
            r-1 = this;
            com.bytedance.applog.tracker.Tracker.setUserVisibleHint(r0, r1)
            super.setUserVisibleHint(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.fragment.NavHostFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @NonNull
    public static NavController findNavController(@NonNull Fragment fragment) {
        for (Fragment fragment2 = fragment; fragment2 != null; fragment2 = fragment2.getParentFragment()) {
            if (fragment2 instanceof NavHostFragment) {
                return ((NavHostFragment) fragment2).getNavController();
            }
            Fragment primaryNavigationFragment = fragment2.requireFragmentManager().getPrimaryNavigationFragment();
            if (primaryNavigationFragment instanceof NavHostFragment) {
                return ((NavHostFragment) primaryNavigationFragment).getNavController();
            }
        }
        View view = fragment.getView();
        if (view != null) {
            return Navigation.findNavController(view);
        }
        throw new IllegalStateException("Fragment " + fragment + " does not have a NavController set");
    }

    @NonNull
    public static NavHostFragment create(@NavigationRes int i) {
        return create(i, null);
    }

    @NonNull
    public static NavHostFragment create(@NavigationRes int i, @Nullable Bundle bundle) {
        Bundle bundle2;
        if (i != 0) {
            bundle2 = new Bundle();
            bundle2.putInt(KEY_GRAPH_ID, i);
        } else {
            bundle2 = null;
        }
        if (bundle != null) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putBundle(KEY_START_DESTINATION_ARGS, bundle);
        }
        NavHostFragment navHostFragment = new NavHostFragment();
        if (bundle2 != null) {
            navHostFragment.setArguments(bundle2);
        }
        return navHostFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:android.support.v4.app.Fragment), (r1 I:android.content.Context) type: SUPER call: android.support.v4.app.Fragment.onAttach(android.content.Context):void, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.support.v4.app.Fragment, androidx.navigation.fragment.NavHostFragment] */
    @NonNull
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        ?? onAttach;
        super.onAttach(this);
        if (onAttach.mDefaultNavHost) {
            onAttach.requireFragmentManager().beginTransaction().setPrimaryNavigationFragment(onAttach).commit();
            return;
        }
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:android.support.v4.app.Fragment), (r1 I:android.content.Context) type: SUPER call: android.support.v4.app.Fragment.onAttach(android.content.Context):void, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.support.v4.app.Fragment, androidx.navigation.fragment.NavHostFragment] */
    @Override // androidx.navigation.NavHost
    @NonNull
    public final NavController getNavController() {
        ?? onAttach;
        super.onAttach(this);
        if (onAttach.mDefaultNavHost) {
            onAttach.requireFragmentManager().beginTransaction().setPrimaryNavigationFragment(onAttach).commit();
            return;
        }
        return;
    }

    @Override // android.support.p083v4.app.Fragment
    @CallSuper
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (this.mDefaultNavHost) {
            requireFragmentManager().beginTransaction().setPrimaryNavigationFragment(this).commit();
        }
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0009: INVOKE  (r1 I:int) = (r0 I:androidx.navigation.fragment.NavHostFragment) type: VIRTUAL call: androidx.navigation.fragment.NavHostFragment.getId():int, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0002: INVOKE  (r1 I:android.content.Context) = (r1 I:android.view.LayoutInflater) type: VIRTUAL call: android.view.LayoutInflater.getContext():android.content.Context, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.fragment.NavHostFragment] */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.view.LayoutInflater] */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.widget.FrameLayout, void] */
    @Override // android.support.p083v4.app.Fragment
    @CallSuper
    public void onCreate(@Nullable Bundle bundle) {
        ?? context;
        ?? id;
        ?? frameLayout = new FrameLayout(context.getContext());
        frameLayout.setId(id.getId());
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        frameLayout.setId(getId());
        return frameLayout;
    }
}
