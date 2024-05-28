package com.mob;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.mob.apc.p228a.C5678a;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MobACService extends Service {

    /* renamed from: a */
    private C5678a f13977a = new C5678a(this);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f13977a.m12863a();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return this.f13977a.m12861a(intent, i, i2);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f13977a.m12862a(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f13977a.m12858b();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return this.f13977a.m12857b(intent);
    }

    /* renamed from: a */
    public boolean m12868a(Intent intent) {
        return super.onUnbind(intent);
    }

    /* renamed from: a */
    public int m12867a(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
