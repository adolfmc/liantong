package com.youth.banner.loader;

import android.content.Context;
import android.view.View;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ImageLoaderInterface<T extends View> extends Serializable {
    T createImageView(Context context);

    void displayImage(Context context, Object obj, T t);
}
