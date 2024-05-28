package com.vivo.push.restructure.p375a;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.restructure.request.p379a.CFToClientDS;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.MessageConvertUtil;
import org.json.JSONException;

/* renamed from: com.vivo.push.restructure.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ReceivedMessageImpl implements ReceivedMessage {

    /* renamed from: a */
    private Intent f21105a;

    /* renamed from: c */
    private CFToClientDS f21107c;

    /* renamed from: e */
    private InsideNotificationItem f21109e;

    /* renamed from: f */
    private UnvarnishedMessage f21110f;

    /* renamed from: b */
    private String f21106b = "";

    /* renamed from: d */
    private String f21108d = "";

    public ReceivedMessageImpl(Intent intent) {
        this.f21105a = intent;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: a */
    public final String mo5563a() {
        Bundle extras;
        Intent intent = this.f21105a;
        long j = (intent == null || (extras = intent.getExtras()) == null) ? 0L : extras.getLong("notify_id", 0L);
        return j != 0 ? String.valueOf(j) : "";
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: b */
    public final Intent mo5562b() {
        return this.f21105a;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: c */
    public final String mo5561c() {
        if (TextUtils.isEmpty(this.f21106b)) {
            this.f21106b = this.f21105a.getStringExtra("req_id");
        }
        return this.f21106b;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: d */
    public final long mo5560d() {
        Intent intent = this.f21105a;
        if (intent != null) {
            return intent.getLongExtra("ipc_start_time", 0L);
        }
        return 0L;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: e */
    public final boolean mo5559e() {
        Intent intent = this.f21105a;
        if (intent != null) {
            return intent.getBooleanExtra("core_support_monitor", false);
        }
        return false;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: f */
    public final boolean mo5558f() {
        Bundle extras;
        Intent intent = this.f21105a;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return false;
        }
        return extras.getBoolean("client_collect_node", false);
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: g */
    public final boolean mo5557g() {
        CFToClientDS mo5556h = mo5556h();
        return mo5556h != null && mo5556h.m5520a() == 2018;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: h */
    public final CFToClientDS mo5556h() {
        String stringExtra;
        JsonParcel jsonParcel;
        if (this.f21107c == null && (stringExtra = this.f21105a.getStringExtra("cf_content")) != null) {
            try {
                jsonParcel = new JsonParcel(stringExtra);
            } catch (JSONException unused) {
                jsonParcel = null;
            }
            if (jsonParcel != null) {
                this.f21107c = CFToClientDS.f21123a.mo5505a(jsonParcel);
            }
        }
        return this.f21107c;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: i */
    public final String mo5555i() {
        if (TextUtils.isEmpty(this.f21108d)) {
            this.f21108d = this.f21105a.getStringExtra("content");
        }
        return this.f21108d;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: j */
    public final int mo5554j() {
        Intent intent = this.f21105a;
        if (intent != null) {
            int intExtra = intent.getIntExtra("command", -1);
            return intExtra < 0 ? this.f21105a.getIntExtra("method", -1) : intExtra;
        }
        return -1;
    }

    /* renamed from: m */
    private boolean m5551m() {
        return mo5554j() == 4;
    }

    /* renamed from: n */
    private boolean m5550n() {
        return mo5554j() == 3;
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: k */
    public final int mo5553k() {
        if (this.f21105a == null) {
            return 0;
        }
        if (m5551m() && m5549o() != null) {
            return m5549o().getTargetType();
        }
        if (!m5550n() || m5548p() == null) {
            return 0;
        }
        return m5548p().getTargetType();
    }

    @Override // com.vivo.push.restructure.p375a.ReceivedMessage
    /* renamed from: l */
    public final String mo5552l() {
        if (this.f21105a == null) {
            return "";
        }
        if (!m5551m() || m5549o() == null) {
            return (!m5550n() || m5548p() == null) ? "" : m5548p().getTragetContent();
        }
        return m5549o().getTragetContent();
    }

    /* renamed from: o */
    private InsideNotificationItem m5549o() {
        InsideNotificationItem insideNotificationItem = this.f21109e;
        if (insideNotificationItem != null) {
            return insideNotificationItem;
        }
        InsideNotificationItem insideNotificationItem2 = null;
        Intent intent = this.f21105a;
        if (intent != null) {
            try {
                String stringExtra = intent.getStringExtra("notification_v1");
                if (stringExtra != null && (insideNotificationItem2 = MessageConvertUtil.m5338a(stringExtra)) != null) {
                    insideNotificationItem2.setMsgId(Long.parseLong(mo5563a()));
                }
            } catch (Exception e) {
                LogUtil.m5354a("ReceivedMessageImpl", "getNotificationMessage " + e.getMessage());
            }
        }
        this.f21109e = insideNotificationItem2;
        return insideNotificationItem2;
    }

    /* renamed from: p */
    private UnvarnishedMessage m5548p() {
        UnvarnishedMessage unvarnishedMessage = this.f21110f;
        if (unvarnishedMessage != null) {
            return unvarnishedMessage;
        }
        UnvarnishedMessage unvarnishedMessage2 = null;
        Intent intent = this.f21105a;
        if (intent != null) {
            try {
                String stringExtra = intent.getStringExtra("msg_v1");
                if (!TextUtils.isEmpty(stringExtra)) {
                    UnvarnishedMessage unvarnishedMessage3 = new UnvarnishedMessage(stringExtra);
                    try {
                        unvarnishedMessage3.setMsgId(Long.parseLong(mo5563a()));
                        unvarnishedMessage2 = unvarnishedMessage3;
                    } catch (Exception e) {
                        e = e;
                        unvarnishedMessage2 = unvarnishedMessage3;
                        LogUtil.m5354a("ReceivedMessageImpl", "getTransmissionMessage " + e.getMessage());
                        this.f21110f = unvarnishedMessage2;
                        return unvarnishedMessage2;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        this.f21110f = unvarnishedMessage2;
        return unvarnishedMessage2;
    }
}
