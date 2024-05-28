package com.baidu.platform.comjni.map.basemap;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3109l implements Runnable {

    /* renamed from: a */
    final /* synthetic */ long f8111a;

    /* renamed from: b */
    final /* synthetic */ long f8112b;

    /* renamed from: c */
    final /* synthetic */ NABaseMap f8113c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3109l(NABaseMap nABaseMap, long j, long j2) {
        this.f8113c = nABaseMap;
        this.f8111a = j;
        this.f8112b = j2;
    }

    @Override // java.lang.Runnable
    public void run() {
        ReadWriteLock readWriteLock;
        ReadWriteLock readWriteLock2;
        ReadWriteLock readWriteLock3;
        boolean m17665a;
        boolean m17665a2;
        long j;
        boolean z = false;
        try {
            readWriteLock3 = this.f8113c.f8086d;
            z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (z) {
                m17665a = this.f8113c.m17665a(this.f8111a);
                if (!m17665a) {
                    m17665a2 = this.f8113c.m17665a(this.f8112b);
                    if (!m17665a2) {
                        NABaseMap nABaseMap = this.f8113c;
                        j = this.f8113c.f8084b;
                        nABaseMap.nativeSwitchLayer(j, this.f8111a, this.f8112b);
                    }
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
                readWriteLock = this.f8113c.f8086d;
                readWriteLock.readLock().unlock();
            }
            throw th;
        }
        readWriteLock2 = this.f8113c.f8086d;
        readWriteLock2.readLock().unlock();
    }
}
