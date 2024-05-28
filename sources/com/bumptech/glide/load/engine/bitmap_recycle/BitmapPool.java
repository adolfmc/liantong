package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface BitmapPool {
    void clearMemory();

    @NonNull
    Bitmap get(int i, int i2, Bitmap.Config config);

    @NonNull
    Bitmap getDirty(int i, int i2, Bitmap.Config config);

    long getMaxSize();

    void put(Bitmap bitmap);

    void setSizeMultiplier(float f);

    void trimMemory(int i);
}
