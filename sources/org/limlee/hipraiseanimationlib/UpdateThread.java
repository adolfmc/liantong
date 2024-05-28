package org.limlee.hipraiseanimationlib;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class UpdateThread extends Thread {
    volatile boolean mIsQuited;

    public UpdateThread(String str) {
        super(str);
    }

    public void quit() {
        this.mIsQuited = true;
    }

    public boolean isQuited() {
        return this.mIsQuited;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.mIsQuited) {
        }
    }
}
