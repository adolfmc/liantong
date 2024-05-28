package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Encoder<T> {
    boolean encode(@NonNull T t, @NonNull File file, @NonNull Options options);
}
