package com.bumptech.glide.load.engine.cache;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.util.LruCache;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class LruResourceCache extends LruCache<Key, Resource<?>> implements MemoryCache {
    private MemoryCache.ResourceRemovedListener listener;

    @Override // com.bumptech.glide.load.engine.cache.MemoryCache
    @Nullable
    public /* bridge */ /* synthetic */ Resource put(@NonNull Key key, @Nullable Resource resource) {
        return (Resource) super.put((LruResourceCache) key, (Key) resource);
    }

    @Override // com.bumptech.glide.load.engine.cache.MemoryCache
    @Nullable
    public /* bridge */ /* synthetic */ Resource remove(@NonNull Key key) {
        return (Resource) super.remove((LruResourceCache) key);
    }

    public LruResourceCache(long j) {
        super(j);
    }

    @Override // com.bumptech.glide.load.engine.cache.MemoryCache
    public void setResourceRemovedListener(@NonNull MemoryCache.ResourceRemovedListener resourceRemovedListener) {
        this.listener = resourceRemovedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.util.LruCache
    public void onItemEvicted(@NonNull Key key, @Nullable Resource<?> resource) {
        MemoryCache.ResourceRemovedListener resourceRemovedListener = this.listener;
        if (resourceRemovedListener == null || resource == null) {
            return;
        }
        resourceRemovedListener.onResourceRemoved(resource);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.util.LruCache
    public int getSize(@Nullable Resource<?> resource) {
        if (resource == null) {
            return super.getSize((LruResourceCache) null);
        }
        return resource.getSize();
    }

    @Override // com.bumptech.glide.load.engine.cache.MemoryCache
    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i) {
        if (i >= 40) {
            clearMemory();
        } else if (i >= 20 || i == 15) {
            trimToSize(getMaxSize() / 2);
        }
    }
}
