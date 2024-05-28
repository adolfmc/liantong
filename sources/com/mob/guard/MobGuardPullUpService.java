package com.mob.guard;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.mob.MobSDK;
import com.mob.mgs.impl.C5994e;
import com.mob.mgs.impl.C5997g;
import com.mob.tools.proguard.ClassKeeper;

@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MobGuardPullUpService extends Service implements ClassKeeper {
    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            MobSDK.init(getApplicationContext());
            C5994e.m11860a().m11859a("[MobGuard] MobGuardPullUpService onCreate");
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        m12114a(intent);
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            m12114a(intent);
        }
        return super.onStartCommand(intent, i, i2);
    }

    /* renamed from: a */
    private void m12114a(Intent intent) {
        C5997g.m11845a(this, intent, false);
    }
}
