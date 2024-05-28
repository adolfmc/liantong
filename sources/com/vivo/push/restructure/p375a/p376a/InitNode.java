package com.vivo.push.restructure.p375a.p376a;

import android.content.Context;
import com.vivo.push.PushClientManager;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.p375a.ReceivedMessage;
import com.vivo.push.util.LogUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.restructure.a.a.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class InitNode extends AbstractMessageNodeMonitor<ReceivedMessage> {
    @Override // com.vivo.push.restructure.p375a.p376a.AbstractMessageNodeMonitor
    /* renamed from: a */
    protected final /* synthetic */ int mo5571a(ReceivedMessage receivedMessage) {
        Context m5591b = PushClientController.m5593a().m5591b();
        PushClientManager.m5648a().m5646a(m5591b);
        String mo5561c = receivedMessage.mo5561c();
        LogUtil.m5341d("InitNode", "PushMessageReceiver " + m5591b.getPackageName() + " ; requestId = " + mo5561c);
        return 0;
    }

    public InitNode(ReceivedMessage receivedMessage, NodeListener nodeListener) {
        super("InitNode", receivedMessage, nodeListener);
    }
}
