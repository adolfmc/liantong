package com.baidu.platform.comjni.map.basemap;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3108k implements Runnable {

    /* renamed from: a */
    final /* synthetic */ long f8109a;

    /* renamed from: b */
    final /* synthetic */ NABaseMap f8110b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3108k(NABaseMap nABaseMap, long j) {
        this.f8110b = nABaseMap;
        this.f8109a = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        ReadWriteLock readWriteLock;
        ReadWriteLock readWriteLock2;
        ReadWriteLock readWriteLock3;
        Set set;
        long j;
        boolean z = false;
        try {
            readWriteLock3 = this.f8110b.f8086d;
            z = readWriteLock3.writeLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (z) {
                set = this.f8110b.f8087e;
                set.add(Long.valueOf(this.f8109a));
                NABaseMap nABaseMap = this.f8110b;
                j = this.f8110b.f8084b;
                nABaseMap.nativeRemoveLayer(j, this.f8109a);
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
                readWriteLock = this.f8110b.f8086d;
                readWriteLock.writeLock().unlock();
            }
            throw th;
        }
        readWriteLock2 = this.f8110b.f8086d;
        readWriteLock2.writeLock().unlock();
    }
}
