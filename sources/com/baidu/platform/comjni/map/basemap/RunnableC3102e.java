package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3102e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Bundle[] f8095a;

    /* renamed from: b */
    final /* synthetic */ int f8096b;

    /* renamed from: c */
    final /* synthetic */ NABaseMap f8097c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3102e(NABaseMap nABaseMap, Bundle[] bundleArr, int i) {
        this.f8097c = nABaseMap;
        this.f8095a = bundleArr;
        this.f8096b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean m17656b;
        ReadWriteLock readWriteLock;
        ReadWriteLock readWriteLock2;
        ReadWriteLock readWriteLock3;
        long j;
        m17656b = this.f8097c.m17656b();
        if (m17656b) {
            boolean z = false;
            try {
                readWriteLock3 = this.f8097c.f8086d;
                z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
                if (z) {
                    NABaseMap nABaseMap = this.f8097c;
                    j = this.f8097c.f8084b;
                    nABaseMap.nativeAddOverlayItems(j, this.f8095a, this.f8096b);
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
                    readWriteLock = this.f8097c.f8086d;
                    readWriteLock.readLock().unlock();
                }
                throw th;
            }
            readWriteLock2 = this.f8097c.f8086d;
            readWriteLock2.readLock().unlock();
        }
    }
}
