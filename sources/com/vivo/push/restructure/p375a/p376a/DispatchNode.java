package com.vivo.push.restructure.p375a.p376a;

import android.text.TextUtils;
import com.vivo.push.PushClientManager;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.p375a.ReceivedMessage;
import com.vivo.push.restructure.request.RequestManager;
import com.vivo.push.sdk.PushMessageCallback;
import com.vivo.push.util.ConcurrentUtils;
import com.vivo.push.util.LogUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.restructure.a.a.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class DispatchNode extends AbstractMessageNodeMonitor<ReceivedMessage> {
    public DispatchNode(ReceivedMessage receivedMessage, NodeListener nodeListener) {
        super("ClientDispatchNode", receivedMessage, nodeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.vivo.push.restructure.p375a.p376a.AbstractMessageNodeMonitor
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public int mo5571a(ReceivedMessage receivedMessage) {
        PushMessageCallback m5573b = m5573b(receivedMessage);
        if (m5573b == null) {
            return 2804;
        }
        int i = 0;
        if (receivedMessage != null && receivedMessage.mo5557g()) {
            RequestManager.m5499a().m5497a(receivedMessage);
            return 0;
        }
        if (receivedMessage != null) {
            int mo5553k = receivedMessage.mo5553k();
            String mo5552l = receivedMessage.mo5552l();
            if (mo5553k == 3) {
                String m5613i = PushClientManager.m5648a().m5613i();
                if (TextUtils.isEmpty(m5613i) || !TextUtils.equals(m5613i, mo5552l)) {
                    i = 2810;
                }
            } else if (mo5553k == 4) {
                PushClientManager.m5648a();
                if (!PushClientManager.m5622c().contains(mo5552l)) {
                    i = 2811;
                }
            }
            if (i != 0) {
                ConcurrentUtils.m5404a().execute(new RunnableC10971f(this, mo5553k, mo5552l));
                return i;
            }
        }
        try {
            return PushClientManager.m5648a().m5645a(receivedMessage.mo5562b(), m5573b);
        } catch (Exception unused) {
            return 2808;
        }
    }

    /* renamed from: b */
    private static PushMessageCallback m5573b(ReceivedMessage receivedMessage) {
        try {
            return (PushMessageCallback) Class.forName(PushClientController.m5593a().m5588e().mo5542a(PushClientController.m5593a().m5591b(), receivedMessage.mo5562b().getAction())).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            LogUtil.m5345b("DispatchNode", "reflect e: ", e);
            return null;
        }
    }
}
