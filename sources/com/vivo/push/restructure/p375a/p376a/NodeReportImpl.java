package com.vivo.push.restructure.p375a.p376a;

import com.vivo.push.PushClientManager;
import com.vivo.push.p368b.MsgArriveCommand;
import com.vivo.push.restructure.p375a.ReceivedMessage;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.restructure.a.a.l */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class NodeReportImpl implements NodeReport<ReceivedMessage> {
    @Override // com.vivo.push.restructure.p375a.p376a.NodeReport
    /* renamed from: a */
    public final /* synthetic */ void mo5566a(ReceivedMessage receivedMessage, String str) {
        MsgArriveCommand m5565a = new NodeReportItem(receivedMessage, str).m5565a();
        if (m5565a != null) {
            PushClientManager.m5648a().m5638a(m5565a);
        }
    }
}
