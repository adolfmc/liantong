package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3104g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Bundle f8100a;

    /* renamed from: b */
    final /* synthetic */ NABaseMap f8101b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3104g(NABaseMap nABaseMap, Bundle bundle) {
        this.f8101b = nABaseMap;
        this.f8100a = bundle;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean m17656b;
        ReadWriteLock readWriteLock;
        ReadWriteLock readWriteLock2;
        ReadWriteLock readWriteLock3;
        long j;
        m17656b = this.f8101b.m17656b();
        if (m17656b) {
            boolean z = false;
            try {
                readWriteLock3 = this.f8101b.f8086d;
                z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
                if (z) {
                    NABaseMap nABaseMap = this.f8101b;
                    j = this.f8101b.f8084b;
                    nABaseMap.nativeRemoveOneOverlayItem(j, this.f8100a);
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
                    readWriteLock = this.f8101b.f8086d;
                    readWriteLock.readLock().unlock();
                }
                throw th;
            }
            readWriteLock2 = this.f8101b.f8086d;
            readWriteLock2.readLock().unlock();
        }
    }
}
