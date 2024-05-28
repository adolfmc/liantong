package com.youth.banner.loader;

import android.content.Context;
import android.widget.ImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.youth.banner.loader.ImageLoaderInterface
    public ImageView createImageView(Context context) {
        return new ImageView(context);
    }
}
