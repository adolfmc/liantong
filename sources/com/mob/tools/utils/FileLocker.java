package com.mob.tools.utils;

import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FileLocker implements PublicMemberKeeper {

    /* renamed from: a */
    private FileOutputStream f15185a;

    /* renamed from: b */
    private FileLock f15186b;

    public synchronized void setLockFile(String str) {
        try {
            this.f15185a = new FileOutputStream(str);
        } catch (Throwable unused) {
            if (this.f15185a != null) {
                try {
                    this.f15185a.close();
                } catch (Throwable unused2) {
                }
                this.f15185a = null;
            }
        }
    }

    public synchronized boolean lock(boolean z) {
        return lock(z, z ? 1000L : 500L, 16L);
    }

    public synchronized boolean lock(boolean z, long j, long j2) {
        if (this.f15185a == null) {
            return false;
        }
        return m11276a(z);
    }

    /* renamed from: a */
    private boolean m11276a(boolean z) throws Throwable {
        if (z) {
            this.f15186b = this.f15185a.getChannel().lock();
        } else {
            this.f15186b = this.f15185a.getChannel().tryLock();
        }
        return this.f15186b != null;
    }

    public synchronized void lock(Runnable runnable, boolean z) {
        if (lock(z) && runnable != null) {
            runnable.run();
        }
    }

    public synchronized void unlock() {
        if (this.f15186b == null) {
            return;
        }
        try {
            this.f15186b.release();
        } catch (Throwable unused) {
        }
        this.f15186b = null;
    }

    public synchronized void release() {
        if (this.f15185a == null) {
            return;
        }
        unlock();
        try {
            this.f15185a.close();
        } catch (Throwable unused) {
        }
        this.f15185a = null;
    }
}
