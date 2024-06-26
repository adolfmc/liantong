package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.transition.Transition;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Target<R> extends LifecycleListener {
    public static final int SIZE_ORIGINAL = Integer.MIN_VALUE;

    @Nullable
    Request getRequest();

    void getSize(@NonNull SizeReadyCallback sizeReadyCallback);

    void onLoadCleared(@Nullable Drawable drawable);

    void onLoadFailed(@Nullable Drawable drawable);

    void onLoadStarted(@Nullable Drawable drawable);

    void onResourceReady(@NonNull R r, @Nullable Transition<? super R> transition);

    void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback);

    void setRequest(@Nullable Request request);
}
