package com.mob.commons.p231cc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

/* renamed from: com.mob.commons.cc.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5798f extends BroadcastReceiver implements InterfaceC5812q<C5798f> {

    /* renamed from: a */
    private C5802j f14280a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.f14280a != null) {
            try {
                ArrayList<Object> arrayList = new ArrayList<>(1);
                arrayList.add(intent);
                this.f14280a.m12441a("onReceive", arrayList);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public void m12445a(C5802j c5802j) {
        this.f14280a = c5802j;
    }

    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(C5798f c5798f, Class<C5798f> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if ("setHandler".equals(str) && objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof C5802j)) {
            c5798f.m12445a((C5802j) objArr[0]);
            return true;
        }
        return false;
    }
}
