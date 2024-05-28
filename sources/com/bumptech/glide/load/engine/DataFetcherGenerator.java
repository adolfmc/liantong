package com.bumptech.glide.load.engine;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
interface DataFetcherGenerator {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface FetcherReadyCallback {
        void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource);

        void onDataFetcherReady(Key key, @Nullable Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2);

        void reschedule();
    }

    void cancel();

    boolean startNext();
}
