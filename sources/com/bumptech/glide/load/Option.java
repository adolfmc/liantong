package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Option<T> {
    private static final CacheKeyUpdater<Object> EMPTY_UPDATER = new CacheKeyUpdater<Object>() { // from class: com.bumptech.glide.load.Option.1
        @Override // com.bumptech.glide.load.Option.CacheKeyUpdater
        public void update(@NonNull byte[] bArr, @NonNull Object obj, @NonNull MessageDigest messageDigest) {
        }
    };
    private final CacheKeyUpdater<T> cacheKeyUpdater;
    private final T defaultValue;
    private final String key;
    private volatile byte[] keyBytes;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface CacheKeyUpdater<T> {
        void update(@NonNull byte[] bArr, @NonNull T t, @NonNull MessageDigest messageDigest);
    }

    @NonNull
    public static <T> Option<T> memory(@NonNull String str) {
        return new Option<>(str, null, emptyUpdater());
    }

    @NonNull
    public static <T> Option<T> memory(@NonNull String str, @NonNull T t) {
        return new Option<>(str, t, emptyUpdater());
    }

    @NonNull
    public static <T> Option<T> disk(@NonNull String str, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, null, cacheKeyUpdater);
    }

    @NonNull
    public static <T> Option<T> disk(@NonNull String str, @Nullable T t, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, t, cacheKeyUpdater);
    }

    private Option(@NonNull String str, @Nullable T t, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        this.key = Preconditions.checkNotEmpty(str);
        this.defaultValue = t;
        this.cacheKeyUpdater = (CacheKeyUpdater) Preconditions.checkNotNull(cacheKeyUpdater);
    }

    @Nullable
    public T getDefaultValue() {
        return this.defaultValue;
    }

    public void update(@NonNull T t, @NonNull MessageDigest messageDigest) {
        this.cacheKeyUpdater.update(getKeyBytes(), t, messageDigest);
    }

    @NonNull
    private byte[] getKeyBytes() {
        if (this.keyBytes == null) {
            this.keyBytes = this.key.getBytes(Key.CHARSET);
        }
        return this.keyBytes;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Option) {
            return this.key.equals(((Option) obj).key);
        }
        return false;
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    @NonNull
    private static <T> CacheKeyUpdater<T> emptyUpdater() {
        return (CacheKeyUpdater<T>) EMPTY_UPDATER;
    }

    public String toString() {
        return "Option{key='" + this.key + "'}";
    }
}
