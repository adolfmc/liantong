package com.baidu.platform.comjni.map.basemap;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3099b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ long f8088a;

    /* renamed from: b */
    final /* synthetic */ boolean f8089b;

    /* renamed from: c */
    final /* synthetic */ NABaseMap f8090c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3099b(NABaseMap nABaseMap, long j, boolean z) {
        this.f8090c = nABaseMap;
        this.f8088a = j;
        this.f8089b = z;
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
            readWriteLock3 = this.f8090c.f8086d;
            z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (z) {
                m17665a = this.f8090c.m17665a(this.f8088a);
                if (!m17665a) {
                    NABaseMap nABaseMap = this.f8090c;
                    j = this.f8090c.f8084b;
                    nABaseMap.nativeShowLayers(j, this.f8088a, this.f8089b);
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
                readWriteLock = this.f8090c.f8086d;
                readWriteLock.readLock().unlock();
            }
            throw th;
        }
        readWriteLock2 = this.f8090c.f8086d;
        readWriteLock2.readLock().unlock();
    }
}
