package com.baidu.p120ar.async;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.async.ARTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ARTask<T> implements Runnable {
    private int mPriority = 5;

    public abstract T execute();

    public abstract String getTag();

    public void setPriority(int i) {
        if (i > 10) {
            i = 10;
        } else if (i < 1) {
            i = 1;
        }
        this.mPriority = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Thread.currentThread().setPriority(this.mPriority);
        try {
            AsyncTaskManager.getInstance().postEvent(execute());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
