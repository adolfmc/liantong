package android.support.p083v4.app;

import android.arch.lifecycle.ViewModelStore;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.app.FragmentManagerNonConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FragmentManagerNonConfig {
    private final List<FragmentManagerNonConfig> mChildNonConfigs;
    private final List<Fragment> mFragments;
    private final List<ViewModelStore> mViewModelStores;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentManagerNonConfig(List<Fragment> list, List<FragmentManagerNonConfig> list2, List<ViewModelStore> list3) {
        this.mFragments = list;
        this.mChildNonConfigs = list2;
        this.mViewModelStores = list3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Fragment> getFragments() {
        return this.mFragments;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<FragmentManagerNonConfig> getChildNonConfigs() {
        return this.mChildNonConfigs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<ViewModelStore> getViewModelStores() {
        return this.mViewModelStores;
    }
}
