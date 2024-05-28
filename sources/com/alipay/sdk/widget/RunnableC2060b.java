package com.alipay.sdk.widget;

import android.os.Handler;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.widget.C2058a;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2060b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C2058a f3936a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2060b(C2058a c2058a) {
        this.f3936a = c2058a;
    }

    @Override // java.lang.Runnable
    public void run() {
        C2058a.AlertDialogC2059a alertDialogC2059a;
        C2058a.AlertDialogC2059a alertDialogC2059a2;
        C2058a.AlertDialogC2059a alertDialogC2059a3;
        Handler handler;
        C2058a.AlertDialogC2059a alertDialogC2059a4;
        boolean z;
        alertDialogC2059a = this.f3936a.f3927e;
        if (alertDialogC2059a == null) {
            C2058a c2058a = this.f3936a;
            c2058a.f3927e = new C2058a.AlertDialogC2059a(c2058a.f3928f);
            alertDialogC2059a4 = this.f3936a.f3927e;
            z = this.f3936a.f3933k;
            alertDialogC2059a4.setCancelable(z);
        }
        try {
            alertDialogC2059a2 = this.f3936a.f3927e;
            if (alertDialogC2059a2.isShowing()) {
                return;
            }
            alertDialogC2059a3 = this.f3936a.f3927e;
            alertDialogC2059a3.show();
            handler = this.f3936a.f3934l;
            handler.sendEmptyMessageDelayed(1, 15000L);
        } catch (Exception e) {
            C2040c.m20715a(e);
        }
    }
}
