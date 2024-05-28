package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VideoBitmapDecoder extends VideoDecoder<ParcelFileDescriptor> {
    public VideoBitmapDecoder(Context context) {
        this(Glide.get(context).getBitmapPool());
    }

    public VideoBitmapDecoder(BitmapPool bitmapPool) {
        super(bitmapPool, new VideoDecoder.ParcelFileDescriptorInitializer());
    }
}
