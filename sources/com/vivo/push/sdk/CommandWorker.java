package com.vivo.push.sdk;

import android.content.Intent;
import android.os.Message;
import com.vivo.push.Worker;
import com.vivo.push.restructure.p375a.ReceivedMessageImpl;
import com.vivo.push.util.ConcurrentUtils;
import com.vivo.push.util.LogUtil;

/* renamed from: com.vivo.push.sdk.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CommandWorker extends Worker {

    /* renamed from: c */
    private static CommandWorker f21157c;

    /* renamed from: d */
    private String f21158d = "";

    private CommandWorker() {
    }

    /* renamed from: a */
    public static synchronized CommandWorker m5490a() {
        CommandWorker commandWorker;
        synchronized (CommandWorker.class) {
            if (f21157c == null) {
                f21157c = new CommandWorker();
            }
            commandWorker = f21157c;
        }
        return commandWorker;
    }

    /* renamed from: a */
    public final void m5487a(String str) {
        this.f21158d = str;
    }

    /* renamed from: b */
    public final String m5486b() {
        return this.f21158d;
    }

    /* renamed from: a */
    public final void m5489a(Intent intent) {
        if (intent == null || this.f20863a == null) {
            LogUtil.m5341d("CommandWorker", " sendMessage error: intent : " + intent + ", mContext: " + this.f20863a);
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = intent;
        m5812a(obtain);
    }

    @Override // com.vivo.push.Worker
    /* renamed from: b */
    public final void mo5485b(Message message) {
        Intent intent = (Intent) message.obj;
        if (intent == null || this.f20863a == null) {
            LogUtil.m5341d("CommandWorker", " handleMessage error: intent : " + intent + ", mContext: " + this.f20863a);
            return;
        }
        ReceivedMessageImpl receivedMessageImpl = new ReceivedMessageImpl(intent);
        try {
            LogUtil.m5341d("CommandWorker", "received msg : ".concat(String.valueOf(receivedMessageImpl.mo5563a())));
            ConcurrentUtils.m5404a().execute(new RunnableC10981b(this, receivedMessageImpl));
        } catch (Exception e) {
            LogUtil.m5354a("CommandWorker", "handle message err : " + e.getMessage());
        }
    }
}
