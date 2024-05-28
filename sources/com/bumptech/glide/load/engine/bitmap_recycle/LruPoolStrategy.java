package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
interface LruPoolStrategy {
    @Nullable
    Bitmap get(int i, int i2, Bitmap.Config config);

    int getSize(Bitmap bitmap);

    String logBitmap(int i, int i2, Bitmap.Config config);

    String logBitmap(Bitmap bitmap);

    void put(Bitmap bitmap);

    @Nullable
    Bitmap removeLast();
}
