package com.vivo.push.restructure.p375a.p376a;

import android.text.TextUtils;
import com.vivo.push.p368b.MsgArriveCommand;
import com.vivo.push.restructure.p375a.ReceivedMessage;
import com.vivo.push.util.LogUtil;

/* renamed from: com.vivo.push.restructure.a.a.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class NodeReportItem {

    /* renamed from: a */
    private String f21102a;

    /* renamed from: b */
    private String f21103b;

    public NodeReportItem(ReceivedMessage receivedMessage, String str) {
        if (receivedMessage != null) {
            this.f21102a = receivedMessage.mo5563a();
        }
        this.f21103b = str;
    }

    /* renamed from: a */
    public final MsgArriveCommand m5565a() {
        if (TextUtils.isEmpty(this.f21102a) || TextUtils.isEmpty(this.f21103b)) {
            LogUtil.m5355a("convertOffLineMsg() error, mMessageID = " + this.f21102a + ", mNodeArrayInfo = " + this.f21103b);
            return null;
        }
        return new MsgArriveCommand(this.f21102a, this.f21103b);
    }
}
