package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BitmapPoolAdapter implements BitmapPool {
    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void clearMemory() {
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public long getMaxSize() {
        return 0L;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void setSizeMultiplier(float f) {
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void trimMemory(int i) {
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void put(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @NonNull
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @NonNull
    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        return get(i, i2, config);
    }
}
