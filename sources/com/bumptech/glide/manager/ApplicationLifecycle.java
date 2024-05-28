package com.bumptech.glide.manager;

import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
class ApplicationLifecycle implements Lifecycle {
    @Override // com.bumptech.glide.manager.Lifecycle
    public void removeListener(@NonNull LifecycleListener lifecycleListener) {
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(@NonNull LifecycleListener lifecycleListener) {
        lifecycleListener.onStart();
    }
}
