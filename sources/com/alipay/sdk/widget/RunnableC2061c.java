package com.alipay.sdk.widget;

import android.os.Handler;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.widget.C2058a;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2061c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C2058a f3937a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2061c(C2058a c2058a) {
        this.f3937a = c2058a;
    }

    @Override // java.lang.Runnable
    public void run() {
        C2058a.AlertDialogC2059a alertDialogC2059a;
        C2058a.AlertDialogC2059a alertDialogC2059a2;
        Handler handler;
        C2058a.AlertDialogC2059a alertDialogC2059a3;
        alertDialogC2059a = this.f3937a.f3927e;
        if (alertDialogC2059a != null) {
            alertDialogC2059a2 = this.f3937a.f3927e;
            if (alertDialogC2059a2.isShowing()) {
                try {
                    handler = this.f3937a.f3934l;
                    handler.removeMessages(1);
                    alertDialogC2059a3 = this.f3937a.f3927e;
                    alertDialogC2059a3.dismiss();
                } catch (Exception e) {
                    C2040c.m20715a(e);
                }
            }
        }
    }
}
