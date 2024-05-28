package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface ImageAware {
    int getHeight();

    int getId();

    ViewScaleType getScaleType();

    int getWidth();

    View getWrappedView();

    boolean isCollected();

    boolean setImageBitmap(Bitmap bitmap);

    boolean setImageDrawable(Drawable drawable);
}
