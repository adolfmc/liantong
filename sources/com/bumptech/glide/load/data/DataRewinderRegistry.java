package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DataRewinderRegistry {
    private static final DataRewinder.Factory<?> DEFAULT_FACTORY = new DataRewinder.Factory<Object>() { // from class: com.bumptech.glide.load.data.DataRewinderRegistry.1
        @Override // com.bumptech.glide.load.data.DataRewinder.Factory
        @NonNull
        public DataRewinder<Object> build(@NonNull Object obj) {
            return new DefaultRewinder(obj);
        }

        @Override // com.bumptech.glide.load.data.DataRewinder.Factory
        @NonNull
        public Class<Object> getDataClass() {
            throw new UnsupportedOperationException("Not implemented");
        }
    };
    private final Map<Class<?>, DataRewinder.Factory<?>> rewinders = new HashMap();

    public synchronized void register(@NonNull DataRewinder.Factory<?> factory) {
        this.rewinders.put(factory.getDataClass(), factory);
    }

    @NonNull
    public synchronized <T> DataRewinder<T> build(@NonNull T t) {
        DataRewinder.Factory<?> factory;
        Preconditions.checkNotNull(t);
        factory = this.rewinders.get(t.getClass());
        if (factory == null) {
            Iterator<DataRewinder.Factory<?>> it = this.rewinders.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DataRewinder.Factory<?> next = it.next();
                if (next.getDataClass().isAssignableFrom(t.getClass())) {
                    factory = next;
                    break;
                }
            }
        }
        if (factory == null) {
            factory = DEFAULT_FACTORY;
        }
        return (DataRewinder<T>) factory.build(t);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class DefaultRewinder implements DataRewinder<Object> {
        private final Object data;

        @Override // com.bumptech.glide.load.data.DataRewinder
        public void cleanup() {
        }

        DefaultRewinder(@NonNull Object obj) {
            this.data = obj;
        }

        @Override // com.bumptech.glide.load.data.DataRewinder
        @NonNull
        public Object rewindAndGet() {
            return this.data;
        }
    }
}
