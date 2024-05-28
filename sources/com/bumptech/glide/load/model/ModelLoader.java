package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.util.Preconditions;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ModelLoader<Model, Data> {
    @Nullable
    LoadData<Data> buildLoadData(@NonNull Model model, int i, int i2, @NonNull Options options);

    boolean handles(@NonNull Model model);

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class LoadData<Data> {
        public final List<Key> alternateKeys;
        public final DataFetcher<Data> fetcher;
        public final Key sourceKey;

        public LoadData(@NonNull Key key, @NonNull DataFetcher<Data> dataFetcher) {
            this(key, Collections.emptyList(), dataFetcher);
        }

        public LoadData(@NonNull Key key, @NonNull List<Key> list, @NonNull DataFetcher<Data> dataFetcher) {
            this.sourceKey = (Key) Preconditions.checkNotNull(key);
            this.alternateKeys = (List) Preconditions.checkNotNull(list);
            this.fetcher = (DataFetcher) Preconditions.checkNotNull(dataFetcher);
        }
    }
}
