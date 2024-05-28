package android.arch.lifecycle;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ViewModelStore {
    private final HashMap<String, ViewModel> mMap = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void put(String str, ViewModel viewModel) {
        ViewModel put = this.mMap.put(str, viewModel);
        if (put != null) {
            put.onCleared();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ViewModel get(String str) {
        return this.mMap.get(str);
    }

    public final void clear() {
        for (ViewModel viewModel : this.mMap.values()) {
            viewModel.onCleared();
        }
        this.mMap.clear();
    }
}
