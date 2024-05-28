package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ResourceEncoder<T> extends Encoder<Resource<T>> {
    @NonNull
    EncodeStrategy getEncodeStrategy(@NonNull Options options);
}
