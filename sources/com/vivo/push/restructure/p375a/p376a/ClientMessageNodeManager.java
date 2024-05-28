package com.vivo.push.restructure.p375a.p376a;

import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.p375a.ReceivedMessage;

/* renamed from: com.vivo.push.restructure.a.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ClientMessageNodeManager {

    /* renamed from: a */
    private NodeSave f21092a = new NodeSaveImpl();

    /* renamed from: b */
    private NodeReport f21093b = new NodeReportImpl();

    /* renamed from: a */
    public final void m5575a(ReceivedMessage receivedMessage) {
        NodeListenerImpl nodeListenerImpl = new NodeListenerImpl(this.f21092a, this.f21093b, PushClientController.m5593a().m5590c());
        IPCNode iPCNode = new IPCNode(receivedMessage, nodeListenerImpl);
        CheckNode checkNode = new CheckNode(receivedMessage, nodeListenerImpl);
        InitNode initNode = new InitNode(receivedMessage, nodeListenerImpl);
        DispatchNode dispatchNode = new DispatchNode(receivedMessage, nodeListenerImpl);
        iPCNode.m5582a((AbstractMessageNodeMonitor) checkNode);
        checkNode.m5582a((AbstractMessageNodeMonitor) initNode);
        initNode.m5582a((AbstractMessageNodeMonitor) dispatchNode);
        nodeListenerImpl.mo5570a((AbstractMessageNodeMonitor) iPCNode);
        iPCNode.m5584a();
    }
}
