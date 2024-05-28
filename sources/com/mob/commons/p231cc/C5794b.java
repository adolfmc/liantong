package com.mob.commons.p231cc;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import com.mob.commons.C5869r;

/* renamed from: com.mob.commons.cc.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5794b implements InterfaceC5812q<Context> {
    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(Context context, Class<Context> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if (C5869r.m12200a("016See>fiQejdkfh'ifXdfej+fUdjdddiRcf").equals(str) && objArr.length == 1 && (objArr[0] instanceof String)) {
            try {
                objArr2[0] = context.getSystemService((String) objArr[0]);
            } catch (Throwable th) {
                objArr2[0] = null;
                thArr[0] = th;
            }
            return true;
        } else if ("getApplicationInfo".equals(str) && objArr.length == 0) {
            objArr2[0] = context.getApplicationInfo();
            return true;
        } else if (C5869r.m12200a("018]eeCfiGgjed^eifeiDghQf fhedWg%ddOfXdj").equals(str) && objArr.length == 0) {
            objArr2[0] = context.getContentResolver();
            return true;
        } else if (C5869r.m12200a("014Dee%fi@gkHdc?dl6d7ee]f3eh3dQdfHf").equals(str) && objArr.length == 0) {
            objArr2[0] = context.getPackageName();
            return true;
        } else if (C5869r.m12200a("0174eeGfi@gk*dc>dl?dTee%fQhc^dedSee!fTdj").equals(str) && objArr.length == 0) {
            objArr2[0] = context.getPackageManager();
            return true;
        } else if (C5869r.m12200a("013:fh<idPdjEiJel4ciGdidddiOi*dk").equals(str) && objArr.length == 1 && (objArr[0] instanceof Intent)) {
            context.startActivity((Intent) objArr[0]);
            return true;
        } else if (C5869r.m12200a("011See[fiKfldiOgfEfhfkdidj").equals(str)) {
            objArr2[0] = context.getFilesDir();
            return true;
        } else if (C5869r.m12200a("009HeeTfiAelfhfhYfi fh").equals(str)) {
            objArr2[0] = context.getAssets();
            return true;
        } else if (C5869r.m12200a("019chfc+dlejYfg.efgkRfOdjdfdifhfhdied!e").equals(str) && objArr.length == 1 && (objArr[0] instanceof String)) {
            if (Build.VERSION.SDK_INT >= 23) {
                objArr2[0] = Integer.valueOf(context.checkSelfPermission((String) objArr[0]));
            } else {
                objArr2[0] = Integer.valueOf(context.getPackageManager().checkPermission((String) objArr[0], context.getPackageName()));
            }
            return true;
        } else if (C5869r.m12200a("0113fedi?eFdcejKfDdjdddiFcf").equals(str) && objArr.length == 3) {
            objArr2[0] = Boolean.valueOf(context.bindService((Intent) objArr[0], (ServiceConnection) objArr[1], ((Integer) objArr[2]).intValue()));
            return true;
        } else if (C5869r.m12200a("013'dg%e9fediVePdcej.fIdjdddiQcf").equals(str) && objArr.length == 1 && (objArr[0] instanceof ServiceConnection)) {
            context.unbindService((ServiceConnection) objArr[0]);
            return true;
        } else {
            return false;
        }
    }
}
