package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import java.io.File;
import java.net.URL;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
interface ModelTypes<T> {
    @CheckResult
    @NonNull
    T load(@Nullable Bitmap bitmap);

    @CheckResult
    @NonNull
    T load(@Nullable Drawable drawable);

    @CheckResult
    @NonNull
    T load(@Nullable Uri uri);

    @CheckResult
    @NonNull
    T load(@Nullable File file);

    @CheckResult
    @NonNull
    T load(@RawRes @DrawableRes @Nullable Integer num);

    @CheckResult
    @NonNull
    T load(@Nullable Object obj);

    @CheckResult
    @NonNull
    T load(@Nullable String str);

    @CheckResult
    @Deprecated
    T load(@Nullable URL url);

    @CheckResult
    @NonNull
    T load(@Nullable byte[] bArr);
}
