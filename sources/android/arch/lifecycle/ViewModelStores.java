package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ViewModelStores {
    private ViewModelStores() {
    }

    @NonNull
    @MainThread
    /* renamed from: of */
    public static ViewModelStore m22215of(@NonNull FragmentActivity fragmentActivity) {
        if (fragmentActivity instanceof ViewModelStoreOwner) {
            return fragmentActivity.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragmentActivity).getViewModelStore();
    }

    @NonNull
    @MainThread
    /* renamed from: of */
    public static ViewModelStore m22216of(@NonNull Fragment fragment) {
        if (fragment instanceof ViewModelStoreOwner) {
            return fragment.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragment).getViewModelStore();
    }
}
