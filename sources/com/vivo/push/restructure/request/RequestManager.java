package com.vivo.push.restructure.request;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.vivo.push.IPCManager;
import com.vivo.push.p367a.CommandBridge;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.p375a.ReceivedMessage;
import com.vivo.push.restructure.request.p379a.CFToClientDS;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.util.LogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;

/* renamed from: com.vivo.push.restructure.request.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RequestManager {

    /* renamed from: a */
    private Map<Integer, CommandRequest> f21141a;

    /* renamed from: b */
    private Integer f21142b;

    /* renamed from: c */
    private HandlerThread f21143c;

    /* renamed from: d */
    private Handler f21144d;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: RequestManager.java */
    /* renamed from: com.vivo.push.restructure.request.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C10977a {

        /* renamed from: a */
        static RequestManager f21145a = new RequestManager((byte) 0);
    }

    /* synthetic */ RequestManager(byte b) {
        this();
    }

    private RequestManager() {
        this.f21141a = new ConcurrentHashMap();
        this.f21142b = null;
        this.f21143c = new HandlerThread("request_timer_task——thread");
        this.f21143c.start();
        this.f21144d = new HandlerC10978e(this, this.f21143c.getLooper());
    }

    /* renamed from: a */
    public static RequestManager m5499a() {
        return C10977a.f21145a;
    }

    /* renamed from: b */
    private synchronized Integer m5494b() {
        Integer valueOf;
        if (this.f21142b == null) {
            this.f21142b = 0;
        }
        if (this.f21142b.intValue() < 0 || this.f21142b.intValue() >= Integer.MAX_VALUE) {
            this.f21142b = 0;
        }
        valueOf = Integer.valueOf(this.f21142b.intValue() + 1);
        this.f21142b = valueOf;
        return new Integer(valueOf.intValue());
    }

    /* renamed from: a */
    public final void m5496a(CommandRequest commandRequest) {
        Integer m5494b = m5494b();
        int m5498a = m5498a(commandRequest.m5504a().m5522a(m5494b.intValue()));
        if (m5498a == 0) {
            if (commandRequest.m5502c() <= 0 || commandRequest.m5503b() == null) {
                return;
            }
            this.f21141a.put(m5494b, commandRequest);
            this.f21144d.sendEmptyMessageDelayed(m5494b.intValue(), commandRequest.m5502c());
        } else if (commandRequest.m5503b() != null) {
            commandRequest.m5503b().mo5501a(m5498a);
        }
    }

    /* renamed from: a */
    public final void m5497a(ReceivedMessage receivedMessage) {
        CFToClientDS mo5556h;
        int m5519b;
        JsonParcel jsonParcel;
        if (receivedMessage == null || !receivedMessage.mo5557g() || (m5519b = (mo5556h = receivedMessage.mo5556h()).m5519b()) <= 0) {
            return;
        }
        this.f21144d.removeMessages(m5519b);
        CommandRequest remove = this.f21141a.remove(Integer.valueOf(m5519b));
        if (remove == null || remove.m5503b() == null || remove.m5504a() == null) {
            return;
        }
        if (mo5556h.m5518c() == 0) {
            try {
                jsonParcel = new JsonParcel(receivedMessage.mo5555i());
            } catch (JSONException e) {
                e.printStackTrace();
                jsonParcel = null;
            }
            if (jsonParcel != null) {
                remove.m5503b().mo5500a((ISendCallback) remove.m5504a().mo5521a(jsonParcel));
                return;
            } else {
                remove.m5503b().mo5501a(8003);
                return;
            }
        }
        remove.m5503b().mo5501a(mo5556h.m5518c());
    }

    /* renamed from: a */
    private static int m5498a(Intent intent) {
        Context m5591b = PushClientController.m5593a().m5591b();
        if (m5591b == null) {
            return 8002;
        }
        IPCManager m5658a = IPCManager.m5658a(m5591b, "com.vivo.vms.aidlservice");
        if (m5658a.m5660a() && !"com.vivo.pushservice".equals(m5591b.getPackageName())) {
            if (m5658a.m5657a(intent.getExtras())) {
                return 0;
            }
            LogUtil.m5346b("RequestManager", "send command error by aidl");
            LogUtil.m5343c(m5591b, "send command error by aidl");
        }
        String mo5525k = PushClientController.m5593a().m5588e().mo5525k();
        if (TextUtils.isEmpty(mo5525k)) {
            return 8001;
        }
        Intent intent2 = new Intent("com.vivo.pushservice.action.METHOD");
        intent2.setPackage(mo5525k);
        intent2.setClassName(mo5525k, "com.vivo.push.sdk.service.PushService");
        try {
            CommandBridge.m5817a(m5591b, intent2);
        } catch (Exception e) {
            LogUtil.m5353a("RequestManager", "CommandBridge startService exception: ", e);
        }
        return 0;
    }
}
