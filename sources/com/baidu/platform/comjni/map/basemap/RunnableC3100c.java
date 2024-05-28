package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3100c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Bundle f8091a;

    /* renamed from: b */
    final /* synthetic */ NABaseMap f8092b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3100c(NABaseMap nABaseMap, Bundle bundle) {
        this.f8092b = nABaseMap;
        this.f8091a = bundle;
    }

    @Override // java.lang.Runnable
    public void run() {
        ReadWriteLock readWriteLock;
        ReadWriteLock readWriteLock2;
        ReadWriteLock readWriteLock3;
        long j;
        boolean m17665a;
        ReadWriteLock readWriteLock4;
        boolean z = false;
        try {
            readWriteLock3 = this.f8092b.f8086d;
            z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (z) {
                if (this.f8091a != null) {
                    m17665a = this.f8092b.m17665a(this.f8091a.getLong("itemaddr", 0L));
                    if (m17665a) {
                        if (z) {
                            readWriteLock4 = this.f8092b.f8086d;
                            readWriteLock4.readLock().unlock();
                            return;
                        }
                        return;
                    }
                }
                NABaseMap nABaseMap = this.f8092b;
                j = this.f8092b.f8084b;
                nABaseMap.nativeRemoveItemData(j, this.f8091a);
            }
            if (!z) {
                return;
            }
        } catch (Exception unused) {
            if (!z) {
                return;
            }
        } catch (Throwable th) {
            if (z) {
                readWriteLock = this.f8092b.f8086d;
                readWriteLock.readLock().unlock();
            }
            throw th;
        }
        readWriteLock2 = this.f8092b.f8086d;
        readWriteLock2.readLock().unlock();
    }
}
