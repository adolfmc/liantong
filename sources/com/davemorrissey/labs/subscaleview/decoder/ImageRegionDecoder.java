package com.davemorrissey.labs.subscaleview.decoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ImageRegionDecoder {
    @NonNull
    Bitmap decodeRegion(@NonNull Rect rect, int i);

    @NonNull
    Point init(Context context, @NonNull Uri uri) throws Exception;

    boolean isReady();

    void recycle();
}
