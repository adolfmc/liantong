package com.mob.commons.p231cc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import com.mob.commons.C5857m;

/* renamed from: com.mob.commons.cc.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5796d implements InterfaceC5812q<NotificationManager> {
    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(NotificationManager notificationManager, Class<NotificationManager> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if (C5857m.m12226a("025a2bh4dbgdFcfcbVg1bgcdbgUabg,bgcb-cBeh!fbccde").equals(str) && Build.VERSION.SDK_INT >= 26 && objArr.length == 1 && (objArr[0] instanceof NotificationChannel)) {
            notificationManager.createNotificationChannel((NotificationChannel) objArr[0]);
            return true;
        }
        if (C5857m.m12226a("006c8cb(gBbgcdbi").equals(str)) {
            if (objArr.length == 2) {
                notificationManager.notify(((Integer) objArr[0]).intValue(), (Notification) objArr[1]);
                return true;
            } else if (objArr.length == 3) {
                notificationManager.notify((String) objArr[0], ((Integer) objArr[1]).intValue(), (Notification) objArr[2]);
                return true;
            }
        } else if (C5857m.m12226a("0252ba)dedgd7cfcbYg-bgcdbgAabgTbgcb)cMehTfbccde").equals(str) && Build.VERSION.SDK_INT >= 26 && objArr.length == 1) {
            notificationManager.deleteNotificationChannel((String) objArr[0]);
            return true;
        } else if (C5857m.m12226a("006abcade").equals(str)) {
            if (objArr.length == 1) {
                notificationManager.cancel(((Integer) objArr[0]).intValue());
                return true;
            } else if (objArr.length == 2) {
                notificationManager.cancel((String) objArr[0], ((Integer) objArr[1]).intValue());
                return true;
            }
        }
        return false;
    }
}
