package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3105h implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Bundle[] f8102a;

    /* renamed from: b */
    final /* synthetic */ NABaseMap f8103b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3105h(NABaseMap nABaseMap, Bundle[] bundleArr) {
        this.f8103b = nABaseMap;
        this.f8102a = bundleArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean m17656b;
        boolean z;
        Throwable th;
        ReadWriteLock readWriteLock;
        ReadWriteLock readWriteLock2;
        ReadWriteLock readWriteLock3;
        Bundle[] bundleArr;
        boolean z2;
        long j;
        m17656b = this.f8103b.m17656b();
        if (m17656b) {
            try {
                readWriteLock3 = this.f8103b.f8086d;
                z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
                if (z) {
                    try {
                        for (Bundle bundle : this.f8102a) {
                            z2 = this.f8103b.f8085c;
                            if (z2) {
                                break;
                            }
                            NABaseMap nABaseMap = this.f8103b;
                            j = this.f8103b.f8084b;
                            nABaseMap.nativeRemoveOneOverlayItem(j, bundle);
                        }
                    } catch (Exception unused) {
                        if (!z) {
                            return;
                        }
                        readWriteLock2 = this.f8103b.f8086d;
                        readWriteLock2.readLock().unlock();
                    } catch (Throwable th2) {
                        th = th2;
                        if (z) {
                            readWriteLock = this.f8103b.f8086d;
                            readWriteLock.readLock().unlock();
                        }
                        throw th;
                    }
                }
                if (!z) {
                    return;
                }
            } catch (Exception unused2) {
                z = false;
            } catch (Throwable th3) {
                z = false;
                th = th3;
            }
            readWriteLock2 = this.f8103b.f8086d;
            readWriteLock2.readLock().unlock();
        }
    }
}
