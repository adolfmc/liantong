package com.opensource.svgaplayer.utils;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Pools.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, m1890d2 = {"Lcom/opensource/svgaplayer/utils/Pools;", "", "()V", "Pool", "SimplePool", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class Pools {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Pools.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u000f\u0010\u0003\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0002\u0010\b¨\u0006\t"}, m1890d2 = {"Lcom/opensource/svgaplayer/utils/Pools$Pool;", "T", "", "acquire", "()Ljava/lang/Object;", "release", "", "instance", "(Ljava/lang/Object;)Z", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface Pool<T> {
        @Nullable
        T acquire();

        boolean release(T t);
    }

    private Pools() {
    }

    /* compiled from: Pools.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000f\u0010\u000b\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1890d2 = {"Lcom/opensource/svgaplayer/utils/Pools$SimplePool;", "T", "Lcom/opensource/svgaplayer/utils/Pools$Pool;", "maxPoolSize", "", "(I)V", "mPool", "", "", "[Ljava/lang/Object;", "mPoolSize", "acquire", "()Ljava/lang/Object;", "isInPool", "", "instance", "(Ljava/lang/Object;)Z", "release", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int i) {
            if (!(i > 0)) {
                throw new IllegalArgumentException("The max pool size must be > 0".toString());
            }
            this.mPool = new Object[i];
        }

        @Override // com.opensource.svgaplayer.utils.Pools.Pool
        @Nullable
        public T acquire() {
            int i = this.mPoolSize;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = this.mPool;
                T t = (T) objArr[i2];
                objArr[i2] = null;
                this.mPoolSize = i - 1;
                return t;
            }
            return null;
        }

        @Override // com.opensource.svgaplayer.utils.Pools.Pool
        public boolean release(T t) {
            if (!(!isInPool(t))) {
                throw new IllegalStateException("Already in the pool!".toString());
            }
            int i = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i < objArr.length) {
                objArr[i] = t;
                this.mPoolSize = i + 1;
                return true;
            }
            return false;
        }

        private final boolean isInPool(T t) {
            int i = this.mPoolSize;
            for (int i2 = 0; i2 < i; i2++) {
                if (this.mPool[i2] == t) {
                    return true;
                }
            }
            return false;
        }
    }
}
