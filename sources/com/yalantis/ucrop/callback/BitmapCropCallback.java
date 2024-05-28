package com.yalantis.ucrop.callback;

import android.net.Uri;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface BitmapCropCallback {
    void onBitmapCropped(@NonNull Uri uri, int i, int i2, int i3, int i4);

    void onCropFailure(@NonNull Throwable th);
}
