package com.bumptech.glide.load.engine.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface MemoryCache {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ResourceRemovedListener {
        void onResourceRemoved(@NonNull Resource<?> resource);
    }

    void clearMemory();

    long getCurrentSize();

    long getMaxSize();

    @Nullable
    Resource<?> put(@NonNull Key key, @Nullable Resource<?> resource);

    @Nullable
    Resource<?> remove(@NonNull Key key);

    void setResourceRemovedListener(@NonNull ResourceRemovedListener resourceRemovedListener);

    void setSizeMultiplier(float f);

    void trimMemory(int i);
}
