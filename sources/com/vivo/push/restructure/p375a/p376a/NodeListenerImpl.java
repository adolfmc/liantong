package com.vivo.push.restructure.p375a.p376a;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.vivo.push.restructure.p375a.ReceivedMessage;
import com.vivo.push.restructure.p378c.IReport;
import com.vivo.push.util.LogUtil;
import org.json.JSONArray;

@NBSInstrumented
/* renamed from: com.vivo.push.restructure.a.a.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class NodeListenerImpl implements NodeListener<ReceivedMessage> {

    /* renamed from: a */
    private AbstractMessageNodeMonitor f21098a;

    /* renamed from: b */
    private NodeSave f21099b;

    /* renamed from: c */
    private NodeReport f21100c;

    /* renamed from: d */
    private IReport f21101d;

    @Override // com.vivo.push.restructure.p375a.p376a.NodeListener
    /* renamed from: a */
    public final /* synthetic */ void mo5569a(AbstractMessageNodeMonitor abstractMessageNodeMonitor, ReceivedMessage receivedMessage, int i) {
        ReceivedMessage receivedMessage2 = receivedMessage;
        if (receivedMessage2 == null) {
            LogUtil.m5355a("onNodeError() receivedMsg is null ");
            return;
        }
        LogUtil.m5355a("onNodeError() , msgID = " + receivedMessage2.mo5563a() + ", nodeName = " + abstractMessageNodeMonitor.mo5572b());
        IReport iReport = this.f21101d;
        if (iReport != null) {
            iReport.mo5523a(i, receivedMessage2.mo5563a());
        }
        m5568a(receivedMessage2);
    }

    @Override // com.vivo.push.restructure.p375a.p376a.NodeListener
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo5567a(ReceivedMessage receivedMessage) {
        ReceivedMessage receivedMessage2 = receivedMessage;
        if (receivedMessage2 == null) {
            LogUtil.m5355a("onAllNodeExecuteComplete, receivedMsg is null");
        } else if (this.f21098a == null) {
            LogUtil.m5355a("onAllNodeExecuteComplete, mFirstNode is null");
        } else {
            m5568a(receivedMessage2);
        }
    }

    public NodeListenerImpl(NodeSave nodeSave, NodeReport nodeReport, IReport iReport) {
        this.f21099b = nodeSave;
        this.f21100c = nodeReport;
        this.f21101d = iReport;
    }

    @Override // com.vivo.push.restructure.p375a.p376a.NodeListener
    /* renamed from: a */
    public final void mo5570a(AbstractMessageNodeMonitor abstractMessageNodeMonitor) {
        this.f21098a = abstractMessageNodeMonitor;
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    private void m5568a(ReceivedMessage receivedMessage) {
        if (receivedMessage == null) {
            return;
        }
        if (!receivedMessage.mo5559e()) {
            LogUtil.m5355a("core is not support monitor report");
            return;
        }
        LogUtil.m5355a("reportNodeMonitorInfo() , isNeedCollectNodeMonitor: " + receivedMessage.mo5558f());
        if (receivedMessage.mo5558f()) {
            NodeSave nodeSave = this.f21099b;
            if (nodeSave != null) {
                nodeSave.mo5564a(receivedMessage, this.f21098a);
            }
            NodeReport nodeReport = this.f21100c;
            if (nodeReport != null) {
                JSONArray m5580c = this.f21098a.m5580c();
                nodeReport.mo5566a(receivedMessage, !(m5580c instanceof JSONArray) ? m5580c.toString() : NBSJSONArrayInstrumentation.toString(m5580c));
                LogUtil.m5355a("reportNodeMonitorInfo() , report client NodeInfo！！！");
                return;
            }
            LogUtil.m5355a("onNodeError , mReporter is null， can not report");
        }
    }
}
