package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comjni.map.basemap.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3112o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ long f8118a;

    /* renamed from: b */
    final /* synthetic */ long f8119b;

    /* renamed from: c */
    final /* synthetic */ boolean f8120c;

    /* renamed from: d */
    final /* synthetic */ Bundle f8121d;

    /* renamed from: e */
    final /* synthetic */ NABaseMap f8122e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3112o(NABaseMap nABaseMap, long j, long j2, boolean z, Bundle bundle) {
        this.f8122e = nABaseMap;
        this.f8118a = j;
        this.f8119b = j2;
        this.f8120c = z;
        this.f8121d = bundle;
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
            readWriteLock3 = this.f8122e.f8086d;
            z = readWriteLock3.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (z) {
                m17665a = this.f8122e.m17665a(this.f8118a);
                if (!m17665a) {
                    NABaseMap nABaseMap = this.f8122e;
                    j = this.f8122e.f8084b;
                    nABaseMap.nativeSetFocus(j, this.f8118a, this.f8119b, this.f8120c, this.f8121d);
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
                readWriteLock = this.f8122e.f8086d;
                readWriteLock.readLock().unlock();
            }
            throw th;
        }
        readWriteLock2 = this.f8122e.f8086d;
        readWriteLock2.readLock().unlock();
    }
}
