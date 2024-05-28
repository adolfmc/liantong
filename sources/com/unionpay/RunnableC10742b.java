package com.unionpay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.unionpay.p362a.C10738c;
import com.unionpay.p362a.C10739d;
import com.unionpay.utils.C10915b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10742b implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        Handler handler;
        C10739d c10739d;
        Context m5981q;
        Handler handler2;
        Handler handler3;
        Handler handler4;
        Handler handler5;
        try {
            handler = UPPayAssistEx.f20593W;
            handler.sendEmptyMessageDelayed(1001, 800L);
            c10739d = UPPayAssistEx.f20592V;
            m5981q = UPPayAssistEx.m5981q();
            C10738c c10738c = new C10738c(c10739d, C10915b.m5861a(m5981q));
            c10738c.m5964a();
            String m5963b = c10738c.m5963b();
            handler2 = UPPayAssistEx.f20593W;
            if (handler2 != null) {
                handler3 = UPPayAssistEx.f20593W;
                Message obtainMessage = handler3.obtainMessage();
                obtainMessage.what = 1002;
                obtainMessage.obj = m5963b;
                handler4 = UPPayAssistEx.f20593W;
                handler4.removeMessages(1001);
                handler5 = UPPayAssistEx.f20593W;
                handler5.sendMessage(obtainMessage);
            }
        } catch (Exception unused) {
        }
    }
}
