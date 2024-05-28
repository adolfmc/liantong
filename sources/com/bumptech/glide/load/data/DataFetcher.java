package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DataFetcher<T> {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface DataCallback<T> {
        void onDataReady(@Nullable T t);

        void onLoadFailed(@NonNull Exception exc);
    }

    void cancel();

    void cleanup();

    @NonNull
    Class<T> getDataClass();

    @NonNull
    DataSource getDataSource();

    void loadData(@NonNull Priority priority, @NonNull DataCallback<? super T> dataCallback);
}
