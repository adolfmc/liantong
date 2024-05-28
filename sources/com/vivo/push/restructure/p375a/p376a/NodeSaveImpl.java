package com.vivo.push.restructure.p375a.p376a;

import android.text.TextUtils;
import com.vivo.push.restructure.p375a.ReceivedMessage;
import com.vivo.push.util.LogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.restructure.a.a.o */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class NodeSaveImpl implements NodeSave {

    /* renamed from: a */
    private Map<String, NodeReportItem> f21104a = new ConcurrentHashMap();

    @Override // com.vivo.push.restructure.p375a.p376a.NodeSave
    /* renamed from: a */
    public final void mo5564a(ReceivedMessage receivedMessage, AbstractMessageNodeMonitor abstractMessageNodeMonitor) {
        if (receivedMessage == null) {
            LogUtil.m5355a("addToCache error. msg is null");
        } else if (TextUtils.isEmpty(receivedMessage.mo5563a())) {
            LogUtil.m5355a("addToCache error. messageID is null");
        } else if (abstractMessageNodeMonitor == null) {
            LogUtil.m5355a("addToCache error. firstNode is null");
        }
    }
}
