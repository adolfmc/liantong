package com.vivo.push.restructure.p375a.p376a;

import android.os.SystemClock;
import com.vivo.push.restructure.p375a.ReceivedMessage;

/* renamed from: com.vivo.push.restructure.a.a.g */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class IPCNode extends AbstractMessageNodeMonitor<ReceivedMessage> {

    /* renamed from: b */
    private long f21097b;

    @Override // com.vivo.push.restructure.p375a.p376a.AbstractMessageNodeMonitor
    /* renamed from: a */
    protected final /* bridge */ /* synthetic */ int mo5571a(ReceivedMessage receivedMessage) {
        return 0;
    }

    public IPCNode(ReceivedMessage receivedMessage, NodeListener nodeListener) {
        super("IPCNode", receivedMessage, nodeListener);
        this.f21097b = 0L;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long mo5560d = receivedMessage.mo5560d();
        if (mo5560d <= 0 || elapsedRealtime <= mo5560d) {
            return;
        }
        this.f21097b = elapsedRealtime - mo5560d;
    }

    @Override // com.vivo.push.restructure.p375a.p376a.AbstractMessageNodeMonitor
    /* renamed from: b */
    public final synchronized String mo5572b() {
        m5583a(this.f21097b);
        return super.mo5572b();
    }
}
