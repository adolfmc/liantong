package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ComputableLiveData<T> {
    private AtomicBoolean mComputing;
    private final Executor mExecutor;
    private AtomicBoolean mInvalid;
    @VisibleForTesting
    final Runnable mInvalidationRunnable;
    private final LiveData<T> mLiveData;
    @VisibleForTesting
    final Runnable mRefreshRunnable;

    @WorkerThread
    protected abstract T compute();

    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    public ComputableLiveData(@NonNull Executor executor) {
        this.mInvalid = new AtomicBoolean(true);
        this.mComputing = new AtomicBoolean(false);
        this.mRefreshRunnable = new Runnable() { // from class: android.arch.lifecycle.ComputableLiveData.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            @WorkerThread
            public void run() {
                boolean z;
                do {
                    if (ComputableLiveData.this.mComputing.compareAndSet(false, true)) {
                        Object obj = null;
                        z = false;
                        while (ComputableLiveData.this.mInvalid.compareAndSet(true, false)) {
                            try {
                                obj = ComputableLiveData.this.compute();
                                z = true;
                            } finally {
                                ComputableLiveData.this.mComputing.set(false);
                            }
                        }
                        if (z) {
                            ComputableLiveData.this.mLiveData.postValue(obj);
                        }
                    } else {
                        z = false;
                    }
                    if (!z) {
                        return;
                    }
                } while (ComputableLiveData.this.mInvalid.get());
            }
        };
        this.mInvalidationRunnable = new Runnable() { // from class: android.arch.lifecycle.ComputableLiveData.3
            @Override // java.lang.Runnable
            @MainThread
            public void run() {
                boolean hasActiveObservers = ComputableLiveData.this.mLiveData.hasActiveObservers();
                if (ComputableLiveData.this.mInvalid.compareAndSet(false, true) && hasActiveObservers) {
                    ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
                }
            }
        };
        this.mExecutor = executor;
        this.mLiveData = new LiveData<T>() { // from class: android.arch.lifecycle.ComputableLiveData.1
            @Override // android.arch.lifecycle.LiveData
            protected void onActive() {
                ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
            }
        };
    }

    @NonNull
    public LiveData<T> getLiveData() {
        return this.mLiveData;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
    }
}
