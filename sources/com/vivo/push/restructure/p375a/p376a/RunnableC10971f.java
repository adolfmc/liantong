package com.vivo.push.restructure.p375a.p376a;

import com.vivo.push.PushClient;
import com.vivo.push.restructure.PushClientController;

/* compiled from: DispatchNode.java */
/* renamed from: com.vivo.push.restructure.a.a.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10971f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f21094a;

    /* renamed from: b */
    final /* synthetic */ String f21095b;

    /* renamed from: c */
    final /* synthetic */ DispatchNode f21096c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10971f(DispatchNode dispatchNode, int i, String str) {
        this.f21096c = dispatchNode;
        this.f21094a = i;
        this.f21095b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.f21094a;
        if (i == 3) {
            PushClient.getInstance(PushClientController.m5593a().m5591b()).unBindAlias(this.f21095b, null);
        } else if (i == 4) {
            PushClient.getInstance(PushClientController.m5593a().m5591b()).delTopic(this.f21095b, null);
        }
    }
}
