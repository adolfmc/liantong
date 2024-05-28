package com.bumptech.glide.manager;

import android.support.annotation.NonNull;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class TargetTracker implements LifecycleListener {
    private final Set<Target<?>> targets = Collections.newSetFromMap(new WeakHashMap());

    public void track(@NonNull Target<?> target) {
        this.targets.add(target);
    }

    public void untrack(@NonNull Target<?> target) {
        this.targets.remove(target);
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
        for (Target target : Util.getSnapshot(this.targets)) {
            target.onStart();
        }
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
        for (Target target : Util.getSnapshot(this.targets)) {
            target.onStop();
        }
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
        for (Target target : Util.getSnapshot(this.targets)) {
            target.onDestroy();
        }
    }

    @NonNull
    public List<Target<?>> getAll() {
        return Util.getSnapshot(this.targets);
    }

    public void clear() {
        this.targets.clear();
    }
}
