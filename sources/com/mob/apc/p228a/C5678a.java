package com.mob.apc.p228a;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.mob.MobACService;
import com.mob.apc.C5677a;
import com.mob.apc.C5688b;

/* renamed from: com.mob.apc.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5678a {

    /* renamed from: a */
    private MobACService f14002a;

    /* renamed from: b */
    private volatile boolean f14003b = false;

    /* renamed from: c */
    private final AbstractBinderC5684d f14004c = new AbstractBinderC5684d() { // from class: com.mob.apc.a.a.1
        @Override // com.mob.apc.p228a.AbstractBinderC5684d
        /* renamed from: a */
        public C5686e mo12840a(C5686e c5686e) throws RemoteException {
            C5677a c5677a;
            C5687f m12837a = C5687f.m12837a();
            m12837a.m12834b("APC msg received. msg: " + c5686e, new Object[0]);
            if (C5678a.this.f14003b) {
                C5687f m12837a2 = C5687f.m12837a();
                m12837a2.m12834b("inited: " + C5688b.f14033a, new Object[0]);
                if (!C5688b.f14033a) {
                    C5678a.this.f14003b = false;
                    if (c5686e != null && (c5677a = c5686e.f14027a) != null) {
                        Bundle bundle = new Bundle();
                        int i = c5677a.f13996a;
                        if (i == 1001) {
                            bundle.putInt("acsActType", 1);
                        } else if (i == 9004) {
                            bundle.putInt("acsActType", 2);
                        }
                        bundle.putString("pkg", c5686e.f14029c);
                        C5682c.m12850a().m12848a(bundle);
                    }
                }
            }
            return C5682c.m12850a().m12847a(c5686e);
        }
    };

    public C5678a(MobACService mobACService) {
        this.f14002a = mobACService;
    }

    /* renamed from: a */
    public void m12863a() {
        try {
            this.f14003b = true;
            C5688b.m12831a(this.f14002a.getApplicationContext());
        } catch (Throwable th) {
            C5687f.m12837a().m12835a(th);
        }
    }

    /* renamed from: a */
    public int m12861a(Intent intent, int i, int i2) {
        return this.f14002a.m12867a(intent, i, i2);
    }

    /* renamed from: a */
    public IBinder m12862a(Intent intent) {
        return this.f14004c;
    }

    /* renamed from: b */
    public void m12858b() {
        this.f14003b = false;
    }

    /* renamed from: b */
    public boolean m12857b(Intent intent) {
        return this.f14002a.m12868a(intent);
    }
}
