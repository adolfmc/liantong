package com.mob.commons.p231cc;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.ArrayList;

/* renamed from: com.mob.commons.cc.m */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ServiceConnectionC5807m implements ServiceConnection, InterfaceC5812q<ServiceConnectionC5807m> {

    /* renamed from: a */
    private C5802j f14285a;

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (this.f14285a != null) {
            try {
                ArrayList<Object> arrayList = new ArrayList<>();
                arrayList.add(componentName);
                arrayList.add(iBinder);
                this.f14285a.m12441a("onServiceConnected", arrayList);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (this.f14285a != null) {
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(componentName);
            this.f14285a.m12441a("onServiceDisconnected", arrayList);
        }
    }

    /* renamed from: a */
    public void m12434a(C5802j c5802j) {
        this.f14285a = c5802j;
    }

    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(ServiceConnectionC5807m serviceConnectionC5807m, Class<ServiceConnectionC5807m> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if ("setHandler".equals(str) && objArr.length == 1) {
            serviceConnectionC5807m.m12434a((C5802j) objArr[0]);
            return true;
        }
        return false;
    }
}
