package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3113p implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Bundle f8123a;

    /* renamed from: b */
    final /* synthetic */ boolean f8124b;

    /* renamed from: c */
    final /* synthetic */ NABaseMap f8125c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3113p(NABaseMap nABaseMap, Bundle bundle, boolean z) {
        this.f8125c = nABaseMap;
        this.f8123a = bundle;
        this.f8124b = z;
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
            readWriteLock3 = this.f8125c.f8086d;
            z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (z) {
                if (this.f8123a != null) {
                    m17665a = this.f8125c.m17665a(this.f8123a.getLong("itemaddr", 0L));
                    if (m17665a) {
                        if (z) {
                            readWriteLock4 = this.f8125c.f8086d;
                            readWriteLock4.readLock().unlock();
                            return;
                        }
                        return;
                    }
                }
                NABaseMap nABaseMap = this.f8125c;
                j = this.f8125c.f8084b;
                nABaseMap.nativeAddItemData(j, this.f8123a, this.f8124b);
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
                readWriteLock = this.f8125c.f8086d;
                readWriteLock.readLock().unlock();
            }
            throw th;
        }
        readWriteLock2 = this.f8125c.f8086d;
        readWriteLock2.readLock().unlock();
    }
}
