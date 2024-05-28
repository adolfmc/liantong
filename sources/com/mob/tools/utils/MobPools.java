package com.mob.tools.utils;

import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class MobPools implements PublicMemberKeeper {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface Pool<T> extends PublicMemberKeeper {
        T acquire();

        boolean release(T t);
    }

    private MobPools() {
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class SimplePool<T> implements Pool<T> {

        /* renamed from: a */
        private final Object[] f15247a;

        /* renamed from: b */
        private int f15248b;

        public SimplePool(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.f15247a = new Object[i];
        }

        @Override // com.mob.tools.utils.MobPools.Pool
        public T acquire() {
            int i = this.f15248b;
            if (i > 0) {
                int i2 = i - 1;
                try {
                    T t = (T) this.f15247a[i2];
                    this.f15247a[i2] = null;
                    this.f15248b = i - 1;
                    return t;
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                }
            }
            return null;
        }

        @Override // com.mob.tools.utils.MobPools.Pool
        public boolean release(T t) {
            if (m11156a(t)) {
                throw new IllegalStateException("Already in the pool!");
            }
            int i = this.f15248b;
            Object[] objArr = this.f15247a;
            if (i < objArr.length) {
                objArr[i] = t;
                this.f15248b = i + 1;
                return true;
            }
            return false;
        }

        /* renamed from: a */
        private boolean m11156a(T t) {
            for (int i = 0; i < this.f15248b; i++) {
                if (this.f15247a[i] == t) {
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class SynchronizedPool<T> extends SimplePool<T> {

        /* renamed from: a */
        private final Object f15249a;

        public SynchronizedPool(int i, Object obj) {
            super(i);
            this.f15249a = obj;
        }

        public SynchronizedPool(int i) {
            this(i, new Object());
        }

        @Override // com.mob.tools.utils.MobPools.SimplePool, com.mob.tools.utils.MobPools.Pool
        public T acquire() {
            T t;
            synchronized (this.f15249a) {
                t = (T) super.acquire();
            }
            return t;
        }

        @Override // com.mob.tools.utils.MobPools.SimplePool, com.mob.tools.utils.MobPools.Pool
        public boolean release(T t) {
            boolean release;
            synchronized (this.f15249a) {
                release = super.release(t);
            }
            return release;
        }
    }
}
