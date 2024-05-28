package com.mob.tools.utils;

import com.mob.tools.MobLog;

/* renamed from: com.mob.tools.utils.h */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class AbstractRunnableC6217h implements Runnable {
    /* renamed from: a */
    protected abstract void mo10991a() throws Throwable;

    @Override // java.lang.Runnable
    public final void run() {
        try {
            mo10991a();
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }
}
