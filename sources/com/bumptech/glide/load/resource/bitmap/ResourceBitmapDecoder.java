package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ResourceBitmapDecoder implements ResourceDecoder<Uri, Bitmap> {
    private final BitmapPool bitmapPool;
    private final ResourceDrawableDecoder drawableDecoder;

    public ResourceBitmapDecoder(ResourceDrawableDecoder resourceDrawableDecoder, BitmapPool bitmapPool) {
        this.drawableDecoder = resourceDrawableDecoder;
        this.bitmapPool = bitmapPool;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(@NonNull Uri uri, @NonNull Options options) {
        return "android.resource".equals(uri.getScheme());
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    @Nullable
    public Resource<Bitmap> decode(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        Resource<Drawable> decode = this.drawableDecoder.decode(uri, i, i2, options);
        if (decode == null) {
            return null;
        }
        return DrawableToBitmapConverter.convert(this.bitmapPool, decode.get(), i, i2);
    }
}
