package com.baidu.platform.comjni.map.basemap;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3111n implements Runnable {

    /* renamed from: a */
    final /* synthetic */ long f8116a;

    /* renamed from: b */
    final /* synthetic */ NABaseMap f8117b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3111n(NABaseMap nABaseMap, long j) {
        this.f8117b = nABaseMap;
        this.f8116a = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        ReadWriteLock readWriteLock;
        ReadWriteLock readWriteLock2;
        ReadWriteLock readWriteLock3;
        boolean m17665a;
        long j;
        boolean z = false;
        try {
            readWriteLock3 = this.f8117b.f8086d;
            z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (z) {
                m17665a = this.f8117b.m17665a(this.f8116a);
                if (!m17665a) {
                    NABaseMap nABaseMap = this.f8117b;
                    j = this.f8117b.f8084b;
                    nABaseMap.nativeClearLayer(j, this.f8116a);
                }
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
                readWriteLock = this.f8117b.f8086d;
                readWriteLock.readLock().unlock();
            }
            throw th;
        }
        readWriteLock2 = this.f8117b.f8086d;
        readWriteLock2.readLock().unlock();
    }
}
