package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DataRewinder<T> {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Factory<T> {
        @NonNull
        DataRewinder<T> build(@NonNull T t);

        @NonNull
        Class<T> getDataClass();
    }

    void cleanup();

    @NonNull
    T rewindAndGet() throws IOException;
}
