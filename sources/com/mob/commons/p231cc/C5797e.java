package com.mob.commons.p231cc;

import android.content.Intent;
import android.content.pm.PackageManager;
import com.mob.commons.C5857m;

/* renamed from: com.mob.commons.cc.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5797e implements InterfaceC5812q<PackageManager> {
    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(PackageManager packageManager, Class<PackageManager> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if (C5857m.m12226a("019Cbcbe:d'bhbicg;cgdcg,chAd@bhbbbg)adVdf").equals(str) && objArr.length == 2 && (objArr[0] instanceof Intent) && (objArr[1] instanceof Integer)) {
            objArr2[0] = packageManager.queryIntentServices((Intent) objArr[0], ((Integer) objArr[1]).intValue());
            return true;
        } else if (C5857m.m12226a("025@ccPdg daGbDbePcaf5cg^cgdcg$djcbbhei ba=bj]b;ccYd").equals(str) && objArr.length == 1 && (objArr[0] instanceof String)) {
            objArr2[0] = packageManager.getLaunchIntentForPackage((String) objArr[0]);
            return true;
        } else if (C5857m.m12226a("015KbhLdMdfcb=e%bb4d,cjJag<bgbbbgJgXbi").equals(str) && objArr.length == 2 && (objArr[0] instanceof Integer) && (objArr[1] instanceof Integer)) {
            objArr2[0] = packageManager.resolveActivity((Intent) objArr[0], ((Integer) objArr[1]).intValue());
            return true;
        } else {
            return false;
        }
    }
}
