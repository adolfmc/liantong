package com.mob.commons.p231cc;

import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import com.mob.commons.p229a.C5731l;
import java.util.ArrayList;

/* renamed from: com.mob.commons.cc.k */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5804k implements InterfaceC5812q<C5804k> {

    /* renamed from: a */
    private C5802j f14283a;

    /* renamed from: a */
    private ConnectivityManager.NetworkCallback m12439a() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ConnectivityManager.NetworkCallback() { // from class: com.mob.commons.cc.k.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    ArrayList<Object> arrayList = new ArrayList<>();
                    arrayList.add(network);
                    C5804k.this.f14283a.m12441a(C5731l.m12674a("011FfeOf:fmee@eGej!heFgfShg"), arrayList);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    super.onLost(network);
                    ArrayList<Object> arrayList = new ArrayList<>();
                    arrayList.add(network);
                    C5804k.this.f14283a.m12441a(C5731l.m12674a("006QfeYfJgdfegiYj"), arrayList);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onUnavailable() {
                    super.onUnavailable();
                }
            };
        }
        return null;
    }

    /* renamed from: a */
    public void m12438a(C5802j c5802j) {
        this.f14283a = c5802j;
    }

    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(C5804k c5804k, Class<C5804k> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if ("setHandler".equals(str) && objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof C5802j)) {
            c5804k.m12438a((C5802j) objArr[0]);
        } else if (!C5731l.m12674a("0194ejMf9ejDjTfi*gjRggfeekemhkSehhYgf=ed9em").equals(str) || objArr.length != 0) {
            return false;
        } else {
            objArr2[0] = c5804k.m12439a();
        }
        return true;
    }
}
