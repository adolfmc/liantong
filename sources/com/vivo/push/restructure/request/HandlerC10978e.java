package com.vivo.push.restructure.request;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.vivo.push.util.ConcurrentUtils;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RequestManager.java */
/* renamed from: com.vivo.push.restructure.request.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class HandlerC10978e extends Handler {

    /* renamed from: a */
    final /* synthetic */ RequestManager f21146a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC10978e(RequestManager requestManager, Looper looper) {
        super(looper);
        this.f21146a = requestManager;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Map map;
        Map map2;
        int i = message.what;
        map = this.f21146a.f21141a;
        if (map.containsKey(Integer.valueOf(i))) {
            map2 = this.f21146a.f21141a;
            ConcurrentUtils.m5404a().execute(new RunnableC10979f(this, (CommandRequest) map2.remove(Integer.valueOf(i))));
        }
    }
}
