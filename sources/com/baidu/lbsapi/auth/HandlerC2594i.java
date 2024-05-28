package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Hashtable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandlerC2594i extends Handler {

    /* renamed from: a */
    final /* synthetic */ LBSAuthManager f4982a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2594i(LBSAuthManager lBSAuthManager, Looper looper) {
        super(looper);
        this.f4982a = lBSAuthManager;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Hashtable hashtable;
        C2583a.m19676a("handleMessage !!");
        String string = message.getData().getString("listenerKey");
        hashtable = LBSAuthManager.f4956f;
        LBSAuthManagerListener lBSAuthManagerListener = (LBSAuthManagerListener) hashtable.get(string);
        C2583a.m19676a("handleMessage listener = " + lBSAuthManagerListener);
        if (lBSAuthManagerListener != null) {
            lBSAuthManagerListener.onAuthResult(message.what, message.obj.toString());
        }
    }
}
